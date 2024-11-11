package org.ghostInspector.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class basePage {
    protected WebDriver driver;

    public  basePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
public String getPageTitle(){
        return  driver.getTitle();
    }
}
