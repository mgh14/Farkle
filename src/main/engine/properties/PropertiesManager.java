package main.engine.properties;

import java.util.HashMap;

public class PropertiesManager {

    private static HashMap<String, String> properties = new HashMap<String, String>();
    private static DieValueEvaluator dieEvaluator;

    private static final String CONFIG_FILENAME_PROP_NAME = "configFile";
    private static final String NUM_PLAYERS_PROP_NAME = "numPlayers";
    private static final String MIN_DIE_VAL_PROP_NAME = "minVal";
    private static final String MAX_DIE_VAL_PROP_NAME = "maxVal";
    private static final String NUM_DICE_PROP_NAME = "numDice";
    private static final String REQ_POINTS_FOR_WIN_PROP_NAME = "reqPointsForWin";

    public static final int DEFAULT_NUM_PLAYERS = 2;
    public static final int DEFAULT_MIN_DIE_VALUE = 1;
    public static final int DEFAULT_MAX_DIE_VALUE = 6;
    public static final int DEFAULT_NUM_DICE = 6;
    public static final int DEFAULT_REQ_POINTS_FOR_WIN = 10000;

    public static final String DEFAULT_CONFIG_FILENAME = "config.xml";

    public static void setDieEvaluator(DieValueEvaluator evaluator) {
        dieEvaluator = evaluator;
    }

    private static void resetPropertiesMap() {
        properties = new HashMap<String, String>();
    }

    public static void loadDefaultConfig() {
        //loadConfigFile(defaultFileName)
    }

    public static void loadConfigFile(String configFile) {
        resetPropertiesMap();

        /*//Load and Parse the XML document

        //document contains the complete XML as a Tree.
        Document document = builder.parse(ClassLoader.getSystemResourceAsStream("xml/employee.xml"));

        List<Employee> empList = new ArrayList<>();
        //Iterating through the nodes and extracting the data.
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            //We have encountered an <employee> tag.
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Employee emp = new Employee();
                emp.id = node.getAttributes().
                getNamedItem("id").getNodeValue();

                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);

                    //Identifying the child tag of employee encountered.
                    if (cNode instanceof Element) {
                        String content = cNode.getLastChild().
                        getTextContent().trim();
                        switch (cNode.getNodeName()) {
                            case "firstName":
                                emp.firstName = content;
                                break;
                            case "lastName":
                                emp.lastName = content;
                                break;
                            case "location":
                                emp.location = content;
                                break;
                        }
                    }
                }
                empList.add(emp);
            }
        }*/

        properties.put(CONFIG_FILENAME_PROP_NAME, configFile);
    }

    public static int getNumPlayers() {
        return Integer.getInteger(properties.get(NUM_PLAYERS_PROP_NAME));
    }

    public static int getMinDieValue() {
        return Integer.getInteger(properties.get(MIN_DIE_VAL_PROP_NAME));
    }

    public static int getMaxDieValue() {
        return Integer.getInteger(properties.get(MAX_DIE_VAL_PROP_NAME));
    }

    public static int getNumDice() {
        return Integer.getInteger(properties.get(NUM_DICE_PROP_NAME));
    }

    public static int getPointsReqForWin() {
        return Integer.getInteger(properties.get(REQ_POINTS_FOR_WIN_PROP_NAME));
    }

    public static void verifyDieValueIsValid(int value) {
        dieEvaluator.verifyDieValueIsValid(getMinDieValue(), getMaxDieValue(), value);
    }

}
