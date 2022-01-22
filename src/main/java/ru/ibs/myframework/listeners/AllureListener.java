package ru.ibs.myframework.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ibs.myframework.managers.DriverManager;

import java.time.LocalDate;
import java.time.LocalTime;

public class AllureListener extends AllureJunit5 implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        if (extensionContext.getExecutionException().isPresent()) {
            Allure.getLifecycle().addAttachment("FailScr-" + LocalDate.now().toString() + "-" + LocalTime.now().toString(), "image/png", "png", takeScreenshot());
        }
    }


    public byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
