package com.test.core.entities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.Getter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ImplicitlyWaitSwitch {
    private @Getter boolean implicitlyWaitDisabled;

    public void enableImplicitlyWait() {
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Configuration.timeout, MILLISECONDS);
        implicitlyWaitDisabled = false;
    }

    public void disableImplicitlyWait() {
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(0, SECONDS);
        implicitlyWaitDisabled = true;
    }
}
