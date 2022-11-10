package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;

public class StepDefinitions {

  @Given("say {string}")
  public void qwe(String text) {
    System.out.println("----->>> " + text);
  }

  @And("send {string}")
  public void sendBooleanVariable(String variable) {
    Assert.assertTrue("test failed", Boolean.parseBoolean(variable));
  }
}
