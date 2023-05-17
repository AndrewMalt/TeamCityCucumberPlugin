package plugins;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import java.net.URI;
import org.apache.commons.io.FilenameUtils;

public class TeamCityPlugin implements EventListener {

  private URI currentFeatureFile = null;
  private String previousTestCaseName;
  private int exampleNumber;
  private String testCaseName;

  @Override
  public void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
    publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
  }

  private void handleTestCaseStarted(TestCaseStarted event) {

    getFileName(event);

    testCaseName = event.getTestCase().getName();

    if (currentFeatureFile == null || !currentFeatureFile.equals(event.getTestCase().getUri())) {
      currentFeatureFile = event.getTestCase().getUri();
      previousTestCaseName = "";
      exampleNumber = 1;
    }

    if (testCaseName.equals(previousTestCaseName)) {
      testCaseName = getUniqueTestNameForScenarioExample(testCaseName, ++exampleNumber);
    } else {
      previousTestCaseName = event.getTestCase().getName();
      exampleNumber = 1;
    }
    System.out.println(
        "##teamcity[testStarted name = '"
            + escape(testCaseName)
            + " ("
            + getFileName(event)
            + ")']");
  }

  private void handleTestCaseFinished(TestCaseFinished event) {

    if (event.getResult().getStatus().is(Status.PASSED)) {
      System.out.println(
          "##teamcity[testFinished name = '"
              + escape(testCaseName)
              + " ("
              + getFileName(event)
              + ")']");
    } else if (event.getResult().getStatus().is(Status.SKIPPED)) {
      System.out.println(
          "##teamcity[testIgnored name = '"
              + escape(testCaseName)
              + " ("
              + getFileName(event)
              + ")']");
    } else {
      System.out.println(
          "##teamcity[testFailed name = '"
              + escape(testCaseName)
              + " ("
              + getFileName(event)
              + ")']");
    }
  }

  private String getUniqueTestNameForScenarioExample(String testCaseName, int exampleNumber) {
    return testCaseName + (testCaseName.contains(" ") ? " " : "_") + exampleNumber;
  }

  private String getFileName(TestCaseEvent event) {
    return escape(FilenameUtils.getBaseName(String.valueOf(event.getTestCase().getUri())));
  }

  private String escape(String source) {
    if (source == null) {
      return "";
    }
    return source
        .replace("|", "||")
        .replace("'", "|'")
        .replace("\n", "|n")
        .replace("\r", "|r")
        .replace("[", "|[")
        .replace("]", "|]");
  }
}
