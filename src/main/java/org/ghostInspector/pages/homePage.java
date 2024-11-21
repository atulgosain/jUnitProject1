package org.ghostInspector.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage{
    public homePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(linkText = "Features")
    private WebElement featuresLink;

    @FindBy(linkText = "Pricing")
    private WebElement pricingLink;

    @FindBy(linkText = "Login")
    private WebElement loginLink;

    @FindBy(css = "a.btn-cta") // Example for a CTA button
    private WebElement getStartedButton;

    // Define actions/methods on the home page
    public void clickFeaturesLink() {
        featuresLink.click();
    }

    public void clickPricingLink() {
        pricingLink.click();
    }

    public void clickGetStartedButton() {
        getStartedButton.click();
    }
    public void clickLoginLink(){
        loginLink.click();
    }
}
