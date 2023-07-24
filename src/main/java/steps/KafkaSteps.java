package steps;

import java.util.*;
import org.assertj.core.api.SoftAssertions;

public class KafkaSteps {

  public static void main(String[] args) {

    List<Map<String, String>> list = new ArrayList<>();
    Map<String, String> map1 = new HashMap<>();
    Map<String, String> map2 = new HashMap<>();
    Map<String, String> map3 = new HashMap<>();
    Map<String, String> map4 = new HashMap<>();
    SoftAssertions softAssertions = new SoftAssertions();

    map1.put("1", "qwe");
    map1.put("2", "asd");
    map1.put("3", "zxc");
    map1.put("4", "rty");
    map1.put("5", "fgh");

    map2.put("12", "qwe2");
    map2.put("22", "asd2");
    map2.put("32", "zxc2");
    map2.put("42", "rty2");
    map2.put("52", "fgh2");

    map3.put("13", "qwe3");
    map3.put("23", "asd3");
    map3.put("33", "zxc3");
    map3.put("43", "rty3");
    map3.put("53", "fgh3");

    map4.put("14", "qwe4");
    map4.put("24", "asd4");
    map4.put("34", "zxc4");
    map4.put("44", "rty4");
    map4.put("54", "fgh4");

    list.add(map1);
    list.add(map2);
    list.add(map3);
    list.add(map4);

    for (Map<String, String> m : list) {
      System.out.println("values -> " + m.values());
    }
    System.out.println(
        list.stream().anyMatch(m -> m.values().stream().anyMatch(v -> v.contains("rty47"))));
    //        list.stream().anyMatch(m -> m.values().stream().anyMatch(v -> v.contains("rty4")));

    //        list.stream().anyMatch(m -> l.)

  }
}
