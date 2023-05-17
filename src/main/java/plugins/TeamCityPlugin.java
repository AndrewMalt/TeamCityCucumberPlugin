package plugins;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.net.URI;

public class TeamCityPlugin implements EventListener {

  private URI currentFeatureFile = null;
  private String previousTestCaseName;
  private int exampleNumber;
  private String testCaseName;

  //  private String

  @Override
  public void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
    publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
  }

  private void handleTestCaseStarted(TestCaseStarted event) {

    getFileName(event);

    testCaseName = event.getTestCase().getName();
    String fullPath = event.getTestCase().getUri().getRawPath();

    //    Path p = Path.of(fullPath);
    //    System.out.println("-----------=-=------------------>> " + p.getFileName());
    File f = new File(event.getTestCase().getUri());
    //    System.out.println("-----------=-=------------------>> " + f.getName().replace(".feature",
    // ""));
    String path;
    try {
      path = fullPath.substring(fullPath.indexOf("src"));
    } catch (StringIndexOutOfBoundsException e) {
      path = fullPath;
    }
    //    String path = fullPath.substring(fullPath.indexOf("qqq"));

    String filePath = event.getTestCase().getUri().getPath();
    String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
    //    System.out.println("==================> " + fileName);

    //    System.out.println("-----p----->> > > " + path);
    //    System.out.println("-----path----->> > > " + event.getTestCase().getUri().getPath());
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
