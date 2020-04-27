package com.ai.appium.testsuite;

import com.ai.appium.testcase.TC01;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/27 上午11:43
 * @description:
 */
public class TestSuite extends BaseSuite{

    @Autowired
    TC01 tc01;

    @Test
    public void tc01(){
        Map<String,String> map = new HashMap<>();
        map.put("key","value");
        tc01.runScript(map);
    }
}
