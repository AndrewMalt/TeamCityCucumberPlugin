package steps;

import static io.restassured.RestAssured.given;

import core.api.EndpointCollector;
import helpers.VarStore;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class RestSteps {

  @Given(
      "^send (GET|POST|PUT|PATCH|DELETE) request on URL \"([^\"]*)\". Response saved in variable \"([^\"]*)\"$")
  public void sendGETRequestOnURLResponseSavedInVariable(
      String requestTypeString, String address, String varName, DataTable requestParams) {
    EndpointCollector.getInstance().addEndPoint(address);
    RequestSpecification reqSpec = given();
    System.out.println("--requestParams->> " + requestTypeString);
    System.out.println("--address->> " + address);
    reqSpec.request(Method.valueOf(requestTypeString), address);
    VarStore.getInstance().setVar("qwe", "qweq");
    requestParams.cells().forEach(System.out::println);
    //        reqSpec.
    //        requestParams.
  }

  public static void main(String[] args) {

    //        Response response = given()
    //        given()
    //                .request().get("https://642c18f5208dfe254727ed5e.mockapi.io/api/v1/animal")
    //                .then()
    //                .log().all()
    //                .assertThat()
    //                .statusCode(200);
    //
    //        Response response = given()
    //                .get("https://642c18f5208dfe254727ed5e.mockapi.io/api/v1/animal")
    //                .then()
    //                .log().all()
    //                .extract()
    //                .response();
    //       String name = response.getBody().jsonPath().get("[0].name");
    //        System.out.println("-------->>> " + name);
  }

  @And("say URL {string}")
  public void sayURL(String url) {
    EndpointCollector.getInstance().addEndPoint(url);
    System.out.println("------url------>>> " + url);
  }

  //    @Given("send (GET|POST|PUT|PATCH|DELETE) request on URL {string}. Response saved in variable
  // {string}")
  //    public void sendGETRequestOnURLResponseSavedInVariable(String arg0, String arg1) {
  //    }
}
