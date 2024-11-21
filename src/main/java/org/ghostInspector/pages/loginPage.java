package org.ghostInspector.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage extends basePage {
     WebDriver driver;



    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "input-email")
    private WebElement emailTextBox;

    @FindBy(id = "input-password")
    private WebElement passwordTextBox;

    @FindBy(id = "btn-login")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@class='alert-content']/span")
    private WebElement errorMsg;

   public void enterLoginEmail(String email) {
        emailTextBox.sendKeys(email);
    }

    public void enterPassword(String psd) {
        passwordTextBox.sendKeys(psd);
    }

    public void clickLoginBtn() {
        signInButton.click();
    }

    public String getErrorMsg(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.visibilityOf(errorMsg));
        String ErrMsg = errorMsg.getText();
       return ErrMsg;
    }



}
