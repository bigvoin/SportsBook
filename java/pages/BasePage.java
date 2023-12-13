package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

public class BasePage {

  public SelenideElement getLiveInGameTab() {
    return $("[data-test-id=\"Live In-Game-link\"]");
  }

  public SelenideElement sportsBookNavigationContainer() {
    return $(".sportsbook-sport-navigation");
  }

  public SelenideElement sportListContainer() {
    return sportsBookNavigationContainer().$$(".sportsbook-expandable-shell__wrapper").get(1);
  }

  public SelenideElement getSportContainer(String sportName) {
    return sportsBookNavigationContainer().$(Selectors.byTitle("Expandable container " + sportName));
  }

  public SelenideElement getSportByHeading(String headingName) {
    return $("[role='heading']").$(Selectors.byText(headingName));
  }
  public SelenideElement getElementByText(String text) {
    return sportsBookNavigationContainer().$(Selectors.byText(text));
  }

}
