package com.test.model.pages.dynamic_forms;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.test.model.entities.TripInfo;
import com.test.model.entities.enums.Extras;
import com.test.model.pages.BasePage;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class MaterialUIFormPage extends BasePage<MaterialUIFormPage> {
    public MaterialUIFormPage() {
        super("http://ng2-dynamic-forms.udos86.de/sample/#/material-sample-form", byCssSelector("pre"));
    }

    public NGBootstrapUIPage openNgBootstrapUI() {
        $(byAttribute("routerlink", "/ng-bootstrap-sample-form")).click();
        log.debug("Clicked on 'NG Bootstrap UI' link");
        return new NGBootstrapUIPage().waitUntilPageWillOpen();
    }

    public MaterialUIFormPage createTrip(TripInfo tripInfo, Extras... extras) {
        $(byId("arrivalDate")).setValue(tripInfo.getArrivalDate());
        log.debug("Set value '{}' to 'Date of Arrival' text field", tripInfo.getArrivalDate());

        $(byId("departureDate")).setValue(tripInfo.getDepartureDate());
        log.debug("Set value '{}' to 'Date of Departure' text field", tripInfo.getDepartureDate());

        $(byId("roomSize")).click();
        $$(".mat-option > .mat-option-text").find(text(tripInfo.getRoomSize().getRoomName())).click();
        log.debug("Select 'Room Size' option '{}'", tripInfo.getRoomSize().getRoomName());

        $(byId("roomQuantity")).setValue(Integer.toString(tripInfo.getRoomQuantity()));
        log.debug("Set value '{}' to 'Room Quantity' text field", tripInfo.getRoomQuantity());

        $(byId("firstName")).setValue(tripInfo.getFirstName());
        log.debug("Set value '{}' to 'First Name' text field", tripInfo.getFirstName());

        $(byId("lastName")).setValue(tripInfo.getLastName());
        log.debug("Set value '{}' to 'Last Name' text field", tripInfo.getLastName());

        $(byId("email")).setValue(tripInfo.getEmail());
        log.debug("Set value '{}' to 'Email' text field", tripInfo.getEmail());

        $(byId("phone")).setValue(tripInfo.getPhone());
        log.debug("Set value '{}' to 'Phone Number' text field", tripInfo.getPhone());

        $(byId("streetName")).setValue(tripInfo.getStreetName());
        log.debug("Set value '{}' to 'Street Name' text field", tripInfo.getStreetName());

        $(byId("streetNumber")).setValue(tripInfo.getStreetNumber());
        log.debug("Set value '{}' to 'Street Number' text field", tripInfo.getStreetNumber());

        $(byId("zipCode")).setValue(tripInfo.getZipCode());
        log.debug("Set value '{}' to 'ZIP' text field", tripInfo.getZipCode());

        SelenideElement stateElement = $(byId("state"));
        stateElement.setValue(tripInfo.getState()); // Autocomplete not working on UI - maybe JS bug
        log.debug("Set value '{}' to 'State' text field", tripInfo.getState());
        stateElement.pressEscape();
        log.debug("Pressed 'Escape' to 'Tags' text field");

        $(byId("city")).setValue(tripInfo.getCity());
        log.debug("Set value '{}' to 'City' text field", tripInfo.getCity());

        SelenideElement extrasSelector = $(byId("extras"));
        extrasSelector.click();
        log.debug("Clicked on 'Extras' field");
        ElementsCollection extrasElements = $$(".mat-option > .mat-option-text");
        for (Extras extra : extras) {
            if (extra.isChecked()) {
                extrasElements.find(text(extra.getName())).click();
                log.debug("Check '{}' extra", extra.getName());
            }
        }
        extrasSelector.pressEscape();
        log.debug("Pressed 'Escape' to 'Tags' text field");

        $$(".mat-radio-button").find(text(tripInfo.getPayType().getName())).click();
        log.debug("Select 'Pay Type' option '{}'", tripInfo.getPayType().getName());

        $(byId("note")).setValue(tripInfo.getNote());
        log.debug("Set value '{}' to 'Personal Note' text field", tripInfo.getNote());

        SelenideElement tags = $(byId("mat-input-13"));
        tags.click();
        log.debug("Clicked on 'Tags' field");
        tripInfo.getTags().forEach(tag -> {
            tags.setValue(tag);
            log.debug("Set value '{}' to 'Tags' text field", tag);
            tags.pressEnter();
            log.debug("Pressed 'Enter' to 'Tags' text field");
        });

        SelenideElement reminderCheckbox = $(byXpath("//input[@id='reminder-input']/../div"));
        if (tripInfo.isReminder() && !reminderCheckbox.isSelected()) {
            reminderCheckbox.click();
            log.debug("Clicked on 'Send me a reminder' checkbox");
        }

        SelenideElement newsletterCheckbox = $(byXpath("//input[@id='newsletter-input']/../div"));
        if (tripInfo.isNewsletter() && !newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
            log.debug("Clicked on 'Subscribe to newsletter' checkbox");
        }

        SelenideElement infoConfirmCheckbox = $(byXpath("//input[@id='confirm-input']/.."));
        if (tripInfo.isInformationConfirm() && !infoConfirmCheckbox.isSelected()) {
            infoConfirmCheckbox.click();
            log.debug("Clicked on 'I confirm the information given above' checkbox");
        }

        return this;
    }
}
