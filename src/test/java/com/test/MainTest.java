package com.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.test.core.listeners.ScreenShooter;
import com.test.model.entities.TripInfo;
import com.test.model.entities.enums.Extras;
import com.test.model.entities.enums.PayType;
import com.test.model.entities.enums.RoomSize;
import com.test.model.pages.dynamic_forms.MaterialUIFormPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$$;

@Listeners({ScreenShooter.class})
public class MainTest {
    MaterialUIFormPage materialUIFormPage = new MaterialUIFormPage();

    @Test
    public void shouldFillFormAndAttachFile() {
        materialUIFormPage.openPage().createTrip(TripInfo.builder()
                .arrivalDate("5/21/2020")
                .departureDate("6/21/2020")
                .roomSize(RoomSize.BUSINESS_SUITE)
                .roomQuantity(2)
                .firstName("Vladyslav")
                .lastName("Zahorodnii")
                .email("temp@email.com")
                .phone("380661111122")
                .streetName("Gorodovikova")
                .streetNumber("21")
                .zipCode("28331")
                .state("Alabama")
                .city("Montgomery")
                .payType(PayType.CASH)
                .note(RandomStringUtils.randomAlphanumeric(25))
                .tag("trip")
                .tag("dream")
                .reminder(true)
                .newsletter(false)
                .informationConfirm(true)
                .build(),
                Extras.BREAKFAST.check(),
                Extras.WIFI.check()
        );

        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("window.open()");
        Selenide.switchTo().window(1);

        Assert.assertTrue(materialUIFormPage.openPage()
                .openNgBootstrapUI()
                .attachFile("test_file.txt")
                .getAttachedFilepath()
                .contains("test_file.txt"));
    }

    @Test // Should fail, screenshot path in log
    public void shouldInputInvalidData() {
        materialUIFormPage.openPage().createTrip(TripInfo.builder()
                .roomSize(RoomSize.SINGLE_ROOM)
                .email(RandomStringUtils.randomAlphabetic(10))
                .payType(PayType.PAYPAL)
                .build()
        );

        Assert.assertEquals($$(byAttribute("aria-invalid", "true")).size(), 0);
    }
}
