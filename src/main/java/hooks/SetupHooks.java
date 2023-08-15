package hooks;

import com.typesafe.config.ConfigFactory;
import helpers.VarStore;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import java.util.Map;

public class SetupHooks {

  public static Map<String, Object> varStore;
  public static final String DEFAULT_CONFIG = "url.conf";

  @BeforeAll
  public static void setUp2() {
    if (System.getProperty("config.resource") == null) {
      System.setProperty("config.resource", DEFAULT_CONFIG);
    }
  }

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
