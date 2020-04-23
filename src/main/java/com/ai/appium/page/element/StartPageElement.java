package com.ai.appium.page.element;

import com.ai.appium.common.CommonAppium;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.concurrent.TimeUnit;

public class StartPageElement extends CommonAppium {

    public StartPageElement(AndroidDriver androidDriver){
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.tencent.mm:id/ecv\")")
    public AndroidElement loginBtn;

}
