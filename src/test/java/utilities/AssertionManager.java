package utilities;

import com.aventstack.extentreports.ExtentTest;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AssertionManager {

    private final ExtentTest extentTest;
    private final SoftAssertions softAssertions;

    /**
     * @param extentTest
     */
    public AssertionManager(ExtentTest extentTest) {
        this.extentTest = extentTest;
        this.softAssertions = new SoftAssertions();
    }

    /**
     * @param condition
     * @param message
     */
    public void hardAssertTrue(boolean condition, String message) {
        try {
            Assertions.assertThat(condition)
                    .as(message)
                    .isTrue();
        } catch (AssertionError error) {
            extentTest.fail(message + "- Failed: " + error.getMessage());
        }
    }

    /**
     * @param condition
     * @param message
     */
    public void softAssertTrue(boolean condition, String message) {

        softAssertions.assertThat(condition)
                .as(message)
                .isTrue();
    }

    /**
     *
     */
    public void assertAllSoftAssertions() {

        try {
            softAssertions.assertAll();
        } catch (AssertionError e) {
            extentTest.fail("Soft assertions - Failed: " + e.getMessage());
            throw e;
        }
    }
}
