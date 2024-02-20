package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter htmlReporter;
    private static final String REPORT_PATH = "reports/";
    private static final String NAME_REPORT = "ExtentReport";

    public static void initializeReport() {
        htmlReporter = new ExtentSparkReporter(REPORT_PATH + NAME_REPORT + dateFormat() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }

    public static void flushReport() {
        extent.flush();
    }

    private static String dateFormat(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return localDateTime.format(formatter);
    }
}
