package com.ai.appium.configuration;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mobile")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MobileConfig {

    String platformName;

    String deviceName;

    String platformVersion;

    String h5Browser;

}

