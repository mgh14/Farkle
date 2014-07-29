package main.engine.properties;

import java.util.HashMap;

import main.engine.RollGenerator;

public class PropertiesManager {

    private static HashMap<String, String> properties = new HashMap<String, String>();
    private static RollGenerator generator;

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
    public static final int DEFAULT_ERROR_INDICATOR = -1;

    public static HashMap<String, Integer> defaults;

    public static final String DEFAULT_PROFILE_FILENAME = "config.xml";

    public static void setGenerator(RollGenerator evaluator) {
        generator = evaluator;
    }

    private static void resetPropertiesMap() {
        properties = new HashMap<String, String>();
    }

    public static int getDefaultSetting(String propName) {
        if(defaults.containsKey(propName)) {
            return defaults.get(propName);
        }

        return DEFAULT_ERROR_INDICATOR;
    }

    private static void loadDefaults() {
        defaults = new HashMap<String, Integer>();

        defaults.put(NUM_PLAYERS_PROP_NAME, DEFAULT_NUM_PLAYERS);
        defaults.put(MIN_DIE_VAL_PROP_NAME, DEFAULT_MIN_DIE_VALUE);
        defaults.put(MAX_DIE_VAL_PROP_NAME, DEFAULT_MAX_DIE_VALUE);
        defaults.put(NUM_DICE_PROP_NAME, DEFAULT_NUM_DICE);
        defaults.put(REQ_POINTS_FOR_WIN_PROP_NAME, DEFAULT_REQ_POINTS_FOR_WIN);
    }

    public static void loadDefaultConfig() {
        loadDefaults();
        properties.put(NUM_PLAYERS_PROP_NAME, Integer.toString(DEFAULT_NUM_PLAYERS));
        properties.put(MIN_DIE_VAL_PROP_NAME, Integer.toString(DEFAULT_MIN_DIE_VALUE));
        properties.put(MAX_DIE_VAL_PROP_NAME, Integer.toString(DEFAULT_MAX_DIE_VALUE));
        properties.put(NUM_DICE_PROP_NAME, Integer.toString(DEFAULT_NUM_DICE));
        properties.put(REQ_POINTS_FOR_WIN_PROP_NAME, Integer.toString(DEFAULT_REQ_POINTS_FOR_WIN));
    }

    public static void loadSettingsProfile(String configFile) {
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
        generator.verifyDieValueIsValid(getMinDieValue(), getMaxDieValue(), value);
    }

}
