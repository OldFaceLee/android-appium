package com.ai.appium.page.element;

import com.ai.appium.common.CommonAppium;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPageElement extends CommonAppium{



    public LoginPageElement(AndroidDriver androidDriver){
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
    }

    @AndroidFindBy(uiAutomator = "")
    public AndroidElement username;

    @AndroidFindBy(uiAutomator = "")
    public AndroidElement password;
}
