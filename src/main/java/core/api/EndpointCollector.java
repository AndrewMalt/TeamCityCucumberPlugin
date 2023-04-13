package core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.cucumber.java.AfterAll;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

public class EndpointCollector {

  private static EndpointCollector instance;
  private final TreeSet<String> endpointCollection = new TreeSet<>();

  private EndpointCollector() {}

  public static EndpointCollector getInstance() {
    if (instance == null) {
      instance = new EndpointCollector();
    }
    return instance;
  }

  public void addEndPoint(String endpoint) {
    URI uri;
    try {
      uri = new URI(endpoint);
      URI uri2 =
          URI.create(
              new URI(
                      uri.getScheme(),
                      uri.getAuthority(),
                      StringUtils.removeEnd(uri.getPath(), "/"),
                      uri.getFragment())
                  .toString());
      endpointCollection.add(String.valueOf(uri2));
    } catch (URISyntaxException e) {
      System.out.println(e.getMessage());
    }
  }

  public void showEndpoint() {
    endpointCollection.forEach(System.out::println);
  }

  @AfterAll
  public static void tearDown() throws JsonProcessingException {
    EndpointCollector.getInstance().showEndpoint();
    System.out.println("--->> Json:");
    ObjectWriter ow =
        new ObjectMapper().findAndRegisterModules().writer().withDefaultPrettyPrinter();
    EndpointBody endpointBody = new EndpointBody();
    endpointBody.setEndpoints(EndpointCollector.getInstance().endpointCollection);
    endpointBody.setDate((LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    String json = ow.writeValueAsString(endpointBody);
    System.out.println(json);
    System.out.println("--->> Finish!");
  }
}
