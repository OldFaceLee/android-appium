package com.ai.appium.testcase;

import com.ai.appium.common.CommonAppium;
import com.ai.appium.common.TestDriver;
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
    TestDriver drivers;

    @Override
    public void runScript(Map<String, String> excelMap) {
        System.out.println(drivers.getMobileDriver().equals(driver));
    }
}
