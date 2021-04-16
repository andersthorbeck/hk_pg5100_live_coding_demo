package org.tsdes.intro.spring.demojsfselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CounterPageObject {

    private static final String COUNTER_SPAN_ID = "counterValue";
    private static final String INCREMENT_BUTTON_ID = "incrementButton";

    private final WebDriver driver;

    public CounterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public int getCounterValue() {
        WebElement counterSpan = driver.findElement(By.id(COUNTER_SPAN_ID));
        return Integer.parseInt(counterSpan.getText());
    }

    public void clickIncrementButton() {
        WebElement incrementButton = driver.findElement(By.id(INCREMENT_BUTTON_ID));
        incrementButton.click();
    }

}
