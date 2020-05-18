package com.test.core.listeners;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ErrorMessages;
import com.codeborne.selenide.ex.UIAssertionError;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;

@Log4j2
public class ScreenShooter extends ExitCodeListener {
    public static boolean captureSuccessfulTests;

    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        String className = result.getMethod().getTestClass().getName();
        String methodName = result.getMethod().getMethodName();
        Screenshots.startContext(className, methodName);
    }

    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        if (!(result.getThrowable() instanceof UIAssertionError)) {
            log.info(ErrorMessages.screenshot(WebDriverRunner.driver()));
        }

        Screenshots.finishContext();
    }

    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        if (captureSuccessfulTests) {
            log.info(ErrorMessages.screenshot(WebDriverRunner.driver()));
        }

        Screenshots.finishContext();
    }
}
