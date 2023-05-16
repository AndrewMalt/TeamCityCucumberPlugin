package core.api;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

public class EndpointCollector implements EventListener {

  Scenario scenario;

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
    //    System.out.println("---------tags--------->> " + scenario.getSourceTagNames());
    //    scenario.getSourceTagNames();
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
  public static void tearDown() throws IOException {
    EndpointCollector.getInstance().showEndpoint();
    System.out.println("--->> Json:");
    DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
    prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
    ObjectWriter ow = new ObjectMapper().findAndRegisterModules().writer(prettyPrinter);

    EndpointBody endpointBody = new EndpointBody();
    endpointBody.setEndpoints(EndpointCollector.getInstance().endpointCollection);
    endpointBody.setDate((LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    endpointBody.setTotalTestCases(String.valueOf(TagCollectorPlugin.total));
    endpointBody.setApi(String.valueOf(TagCollectorPlugin.apiNum));
    endpointBody.setUi(String.valueOf(TagCollectorPlugin.uiNum));
    endpointBody.setMix(String.valueOf(TagCollectorPlugin.mixNum));
    String json = ow.writeValueAsString(endpointBody);

    System.out.println("--------DIR->>> " + System.getProperty("user.dir"));
    ow.writeValue(
        new File(System.getProperty("user.dir") + "/src/main/resources/endpoints.json"),
        endpointBody);
    System.out.println(json);
    System.out.println("--->> Finish!");
  }

  @Override
  public void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
  }

  private void handleTestCaseStarted(TestCaseStarted event) {
    System.out.println("---------tags321321--------->> " + event.getTestCase().getTags());
  }
}
