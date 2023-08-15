package core.api;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import core.api.stat.v2.MetricDto;
import core.api.stat.v2.StatisticDto;
//import core.api.stat.v2.ValueDto;
import io.cucumber.java.AfterAll;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class EndpointCollector /* implements EventListener */ {

  //  Scenario scenario;

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
      //      StringBuilder builder = new StringBuilder().
      //      System.out.println("*******uri********** > " + uri);
      //      System.out.println("**********getFragment******* > " + uri.getFragment());
      //      System.out.println("*******getPath********** > " + uri.getPath());
      //      System.out.println("******getAuthority*********** > " + uri.getAuthority());
      //      System.out.println("********getScheme********* > " + uri.getScheme());
      //      System.out.println("******getHost*********** > " + uri.getHost());
      //      System.out.println("*********getUserInfo******** > " + uri.getUserInfo());
      //      System.out.println("**********normalize******* > " + uri.normalize());
      URI uri2 =
          URI.create(
              new URI(
                      uri.getScheme(),
                      //                      uri.getAuthority(),
                      StringUtils.removeEnd(uri.getPath(), "/"),
                      uri.getFragment())
                  .toString());
      //      endpointCollection.add(String.valueOf(uri2));
      endpointCollection.add(StringUtils.removeEnd(uri.getPath(), "/"));
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
    Tags tags = new Tags();
    tags.setMixT("qweqweqweqweq");
    endpointBody.setTags(tags);
    String json = ow.writeValueAsString(endpointBody);

    System.out.println("--------DIR->>> " + System.getProperty("user.dir"));
    ow.writeValue(
        new File(System.getProperty("user.dir") + "/src/main/resources/endpoints.json"),
        endpointBody);
    System.out.println(json);
    System.out.println("--->> Finish!");

    StatisticDto statisticDto = new StatisticDto();
    statisticDto.setReportDate(
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss.SSSSSS")));
    statisticDto.setReportType("end2end");
    statisticDto.setAppVersion(getProp("app_version"));
    statisticDto.setAppName(getProp("app_name"));
    List<MetricDto> listMetricDto = new ArrayList<>();

    listMetricDto.add(new MetricDto("count", String.valueOf(TagCollectorPlugin.total)));
    listMetricDto.add(new MetricDto("api", String.valueOf(TagCollectorPlugin.apiNum)));
    listMetricDto.add(new MetricDto("ui", String.valueOf(TagCollectorPlugin.uiNum)));
    listMetricDto.add(new MetricDto("mix", String.valueOf(TagCollectorPlugin.mixNum)));
    EndpointCollector.getInstance()
        .endpointCollection
        .forEach(tag -> listMetricDto.add(new MetricDto(tag)));
    statisticDto.setMetricDto(listMetricDto);
    ow.writeValue(
        new File(
            FilenameUtils.normalize(
                System.getProperty("user.dir") + "/src/main/resources/statistic.json")),
        statisticDto);
  }

  private static String getProp(String name) {
    return FilenameUtils.normalize(
        System.getProperty(name) == null ? "" : System.getProperty(name));
  }

  //  @Override
  //  public void setEventPublisher(EventPublisher publisher) {
  //    publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
  //  }
  //
  //  private void handleTestCaseStarted(TestCaseStarted event) {
  //    System.out.println("---------tags321321--------->> " + event.getTestCase().getTags());
  //  }
}
