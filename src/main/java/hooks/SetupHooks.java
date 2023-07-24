package hooks;

import helpers.VarStore;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import java.util.Map;

public class SetupHooks {

  public static Map<String, Object> varStore;

  @BeforeAll
  public static void setUp2() {}

  //  @Before
  //  public void bef(Scenario scenario) {
  ////    System.out.println("=----------------bef-------------=-=== " +
  // scenario.getSourceTagNames());
  //
  //  }

  @Before
  public static void setup() {
    System.out.println("--->> setup (Before)");
    //    Feature feature;
    //    feature.
    //    Scenario scenario = null;
    //    scenario.getSourceTagNames();
    //        System.setProperty("--plugin", "core.api.TagCollectorPlugin");
    //    System.getProperties().list(System.out);
    //    System.setProperty("--plugin", "core.api.TagCollectorPlugin");
    VarStore.getInstance();
  }

  @After
  public static void tearDown() {
    System.out.println("--->> tearDown");
  }
}
