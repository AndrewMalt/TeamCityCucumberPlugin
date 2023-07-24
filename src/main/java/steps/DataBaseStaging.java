package steps;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataBaseStaging {

  public static void main(String[] args) {

    DataSourceProperties dataSourceProperties = new DataSourceProperties();
    dataSourceProperties.setDriverClassName("org.h2.Driver");
    dataSourceProperties.setUrl("jdbc:h2:mem:myDb");
    dataSourceProperties.setUsername("sa");
    dataSourceProperties.setPassword("sa");

    DataSource dataSource =
        dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    //        jdbcTemplate.execute("SELECT * FROM employees e;");

    jdbcTemplate.execute(
        "CREATE TABLE Customers (\n"
            + "    PersonID int,\n"
            + "    LastName varchar(255),\n"
            + "    FirstName varchar(255),\n"
            + "    Address varchar(255),\n"
            + "    City varchar(255)\n"
            + ");");
    jdbcTemplate.execute(
        "INSERT INTO Customers (PersonID, LastName, FirstName, Address, City)\n"
            + "        VALUES (1, 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006');");
    jdbcTemplate.execute(
        "INSERT INTO Customers (PersonID, LastName, FirstName, Address, City)\n"
            + "        VALUES (2, 'To2sen', 'S21', 'Sta2ger', '426');");
    String str2 =
        StringEscapeUtils.escapeSql(
            "INSERT INTO Customers (PersonID, LastName, FirstName, Address, City)\n"
                + "        VALUES (2, 'To2sen', 'S21', 'Sta2ger', '426');");
    jdbcTemplate.execute(str2);
    //        String str = ESAPI.encoder().encodeForSQL( new OracleCodec(), "INSERT INTO Customers
    // (PersonID, LastName, FirstName, Address, City)\n" +
    //                "        VALUES (2, 'To2sen', 'S21', 'Sta2ger', '426');" );
    //        System.out.println("---------> " + str);
    //        PreparedStatement statement = (PreparedStatement) jdbcTemplate;
    //        statement.

    //        System.out.println(jdbcTemplate.queryForList("SELECT * FROM ? e;", "Customers"));
    System.out.println(jdbcTemplate.queryForList("SELECT * FROM Customers e;"));
    //        PreparedStatementSetter setter = ps -> ps.setString(1, "qwe");
    //        System.out.println("--------setter------->>> " + setter);

    //        jdbcTemplate.queryForList("SELECT * FROM Customers e;", setter);
    System.out.println("======================");
    System.out.println(str2);
    //        jdbcTemplate.queryForList("SELECT * FROM Customers
    // e;").get(0).keySet().forEach(System.out::print);

  }
}
