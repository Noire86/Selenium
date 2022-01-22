package ru.ibs.myframework.listeners;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ibs.myframework.managers.DriverManager;

import java.time.LocalDate;
import java.time.LocalTime;

public class AllureListener implements TestWatcher {


    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment("FailScr-" + LocalDate.now().toString() + "-" + LocalTime.now().toString(), "image/png", "png", takeScreenshot());
        DriverManager.getInstance().quitDriver();

    }


    public byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
