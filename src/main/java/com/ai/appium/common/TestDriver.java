package com.ai.appium.common;

import com.ai.appium.configuration.AppConfig;
import io.appium.java_client.android.AndroidDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/27 上午10:45
 * @description:
 */

@Service
public class TestDriver extends CommonAppium{

    @Autowired
    AppConfig appConfig;

    private void init(){
        if(appConfig.getApkName().contains("wx")){
            System.out.println("初始化模拟器");
        }

    }

    public AndroidDriver getMobileDriver(){
        init();
        return driver;
    }
}
