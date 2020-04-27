package com.ai.appium.support.listener;

import com.ai.appium.common.CommonAppium;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Iterator;

/**
 * @author: lixuejun
 * @date: Create in 2019/10/30 下午5:10
 * @description: testNG的监听器
 */

@Slf4j
public class TestNGListener extends TestListenerAdapter {

    /**
     * 如果testNG执行为fail则，进行自动截图
     * @param tr
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        if (CommonAppium.driver == null) {
            log.info("driver为空，不需要截图！！！");
        }else {
            String screenshotName = tr.getMethod().getMethodName()+"_TestNG监听异常_";
            CommonAppium.snapShot(screenshotName);
            log.info(String.format("testNG监听异常，自动触发截图[%s]",screenshotName));
            if (CommonAppium.driver != null) {
                log.info("浏览器driver["+CommonAppium.driver.toString()+"]!=null,testNG监听器主动关闭浏览器");
                CommonAppium.quitDriver();
            }
        }
    }


    /**
     * 重写onFinish，当retry时不会多生成错误报告
     * @param context
     */
    @Override
    public void onFinish(ITestContext context) {
        if(CommonAppium.driver != null){
            log.info("浏览器driver["+CommonAppium.driver.toString()+"]!=null,testNG监听器主动关闭浏览器");
            CommonAppium.driver.quit();
        }

    }


    /**
     * 当测试用例重跑时，会在结果中会发现失败的 case 会生成多个，所以优化，并重写onFinished
     * @param context
     */
    private void removeFailed(ITestContext context) {
        Iterator<ITestResult> iterator = context.getFailedTests().getAllResults().iterator();

        while (iterator.hasNext()) {
            ITestResult failedTest = iterator.next();
            ITestNGMethod method = failedTest.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                iterator.remove();
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    iterator.remove();
                }
            }
        }
    }

}
