package org.tsdes.intro.spring.demojsfselenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

      WebElement counterSpan0 = driver.findElement(By.id(COUNTER_SPAN_ID));
      String counterValue0 = counterSpan0.getText();
      assertThat(counterValue0, is("0"));
      assertThat(Integer.parseInt(counterValue0), is(0));

      WebElement incrementButton = driver.findElement(By.id(INCREMENT_BUTTON_ID));
      incrementButton.click();

      WebElement counterSpan1 = driver.findElement(By.id(COUNTER_SPAN_ID));
      String counterValue1 = counterSpan1.getText();
      assertThat(counterValue1, is("1"));

      Thread.sleep(5_000);
    } finally {
      driver.close();
    }
  }
}
