package com.ai.appium.page.element;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class WxDiscoverPageElement{

    public WxDiscoverPageElement(AndroidDriver androidDriver){
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
        try {
            Thread.sleep(17000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"发现\")")
    public AndroidElement discoverIcon;
}
