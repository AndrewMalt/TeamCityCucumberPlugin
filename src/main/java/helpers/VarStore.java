package helpers;

import java.util.HashMap;
import java.util.Map;

public class VarStore {

  private static VarStore instance;
  private final Map<String, Object> varStore = new HashMap<>();

  private VarStore() {}

  public static synchronized VarStore getInstance() {
    if (instance == null) {
      instance = new VarStore();
    }
    return instance;
  }

  public void setVar(String name, Object var) {
    varStore.put(name, var);
  }

  public Object getVar(String name) {
    return varStore.get(name);
  }
}
