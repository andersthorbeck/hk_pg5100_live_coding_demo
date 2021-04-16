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

  @LocalServerPort
  private int port;

  @Test
  void testIncrement() throws Exception {
    WebDriver driver = SeleniumDriverHandler.getChromeDriver();
    CounterPageObject pageObject = new CounterPageObject(driver);

    assertThat(port, is(greaterThan(0)));

    try {
      driver.get("http://localhost:" + port);

      assertThat(pageObject.getCounterValue(), is(0));
      pageObject.clickIncrementButton();
      assertThat(pageObject.getCounterValue(), is(1));

      Thread.sleep(5_000);
    } finally {
      driver.close();
    }
  }
}
