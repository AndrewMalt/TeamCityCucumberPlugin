package steps;

import java.io.IOException;

public class WriteFile {
  public static void main(String[] args) throws IOException {

    System.out.println("-----------> " + System.getProperty("sad"));
    System.out.println("-----------> " + System.getProperty("qwer"));
    System.setProperty("qwer", "123444444");
    System.out.println("-----------> " + System.getProperty("qwer"));

    System.out.println("-----------> " + getProp("sad"));
    System.out.println("-----------> " + getProp("qwer"));
    System.setProperty("qwer", "123444444");
    System.out.println("-----------> " + getProp("qwer"));
  }

  public static String getProp(String name) {
    //    System.
    //    System.getenv()
    return System.getProperty(name) == null ? "T_T" : System.getProperty(name);
  }
  //    public static void main(String[] args) throws IOException {
  //
  ////        PreparedStatement
  //
  //        String query = "INSERT INTO qw_e.asd(id, name) VALUES('12313dwewf', 'Тесттест');";
  //        System.out.println(query);
  ////        System.out.println(ESAPI.encoder().encodeForSQL(new
  // MySQLCodec(MySQLCodec.Mode.STANDARD), query));
  //        String escapedSQL = StringEscapeUtils.escapeSql(query);
  //        System.out.println(escapedSQL);
  ////        System.out.println(ESAPI.encoder().encodeForSQL(new MySQLCodec(MySQLCodec.Mode.ANSI),
  // query));
  //
  //
  ////        Path path =
  // Path.of("/src/main/resources/writeFile.json").normalize().toAbsolutePath();
  ////        String path2 = new File(".").getCanonicalPath();
  ////        Path path3 = Path.of(".");
  ////        System.out.println("------->> " + path2);
  ////        System.out.println("---path---->> " + path.getRoot());
  ////        System.out.println("------->> " + path3.getRoot());
  ////        System.out.println("------->> " + System.getProperty("user.dir"));
  //
  ////       File file = new File(path.toUri());
  ////       File file = new File(System.getProperty("user.dir") +
  // "/src/main/resources/writeFile.json");
  ////        System.out.println(file.createNewFile());
  ////       file.createNewFile();
  //    }
}
