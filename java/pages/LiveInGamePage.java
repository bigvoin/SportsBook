package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class LiveInGamePage {

  public ElementsCollection getAllAvailableSportsTabs() {
    return $$(".sportsbook-tabbed-subheader__tab-link");
  }

  public SelenideElement getCardsContainer() {
    return $(".cards");
  }

  public SelenideElement getEventContainer() {
    return $(".sportsbook-event-accordion__title");
  }
}
