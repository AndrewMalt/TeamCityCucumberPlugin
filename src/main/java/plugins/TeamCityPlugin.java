package plugins;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.Event;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TeamCityPlugin implements EventListener {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

  @Override
  public void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
    publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
  }

  private void handleTestCaseStarted(TestCaseStarted event) {
    System.out.println("##teamcity[testStarted name = '" + event.getTestCase().getName() + "' timestamp = '" + extractTimeStamp(event) + "']");
  }

  private void handleTestCaseFinished(TestCaseFinished event) {
    if (event.getResult().getStatus().is(Status.PASSED)) {
//      System.out.println("##teamcity[testStarted name = '" + event.getTestCase().getName() + " " + event.getTestCase().getId() + "']");
//      System.out.println("##teamcity[testStarted name = '" + event.getTestCase().getName() + "']");
//      System.out.println("##teamcity[testFinished name = '" + event.getTestCase().getName() + " " + event.getTestCase().getId() + "']");
      System.out.println("##teamcity[testFinished name = '" + event.getTestCase().getName() + "' timestamp = '" + extractTimeStamp(event) + "']");
    } else {
//      System.out.println("##teamcity[testStarted name = '" + event.getTestCase().getName() + " " + event.getTestCase().getId() + "']");
//      System.out.println("##teamcity[testStarted name = '" + event.getTestCase().getName() + "']");
//      System.out.println("##teamcity[testFailed name = '" + event.getTestCase().getName() + " " + event.getTestCase().getId() + "']");
      System.out.println("##teamcity[testFinished name = '" + event.getTestCase().getName() + "' timestamp = '" + extractTimeStamp(event) + "']");
//      System.out.println("##teamcity[testFailed name = '" + event.getTestCase().getName() + "' timestamp = '" + extractTimeStamp(event) + "']");
    }
//    System.out.println("------STATUS---->>> " + event.getResult().getStatus());
  }

  private String extractTimeStamp(Event event) {
    ZonedDateTime date = event.getInstant().atZone(ZoneOffset.UTC);
    return DATE_FORMAT.format(date);
  }
}
