package com.ai.appium.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriver extends CommonAppium{
    private String apkName = "wx7_0_5.apk";
    private String platformName = "Android";
    private String platformVersion = "5.1.1";
    private String deviceName = "127.0.0.1:62001";
    private String h5Browser = "";
    private String appPackage = "com.tencent.mm";
    private String appActivity = "com.tencent.mm.ui.LauncherUI";

    private DesiredCapabilities desiredCapabilities;

    /**
     * 获取apk的地址
     * @return
     */

    private String apkDir(){
        File apkDir = apkDir = new File(System.getProperty("user.dir")+File.separator+"apk"+File.separator+apkName); //apk包的位置
        return apkDir.getAbsolutePath();
    }

    /**
     * 初始化DesiredCapabilities对象
     * @param phoneEnum
     */

    private void init(PhoneEnum phoneEnum){
        desiredCapabilities = new DesiredCapabilities(); //初始化
//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,isReInstall); //是否重新安装
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");//指定使用uiAutomator2的Api
        if(phoneEnum.toString().equalsIgnoreCase("android") || phoneEnum.toString().equalsIgnoreCase("android_avd")){
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,platformName);//指定手机系统平台
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);//链接模拟器
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,h5Browser);//这里初始化的是浏览器的名字
            desiredCapabilities.setCapability("appPackage", appPackage);//App安装后的包名,注意与原来的CalcTest.apk不一样
            desiredCapabilities.setCapability("appActivity", appActivity);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,platformVersion);//设置手机系统版本
            desiredCapabilities.setCapability(MobileCapabilityType.APP,apkDir());//设置app路径
            desiredCapabilities.setCapability("autoLaunch",false);//设置是否自动打开app
            desiredCapabilities.setCapability("unicodeKeyBoard",true);//支持输入中文
            desiredCapabilities.setCapability("resetKeyboard",true);//重置输入法为appium默认,支持中文输入
            desiredCapabilities.setCapability("noSign",true);//安装时不对apk进行重签名，否则apk重新签名后无法正常使用
            desiredCapabilities.setCapability("noReset",true);//启动后、结束后不清空应用数据
        }else {
            desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME,"IOS");//指定手机系统平台
//            desiredCapabilities.setCapability("deviceName",avdIpPortOrTruePhoneName);//真机ios
        }
    }

    /**
     * 重新安装app
     */

    private void reInstall(){
        if(driver.isAppInstalled(appPackage)){
//            log.info("存在包名"+appPackage+" 触发删除后重新安装");
            driver.removeApp(appPackage);
            driver.installApp(apkDir());
            try {
                Thread.sleep(5000);
//                log.info("重新安装后等待5秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public MobileDriver(PhoneEnum phoneEnum,boolean isReInstall){
        try {
        switch (phoneEnum){
            case ANDROID_AVD:
                init(phoneEnum);
//                log.info("完成模拟器的初始化");
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
                driver.launchApp();
//                log.info("启动AdnroidDriver" + "http://127.0.0.1:4723/wd/hub");
                break;
            case IOS:
                break;
            case ANDROID:
                break;
        }
    }catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
