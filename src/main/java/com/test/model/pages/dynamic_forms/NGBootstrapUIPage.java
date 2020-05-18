package com.test.model.pages.dynamic_forms;

import com.test.model.pages.BasePage;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class NGBootstrapUIPage extends BasePage<NGBootstrapUIPage> {
    public NGBootstrapUIPage() {
        super(byCssSelector("pre"));
    }

    public NGBootstrapUIPage attachFile(String classpathFilename) {
        $(byId("attachments")).uploadFromClasspath(classpathFilename);
        log.debug("Attached '{}' file", classpathFilename);
        return this;
    }

    public String getAttachedFilepath() {
        return $(byId("attachments")).getValue();
    }
}
