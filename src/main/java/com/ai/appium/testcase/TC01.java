package com.ai.appium.testcase;

import com.ai.appium.common.CommonAppium;
import com.ai.appium.common.PhoneDriver;
import com.ai.appium.support.ITestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/27 上午11:41
 * @description:
 */
@Service
public class TC01 extends CommonAppium implements ITestCase {

    @Autowired
    PhoneDriver engine;

    @Override
    public void runScript(Map<String, String> excelMap) {
        engine.createPhoneDriver();
        driver.launchApp();
    }
}
