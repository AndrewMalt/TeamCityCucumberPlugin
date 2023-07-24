package steps;

import static core.api.TagCollectorPlugin.*;

import core.api.Tag;
import java.util.*;
import java.util.stream.Collectors;

public class TestStepsForResearch {

  public static void main(String[] args) {
    //    Properties properties = new Properties();
    //    properties.setProperty("qwe", "1qwede");
    //    System.out.println("----> " + properties.get("qwe"));
    final TreeSet<String> endpointCollection2 = new TreeSet<>();
    final int num = 2;

    endpointCollection2.add("qwe");

    System.out.println("---------------------<<" + endpointCollection2);

    Collection<String> strList = new ArrayList<>();

    strList.add("@quQiw");
    strList.add("@mix");
    strList.add("@mix");
    strList.add("@api");
    strList.add("@qwWe");
    System.out.println(Tag.valueOf("UI").getName());
    System.out.println(Arrays.toString(Tag.values()));
    System.out.println(Arrays.toString(Tag.values()));
    List<String> list =
        Arrays.stream(strList.toArray())
            .map(s -> s.toString().toLowerCase())
            .collect(Collectors.toList());
    System.out.println(list);
    Arrays.stream(Tag.values())
        .forEach(
            tag -> {
              if (strList.contains(tag.getName().toLowerCase())) {
                tag.incTag();
              }
            });
    System.out.println("----uiNum---> " + uiNum);
    System.out.println("----apiNum---> " + apiNum);
    System.out.println("----mixNum---> " + mixNum);
    System.out.println("----total---> " + total);

    //    System.out.println(strList.stream().anyMatch(s ->
    // s.toLowerCase().equals(Tag.MIX.name())));

  }
}
