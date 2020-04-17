package com.ai.appium.page.service;

import com.ai.appium.common.CommonAppium;
import com.ai.appium.page.element.StartPageElement;

public class StartPageSv extends CommonAppium {

    public void login(){
        StartPageElement startPageElement = new StartPageElement(driver);
        startPageElement.loginBtn.click();
    }
}
