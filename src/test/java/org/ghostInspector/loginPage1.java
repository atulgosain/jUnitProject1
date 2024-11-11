package org.ghostInspector;



import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class loginPage1 {
    public loginPage1() {
    }

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
        Thread.sleep(2000L);
        WebElement formAuthen = driver.findElement(By.xpath("//a[normalize-space()='Form Authentication']"));
        formAuthen.click();
        Thread.sleep(200L);
        WebElement userName = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement loginBtn = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        userName.sendKeys(new CharSequence[]{"tomsmith"});
        password.sendKeys(new CharSequence[]{"SuperSecretPassword!"});
        loginBtn.click();
        WebElement secureArea = driver.findElement(By.xpath("//div[@id='flash']"));
        Assertions.assertTrue(secureArea.getText().contains("You logged into a secure area"));
    }
}

