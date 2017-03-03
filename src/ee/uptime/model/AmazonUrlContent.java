package ee.uptime.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AmazonUrlContent {
	
	public List<Amazon> fetchTitle(String requestUrl) {
    	List<Amazon> titles = new ArrayList<Amazon>();
    	
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            NodeList titleNodes = doc.getElementsByTagName("Item");
            for (int i = 0; i < titleNodes.getLength(); i++) {
            	Node titleNode = titleNodes.item(i);
            	if (titleNode.getNodeType() == Node.ELEMENT_NODE) {
            		Element element = (Element) titleNode;
            		String toode = element.getElementsByTagName("Title").item(0).getTextContent().toString();
            		String hind = "Hinda ei ole";
            		try {
            			hind = element.getElementsByTagName("FormattedPrice").item(0).getTextContent().toString();
            		} catch (NullPointerException e) {
            			
            		}
            		titles.add(new Amazon(toode, hind));
            	}
            }
            
        } catch (Exception e) {
            System.out.println("URL was empty!");;
        }
        return titles;
    }
}
