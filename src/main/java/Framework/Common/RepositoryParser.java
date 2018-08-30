package Framework.Common;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositoryParser {

    public static HashMap<String,List<HashMap<String,String>>> infoStorage;

    public static void ParseXML(){
        try {
            infoStorage = infoStorage != null? infoStorage : new HashMap<String,List<HashMap<String,String>>>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("Repository.xml"));
//            List<String> pagesList = new ArrayList<String>();
            document.getDocumentElement().normalize();
//            NodeList nodeList = document.getDocumentElement().getChildNodes();
            NodeList nodeList = document.getElementsByTagName("Page");
            for (int i = 0; i < nodeList.getLength(); i++) {
                List<HashMap<String,String>> allPageData = new ArrayList<HashMap<String,String>>();
                HashMap<String,String> innerPageData;

                Node node = nodeList.item(i);
                String pageName = node.getAttributes().getNamedItem("name").getNodeValue();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element)node;
                    NodeList controls = el.getElementsByTagName("Control");
                    for (int j = 0; j < controls.getLength(); j++) {
                        innerPageData = new HashMap<String,String>();
                        Node controlNode = controls.item(j);
                        Element controlEl = (Element)controlNode;
                        innerPageData.put("label",controlEl.getAttribute("label"));
                        innerPageData.put("type",controlEl.getAttribute("type"));

                        if(!controlEl.getAttribute("id").isEmpty())
                            innerPageData.put("id", controlEl.getAttribute("id"));
                        else if(!controlEl.getAttribute("name").isEmpty())
                            innerPageData.put("name", controlEl.getAttribute("name"));
                        else if(!controlEl.getAttribute("xpath").isEmpty())
                            innerPageData.put("xpath", controlEl.getAttribute("xpath"));
                        else
                            throw new Exception("Please, determine correct locator for element");
                        allPageData.add(innerPageData);
                    }

                }
                infoStorage.put(pageName, allPageData);
                System.out.print("");
            }
            System.out.print("");
            } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static HashMap<String, String> GetControlInfo(String pageName, String controlLabel)  {
//        HashMap<String, String> resInfo = new HashMap<String, String>();
        List<HashMap<String,String>> myMap = infoStorage.get(pageName);
        for (HashMap<String, String> aMyMap : myMap) {
            if (aMyMap.get("label").equals(controlLabel)) {
                return aMyMap;
            }
        }
        System.out.print(String.format("\r\nDEBUG: Cannot find an information for control with label '%1$s' in page '%2$s'." ,controlLabel, pageName));
        return null;
    }

    public static String GetControlType(String pageName, String controlLabel){
        return GetControlInfo(pageName, controlLabel).get("type");
    }
}
