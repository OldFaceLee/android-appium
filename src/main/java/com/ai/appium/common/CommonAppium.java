package com.ai.appium.common;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

public class CommonAppium {

    public static AndroidDriver driver;

    public static void sendKeyByAdb(PlatformEnum platformEnum,String phoneDeviceNumber,String value){
        try {
        switch (platformEnum){
            case WINDOWS:
                Runtime.getRuntime().exec("adb -s "+phoneDeviceNumber+"shell input text "+value);
                break;
            case MAC:
                break;
            case LINUX:
                break;
        } } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
