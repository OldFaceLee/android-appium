package com.ai.appium.page.service.impl;

import com.ai.appium.common.CommonAppium;
import com.ai.appium.page.element.WxDiscoverPageElement;
import com.ai.appium.page.service.IWxDiscoverPageSv;

public class WxDiscoverPageSvImpl extends CommonAppium implements IWxDiscoverPageSv{

    public void goToDiscoverPage() {
        WxDiscoverPageElement discoverPageElement = new WxDiscoverPageElement(driver);
        discoverPageElement.discoverIcon.click();
    }
}
