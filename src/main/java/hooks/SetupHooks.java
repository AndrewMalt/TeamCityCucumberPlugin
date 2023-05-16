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

  @Before
  public static void setup() {
    System.out.println("--->> setup (Before)");
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
