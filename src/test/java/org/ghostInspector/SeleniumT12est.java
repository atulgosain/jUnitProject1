package org.ghostInspector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumT12est {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set up WebDriver (make sure the path to chromedriver is correct)
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        // Set the path to your Chrome binary (if needed)
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

        // Assign the WebDriver instance to the class-level 'driver' variable
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testHomePage() {
        driver.get("https://www.saucedemo.com/");
        String title = driver.getTitle();
        System.out.println("Title is: "+title);
        //assertEquals("Swag Labs", title);
    }

    @AfterEach
    public void tearDown() {
        // Quit the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}


