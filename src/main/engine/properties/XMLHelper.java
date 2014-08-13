package main.engine.properties;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XMLHelper {

    private final String SETTING_XML_NAME = "setting";
    private final String VARIABLE_XML_NAME = "name";
    private final String VARIABLE_XML_VALUE = "value";

    public void writeProfile(String profileName, HashMap<String, Object> variableMap) {

    }

    public Document parseProfile(String profileFile) throws ParserConfigurationException,
    SAXException, IOException {

        File fXmlFile = new File(PropertiesManager.translateFilepathToAbsoluteFilepath(profileFile));
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
        return docBuilder.parse(fXmlFile);
    }

    public HashMap<String, String> getSettingsFromDocument(Document document) {
        HashMap<String, String> properties = new HashMap<String, String>();

        //Iterating through the nodes and extracting the data.
        NodeList firstLevelChildren = document.getDocumentElement().getChildNodes();
        for(int i=0; i<firstLevelChildren.getLength(); i++) {
            // find <setting> nodes
            Node node = firstLevelChildren.item(i);
            if(!node.getNodeName().equals(SETTING_XML_NAME)) {
                continue;
            }

            // find <name> and <value> nodes
            NodeList secondLevelChildren = node.getChildNodes();
            String name = null;
            String value = null;
            for(int j=0; j<secondLevelChildren.getLength(); j++) {
                Node var = secondLevelChildren.item(j);
                if(var.getNodeName().equals(VARIABLE_XML_NAME)) {
                    name = var.getChildNodes().item(0).getNodeValue();
                }
                else if(var.getNodeName().equals(VARIABLE_XML_VALUE)) {
                    value = var.getChildNodes().item(0).getNodeValue();
                }
            }
            if(name != null && value != null) {
                properties.put(name, value);
            }
        }

        return properties;
    }
}
