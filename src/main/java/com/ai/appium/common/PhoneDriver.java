package com.ai.appium.common;

import com.ai.appium.configuration.AppConfig;
import com.ai.appium.configuration.MobileConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/27 上午10:45
 * @description:  只暴露了createPhoneDriver()对外使用，进行driver的初始化创建
 */

@Service
@Slf4j
public class PhoneDriver extends CommonAppium{
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private MobileConfig mobileConfig;

    private DesiredCapabilities desiredCapabilities = null;

    /**
     * 获取apk的地址
     * @return
     */

    private String apkDir(){
        File apkDir = apkDir = new File(System.getProperty("user.dir")+File.separator+"apk"+File.separator+appConfig.getApkName()); //apk包的位置
        log.info("apk文件的地址【"+apkDir.getAbsolutePath()+"】");
        return apkDir.getAbsolutePath();
    }

    /**
     * 初始化DesiredCapabilities对象
     */
    private DesiredCapabilities decapInit(){
        desiredCapabilities = new DesiredCapabilities(); //初始化
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");//指定使用uiAutomator2的Api
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,mobileConfig.getPlatformName());//指定手机系统平台
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,mobileConfig.getPlatformVersion());//设置手机系统版本
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,mobileConfig.getDeviceName());//链接模拟器、真机
        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,mobileConfig.getH5Browser());//这里初始化的是浏览器的名字
        desiredCapabilities.setCapability("appPackage", appConfig.getAppPackage());//App安装后的包名,注意与原来的CalcTest.apk不一样
        desiredCapabilities.setCapability("appActivity", appConfig.getAppActivity());
        desiredCapabilities.setCapability(MobileCapabilityType.APP,apkDir());//设置app路径
        desiredCapabilities.setCapability("autoLaunch",false);//设置是否自动打开app
        desiredCapabilities.setCapability("unicodeKeyBoard",true);//支持输入中文
        desiredCapabilities.setCapability("resetKeyboard",true);//重置输入法为appium默认,支持中文输入
        desiredCapabilities.setCapability("noSign",true);//安装时不对apk进行重签名，否则apk重新签名后无法正常使用
        desiredCapabilities.setCapability("noReset",true);//启动后、结束后不清空应用数据
        log.info("初始化desiredCapabilities对象...");
        return desiredCapabilities;
    }


    /**
     * 判断platform，并且判断是否是模拟器还是真机
     */
    private String chooseEnv(){
        if(mobileConfig.getPlatformName().equalsIgnoreCase("android")){
            log.info("检测出platformName【"+mobileConfig.getPlatformName()+"】");
            if(mobileConfig.getDeviceName().contains("127.0.0.1:")){
               decapInit();
               log.info("使用安卓模拟器");
            }else {
                decapInit();
                log.info("使用安卓真机");
            }
        }else {
            log.info("ios");
        }
        return mobileConfig.getPlatformName();

    }

    public void createPhoneDriver(){
        if(chooseEnv().equalsIgnoreCase("android")){
            try {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
                log.info("创建AndroidDriver对象，URL【"+driver.getCurrentUrl()+"】");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else {
            log.info("new 一个 ios");
        }
    }
}
