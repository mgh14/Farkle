package main.engine.properties;

import java.util.HashMap;

public class PropertiesManager {

    private HashMap<String, String> properties = new HashMap<String, String>();
    private DieValueEvaluator dieEvaluator;

    private final String CONFIG_FILENAME_PROP_NAME = "configFile";
    private final String NUM_PLAYERS_PROP_NAME = "numPlayers";
    private final String MIN_DIE_VAL_PROP_NAME = "minVal";
    private final String MAX_DIE_VAL_PROP_NAME = "maxVal";
    private final String NUM_DICE_PROP_NAME = "numDice";
    private final String REQ_POINTS_FOR_WIN_PROP_NAME = "reqPointsForWin";

    public final int DEFAULT_NUM_PLAYERS = 2;
    public final int DEFAULT_MIN_DIE_VALUE = 1;
    public final int DEFAULT_MAX_DIE_VALUE = 6;
    public final int DEFAULT_NUM_DICE = 6;
    public final int DEFAULT_REQ_POINTS_FOR_WIN = 10000;

    public final String DEFAULT_CONFIG_FILENAME = "config.xml";

    public void setDieEvaluator(DieValueEvaluator evaluator) {
        dieEvaluator = evaluator;
    }

    private void resetPropertiesMap() {
        properties = new HashMap<String, String>();
    }

    public void loadDefaultConfig() {
        resetPropertiesMap();
        properties.put(MIN_DIE_VAL_PROP_NAME, "here");
        System.out.println("here");
        //loadConfigFile(defaultFileName)
    }

    public void loadConfigFile(String configFile) {
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

    public int getNumPlayers() {
        return Integer.getInteger(properties.get(NUM_PLAYERS_PROP_NAME));
    }

    public int getMinDieValue() {
        return Integer.getInteger(properties.get(MIN_DIE_VAL_PROP_NAME));
    }

    public int getMaxDieValue() {
        return Integer.getInteger(properties.get(MAX_DIE_VAL_PROP_NAME));
    }

    public int getNumDice() {
        return Integer.getInteger(properties.get(NUM_DICE_PROP_NAME));
    }

    public int getPointsReqForWin() {
        return Integer.getInteger(properties.get(REQ_POINTS_FOR_WIN_PROP_NAME));
    }

    public void verifyDieValueIsValid(int value) {
        dieEvaluator.verifyDieValueIsValid(getMinDieValue(), getMaxDieValue(), value);
    }

}
