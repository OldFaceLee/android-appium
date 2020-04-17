package com.ai.appium;

import com.ai.appium.common.MobileDriver;
import com.ai.appium.common.PhoneEnum;
import com.ai.appium.page.service.StartPageSv;


//@SpringBootApplication(scanBasePackages = "com.ai.appium")
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class,args);
        MobileDriver mobileDriver = new MobileDriver(PhoneEnum.ANDROID_AVD,true);
        StartPageSv sv = new StartPageSv();
        sv.login();


    }

}
