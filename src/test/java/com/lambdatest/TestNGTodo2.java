package com.lambdatest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTodo2 {

    private RemoteWebDriver driver;
    private String Status = "failed";
    private static final String CSV_FILE = "ritam.csv";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = "shubhamr";
        String authkey = "dl8Y8as59i1YyGZZUeLF897aCFvIDmaKkUU1e6RgBmlgMLIIhh";
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");
        caps.setCapability("tunnel", true);
        caps.setCapability("network", true);

        String[] Tags = new String[]{"Feature", "Magicleap", "Severe"};
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }

    @Test
    public void basicTest() throws InterruptedException {
        System.out.println("Loading Url");
        // Modify the Python file
         // Define the line number to modify
       // PythonFileModifier.modifyLineInFile("api_url", "data_to_mock_value");
        PythonFileModifier.modifyLineInFile(" ","Server","ritam1.com");
        Thread.sleep(10000);
        driver.get("https://lambdatest.com/");
        Thread.sleep(15000);
        Status = "passed";
        System.out.println("Test Finished");


    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }
}

//class PythonFileModifier {
//
//    int lineNumber = 7;
//
//    public static void modifyLineInFile(String api_url, String request_to_mock,String mock_value) {
//        String filePath = "ritam.py"; // Define your file path here
//        int lineNumber_mock_dta = 7; // Define your line number here
//        String newContent = "data_to_mock" + " = '" + mock_value + "'";
//        String newContent1 = "api_url" + " = '" + api_url + "'";
//        int lineNumber_mock_api_url = 5;
//        String newContent2 = "request_to_mock" + " = '" + request_to_mock + "'";
//        int lineNumber_request_to_mock = 6;
//         // Define your line number here
//
//        try {
//            List<String> lines = Files.readAllLines(Paths.get(filePath));
//            // Replace the specific line
//            lines.set(lineNumber_mock_dta - 1, newContent);
//            lines.set(lineNumber_mock_api_url - 1, newContent1);
//            lines.set(lineNumber_request_to_mock - 1, newContent2);
//            // Write back to the file
//            Files.write(Paths.get(filePath), lines);
//
//            System.out.println("Line " + api_url + " setted successfully.");
//
//        } catch (IOException e) {
//            System.err.println("An error occurred: " + e.getMessage());
//        }
//    }
//}
