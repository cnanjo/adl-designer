package org.cimi.designer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cnanjo on 3/31/16.
 */
public class ReferenceModelLoaderTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void loadReferenceModel() throws Exception {
        ReferenceModelLoader loader = new ReferenceModelLoader();
        ReferenceModelVisitor visitor = loader.loadReferenceModel("/Users/cnanjo/work/cimi/CIMI-RM-3.0.5-generated-from-UML.bmm.txt");
        assertEquals("Stack should consist of a single item", 1, visitor.getStack().size());
        Type root = visitor.getAstRootNode();
        validateRootLevelAttributes(root);
    }

    public void validateRootLevelAttributes(Type root) {
        assertEquals("Root element should have 11 attributes", 11, root.getAttributeCount());
        assertEquals("bmm_version", root.getAttributeAtIndex(0).getName());
        assertTrue(root.getAttributeAtIndex(0).getValue() instanceof String);
        assertEquals("2.0", root.getAttributeAtIndex(0).getValue());
        assertEquals("rm_publisher", root.getAttributeAtIndex(1).getName());
        assertTrue(root.getAttributeAtIndex(1).getValue() instanceof String);
        assertEquals("CIMI", root.getAttributeAtIndex(1).getValue());
        assertEquals("schema_name", root.getAttributeAtIndex(2).getName());
        assertTrue(root.getAttributeAtIndex(2).getValue() instanceof String);
        assertEquals("RM", root.getAttributeAtIndex(2).getValue());
        assertEquals("rm_release", root.getAttributeAtIndex(3).getName());
        assertTrue(root.getAttributeAtIndex(3).getValue() instanceof String);
        assertEquals("3.0.5", root.getAttributeAtIndex(3).getValue());
        assertEquals("schema_revision", root.getAttributeAtIndex(4).getName());
        assertTrue(root.getAttributeAtIndex(4).getValue() instanceof String);
        assertEquals("Monday, October 19, 2015", root.getAttributeAtIndex(4).getValue());
        assertEquals("schema_lifecycle_state", root.getAttributeAtIndex(5).getName());
        assertTrue(root.getAttributeAtIndex(5).getValue() instanceof String);
        assertEquals("dstu", root.getAttributeAtIndex(5).getValue());
        assertEquals("schema_description", root.getAttributeAtIndex(6).getName());
        assertTrue(root.getAttributeAtIndex(6).getValue() instanceof String);
        assertEquals("CIMI_Reference_Model v3.0.5 schema generated from UML", root.getAttributeAtIndex(6).getValue());
        assertEquals("archetype_rm_closure_packages", root.getAttributeAtIndex(7).getName());
        assertTrue(root.getAttributeAtIndex(7).getValue() instanceof List);
        assertEquals("CIMI_Reference_Model.Core", ((List)root.getAttributeAtIndex(7).getValue()).get(0));
        assertEquals("...", ((List)root.getAttributeAtIndex(7).getValue()).get(1));
        assertEquals("packages", root.getAttributeAtIndex(8).getName());
        assertTrue(root.getAttributeAtIndex(8).getValue() instanceof Type);
        validatePackages((Type)root.getAttributeAtIndex(8).getValue());
        assertEquals("class_definitions", root.getAttributeAtIndex(9).getName());
        assertTrue(root.getAttributeAtIndex(9).getValue() instanceof Type);
        validateClassDefinitions((Type)root.getAttributeAtIndex(9).getValue());
        assertEquals("primitive_types", root.getAttributeAtIndex(10).getName());
        assertTrue(root.getAttributeAtIndex(10).getValue() instanceof Type);
        validatePrimitiveTypes((Type)root.getAttributeAtIndex(10).getValue());
    }

    public void validatePackages(Type packages) {
        assertEquals("Packages should have exactly 1 keyed object", 1, packages.getKeyedObjectCount());
        assertEquals("Packages should have exactly 0 attributes", 0, packages.getAttributeCount());
        validateCimiReferenceModelKeyedObject(packages.getKeyedObject("CIMI_Reference_Model"));
    }

    public void validateCimiReferenceModelKeyedObject(Type cimiReferenceModelKO) {
        assertEquals("Packages should have exactly 0 keyed object", 0, cimiReferenceModelKO.getKeyedObjectCount());
        assertEquals("Packages should have exactly 2 attributes: 'name' and 'packages'", 2, cimiReferenceModelKO.getAttributeCount());

        //Validate name
        assertEquals("name", cimiReferenceModelKO.getAttributeAtIndex(0).getName());
        assertTrue(cimiReferenceModelKO.getAttributeAtIndex(0).getValue() instanceof String);
        assertEquals("CIMI_Reference_Model", cimiReferenceModelKO.getAttributeAtIndex(0).getValue());

        //Validate package
        Attribute<Type> packagesAttr = cimiReferenceModelKO.getAttributeAtIndex(1);
        assertEquals("packages", packagesAttr.getName());
        assertTrue(packagesAttr.getValue() instanceof Type);
        validatePackageAttributeType(packagesAttr.getValue());

        //Validate package."Core"
        Type core = packagesAttr.getValue().getKeyedObject("Core");
        assertNotNull(core);
        assertEquals("Core has two attributes: 'name' and 'classes':", 2, core.getAttributeCount());
        assertEquals("Core has no keyed objects:", 0, core.getKeyedObjectCount());
        Attribute<String> coreName = core.getAttributeAtIndex(0);
        Attribute<List<String>> coreClasses = core.getAttributeAtIndex(1);
        assertEquals("name", coreName.getName());
        assertEquals("Core", coreName.getValue());
        assertEquals("classes", coreClasses.getName());
        assertEquals(7, coreClasses.getValue().size());
        assertEquals("ARCHETYPED", coreClasses.getValue().get(0));
        assertEquals("PARTICIPATION", coreClasses.getValue().get(6));

        //Validate package."Data_Value_Types"
        Type dataValueTypes = packagesAttr.getValue().getKeyedObject("Data_Value_Types");
        assertNotNull(dataValueTypes);
        assertEquals("Data_Value_Types has two attributes: 'name' and 'classes':", 2, dataValueTypes.getAttributeCount());
        assertEquals("Data_Value_Types has no keyed objects:", 0, dataValueTypes.getKeyedObjectCount());
        Attribute<String> dataValueTypesName = dataValueTypes.getAttributeAtIndex(0);
        Attribute<List<String>> dataValueTypesClasses = dataValueTypes.getAttributeAtIndex(1);
        assertEquals("name", dataValueTypesName.getName());
        assertEquals("Data_Value_Types", dataValueTypesName.getValue());
        assertEquals("classes", dataValueTypesClasses.getName());
        assertEquals(24, dataValueTypesClasses.getValue().size());
        assertEquals("CODED_TEXT", dataValueTypesClasses.getValue().get(1));
        assertEquals("URI_VALUE", dataValueTypesClasses.getValue().get(22));

        //Validate package."Party"
        Type party = packagesAttr.getValue().getKeyedObject("Party");
        assertNotNull(party);
        assertEquals("Party has two attributes: 'name' and 'classes':", 2, party.getAttributeCount());
        assertEquals("Party has no keyed objects:", 0, party.getKeyedObjectCount());
        Attribute<String> partyName = party.getAttributeAtIndex(0);
        Attribute<List<String>> partyClasses = party.getAttributeAtIndex(1);
        assertEquals("name", partyName.getName());
        assertEquals("Party", partyName.getValue());
        assertEquals("classes", partyClasses.getName());
        assertEquals(4, partyClasses.getValue().size());
        assertEquals("PARTY", partyClasses.getValue().get(1));
        assertEquals("PARTY_RELATIONSHIP", partyClasses.getValue().get(2));

        //Validate package."Primitive_types"
        Type primitiveTypes = packagesAttr.getValue().getKeyedObject("Primitive_Types");
        assertNotNull(primitiveTypes);
        assertEquals("Primitive_Types has two attributes: 'name' and 'classes':", 2, primitiveTypes.getAttributeCount());
        assertEquals("Primitive_Types has no keyed objects:", 0, primitiveTypes.getKeyedObjectCount());
        Attribute<String> primitiveTypesName = primitiveTypes.getAttributeAtIndex(0);
        Attribute<List<String>> primitiveTypesClasses = primitiveTypes.getAttributeAtIndex(1);
        assertEquals("name", primitiveTypesName.getName());
        assertEquals("Primitive_Types", primitiveTypesName.getValue());
        assertEquals("classes", primitiveTypesClasses.getName());
        assertEquals(10, primitiveTypesClasses.getValue().size());
        assertEquals("Byte", primitiveTypesClasses.getValue().get(4));
        assertEquals("Character", primitiveTypesClasses.getValue().get(5));
    }

    public void validatePackageAttributeType(Type packagesAttribute) {
        assertEquals("Packages should have exactly 4 keyed object", 4, packagesAttribute.getKeyedObjectCount());
        assertEquals("Packages should have exactly 0 attributes", 0, packagesAttribute.getAttributeCount());
    }

    public void validateClassDefinitions(Type classDefinitions) {
        assertEquals("Class Definitions should have exactly 36 keyed object", 35, classDefinitions.getKeyedObjectCount());
        assertEquals("Class Definitions should have exactly 0 attributes", 0, classDefinitions.getAttributeCount());
        validateClassDefinitionItemGroupKeyedObject(classDefinitions.getKeyedObject("ITEM_GROUP"));
    }

    public void validateClassDefinitionItemGroupKeyedObject(Type itemGroupKO) {
        assertEquals("Packages should have exactly 3 attributes: 'name', 'ancestors' and 'properties'", 3, itemGroupKO.getAttributeCount());
        assertEquals("Packages should have exactly 0 keyed object", 0, itemGroupKO.getKeyedObjectCount());

        //Validate name
        assertEquals("name", itemGroupKO.getAttributeAtIndex(0).getName());
        assertTrue(itemGroupKO.getAttributeAtIndex(0).getValue() instanceof String);
        assertEquals("ITEM_GROUP", itemGroupKO.getAttributeAtIndex(0).getValue());

        //Validate ancestors
        assertEquals("ancestors", itemGroupKO.getAttributeAtIndex(1).getName());
        assertTrue(itemGroupKO.getAttributeAtIndex(1).getValue() instanceof List);
        assertEquals("ITEM", ((List<String>)itemGroupKO.getAttributeAtIndex(1).getValue()).get(0));
        assertEquals("...", ((List<String>)itemGroupKO.getAttributeAtIndex(1).getValue()).get(1));

        //Validate properties
        Attribute<Type> properties = itemGroupKO.getAttributeAtIndex(2);
        assertEquals("properties", properties.getName());
        assertTrue(properties.getValue() instanceof Type);
        assertEquals("Properties should have exactly 2 keyed object", 2, properties.getValue().getKeyedObjectCount());
        assertEquals("Properties should have exactly 0 attributes", 0, properties.getValue().getAttributeCount());

        //Validate properties."item"
        Type item = properties.getValue().getKeyedObject("item");
        assertEquals("Item should have exactly 4 attributes: 'name', 'type_def', 'cardinality', 'is_mandatory'", 4, item.getAttributeCount());
        assertEquals("Item should have exactly 0 keyed object", 0, item.getKeyedObjectCount());
        assertEquals("Item should have a type of P_BMM_CONTAINER_PROPERTY", "P_BMM_CONTAINER_PROPERTY", item.getType());

        //Validate properties."item".name
        Attribute<String> itemName = item.getAttributeAtIndex(0);
        assertEquals("name", item.getAttributeAtIndex(0).getName());
        assertTrue(item.getAttributeAtIndex(0).getValue() instanceof String);
        assertEquals("item", item.getAttributeAtIndex(0).getValue());

        //Validate properties."item".type_def
        Attribute<Type> typeDef = item.getAttributeAtIndex(1);
        Type typeDefType = typeDef.getValue();
        assertEquals("type_def", typeDef.getName());
        assertEquals("Item should have exactly 2 attributes: 'container_type', 'type'", 2, typeDef.getValue().getAttributeCount());
        assertEquals("Item should have exactly 0 keyed object", 0, typeDef.getValue().getKeyedObjectCount());
        assertTrue(typeDef.getValue() instanceof Type);

        //Validate properties."item".type_def.container_type
        Attribute<String> containerTypeAttribute = typeDefType.getAttributeAtIndex(0);
        assertEquals("container_type", containerTypeAttribute.getName());
        assertTrue(containerTypeAttribute.getValue() instanceof String);
        assertEquals("List", containerTypeAttribute.getValue());

        //Validate properties."item".type_def.type
        Attribute<String> typeAttribute = typeDefType.getAttributeAtIndex(1);
        assertEquals("type", typeAttribute.getName());
        assertTrue(typeAttribute.getValue() instanceof String);
        assertEquals("ITEM", typeAttribute.getValue());

        //Validate properties."item".cardinality
        Attribute<String> cardinality = item.getAttributeAtIndex(2);
        assertEquals("cardinality", cardinality.getName());
        assertTrue(cardinality.getValue() instanceof String);
        assertEquals("|>=1|", cardinality.getValue());//TODO Handle Cardinality

        //Validate properties."item".isMandatory
        Attribute<Boolean> isMandatory = item.getAttributeAtIndex(3);
        assertEquals("is_mandatory", isMandatory.getName());
        assertTrue(isMandatory.getValue() instanceof Boolean);
        assertTrue(isMandatory.getValue());

        //Validate properties."participation"
        Type participation = properties.getValue().getKeyedObject("participation");
        assertEquals("Participation should have exactly 3 attributes: 'name', 'type_def', 'cardinality'", 3, participation.getAttributeCount());
        assertEquals("Participation should have exactly 0 keyed object", 0, participation.getKeyedObjectCount());
        assertEquals("Participation should have a type of P_BMM_CONTAINER_PROPERTY", "P_BMM_CONTAINER_PROPERTY", participation.getType());

        //Validate properties."participation".name
        Attribute<String> participationName = participation.getAttributeAtIndex(0);
        assertEquals("name", participationName.getName());
        assertTrue(participationName.getValue() instanceof String);
        assertEquals("participation", participationName.getValue());

        //Validate properties."participation".type_def
        Attribute<Type> participationTypeDef = participation.getAttributeAtIndex(1);
        Type participationTypeDefType = participationTypeDef.getValue();
        assertEquals("type_def", participationTypeDef.getName());
        assertEquals("Item should have exactly 2 attributes: 'container_type', 'type'", 2, participationTypeDefType.getAttributeCount());
        assertEquals("Item should have exactly 0 keyed object", 0, participationTypeDefType.getKeyedObjectCount());
        assertTrue(participationTypeDefType instanceof Type);

        //Validate properties."participation".type_def.container_type
        Attribute<String> participationContainerTypeAttribute = participationTypeDefType.getAttributeAtIndex(0);
        assertEquals("container_type", participationContainerTypeAttribute.getName());
        assertTrue(participationContainerTypeAttribute.getValue() instanceof String);
        assertEquals("List", participationContainerTypeAttribute.getValue());

        //Validate properties."participation".type_def.type
        Attribute<String> participationTypeDefTypeAttribute = participationTypeDefType.getAttributeAtIndex(1);
        assertEquals("type", participationTypeDefTypeAttribute.getName());
        assertTrue(participationTypeDefTypeAttribute.getValue() instanceof String);
        assertEquals("PARTICIPATION", participationTypeDefTypeAttribute.getValue());

        //Validate properties."participation".cardinality
        Attribute<String> participationCardinality = participation.getAttributeAtIndex(2);
        assertEquals("cardinality", participationCardinality.getName());
        assertTrue(participationCardinality.getValue() instanceof String);
        assertEquals("|>=0|", participationCardinality.getValue());//TODO Handle Cardinality
    }

    public void validatePrimitiveTypes(Type primitiveTypes) {
        assertEquals("Primitive Types should have exactly 10 keyed object", 10, primitiveTypes.getKeyedObjectCount());
        assertEquals("Primitive Types should have exactly 0 attributes", 0, primitiveTypes.getAttributeCount());

        //Validate Boolean
        Type booleanType = primitiveTypes.getKeyedObject("Boolean");
        assertNotNull(booleanType);
        assertEquals("Boolean keyed object specifies a single attribute", 1, booleanType.getAttributeCount());
        Attribute<String> booleanTypeName = booleanType.getAttributeAtIndex(0);
        assertEquals("name", booleanTypeName.getName());
        assertEquals("Boolean", booleanTypeName.getValue());

        //Validate List.name
        Type listType = primitiveTypes.getKeyedObject("List");
        assertNotNull(listType);
        assertEquals("List keyed object specifies two attribute: 'name' and 'generic_parameter_defs'", 2, listType.getAttributeCount());
        Attribute<String> listTypeName = listType.getAttributeAtIndex(0);
        assertEquals("name", listType.getAttributeAtIndex(0).getName());
        assertEquals("List", listType.getAttributeAtIndex(0).getValue());

        //Validate List.genericParameterDefs
        Attribute<Type> listTypeGenericParameterDefs = listType.getAttributeAtIndex(1);
        assertEquals("generic_parameter_defs", listTypeGenericParameterDefs.getName());
        assertTrue(listTypeGenericParameterDefs.getValue() instanceof Type);
        assertEquals(0, listTypeGenericParameterDefs.getValue().getAttributeCount());
        assertEquals(1, listTypeGenericParameterDefs.getValue().getKeyedObjectCount());
        assertNotNull(listTypeGenericParameterDefs.getValue().getKeyedObject("T"));
        assertEquals(1, listTypeGenericParameterDefs.getValue().getKeyedObject("T").getAttributeCount());
    }
}