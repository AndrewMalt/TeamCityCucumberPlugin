package steps;

import io.cucumber.java.en.Given;

public class StepDefinitions {

  @Given("say {string}")
  public void qwe(String text) {
    System.out.println("----->>> " + text);
  }

}
