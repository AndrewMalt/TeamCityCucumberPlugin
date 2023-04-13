package helpers;

public class PropertyLoader {

  public static String loadProperty(String propertyName) {
    String value = System.getProperty(propertyName);
    return value != null ? value : propertyName;
  }
}
