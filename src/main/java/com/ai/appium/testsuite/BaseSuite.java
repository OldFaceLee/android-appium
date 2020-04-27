package com.ai.appium.testsuite;

import com.ai.appium.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/27 上午11:42
 * @description:
 */
@SpringBootTest(classes = Application.class)
public class BaseSuite extends AbstractTestNGSpringContextTests {
}
