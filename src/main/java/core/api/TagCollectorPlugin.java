package core.api;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

// import static omg.path.Drinkver.SumQwer;

public class TagCollectorPlugin /*implements EventListener*/ {

  public static int total = 0;
  public static int uiNum = 0;
  public static int apiNum = 0;
  public static int mixNum = 0;

  //  @Override
  //  public synchronized void setEventPublisher(EventPublisher publisher) {
  //    publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
  //  }

  //  @Before
  //  private void handleTestCaseStarted(TestCaseStarted event) {
  //    System.out.println("B E F O R E - B E F O R E - B E F O R E - B E F O R E");
  //    total++;
  //    System.out.println("---------tags--------->> " + event.getTestCase().getTags());
  //    System.out.println("------api---->>>>> " + event.getTestCase().getTags().contains("api"));
  //    System.out.println("------@api--->>>>> " + event.getTestCase().getTags().contains("@api"));
  //    if (event.getTestCase().getTags().contains("@api")) {
  //      apiNum++;
  //    }
  //    if (event.getTestCase().getTags().contains("@ui")) {
  //      uiNum++;
  //    }
  //    if (event.getTestCase().getTags().contains("@mix")) {
  //      mixNum++;
  //    }
  //  }

  @Before
  public void tagCheck(Scenario scenario) {
    //    SumQwer();
    System.out.println("!! ! ! ! !  @!@ ! ! @ private void tagCheck(Scenario scenario)");
    total++;
    if (scenario.getSourceTagNames().contains("@api")) {
      System.out.println("------> " + "apiNum++;");
      apiNum++;
    }
    if (scenario.getSourceTagNames().contains("@ui")) {
      uiNum++;
    }
    if (scenario.getSourceTagNames().contains("@mix")) {
      mixNum++;
    }
  }
}
