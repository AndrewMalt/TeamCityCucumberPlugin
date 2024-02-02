package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlParseTest {

    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException, TransformerException {

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                new File("src/main/resources/xml/test2.xml"));


        System.out.println("=====");
        System.out.println(doc);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        NodeList myNodeList = (NodeList) xPath.compile("//company")
                .evaluate(doc, XPathConstants.NODESET);

        System.out.println("---> " + myNodeList.item(0).getNodeName());

        System.out.println("--myNodeList--> " + myNodeList);

//        String str = xPath.compile("//company/staff[@id='1001']").evaluate(doc, XPathConstants.STRING).toString();
        String str = xPath.compile("//company/staff[1]/@id").evaluate(doc, XPathConstants.STRING).toString();
//        String str = xPath.compile("//text()").evaluate(doc, XPathConstants.STRING).toString();

//        doc.set
        NodeList myNodeList2 = (NodeList) xPath.compile("//company/staff/firstname")
                .evaluate(doc, XPathConstants.NODESET);
        NodeList myNodeList3 = (NodeList) xPath.compile("//company/staff[@id='1001']/firstname")
                .evaluate(doc, XPathConstants.NODESET);
        Element element = (Element) myNodeList3.item(0);
        element.setAttribute("q", "12312e1");

        NodeList myNodeList4 = (NodeList) xPath.compile("//company/staff[@id='1001']/firstname")
                .evaluate(doc, XPathConstants.NODESET);
        Element element2 = (Element) myNodeList3.item(0);
        element2.setAttribute("pam", "Привет");
//        myNodeList3.item(1).getAttributes().getNamedItem("q").setNodeValue("11111");
        System.out.println("-------->>> . > " + myNodeList2.item(1).getTextContent());
        myNodeList2.item(1).setTextContent("йцуйцуйцуйуцйцу");
        System.out.println("-------->>> . > " + myNodeList2.item(1).getTextContent());
        System.out.println("---str-> " + str);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        doc.setXmlStandalone(true);
//        doc.
        transformer.setOutputProperty(OutputKeys.ENCODING,"WINDOWS-1251");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
//        transformer.setParameter("//company/staff[1]/@id", "123");
        String str2 = xPath.compile("//company/staff/firstname").evaluate(doc, XPathConstants.STRING).toString();
        DOMSource source = new DOMSource(doc);
        System.out.println("-----------str2---------------->> " + str2);
//        source.getNode().getNodeName()
        StreamResult result = new StreamResult("src/main/resources/xml/newTest2.xml");

        transformer.transform(source, result);
    }
}
