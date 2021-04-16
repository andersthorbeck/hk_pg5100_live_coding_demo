package org.tsdes.intro.spring.demojsfselenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SeleniumTest {

  private static final String COUNTER_SPAN_ID = "counterValue";
  private static final String INCREMENT_BUTTON_ID = "incrementButton";

  @LocalServerPort
  private int port;

  @Test
  void testIncrement() throws Exception {
    WebDriver driver = SeleniumDriverHandler.getChromeDriver();

    assertThat(port, is(greaterThan(0)));

    try {
      driver.get("http://localhost:" + port);

      assertThat(getCounterValue(driver), is(0));
      clickIncrementButton(driver);
      assertThat(getCounterValue(driver), is(1));

      Thread.sleep(5_000);
    } finally {
      driver.close();
    }
  }

  private static int getCounterValue(WebDriver driver) {
    WebElement counterSpan = driver.findElement(By.id(COUNTER_SPAN_ID));
    return Integer.parseInt(counterSpan.getText());
  }

  private static void clickIncrementButton(WebDriver driver) {
    WebElement incrementButton = driver.findElement(By.id(INCREMENT_BUTTON_ID));
    incrementButton.click();
  }
}
