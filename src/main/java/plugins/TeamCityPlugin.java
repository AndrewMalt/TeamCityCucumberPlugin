package plugins;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import java.net.URI;

public class TeamCityPlugin implements EventListener {

//  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
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
    System.out.println("##teamcity[testStarted name = '" + testCaseName);
  }

  private void handleTestCaseFinished(TestCaseFinished event) {
    if (event.getResult().getStatus().is(Status.PASSED)) {
      System.out.println("##teamcity[testFinished name = '" + testCaseName);
    } else {
      System.out.println("##teamcity[testFailed name = '" + testCaseName);
//      System.out.println("##teamcity[testFailed name = '" + event.getTestCase().getName() + "' timestamp = '" + extractTimeStamp(event) + "']");
    }
  }

//  private String extractTimeStamp(Event event) {
//    ZonedDateTime date = event.getInstant().atZone(ZoneOffset.UTC);
//    return DATE_FORMAT.format(date);
//  }

  private static String getUniqueTestNameForScenarioExample(String testCaseName, int exampleNumber) {
    return testCaseName + (testCaseName.contains(" ") ? " " : "_") + exampleNumber;
  }
}
