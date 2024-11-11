package org.ghostInspector.tests;

import org.ghostInspector.utils.WebDriverFactory;
import org.ghostInspector.pages.homePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class homePageTest {

    private WebDriver driver;
    private homePage homePG;

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = WebDriverFactory.getDriver();
        driver.get("https://ghostinspector.com/");
       // driver.manage().window().maximize();

        driver.manage().wait(20000);
        homePG = new homePage(driver);
    }

    @Test
    public void testHomePageTitle() {
        // Validate the page title
        String expectedTitle = "Ghost Inspector - Automated Browser Testing";
        String actualTitle = homePG.getPageTitle();
        System.out.println("actualTitle: "+actualTitle);
        assertEquals(expectedTitle, actualTitle, "Page title does not match");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        WebDriverFactory.quitDriver();
    }
}
