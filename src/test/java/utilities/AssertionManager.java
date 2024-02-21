package utilities;

import com.aventstack.extentreports.ExtentTest;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AssertionManager {

    private final ExtentTest extentTest;
    private final SoftAssertions softAssertions;
    private static final String MESSAGE_FAIL_HARD_ASSERTION = "The condition to be compared was not matched";

    /**
     * @param extentTest
     */
    public AssertionManager(ExtentTest extentTest) {
        this.extentTest = extentTest;
        this.softAssertions = new SoftAssertions();
    }

    /**
     * @param condition
     */
    public void hardAssertTrue(boolean condition) {
        try {
            Assertions.assertThat(condition)
                    .as(MESSAGE_FAIL_HARD_ASSERTION)
                    .isTrue();
        } catch (AssertionError error) {
            extentTest.fail(MESSAGE_FAIL_HARD_ASSERTION + "- Failed: " + error.getMessage());
        }
    }
    public void hardAssertEquals(Object condition, Object expected) {
        try {
            Assertions.assertThat(condition)
                    .as(MESSAGE_FAIL_HARD_ASSERTION)
                    .isEqualTo(expected);
        } catch (AssertionError error) {
            extentTest.fail(MESSAGE_FAIL_HARD_ASSERTION + "- Failed: " + error.getMessage());
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
