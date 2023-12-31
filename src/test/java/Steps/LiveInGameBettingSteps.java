package Steps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;
import pages.LiveInGamePage;

public class LiveInGameBettingSteps extends BasePage{


  private static final BasePage BASE_PAGE = new BasePage();

  private static final LiveInGamePage LIVE_IN_GAME_PAGE = new LiveInGamePage();

  @Step("Navigate to Live-In Game tab")
  public LiveInGameBettingSteps navigateToLiveInGameTab() {
    BASE_PAGE.getLiveInGameTab().click();

    return this;
  }

  @Step("Verify There is at least one sports tab visible")
  public LiveInGameBettingSteps verifyAtLeastOneVisibleSportsTab() {
    LIVE_IN_GAME_PAGE.getAllAvailableSportsTabs().first().shouldBe(visible);

    return this;
  }

  @Step("Verify each sportsTab shows at least one event")
  public void verifyEachSportsTabShowsAtLeastOneEvent() {
    for (SelenideElement tab : LIVE_IN_GAME_PAGE.getAllAvailableSportsTabs())
    {
      tab.click();
      LIVE_IN_GAME_PAGE.getCardsContainer().shouldBe(visible);
    }
  }

  @Step("Click on available event")
  public LiveInGameBettingSteps clickOnAvailableEvent() {
    LIVE_IN_GAME_PAGE.getEventContainer().click();

    return this;
  }

  @Step("")
  public void verifyCorrectRoutingIsRedirectedTo() {
    String[] eventParticipants = LIVE_IN_GAME_PAGE.getEventContainer().getText().split(" vs ");
    String firstParticipant = eventParticipants[0];
    String secondParticipant = eventParticipants[1];
    clickOnAvailableEvent();
    String currentUrl = url();
    $(currentUrl).shouldHave(text(firstParticipant));
    $(currentUrl).shouldHave(text(secondParticipant));
  }
}
