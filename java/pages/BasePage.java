package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class BasePage {

  public SelenideElement getLiveInGameTab() {
    return $("[data-test-id=\"Live In-Game-link\"]");
  }
}
