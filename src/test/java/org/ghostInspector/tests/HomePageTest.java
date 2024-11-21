package org.ghostInspector.tests;

import org.apache.commons.io.FileUtils;
import org.ghostInspector.pages.loginPage;
import org.ghostInspector.utils.WebDriverFactory;
import org.ghostInspector.pages.homePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest {

    static WebDriver driver;
    static homePage homePG;
    static loginPage loginPG;

    @BeforeAll
     static void setUp() throws InterruptedException {
        driver = WebDriverFactory.getDriver();
        driver.get("https://ghostinspector.com/");
        driver.manage().window().maximize();
      //  driver.manage().wait(20000);
        homePG = new homePage(driver);
        loginPG = new loginPage(driver);
    }

    @Test
    @Order(1)
    public void testHomePageTitle() {
        // Validate the page title
        String expectedTitle = "Ghost Inspector - Automated Browser Testing";
        String actualTitle = homePG.getPageTitle();
        System.out.println("actualTitle: "+actualTitle);
        takeScreenShot("titleCheck");
        assertEquals(expectedTitle, actualTitle, "Page title does not match");

        //test the window of Feature link
        WebElement featurelink = driver.findElement(By.linkText("Features")) ;
        Actions action = new Actions(driver);

        action.moveToElement(featurelink).perform();

// Find all iframes on the page
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));

        // Print the number of frames found
        System.out.println("Total number of frames: " + frames.size());

        // Print the name or ID of each frame
        for (WebElement frame : frames) {
         //   String nameOrId = frame.getAttribute("name") != null ? frame.getAttribute("name") : frame.getAttribute("id");
          //  System.out.println("Frame Name/ID: " + nameOrId);
        int i = 0;
            // Get the XPath
            String xpath = generateXPath(frame, "");
            System.out.println("Frame " + (i + 1) + " XPath: " + xpath);
        }

////*[@id="menu-item-600"]/div/div/section/div/div/div/section[1]/div/div[2]/div/div/div/p

        Set<String> allWindows = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();

        for (String window : allWindows){
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
            }System.out.println("allWindows.size(): "+allWindows.size());
        }
        //div[class='elementor-element elementor-element-b25d7c3 elementor-widget elementor-widget-text-editor']
        ////div[@class='make-column-clickable-elementor elementor-column elementor-col-50 elementor-inner-column elementor-element elementor-element-de785d5']//div[@class='elementor-widget-wrap elementor-element-populated']
///html/body/div[2]/div/section[1]/div/div[2]/div/div/div/div/div[1]/ul/li[1]/div/div/section/div/div/div/section[1]/div/div[2]/div/div/div/p/strong
////*[@id="menu-item-600"]/div/div/section/div/div/div/section[1]/div/div[2]/div/div/div/p/strong
    }

    @ParameterizedTest
    @CsvSource({
                    "invalidUser@gmail.com, validPassword, false",// Negative case: invalid username
                  "atul.gosain2005@gmail.com, Asdf@1234, true"   // Positive case
                   /*  "validUser, invalidPassword, false", // Negative case: invalid password
                    "invalidUser, invalidPassword, false" // Negative case: both invalid

                   */
            })
    @Test()
    @Disabled("Skipping the method execution")
    @Order(2)
    public void testLogin()
    {
        homePG.clickLoginLink();
       loginPG.enterLoginEmail("atul.gosain2005@gmail.com");
        loginPG.enterPassword("Asdf@1234");

       /*  loginPG.enterLoginEmail("");
        loginPG.enterPassword("");

        */

        loginPG.clickLoginBtn();
        String expectedErrMsg = "The provided credentials are incorrect.";
        String actualErrMsg =loginPG.getErrorMsg();
        System.out.println("Actual Login Error message: "+actualErrMsg);

        assertEquals(expectedErrMsg, actualErrMsg, "Login Error message doesn't match.");





    }

    @AfterAll
    static void tearDown() {
        // Close the browser after each test
       WebDriverFactory.quitDriver();
    }

    public void takeScreenShot(String fileName){
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenshot,new File("target/screenshots/"+fileName+".png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate XPath for an element
    private static String generateXPath(WebElement element, String current) {
        String tag = element.getTagName();
        String id = element.getAttribute("id");
        String className = element.getAttribute("class");

        // If the element has an ID, use it for XPath
        if (id != null && !id.isEmpty()) {
            return "//" + tag + "[@id='" + id + "']";
        }

        // If the element has a class, use it for XPath
        if (className != null && !className.isEmpty()) {
            return current + "//" + tag + "[@class='" + className + "']";
        }

        // Default to using tag name and index
        List<WebElement> siblings = element.findElements(By.xpath("../" + tag));
        int index = siblings.indexOf(element) + 1;
        return current + "//" + tag + "[" + index + "]";
    }
}



