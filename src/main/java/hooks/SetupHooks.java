package hooks;

import helpers.VarStore;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.util.Map;

public class SetupHooks {

  public static Map<String, Object> varStore;

  @Before
  public static void setup() {
    System.out.println("--->> setup (Before)");
    VarStore.getInstance();
  }

  @After
  public static void tearDown() {
    System.out.println("--->> tearDown");
  }
}
