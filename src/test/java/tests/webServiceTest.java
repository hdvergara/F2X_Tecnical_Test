package tests;

import api_methods.api_methods;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.AssertionManager;
import utilities.ReportManager;

import java.util.HashMap;

public class webServiceTest {

    @BeforeAll
    static void setup() {
        ReportManager.initializeReport();
    }

    @AfterEach
    void cleanup() {
        ReportManager.flushReport();
    }

    private static final String END_POINT = "https://reqres.in/api/";
    private static final String PATH_USER = "users";
    private static final String SEARCH_SINGLE_USER = "users?id=5";

    /**
     *
     */
    @Test
    void searchSingleUserTest() {
        ExtentTest extentTest = ReportManager.createTest("Validate simple user search");
        AssertionManager assertionManager = new AssertionManager(extentTest);
        extentTest.info("Test initialized");
        Response response = api_methods.getMethod(END_POINT, SEARCH_SINGLE_USER);
        extentTest.info("Executed service");
        assertionManager.hardAssertEquals(response.getStatusCode(), 200);
        assertionManager.hardAssertTrue("Charles".equals(response.getBody().jsonPath().get("data.first_name")));
        assertionManager.hardAssertTrue("charles.morris@reqres.in".equals(response.getBody().jsonPath().get("data.email")));
        extentTest.info("Service response: \n" + response.getBody().prettyPrint());
        extentTest.info("Test completed");
    }

    @Test
    void createUserTest() {
        ExtentTest extentTest = ReportManager.createTest("Validate user creation");
        AssertionManager assertionManager = new AssertionManager(extentTest);
        Faker faker = new Faker();
        String nameUser = faker.name().firstName();
        extentTest.info("Test initialized");
        extentTest.info("Preparing body JSON");
        HashMap<Object, Object> data = new HashMap<>();
        data.put("name",nameUser);
        data.put("job",faker.job().position());
        extentTest.info("Json ready: " + data);
        Response response = api_methods.postMethod(END_POINT + PATH_USER, data);
        extentTest.info("Executed service");
        assertionManager.hardAssertEquals(response.getStatusCode(), 201);
        assertionManager.hardAssertTrue(nameUser.equals(response.getBody().jsonPath().get("name")));
        extentTest.info("Test completed");
    }

    @Test
    void updateUserTest() {
        ExtentTest extentTest = ReportManager.createTest("Validate user update");
        AssertionManager assertionManager = new AssertionManager(extentTest);
        extentTest.info("Test initialized");
        extentTest.info("Preparing body JSON");
        Faker faker = new Faker();
        String nameUser = faker.name().firstName();
        extentTest.info("Preparing body JSON");
        HashMap<Object, Object> data = new HashMap<>();
        data.put("name",nameUser);
        data.put("job",faker.job().position());
        extentTest.info("Json ready: " + data);
        /*Response response = api_methods.putMethod(END_POINT + PATH_USER + "/", "2", jsonOutput);
        extentTest.info("Executed service");
        assertionManager.hardAssertEquals(response.getStatusCode(), 200);
        extentTest.info("Test completed");*/
    }

}
