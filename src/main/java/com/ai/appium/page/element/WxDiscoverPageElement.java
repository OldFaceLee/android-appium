package com.ai.appium.page.element;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;


import java.util.concurrent.TimeUnit;

public class WxDiscoverPageElement{

    public WxDiscoverPageElement(AndroidDriver androidDriver){
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver,25,TimeUnit.SECONDS),this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"发现\")")
    public AndroidElement discoverIcon;
}
