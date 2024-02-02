package steps;

import static java.lang.Thread.sleep;

import com.typesafe.config.ConfigFactory;
import helpers.PropertyLoader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import io.cucumber.java.ru.Пусть;
import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class StepDefinitions {

  @Given("say {string}")
  public void qwe(String text) throws InterruptedException {
    //    sleep(1000);
    System.out.println("----->>> " + PropertyLoader.loadProperty(text));
  }

  @And("send {string}")
  public void sendBooleanVariable(String variable) {
    Assert.assertTrue("test failed", Boolean.parseBoolean(variable));
  }

  @And("read text")
  public void readText() throws IOException {
    Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/text.txt");
    String str = new String(Files.readAllBytes(path));
    System.out.println("------------->> > > " + str);
  }

  @And("wait {int} sec")
  public void waitSec(int sec) throws InterruptedException {
    sleep(sec * 1000L);
  }

  public static void main(String[] args) throws IOException, URISyntaxException {

    //    URL url = new URL("http://test.com/testapp/test.do?test_id=1&test_name=SS");
    //    URI uri = new URI("http://test.com/testapp/test.do?test_id=1&test_name=SS");
    URI uri = new URI("http://test.com/testapp/test.do/");
    //    URI uri = new URI(url);
    URI uri2 =
        URI.create(
            String.valueOf(
                new URI(
                    uri.getScheme(),
                    uri.getAuthority(),
                    StringUtils.removeEnd(uri.getPath(), "/"),
                    uri.getFragment())));
    System.out.println("---> " + uri.getFragment());
    System.out.println(uri2);

    String str = "qweq";
    System.out.println(StringUtils.removeEnd(str, "e*"));
  }

  @And("throw NPE")
  public void throwNPE() throws Exception {
    throw (Exception) null;
  }

  @And("read prop {string}")
  public void readProp(String propName) {
    System.out.println("--------getProperty------->>> >> > " + System.getProperty(propName));
    System.out.println("---------getenv------>>> >> > " + System.getenv(propName));
  }

  @Given("read conf {string}")
  public void readConf(String name) {
//    RestAssured.
//    RestAssured.authentication.authenticate("",""); = RestAssured.ntlm("qwe","","","");
    System.out.println("----- " + name + " -------->>> " + ConfigFactory.load().getString(name));
  }

  @Пусть("^в XML файле \"(.*)\" произведена замена атрибутов данными из таблицы и результат сохранен в файл \"(.*)\"$")
  public void changeAttributeValue(String pathBefore, String pathAfter, DataTable dataTable) {
    try {
      Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(pathBefore));
      XPathFactory xPathFactory = XPathFactory.newInstance();
      XPath xPath = xPathFactory.newXPath();
      List<Map<String, String>> rows = dataTable.asMaps();
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();

      for (Map<String, String> columns : rows) {
        NodeList nodeList = (NodeList) xPath.compile(columns.get("xPath"))
                .evaluate(doc, XPathConstants.NODESET);
        Element element = (Element) nodeList.item(0);
        element.setAttribute(columns.get("attribute"), columns.get("value"));
      }
      doc.setXmlStandalone(true);
      transformer.setOutputProperty(OutputKeys.ENCODING,"WINDOWS-1251");
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(pathAfter);
      transformer.transform(source, result);
    } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException |
             TransformerException e) {
      throw new RuntimeException(e);
    }
  }

    @And("sys prop")
    public void sysProp() {
      System.setProperty("firstVar", "fvValue");
      System.out.println(System.getProperty("firstVar"));
      System.out.println(System.getProperty("secondVar"));
      System.out.println(System.getProperty("file.encoding"));
    }
}
