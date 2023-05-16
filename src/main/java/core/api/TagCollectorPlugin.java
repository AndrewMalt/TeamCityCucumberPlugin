package core.api;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;

public class TagCollectorPlugin implements EventListener {

  public static int total = 0;
  public static int uiNum = 0;
  public static int apiNum = 0;
  public static int mixNum = 0;

  @Override
  public synchronized void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
  }

  private synchronized void handleTestCaseStarted(TestCaseStarted event) {
    total++;
    System.out.println("---------tags--------->> " + event.getTestCase().getTags());
    System.out.println("------api---->>>>> " + event.getTestCase().getTags().contains("api"));
    System.out.println("------@api--->>>>> " + event.getTestCase().getTags().contains("@api"));
    if (event.getTestCase().getTags().contains("@api")) {
      apiNum++;
    }
    if (event.getTestCase().getTags().contains("@ui")) {
      uiNum++;
    }
    if (event.getTestCase().getTags().contains("@mix")) {
      mixNum++;
    }
  }
}
