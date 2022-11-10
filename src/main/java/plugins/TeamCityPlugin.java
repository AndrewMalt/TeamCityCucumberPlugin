package plugins;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseFinished;

public class TeamCityPlugin implements EventListener {

  @Override
  public void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
  }

  private void handleTestCaseFinished(TestCaseFinished event) {
    if (event.getResult().getStatus().is(Status.PASSED)) {
      System.out.println("##teamcity[testStarted name = '" + event.getTestCase().getName() + "']");
      System.out.println("##teamcity[testFinished name = '" + event.getTestCase().getName() + "']");
    } else {
      System.out.println("##teamcity[testStarted name = '" + event.getTestCase().getName() + "']");
      System.out.println("##teamcity[testFailed name = '" + event.getTestCase().getName() + "']");
    }
//    System.out.println("------STATUS---->>> " + event.getResult().getStatus());
  }
}
