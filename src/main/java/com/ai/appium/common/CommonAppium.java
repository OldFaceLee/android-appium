package com.ai.appium.common;

import com.ai.appium.support.util.RuntimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Slf4j
public class CommonAppium {

    public static AndroidDriver driver;
    //初始化全局滑动为300ms的速度
    private static Duration duration = Duration.ofMillis(300L);

    /**
     *安装app
     */
    public static void installApp(String appPath){
        driver.installApp(appPath);
        log.info("安装app【"+appPath+"】");
    }

    /**
     * 打开app
     */
    public static void launchApp(){
        driver.launchApp();
        log.info("打开app");
    }

    /**
     * 关闭app
     */
    public static void closeApp(){
        driver.closeApp();
        log.info("关闭App");
    }

    /**
     *删除app
     */
    public static void removeApp(String packageName){
        driver.removeApp(packageName);
        log.info("移除app【"+packageName+"】");
    }

    /**
     * 重置app应用，相当于先卸载后安装app
     */
    public static void resetApp(){
        driver.resetApp();
        log.info("重置App");
    }

    /**
     * 重启App,先关闭，再打开app
     */
    public static void rebootApp(){
        closeApp();
        launchApp();
        log.info("重启App");
    }

    /**
     * 唤醒屏幕
     */
    public static void unLockPhone(){
        driver.unlockDevice();
    }

    /**
     *java Thread休眠，传入秒即可
     */
    public static void sleep(double second){
        try {
            Thread.sleep((long)second * 1000);
            log.info("java线程休眠"+second+"秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     *获取当前手机屏幕的宽度
     */
    private static int getPhoneWidth(){
        int width = driver.manage().window().getSize().width;
        log.info("获取当前手机屏幕的宽度【"+width+"】");
        return width;
    }

    /**
     *获取当前手机屏幕的高度
     */
    private static int getPhoneHeight(){
        int height = driver.manage().window().getSize().height;
        log.info("获取当前手机屏幕的高度【"+height+"】");
        return height;
    }

    private static ImmutableMap<String, Point> getStartPointAndEndPointInElement(WebElement element,String startPoint,String endPoint){
        JSONObject _startPoint;
        JSONObject _endPoint;
        try {
            _startPoint = JSON.parseObject(startPoint.trim());
            _endPoint = JSON.parseObject(endPoint.trim());
        } catch (Exception e) {
            throw new RuntimeException("startPoint,endPoint格式错误，正确格式: {x:0.5,y:0.5}");
        }


        org.openqa.selenium.Rectangle containerRect = element.getRect();
        int containerHeight = (int)containerRect.getHeight();
        int containerWidth = (int)containerRect.getWidth();

        // 容器左上角坐标
        org.openqa.selenium.Point containerRectPoint = containerRect.getPoint();
        int containerLeftTopX = (int)containerRectPoint.getX();
        int containerLeftTopY = (int)containerRectPoint.getY();

        int startX = containerLeftTopX + (int) (_startPoint.getFloat("x") * containerWidth);
        int startY = containerLeftTopY + (int) (_startPoint.getFloat("y") * containerHeight);
        int endX = containerLeftTopX + (int) (_endPoint.getFloat("x") * containerWidth);
        int endY = containerLeftTopY + (int) (_endPoint.getFloat("y") * containerHeight);

        return ImmutableMap.of("start", new Point(startX, startY), "end", new Point(endX, endY));


    }

    /**
     * 屏幕向上滑动
     */
    public static void swipeToUp(){
        int width = getPhoneWidth();
        int height = getPhoneHeight();
        new TouchAction(driver).press(PointOption.point(width / 2,height * 3 /4))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width /2,height /4))
                .release().perform();
        log.info("触发手机屏幕上滑事件");
        sleep(0.5);
    }

    /**
     * 屏幕下滑
     */
    public static void swipeToDown(){
        int width = getPhoneWidth();
        int height = getPhoneHeight();
        new TouchAction(driver).press(PointOption.point(width / 2, height / 4))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width / 2, height * 3 / 4))
                .release().perform();
        log.info("触发手机屏幕下滑事件");
        sleep(0.5);
    }

    /**
     * 屏幕左滑
     */
    public static void swipeToLeft(){
        int width = getPhoneWidth();
        int height = getPhoneHeight();
        new TouchAction(driver).press(PointOption.point(width * 3/ 4, height / 2))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width / 4, height / 2))
                .release().perform();
        log.info("触发手机屏幕左滑事件");
        sleep(0.5);
    }

    /**
     *屏幕右滑
     */
    public static void swipeToRight(){
        int width = getPhoneWidth();
        int height = getPhoneHeight();
        new TouchAction(driver).press(PointOption.point(width / 4, height / 2))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width * 3/ 4, height / 2))
                .release().perform();
        log.info("触发手机屏幕右滑事件");
        sleep(0.5);
    }

    /**
     *截屏，自动生成工程下/screenshot/yyyyMMdd/下的截图
     */
    public static void snapShot(String caseName){
        String dailyPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String screenShotRootFolder = System.getProperty("user.dir")+ File.separator+"screenshot"+File.separator;
        if(!new File(screenShotRootFolder).isDirectory()){
            new File(screenShotRootFolder).mkdir();
        }
        File sourceScreen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        InputStream is = null;
        OutputStream os = null;
        String screenFileName = caseName+"_"+new SimpleDateFormat("yyyMMdd_HH:mm:ss").format(new Date())+".png";
        String target = screenShotRootFolder + File.separator + dailyPath+ File.separator;
        if(!new File(target).isDirectory()){
            new File(target).mkdir();
        }
        try {
            is = new FileInputStream(sourceScreen);
            os = new FileOutputStream(target+File.separator+screenFileName);
            byte[] buff = new byte[1024];
            int len = 0;
            while((len = is.read(buff, 0, buff.length)) != -1) {
                os.write(buff, 0, len);
            }
            log.info("Appium进行截图操作："+ target+File.separator+screenFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(is!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    /**
     * 关闭driver
     */
    public static void quitDriver(){
        driver.quit();
        log.info("关闭driver");
    }


    /**
     * 通过adb进行输入
     * @param phoneDeviceNumber
     * @param value
     */
    public static void sendKeyByAdb(String phoneDeviceNumber,String value){
        RuntimeUtil.exeCmd("adb -s "+phoneDeviceNumber+"shell input text "+value);
    }

}
