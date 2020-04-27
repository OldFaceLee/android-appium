package com.ai.appium.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {

    String apkName;

    @Value(value = "${app.app-package}")
    String appPackage;

    @Value(value = "${app.app-activity}")
    String appActivity;


}
