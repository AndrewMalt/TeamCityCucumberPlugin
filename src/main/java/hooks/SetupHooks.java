package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class SetupHooks {

  @Before
  public void setup() {
    System.out.println("--->> setup (Before)");
  }

  @After
  public void tearDown() {
    System.out.println("--->> tearDown");
  }
}
