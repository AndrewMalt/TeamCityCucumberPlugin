package steps;

import static java.lang.Thread.sleep;

import com.typesafe.config.ConfigFactory;
import helpers.PropertyLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

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
    System.out.println("----- " + name + " -------->>> " + ConfigFactory.load().getString(name));

  }
}
