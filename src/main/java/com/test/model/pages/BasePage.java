package com.test.model.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.test.core.annotations.DisableImplicitWait;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.util.concurrent.TimeUnit.SECONDS;

@Log4j2
public abstract class BasePage<PAGE extends BasePage<PAGE>> {
    private static final int DEFAULT_WAIT_TIME = 10;

    private String pageURL;
    private SelenideElement waitOpenPageElement;

    protected BasePage(By waitOpenPageLocator) {
        waitOpenPageElement = $(waitOpenPageLocator);
    }

    protected BasePage(String pageURL, By waitOpenPageLocator) {
        this(waitOpenPageLocator);
        this.pageURL = pageURL;
    }

    public PAGE openPage() {
        if (StringUtils.isNotEmpty(pageURL)) {
            log.debug("Trying to open '{}' page", this.getClass().getSimpleName());
            Selenide.open(pageURL);
            log.debug("Opening '{}' page URL", pageURL);
        } else {
            throw new UnsupportedOperationException("Page URL not establish");
        }

        return waitUntilPageWillOpen();
    }

    @SuppressWarnings("unchecked")
    public <P extends BasePage<PAGE>> P openPage(String pageURL, Class<P> pageObjectType) {
        P openedPage;

        if (StringUtils.isNotEmpty(pageURL)) {
            log.debug("Trying to open '{}' page", this.getClass().getSimpleName());
            openedPage = Selenide.open(pageURL, pageObjectType);
            log.debug("Opening '{}' page URL", pageURL);
        } else {
            throw new UnsupportedOperationException("Page URL is null or empty");
        }

        return (P) openedPage.waitUntilPageWillOpen();
    }

    @DisableImplicitWait
    @SuppressWarnings("unchecked")
    public final PAGE waitUntilPageWillOpen(long time, TimeUnit timeUnit) {
        log.debug("Wait until '{}' page will open", this.getClass().getSimpleName());
        waitOpenPageElement.waitUntil(visible, timeUnit.toMillis(time));
        log.info("'{}' page was opened", this.getClass().getSimpleName());

        return (PAGE) this;
    }

    @SuppressWarnings("unchecked")
    public final PAGE waitUntilPageWillOpen() {
        waitUntilPageWillOpen(DEFAULT_WAIT_TIME, SECONDS);
        return (PAGE) this;
    }
}

