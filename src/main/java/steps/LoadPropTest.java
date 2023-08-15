package steps;

import com.typesafe.config.ConfigFactory;

import java.util.Arrays;
import java.util.List;

public class LoadPropTest {

//    public static void main(String[] args) {
////        ConfigFactory.load();
////        System.setProperty("config.file", "src/main/resources/url.conf");
//        System.out.println("----->> " + System.getProperty("config.resource"));
//
//        System.setProperty("config.resource", "url.conf");
//        System.out.println("----->> " + System.getProperty("config.resource"));
//        System.out.println("----->> " + ConfigFactory.load().getString("user1.login"));
//        System.out.println("----->> " + ConfigFactory.load().getString("url"));
//        System.out.println("----->> " + ConfigFactory.load().getString("url2.stand2"));
//        System.out.println("----->> " + System.getProperty("user1.login"));
//    }

    public static void main(String[] args) {
        String str = "1111 123 43CH=2 3CH=45 345CH= 654333   CH= 33";
        List<String> list = Arrays.asList(str.split(" "));
        System.out.println(list.size());
//        list.remove(1);
//        list.subList(0, 1).clear();
        list.forEach(System.out::println);
        System.out.println("--------->> " + list.get(0));
    }
}
