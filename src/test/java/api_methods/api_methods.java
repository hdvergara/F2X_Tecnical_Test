package api_methods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class api_methods {

    /**
     * @param endPoint Service Url
     * @param parameter Parameter to be searched
     * @return Service execution response
     */
    public static Response getMethod(String endPoint, String parameter) {
        RestAssured.baseURI = endPoint;
        return RestAssured.get(parameter);
    }

    /**
     * Execute a Post method Rest service
     *
     * @param endpoint    Service Url
     * @param bodyRequest Body that is sent in the request
     * @return Service execution response
     */
    public static Response postMethod(String endpoint, String bodyRequest) {
        RestAssured.baseURI = endpoint;
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyRequest)
                .post();
    }

}
