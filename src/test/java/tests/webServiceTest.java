package tests;

import api_methods.api_methods;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utilities.AssertionManager;
import utilities.ConfigProperties;
import utilities.DataGenerator;
import utilities.ReportManager;

import java.util.HashMap;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class webServiceTest {

    @BeforeAll
    static void setup() {
        ReportManager.initializeReport();
    }

    @AfterEach
    void cleanup() {
        ReportManager.flushReport();
    }


    /**
     * Search for a specific user by ID
     */
    @Test
    @Order(1)
    void searchSingleUserTest() {
        ExtentTest extentTest = ReportManager.createTest("VALIDATE SEARCH USER BY ID");
        AssertionManager assertionManager = new AssertionManager(extentTest);
        extentTest.info("Test initialized");
        Response response = api_methods.getMethod(
                ConfigProperties.getProperty("Url.EndPoint"),
                ConfigProperties.getProperty("Search.SingleUser"));
        extentTest.info("Executed service");
        assertionManager.hardAssertEquals(response.getStatusCode(), 200);
        assertionManager.hardAssertTrue("Charles".equals(response.getBody().jsonPath().get("data.first_name")));
        assertionManager.hardAssertTrue("charles.morris@reqres.in".equals(response.getBody().jsonPath().get("data.email")));
        extentTest.info("Service response: \n" + response.getBody().prettyPrint());
        extentTest.info("Test completed");
    }

    /**
     * Create a new user
     */
    @Test
    @Order(2)
    void createUserTest() {
        ExtentTest extentTest = ReportManager.createTest("VALIDATE USER CREATION");
        AssertionManager assertionManager = new AssertionManager(extentTest);
        String nameUser = DataGenerator.randomName();
        extentTest.info("Test initialized");
        extentTest.info("Preparing body JSON");
        HashMap<Object, Object> data = new HashMap<>();
        data.put("name", nameUser);
        data.put("job", DataGenerator.randomJob());
        extentTest.info("Json ready: " + data);
        Response response = api_methods.postMethod(
                ConfigProperties.getProperty("Url.EndPoint")
                        + ConfigProperties.getProperty("Path.User"), data);
        extentTest.info("Executed service");
        assertionManager.hardAssertEquals(response.getStatusCode(), 201);
        extentTest.info("Service response: \n" + response.getBody().prettyPrint());
        assertionManager.softAssertTrue(nameUser.equals(response.getBody().jsonPath().get("name")));
        assertionManager.assertAllSoftAssertions();
        extentTest.info("Test completed");
    }

    /**
     * Update a user by searching for his ID
     */
    @Test
    @Order(3)
    void updateUserTest() {
        ExtentTest extentTest = ReportManager.createTest("VALIDATE USER UPDATE");
        AssertionManager assertionManager = new AssertionManager(extentTest);
        extentTest.info("Test initialized");
        extentTest.info("Preparing body JSON");
        HashMap<Object, Object> data = new HashMap<>();
        String nameUser = DataGenerator.randomName();
        data.put("name", nameUser);
        data.put("job", DataGenerator.randomJob());
        extentTest.info("Json ready: " + data);
        Response response = api_methods.putMethod(
                ConfigProperties.getProperty("Url.EndPoint")
                        + ConfigProperties.getProperty("Path.User"), "/2", data);
        extentTest.info("Executed service");
        assertionManager.hardAssertEquals(response.getStatusCode(), 200);
        extentTest.info("Service response: \n" + response.getBody().prettyPrint());
        assertionManager.softAssertTrue(nameUser.equals(response.getBody().jsonPath().get("name")));
        extentTest.info("Test completed");
    }

    /**
     * Login to the service
     */
    @Test
    @Order(4)
    void loginUserTest() {
        ExtentTest extentTest = ReportManager.createTest("VALIDATE LOGIN");
        AssertionManager assertionManager = new AssertionManager(extentTest);
        extentTest.info("Test initialized");
        HashMap<Object, Object> data = new HashMap<>();
        data.put("email", ConfigProperties.getProperty("email"));
        data.put("password", ConfigProperties.getProperty("password"));
        Response response = api_methods.postMethod(
                ConfigProperties.getProperty("Url.EndPoint")
                        + ConfigProperties.getProperty("Path.Login"), data);
        extentTest.info("Executed service");
        assertionManager.hardAssertEquals(response.getStatusCode(), 200);
        extentTest.info("Service response: \n" + response.getBody().prettyPrint());
        assertionManager.softAssertTrue(response.getBody().jsonPath().get("token") != "");
        extentTest.info("Test completed");
    }
}
