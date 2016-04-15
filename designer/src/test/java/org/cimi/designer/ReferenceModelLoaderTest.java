package org.cimi.designer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openehr.bmm.BmmSchema;
import org.openehr.odin.CompositeOdinObject;
import org.openehr.odin.IntegerObject;
import org.openehr.odin.OdinAttribute;
import org.openehr.odin.StringObject;

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
        ReferenceModelVisitor visitor = loader.loadReferenceModel(ReferenceModelLoaderTest.class.getResourceAsStream("/odin/CIMI-RM-3.0.5.bmm"));
        assertEquals("Stack should consist of a single item", 1, visitor.getStack().size());
        CompositeOdinObject root = visitor.getAstRootNode();
        validateRootLevelAttributes(root);
    }

    @Test
    public void loadOdinNestedAttributeStructures1() throws Exception {
        ReferenceModelLoader loader = new ReferenceModelLoader();
        ReferenceModelVisitor visitor = loader.loadReferenceModel(ReferenceModelLoaderTest.class.getResourceAsStream("/odin/odin_nested_attribute_structure1.txt"));
        assertEquals("Stack should consist of a single item", 1, visitor.getStack().size());
        CompositeOdinObject root = visitor.getAstRootNode();
        validateNestedAttributeStructures(root);
    }

    @Test
    public void loadOdinKeyedObject() throws Exception {
        ReferenceModelLoader loader = new ReferenceModelLoader();
        ReferenceModelVisitor visitor = loader.loadReferenceModel(ReferenceModelLoaderTest.class.getResourceAsStream("/odin/odin_keyed_object.txt"));
        assertEquals("Stack should consist of a single item", 1, visitor.getStack().size());
        CompositeOdinObject root = visitor.getAstRootNode();
        validateKeyedObjects(root);
    }

    @Test
    public void testOdinTypes() throws Exception {
        ReferenceModelLoader loader = new ReferenceModelLoader();
        ReferenceModelVisitor visitor = loader.loadReferenceModel(ReferenceModelLoaderTest.class.getResourceAsStream("/odin/odin_types.txt"));
        assertEquals("Stack should consist of a single item", 1, visitor.getStack().size());
        CompositeOdinObject root = visitor.getAstRootNode();
        validateOdinTypes(root);
    }

    @Test
    public void testOdinPrimitives() throws Exception {
        ReferenceModelLoader loader = new ReferenceModelLoader();
        ReferenceModelVisitor visitor = loader.loadReferenceModel(ReferenceModelLoaderTest.class.getResourceAsStream("/odin/odin_primitive_types.txt"));
        assertEquals("Stack should consist of a single item", 1, visitor.getStack().size());
        CompositeOdinObject root = visitor.getAstRootNode();
        validatePrimitiveType(root, "a_string_attribute", "a string value");
        validatePrimitiveType(root, "a_boolean_attribute", false);
        OdinAttribute attribute = validatePrimitiveType(root, "a_integer_attribute", "1");
        assertEquals(new Integer(1), attribute.getIntegerObject().getAsInteger());
        attribute = validatePrimitiveType(root, "a_real_attribute", "-3.05e-10");
        assertEquals(new Float(-3.05e-10), attribute.getRealObject().getAsFloat());
        attribute = validatePrimitiveType(root, "a_char_attribute", "c");
        assertEquals(new Character('c'), attribute.getCharacterObject().getAsChar());
        attribute = validatePrimitiveType(root, "a_term_code_attribute", "[ISO_639-1::en]");
        attribute = validatePrimitiveType(root, "a_date_attribute", "2007-11-31");
        attribute = validatePrimitiveType(root, "a_time_attribute", "16:23:54,5+2221");
        attribute = validatePrimitiveType(root, "a_datetime_attribute", "2007-11-31T16:23:54,5Z");
        attribute = validatePrimitiveType(root, "a_duration_attribute", "P5Y2M4W5DT34H34M63.276S");
    }

    @Test
    public void testOdinLists() throws Exception {
        ReferenceModelLoader loader = new ReferenceModelLoader();
        ReferenceModelVisitor visitor = loader.loadReferenceModel(ReferenceModelLoaderTest.class.getResourceAsStream("/odin/odin_primitive_lists.txt"));
        assertEquals("Stack should consist of a single item", 1, visitor.getStack().size());
        CompositeOdinObject root = visitor.getAstRootNode();
        OdinAttribute attribute = validatePrimitiveList(root, "a_string_list_attribute", 3);
        attribute = validatePrimitiveList(root, "a_string_list_attribute", 3);
        attribute = validatePrimitiveList(root, "a_boolean_list_attribute", 4);
        attribute = validatePrimitiveList(root, "a_integer_list_attribute", 5);
        attribute = validatePrimitiveList(root, "a_real_list_attribute", 4);
        attribute = validatePrimitiveList(root, "a_char_list_attribute", 3);
        attribute = validatePrimitiveList(root, "a_term_code_list_attribute", 2);
        attribute = validatePrimitiveList(root, "a_time_list_attribute", 3);
        attribute = validatePrimitiveList(root, "a_date_list_attribute", 3);
        attribute = validatePrimitiveList(root, "a_datetime_list_attribute", 2);
    }

    @Test
    public void testOdinIntervals() throws Exception {
        ReferenceModelLoader loader = new ReferenceModelLoader();
        ReferenceModelVisitor visitor = loader.loadReferenceModel(ReferenceModelLoaderTest.class.getResourceAsStream("/odin/odin_primitive_intervals.txt"));
        assertEquals("Stack should consist of a single item", 1, visitor.getStack().size());
        CompositeOdinObject root = visitor.getAstRootNode();
        OdinAttribute attribute = validateInterval(root, "a_integer_interval_attribute1");
        assertEquals(1, attribute.getChildCount());
        attribute = validateInterval(root, "a_integer_interval_attribute2");
        assertEquals(1, attribute.getChildCount());
        attribute = validateInterval(root, "a_real_interval_attribute");
        assertEquals(1, attribute.getChildCount());
        attribute = validateInterval(root, "a_date_interval_attribute");
        assertEquals(1, attribute.getChildCount());
        attribute = validateInterval(root, "a_time_interval_attribute");
        assertEquals(1, attribute.getChildCount());
        attribute = validateInterval(root, "a_datetime_interval_attribute");
        assertEquals(1, attribute.getChildCount());
        attribute = validateInterval(root, "a_duration_interval_attribute");
        assertEquals(1, attribute.getChildCount());
    }

    private OdinAttribute validateInterval(CompositeOdinObject compositeObject, String attributeName) {
        OdinAttribute attribute = compositeObject.retrieveAttributeFromIndex(attributeName);
        System.out.println("Validating " + attributeName);
        assertNotNull(attribute);
        return attribute;
    }

    public OdinAttribute validatePrimitiveType(CompositeOdinObject compositeObject, String attributeName, Object expectedAttributeValue) {
        OdinAttribute attribute = compositeObject.retrieveAttributeFromIndex(attributeName);
        System.out.println("Validating " + attributeName);
        assertNotNull(attribute);
        assertTrue(attribute.isPrimitiveValuedAttribute());
        assertEquals(expectedAttributeValue, attribute.getPrimitiveObjectChild().getValue());
        return attribute;
    }

    public OdinAttribute validatePrimitiveList(CompositeOdinObject compositeObject, String attributeName, int size) {
        OdinAttribute attribute = compositeObject.retrieveAttributeFromIndex(attributeName);
        System.out.println("Validating " + attributeName);
        assertNotNull(attribute);
        assertTrue(attribute.isPrimitiveList());
        assertEquals(size, attribute.getChildren().size());
        return attribute;
    }

    public void validateNestedAttributeStructures(CompositeOdinObject compositeObject) {
        assertEquals(1, compositeObject.getAttributeCount());
        OdinAttribute attribute1 = compositeObject.getAttributeAtIndex(0);
        assertEquals(1, attribute1.getChildCount());
        CompositeOdinObject attribute1Body = (CompositeOdinObject)attribute1.getChildren().get(0);
        assertEquals(3, attribute1Body.getAttributeCount());
        OdinAttribute attribute1_1 = attribute1Body.getAttributeAtIndex(0);
        OdinAttribute attribute1_2 = attribute1Body.getAttributeAtIndex(1);
        OdinAttribute attribute1_3 = attribute1Body.getAttributeAtIndex(2);
        assertEquals("attribute1_1", attribute1_1.getName());
        assertEquals("attribute1_1", attribute1_1.getStringObject().getValue());
        assertEquals("attribute1_2", attribute1_2.getName());
        assertEquals("attribute1_2", attribute1_2.getStringObject().getValue());
        assertEquals(1, attribute1_3.getChildCount());
        CompositeOdinObject attribute1_3Body = (CompositeOdinObject)attribute1_3.getChildren().get(0);
        assertEquals(2, attribute1_3Body.getAttributeCount());
        OdinAttribute attribute1_3_1 = attribute1_3Body.getAttributeAtIndex(0);
        OdinAttribute attribute1_3_2 = attribute1_3Body.getAttributeAtIndex(1);
        assertEquals("attribute1_3_1", attribute1_3_1.getName());
        assertEquals("attribute1_3_1", attribute1_3_1.getStringObject().getValue());
        assertEquals("attribute1_3_2", attribute1_3_2.getName());
        assertEquals("attribute1_3_2", attribute1_3_2.getStringObject().getValue());
    }

    private void validateKeyedObjects(CompositeOdinObject root) {
        assertEquals(1, root.getAttributeCount());
        OdinAttribute attribute1 = root.getAttributeAtIndex(0);
        assertEquals(1, attribute1.getChildCount());
        CompositeOdinObject attribute1Body = (CompositeOdinObject)attribute1.getChildren().get(0);
        assertEquals(2, attribute1Body.getKeyedObjectCount());
        StringObject ko1 = (StringObject)attribute1Body.getKeyedObject(new StringObject("1"));
        assertNotNull(ko1);
        assertEquals("One", ko1.getValue());
        StringObject ko2 = (StringObject)attribute1Body.getKeyedObject(new StringObject("2"));
        assertNotNull(ko2);
        assertEquals("Two", ko2.getValue());
    }

    public void validateRootLevelAttributes(CompositeOdinObject root) {
        assertEquals("Root element should have 11 attributes", 11, root.getAttributeCount());
        assertEquals("bmm_version", root.getAttributeAtIndex(0).getName());
        assertEquals(1, root.getAttributeAtIndex(0).getChildCount());
        assertEquals("2.0", root.getAttributeAtIndex(0).getStringObject().getValue());
        assertEquals("rm_publisher", root.getAttributeAtIndex(1).getName());
        assertEquals(1, root.getAttributeAtIndex(1).getChildCount());
        assertEquals("CIMI", root.getAttributeAtIndex(1).getStringObject().getValue());
        assertEquals("schema_name", root.getAttributeAtIndex(2).getName());
        assertEquals(1, root.getAttributeAtIndex(2).getChildCount());
        assertEquals("RM", root.getAttributeAtIndex(2).getStringObject().getValue());
        assertEquals("rm_release", root.getAttributeAtIndex(3).getName());
        assertEquals(1, root.getAttributeAtIndex(3).getChildCount());
        assertEquals("3.0.5", root.getAttributeAtIndex(3).getStringObject().getValue());
        assertEquals("schema_revision", root.getAttributeAtIndex(4).getName());
        assertEquals(1, root.getAttributeAtIndex(4).getChildCount());
        assertEquals("Monday, October 19, 2015", root.getAttributeAtIndex(4).getStringObject().getValue());
        assertEquals("schema_lifecycle_state", root.getAttributeAtIndex(5).getName());
        assertEquals(1, root.getAttributeAtIndex(5).getChildCount());
        assertEquals("dstu", root.getAttributeAtIndex(5).getStringObject().getValue());
        assertEquals("schema_description", root.getAttributeAtIndex(6).getName());
        assertEquals(1, root.getAttributeAtIndex(6).getChildCount());
        assertEquals("CIMI_Reference_Model v3.0.5 schema generated from UML", root.getAttributeAtIndex(6).getStringObject().getValue());
        assertEquals("archetype_rm_closure_packages", root.getAttributeAtIndex(7).getName());
        assertEquals(2, root.getAttributeAtIndex(7).getChildCount());
        assertEquals("CIMI_Reference_Model.Core", ((StringObject)root.getAttributeAtIndex(7).getChildren().get(0)).getValue());
        assertEquals("...", ((StringObject)root.getAttributeAtIndex(7).getChildren().get(1)).getValue());
        assertEquals("packages", root.getAttributeAtIndex(8).getName());
        assertEquals(1, root.getAttributeAtIndex(8).getChildCount());
        assertTrue(root.getAttributeAtIndex(8).getChildren().get(0) instanceof CompositeOdinObject);
        validatePackages((CompositeOdinObject)root.getAttributeAtIndex(8).getChildren().get(0));
        assertEquals("class_definitions", root.getAttributeAtIndex(9).getName());
        assertEquals(1, root.getAttributeAtIndex(9).getChildCount());
        assertTrue(root.getAttributeAtIndex(9).getChildren().get(0) instanceof CompositeOdinObject);
        validateClassDefinitions(root.getAttributeAtIndex(9).getSoleCompositeObjectBody());
        assertEquals("primitive_types", root.getAttributeAtIndex(10).getName());
        assertEquals(1, root.getAttributeAtIndex(10).getChildCount());
        assertTrue(root.getAttributeAtIndex(10).getChildren().get(0) instanceof CompositeOdinObject);
        validatePrimitiveTypes(root.getAttributeAtIndex(10).getSoleCompositeObjectBody());
    }

    public void validatePackages(CompositeOdinObject packages) {
        assertEquals("Packages should have exactly 1 keyed object", 1, packages.getKeyedObjectCount());
        assertEquals("Packages should have exactly 0 attributes", 0, packages.getAttributeCount());
        validateCimiReferenceModelKeyedObject((CompositeOdinObject)packages.getKeyedObject(new StringObject("CIMI_Reference_Model")));
    }

    public void validateCimiReferenceModelKeyedObject(CompositeOdinObject cimiReferenceModelKO) {
        assertEquals("Packages should have exactly 0 keyed object", 0, cimiReferenceModelKO.getKeyedObjectCount());
        assertEquals("Packages should have exactly 2 attributes: 'name' and 'packages'", 2, cimiReferenceModelKO.getAttributeCount());

        //Validate name
        assertEquals("name", cimiReferenceModelKO.getAttributeAtIndex(0).getName());
        assertNotNull(cimiReferenceModelKO.getAttributeAtIndex(0).getStringObject());
        assertEquals("CIMI_Reference_Model", cimiReferenceModelKO.getAttributeAtIndex(0).getStringObject().getValue());

        //Validate package
        OdinAttribute packagesAttr = cimiReferenceModelKO.getAttributeAtIndex(1);
        assertEquals("packages", packagesAttr.getName());
        assertTrue(packagesAttr.getChildren().get(0) instanceof CompositeOdinObject);
        validatePackageAttributeType((CompositeOdinObject)packagesAttr.getChildren().get(0));

        //Validate package."Core"
        CompositeOdinObject core = (CompositeOdinObject)packagesAttr.getSoleCompositeObjectBody().getKeyedObject("Core");
        assertNotNull(core);
        assertEquals("Core has two attributes: 'name' and 'classes':", 2, core.getAttributeCount());
        assertEquals("Core has no keyed objects:", 0, core.getKeyedObjectCount());
        OdinAttribute coreName = core.getAttributeAtIndex(0);
        OdinAttribute coreClasses = core.getAttributeAtIndex(1);
        assertEquals("name", coreName.getName());
        assertEquals("Core", coreName.getStringObject().getValue());
        assertEquals("classes", coreClasses.getName());
        assertEquals(7, coreClasses.getChildren().size());
        assertEquals("ARCHETYPED", coreClasses.getStringValueAt(0));
        assertEquals("PARTICIPATION", coreClasses.getStringValueAt(6));

        //Validate package."Data_Value_Types"
        CompositeOdinObject dataValueTypes = (CompositeOdinObject)packagesAttr.getSoleCompositeObjectBody().getKeyedObject("Data_Value_Types");
        assertNotNull(dataValueTypes);
        assertEquals("Data_Value_Types has two attributes: 'name' and 'classes':", 2, dataValueTypes.getAttributeCount());
        assertEquals("Data_Value_Types has no keyed objects:", 0, dataValueTypes.getKeyedObjectCount());
        OdinAttribute dataValueTypesName = dataValueTypes.getAttributeAtIndex(0);
        OdinAttribute dataValueTypesClasses = dataValueTypes.getAttributeAtIndex(1);
        assertEquals("name", dataValueTypesName.getName());
        assertEquals("Data_Value_Types", dataValueTypesName.getStringObject().getValue());
        assertEquals("classes", dataValueTypesClasses.getName());
        assertEquals(24, dataValueTypesClasses.getChildren().size());
        assertEquals("CODED_TEXT", dataValueTypesClasses.getStringValueAt(1));
        assertEquals("URI_VALUE", dataValueTypesClasses.getStringValueAt(22));

        //Validate package."Party"
        CompositeOdinObject party = (CompositeOdinObject)packagesAttr.getSoleCompositeObjectBody().getKeyedObject("Party");
        assertNotNull(party);
        assertEquals("Party has two attributes: 'name' and 'classes':", 2, party.getAttributeCount());
        assertEquals("Party has no keyed objects:", 0, party.getKeyedObjectCount());
        OdinAttribute partyName = party.getAttributeAtIndex(0);
        OdinAttribute partyClasses = party.getAttributeAtIndex(1);
        assertEquals("name", partyName.getName());
        assertEquals("Party", partyName.getStringObject().getValue());
        assertEquals("classes", partyClasses.getName());
        assertEquals(4, partyClasses.getChildren().size());
        assertEquals("PARTY", partyClasses.getStringValueAt(1));
        assertEquals("PARTY_RELATIONSHIP", partyClasses.getStringValueAt(2));

        //Validate package."Primitive_types"
        CompositeOdinObject primitiveTypes = (CompositeOdinObject)packagesAttr.getSoleCompositeObjectBody().getKeyedObject("Primitive_Types");
        assertNotNull(primitiveTypes);
        assertEquals("Primitive_Types has two attributes: 'name' and 'classes':", 2, primitiveTypes.getAttributeCount());
        assertEquals("Primitive_Types has no keyed objects:", 0, primitiveTypes.getKeyedObjectCount());
        OdinAttribute primitiveTypesName = primitiveTypes.getAttributeAtIndex(0);
        OdinAttribute primitiveTypesClasses = primitiveTypes.getAttributeAtIndex(1);
        assertEquals("name", primitiveTypesName.getName());
        assertEquals("Primitive_Types", primitiveTypesName.getStringObject().getValue());
        assertEquals("classes", primitiveTypesClasses.getName());
        assertEquals(10, primitiveTypesClasses.getChildren().size());
        assertEquals("Byte", primitiveTypesClasses.getStringValueAt(4));
        assertEquals("Character", primitiveTypesClasses.getStringValueAt(5));
    }

    public void validatePackageAttributeType(CompositeOdinObject packagesAttribute) {
        assertEquals("Packages should have exactly 4 keyed object", 4, packagesAttribute.getKeyedObjectCount());
        assertEquals("Packages should have exactly 0 attributes", 0, packagesAttribute.getAttributeCount());
    }

    public void validateClassDefinitions(CompositeOdinObject classDefinitions) {
        assertEquals("Class Definitions should have exactly 36 keyed object", 35, classDefinitions.getKeyedObjectCount());
        assertEquals("Class Definitions should have exactly 0 attributes", 0, classDefinitions.getAttributeCount());
        validateClassDefinitionItemGroupKeyedObject((CompositeOdinObject)classDefinitions.getKeyedObject("ITEM_GROUP"));
    }

    public void validateClassDefinitionItemGroupKeyedObject(CompositeOdinObject itemGroupKO) {
        assertEquals("Packages should have exactly 3 attributes: 'name', 'ancestors' and 'properties'", 3, itemGroupKO.getAttributeCount());
        assertEquals("Packages should have exactly 0 keyed object", 0, itemGroupKO.getKeyedObjectCount());

        //Validate name
        assertEquals("name", itemGroupKO.getAttributeAtIndex(0).getName());
        assertEquals("ITEM_GROUP", itemGroupKO.getAttributeAtIndex(0).getStringValue());

        //Validate ancestors
        assertEquals("ancestors", itemGroupKO.getAttributeAtIndex(1).getName());
        assertEquals(2, itemGroupKO.getAttributeAtIndex(1).getChildren().size());
        assertEquals("ITEM", itemGroupKO.getAttributeAtIndex(1).getStringValueAt(0));
        assertEquals("...", itemGroupKO.getAttributeAtIndex(1).getStringValueAt(1));

        //Validate properties
        OdinAttribute properties = itemGroupKO.getAttributeAtIndex(2);
        assertEquals("properties", properties.getName());
        assertEquals("Properties should have exactly 2 keyed object", 2, properties.getSoleCompositeObjectBody().getKeyedObjectCount());
        assertEquals("Properties should have exactly 0 attributes", 0, properties.getSoleCompositeObjectBody().getAttributeCount());

        //Validate properties."item"
        CompositeOdinObject item = (CompositeOdinObject)properties.getSoleCompositeObjectBody().getKeyedObject("item");
        assertEquals("Item should have exactly 4 attributes: 'name', 'type_def', 'cardinality', 'is_mandatory'", 4, item.getAttributeCount());
        assertEquals("Item should have exactly 0 keyed object", 0, item.getKeyedObjectCount());
        assertEquals("Item should have a type of P_BMM_CONTAINER_PROPERTY", "P_BMM_CONTAINER_PROPERTY", item.getType());

        //Validate properties."item".name
        OdinAttribute itemName = item.getAttributeAtIndex(0);
        assertEquals("name", item.getAttributeAtIndex(0).getName());
        assertEquals("item", item.getAttributeAtIndex(0).getStringValue());

        //Validate properties."item".type_def
        OdinAttribute typeDef = item.getAttributeAtIndex(1);
        CompositeOdinObject typeDefType = typeDef.getSoleCompositeObjectBody();
        assertEquals("type_def", typeDef.getName());
        assertEquals("Item should have exactly 2 attributes: 'container_type', 'type'", 2, typeDef.getSoleCompositeObjectBody().getAttributeCount());
        assertEquals("Item should have exactly 0 keyed object", 0, typeDef.getSoleCompositeObjectBody().getKeyedObjectCount());

        //Validate properties."item".type_def.container_type
        OdinAttribute containerTypeAttribute = typeDefType.getAttributeAtIndex(0);
        assertEquals("container_type", containerTypeAttribute.getName());
        assertEquals("List", containerTypeAttribute.getStringValue());

        //Validate properties."item".type_def.type
        OdinAttribute typeAttribute = typeDefType.getAttributeAtIndex(1);
        assertEquals("type", typeAttribute.getName());
        assertEquals("ITEM", typeAttribute.getStringValue());

        //Validate properties."item".cardinality
        OdinAttribute cardinality = item.getAttributeAtIndex(2);
        assertEquals("cardinality", cardinality.getName());
        assertEquals("|>=1|", cardinality.getIntegerIntervalObject().getIntervalExpression());
        assertEquals(new Integer(1), cardinality.getIntegerIntervalObject().getLow().getAsInteger());
        assertFalse(cardinality.getIntegerIntervalObject().isExcludeLowerBound());

        //Validate properties."item".isMandatory
        OdinAttribute isMandatory = item.getAttributeAtIndex(3);
        assertEquals("is_mandatory", isMandatory.getName());
        assertTrue(isMandatory.getBooleanValue());

        //Validate properties."participation"
        CompositeOdinObject participation = (CompositeOdinObject)properties.getSoleCompositeObjectBody().getKeyedObject("participation");
        assertEquals("Participation should have exactly 3 attributes: 'name', 'type_def', 'cardinality'", 3, participation.getAttributeCount());
        assertEquals("Participation should have exactly 0 keyed object", 0, participation.getKeyedObjectCount());
        assertEquals("Participation should have a type of P_BMM_CONTAINER_PROPERTY", "P_BMM_CONTAINER_PROPERTY", participation.getType());

        //Validate properties."participation".name
        OdinAttribute participationName = participation.getAttributeAtIndex(0);
        assertEquals("name", participationName.getName());
        assertEquals("participation", participationName.getStringObject().getValue());

        //Validate properties."participation".type_def
        OdinAttribute participationTypeDef = participation.getAttributeAtIndex(1);
        CompositeOdinObject participationTypeDefType = participationTypeDef.getSoleCompositeObjectBody();
        assertEquals("type_def", participationTypeDef.getName());
        assertEquals("Item should have exactly 2 attributes: 'container_type', 'type'", 2, participationTypeDefType.getAttributeCount());
        assertEquals("Item should have exactly 0 keyed object", 0, participationTypeDefType.getKeyedObjectCount());
        assertTrue(participationTypeDefType instanceof CompositeOdinObject);

        //Validate properties."participation".type_def.container_type
        OdinAttribute participationContainerTypeAttribute = participationTypeDefType.getAttributeAtIndex(0);
        assertEquals("container_type", participationContainerTypeAttribute.getName());
        assertEquals("List", participationContainerTypeAttribute.getStringObject().getValue());

        //Validate properties."participation".type_def.type
        OdinAttribute participationTypeDefTypeAttribute = participationTypeDefType.getAttributeAtIndex(1);
        assertEquals("type", participationTypeDefTypeAttribute.getName());
        assertEquals("PARTICIPATION", participationTypeDefTypeAttribute.getStringObject().getValue());

        //Validate properties."participation".cardinality
        OdinAttribute participationCardinality = participation.getAttributeAtIndex(2);
        assertEquals("cardinality", participationCardinality.getName());
        assertEquals("|>=0|", participationCardinality.getIntegerIntervalObject().getIntervalExpression());
        assertEquals(new Integer(0), participationCardinality.getIntegerIntervalObject().getLow().getAsInteger());
        assertFalse(participationCardinality.getIntegerIntervalObject().isExcludeLowerBound());
    }

    public void validatePrimitiveTypes(CompositeOdinObject primitiveTypes) {
        assertEquals("Primitive Types should have exactly 10 keyed object", 10, primitiveTypes.getKeyedObjectCount());
        assertEquals("Primitive Types should have exactly 0 attributes", 0, primitiveTypes.getAttributeCount());

        //Validate Boolean
        CompositeOdinObject booleanType = (CompositeOdinObject)primitiveTypes.getKeyedObject("Boolean");
        assertNotNull(booleanType);
        assertEquals("Boolean keyed object specifies a single attribute", 1, booleanType.getAttributeCount());
        OdinAttribute booleanTypeName = booleanType.getAttributeAtIndex(0);
        assertEquals("name", booleanTypeName.getName());
        assertEquals("Boolean", booleanTypeName.getStringObject().getValue());

        //Validate List.name
        CompositeOdinObject listType = (CompositeOdinObject)primitiveTypes.getKeyedObject("List");
        assertNotNull(listType);
        assertEquals("List keyed object specifies two attribute: 'name' and 'generic_parameter_defs'", 2, listType.getAttributeCount());
        OdinAttribute listTypeName = listType.getAttributeAtIndex(0);
        assertEquals("name", listType.getAttributeAtIndex(0).getName());
        assertEquals("List", listType.getAttributeAtIndex(0).getStringObject().getValue());

        //Validate List.genericParameterDefs
        OdinAttribute listTypeGenericParameterDefs = listType.getAttributeAtIndex(1);
        assertEquals("generic_parameter_defs", listTypeGenericParameterDefs.getName());
        assertTrue(listTypeGenericParameterDefs.getSoleCompositeObjectBody() instanceof CompositeOdinObject);
        assertEquals(0, listTypeGenericParameterDefs.getSoleCompositeObjectBody().getAttributeCount());
        assertEquals(1, listTypeGenericParameterDefs.getSoleCompositeObjectBody().getKeyedObjectCount());
        assertNotNull(listTypeGenericParameterDefs.getSoleCompositeObjectBody().getKeyedObject("T"));
        assertEquals(1, ((CompositeOdinObject)listTypeGenericParameterDefs.getSoleCompositeObjectBody().getKeyedObject("T")).getAttributeCount());
    }

    public void validateOdinTypes(CompositeOdinObject root) {
        assertEquals(2, root.getAttributeCount());
        OdinAttribute person = root.getAttributeAtIndex(0);
        OdinAttribute parent = root.getAttributeAtIndex(1);

        assertEquals("List<PERSON>", person.getSoleCompositeObjectBody().getType());
        CompositeOdinObject _01234 = (CompositeOdinObject) person.getSoleCompositeObjectBody().getKeyedObject(new IntegerObject("01234"));
        assertEquals(2, _01234.getAttributeCount());

        OdinAttribute personName = _01234.getAttributeAtIndex(0);
        assertEquals(3, personName.getSoleCompositeObjectBody().getAttributeCount());
        assertEquals("forenames", personName.getSoleCompositeObjectBody().getAttributeAtIndex(0).getName());
        assertEquals("Sherlock", personName.getSoleCompositeObjectBody().getAttributeAtIndex(0).getStringValue());
        assertEquals("family_name", personName.getSoleCompositeObjectBody().getAttributeAtIndex(1).getName());
        assertEquals("Holmes", personName.getSoleCompositeObjectBody().getAttributeAtIndex(1).getStringValue());
        assertEquals("salutation", personName.getSoleCompositeObjectBody().getAttributeAtIndex(2).getName());
        assertEquals("Mr", personName.getSoleCompositeObjectBody().getAttributeAtIndex(2).getStringValue());

        OdinAttribute personAddress = _01234.getAttributeAtIndex(1);
        assertEquals(4, personAddress.getSoleCompositeObjectBody().getAttributeCount());
        assertEquals("habitation_number", personAddress.getSoleCompositeObjectBody().getAttributeAtIndex(0).getName());
        assertEquals("221B", personAddress.getSoleCompositeObjectBody().getAttributeAtIndex(0).getStringValue());
        assertEquals("street_name", personAddress.getSoleCompositeObjectBody().getAttributeAtIndex(1).getName());
        assertEquals("Baker St", personAddress.getSoleCompositeObjectBody().getAttributeAtIndex(1).getStringValue());
        assertEquals("city", personAddress.getSoleCompositeObjectBody().getAttributeAtIndex(2).getName());
        assertEquals("London", personAddress.getSoleCompositeObjectBody().getAttributeAtIndex(2).getStringValue());
        assertEquals("country", personAddress.getSoleCompositeObjectBody().getAttributeAtIndex(3).getName());
        assertEquals("England", personAddress.getSoleCompositeObjectBody().getAttributeAtIndex(3).getStringValue());

        CompositeOdinObject element = (CompositeOdinObject)parent.getSoleCompositeObjectBody().getKeyedObject("ELEMENT");
        assertEquals(3, element.getAttributeCount());

        OdinAttribute name = element.getAttributeAtIndex(0);
        assertEquals("name", name.getName());
        assertEquals("ELEMENT", name.getStringValue());

        OdinAttribute ancestors = element.getAttributeAtIndex(1);
        assertEquals("ancestors", ancestors.getName());
        assertEquals("ITEM", ancestors.getStringValueAt(0));
        assertEquals("...", ancestors.getStringValueAt(1));

        OdinAttribute properties = element.getAttributeAtIndex(2);
        CompositeOdinObject nullFlavor = (CompositeOdinObject) properties.getSoleCompositeObjectBody().getKeyedObject("null_flavor");
        assertEquals("P_BMM_SINGLE_PROPERTY", nullFlavor.getType());
        OdinAttribute nullFlavorName = nullFlavor.getAttributeAtIndex(0);
        assertEquals("name", nullFlavorName.getName());
        assertEquals("null_flavor", nullFlavorName.getStringValue());
        OdinAttribute nullFlavorType = nullFlavor.getAttributeAtIndex(1);
        assertEquals("type", nullFlavorType.getName());
        assertEquals("CODED_TEXT", nullFlavorType.getStringValue());

        CompositeOdinObject value = (CompositeOdinObject) properties.getSoleCompositeObjectBody().getKeyedObject("value");
        assertEquals("P_BMM_SINGLE_PROPERTY", value.getType());
        OdinAttribute valueName = value.getAttributeAtIndex(0);
        assertEquals("name", valueName.getName());
        assertEquals("value", valueName.getStringValue());
        OdinAttribute valueType = value.getAttributeAtIndex(1);
        assertEquals("type", valueType.getName());
        assertEquals("DATA_VALUE", valueType.getStringValue());
    }

    @Test
    public void loadCimiReferenceModel() {
        ReferenceModelLoader loader = new ReferenceModelLoader();
        BmmSchema cimiRm = loader.loadCimiReferenceModel(ReferenceModelLoaderTest.class.getResourceAsStream("/odin/CIMI-RM-3.0.5.bmm"));
        assertNotNull(cimiRm);
        assertEquals("3.0.5", cimiRm.getRmRelease());
        assertEquals("CIMI", cimiRm.getRmPublisher());
        assertEquals("RM", cimiRm.getSchemaName());
        assertEquals("2.0", cimiRm.getBmmInternalVersion());
        assertEquals("Monday, October 19, 2015", cimiRm.getSchemaRevision());
        assertEquals("dstu", cimiRm.getSchemaLifecycleState());
        assertEquals("CIMI_Reference_Model v3.0.5 schema generated from UML", cimiRm.getSchemaDescription());
        assertEquals("CIMI_Reference_Model.Core", cimiRm.getArchetypeRmClosurePackages().get(0));

    }
}