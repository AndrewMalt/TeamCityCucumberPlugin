package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;

public class StepDefinitions {

  @Given("say {string}")
  public void qwe(String text) throws InterruptedException {
    Thread.sleep(2000);
    System.out.println("----->>> " + text);
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
}


