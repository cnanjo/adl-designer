package org.openehr.bmm;

import org.cimi.designer.ReferenceModelVisitor;
import org.openehr.odin.CompositeOdinObject;
import org.openehr.odin.OdinAttribute;
import org.openehr.odin.OdinObject;

import java.awt.*;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmSchema extends BmmSchemaCore implements IBmmPackageContainer {

    private Map<String, BmmClass> classDefinitions;
    private Map<String, BmmClass> primitives;
    private Map<String, BmmPackageContainer> packageContainers;

    public BmmSchema() {
        classDefinitions = new HashMap<String, BmmClass>();
        primitives = new HashMap<String, BmmClass>();
        packageContainers = new HashMap<String, BmmPackageContainer>();
    }

    public void addClassDefinition(BmmClass classDefinition) {
        this.classDefinitions.put(classDefinition.getName(), classDefinition);
        if(classDefinition.isPrimitiveType()) {
            primitives.put(classDefinition.getName(), classDefinition);
        }
    }

    public void addPackageContainer(BmmPackageContainer packageContainer) {
        packageContainers.put(packageContainer.getName(), packageContainer);
    }

    public List<BmmClass> getClassDefinitions() {
        List<BmmClass> classes = new ArrayList<BmmClass>();
        classes.addAll(classDefinitions.values());
        return classes;
    }

    public static BmmSchema configureModelFromOdinObject(CompositeOdinObject modelNode) {
        BmmSchema model = new BmmSchema();
        model.setBmmInternalVersion(modelNode.getAttribute("bmm_version").getStringValue());
        model.setRmPublisher(modelNode.getAttribute("rm_publisher").getStringValue());
        model.setSchemaName(modelNode.getAttribute("schema_name").getStringValue());
        model.setRmRelease(modelNode.getAttribute("rm_release").getStringValue());
        model.setSchemaRevision(modelNode.getAttribute("schema_revision").getStringValue());
        model.setSchemaLifecycleState(modelNode.getAttribute("schema_lifecycle_state").getStringValue());
        model.setSchemaDescription(modelNode.getAttribute("schema_description").getStringValue());
        model.addArchetypeRmClosurePackage(modelNode.getAttribute("archetype_rm_closure_packages").getStringValueAt(0));

        CompositeOdinObject classDefinitions = modelNode.getAttribute("class_definitions").getSoleCompositeObjectBody();
        processClassDefinitions(model, classDefinitions);

        CompositeOdinObject primitiveTypes = modelNode.getAttribute("primitive_types").getSoleCompositeObjectBody();
        processPrimitiveTypes(model, primitiveTypes);

        CompositeOdinObject packages = modelNode.getAttribute("packages").getSoleCompositeObjectBody();
        processPackages(model, packages);


        return model;
    }

    private static void processClassDefinitions(BmmSchema model, CompositeOdinObject classDefinitions) {
        List<OdinObject> classDefinitionTypes = classDefinitions.getKeyedObjects();
        for (int index = 0; index < classDefinitionTypes.size(); index++) {
            CompositeOdinObject compObject = (CompositeOdinObject) classDefinitionTypes.get(index);
            String name = compObject.getAttribute("name").getStringValue();
            BmmClass classDefType = new BmmClass(name);
            model.addClassDefinition(classDefType);
            if (compObject.getAttribute("properties") != null) {
                List<OdinObject> properties = compObject.getAttribute("properties").getSoleCompositeObjectBody().getKeyedObjects();
                for (int attrIndex = 0; attrIndex < properties.size(); attrIndex++) {
                    CompositeOdinObject propertyDef = (CompositeOdinObject) properties.get(attrIndex);
                    BmmProperty<?> bmmProperty = BmmProperty.configureBmmPropertyFromOdinObject(propertyDef);
                    classDefType.addProperty(bmmProperty);
                }
            }
        }
    }

    private static void processPrimitiveTypes(BmmSchema model, CompositeOdinObject primitiveTypes) {
        List<OdinObject> primitiveTypeDefinitions = primitiveTypes.getKeyedObjects();
        for (int index = 0; index < primitiveTypeDefinitions.size(); index++) {
            CompositeOdinObject compObject = (CompositeOdinObject) primitiveTypeDefinitions.get(index);
            OdinAttribute nameAttribute = compObject.getAttribute("name");
            OdinAttribute genericParameterDefs = compObject.getAttribute("generic_parameter_defs");
            boolean isGenerics =  (genericParameterDefs != null);
            String name = nameAttribute.getStringValue();
            BmmClass classDefType = null;
            if(isGenerics) {
                classDefType = new BmmGenericClass(name);
            } else {
                classDefType = new BmmClass(name);
            }
            classDefType.setPrimitiveType(true);
            model.addClassDefinition(classDefType);
            if (genericParameterDefs != null) {
                List<OdinObject> parameters = genericParameterDefs.getSoleCompositeObjectBody().getKeyedObjects();
                List<BmmGenericParameter> genericParams = new ArrayList<>();
                for(OdinObject parameterDef : parameters) {
                    String parameterName = ((CompositeOdinObject)parameterDef).getAttributeAtIndex(0).getStringValue();
                    BmmGenericParameter genericParam = new BmmGenericParameter(parameterName);
                    genericParams.add(genericParam);
                }
                ((BmmGenericClass)classDefType).setGenericParameters(genericParams);
            }
        }
    }

    public static void processPackages(BmmSchema model, CompositeOdinObject packages) {
        List<OdinObject> packageContainerDefs = packages.getKeyedObjects();
        for(OdinObject object : packageContainerDefs) {
            CompositeOdinObject packageContainerDef = (CompositeOdinObject)object;
            String name = packageContainerDef.getAttribute("name").getStringValue();
            List<OdinObject> packageDefs = packageContainerDef.getAttribute("packages").getSoleCompositeObjectBody().getKeyedObjects();
            BmmPackageContainer packageContainer = new BmmPackageContainer(name);
            for(OdinObject packageObject : packageDefs) {
                processPackage(model, (CompositeOdinObject)packageObject, packageContainer);
            }
            model.addPackageContainer(packageContainer);
        }
    }

    public static void processPackage(BmmSchema model, CompositeOdinObject packageDef, BmmPackageContainer packageContainer) {
        String packageName = packageDef.getAttribute("name").getStringValue();
        List<String> packageClasses = packageDef.getAttribute("classes").getChildrenAsStringList();
        BmmPackage bmmPackage = new BmmPackage(packageName);
        packageContainer.addPackage(bmmPackage);
        for(String bmmClassName : packageClasses) {
            bmmPackage.addClass(bmmClassName, model.getClassDefinition(bmmClassName));
        }
    }

    private BmmClass getClassDefinition(String bmmClassName) {
        BmmClass bmmClass = classDefinitions.get(bmmClassName);
        if(bmmClass == null) {
            throw new RuntimeException("BMM Class " + bmmClassName + " was not found.");
        }
        return bmmClass;
    }
}
