package Steps;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import pages.BasePage;

public class BasePageSteps {

  private static final String BASE_URL = "https://sportsbook.draftkings.com";

  private static final BasePage BASE_PAGE = new BasePage();


  @Step("Open Sportsbook Website")
  public BasePageSteps openSportsBookWebsite() {
    open(BASE_URL);
    String expectedTitle = "Sports Betting | Bet Online Legally with DraftKings Sportsbook";
    String actualTittle = title();
    assert Objects.equals(actualTittle, expectedTitle);

    return this;
  }

  @Step("Verify A-Z Container has all of the expected sports: {availableSports}")
  public void verifySportsListContainsAllSports(String[] availableSports) {
    for (String sport : availableSports) {
      BASE_PAGE.sportListContainer().shouldHave(text(sport));
    }
  }

  @Step("Verify Sport container: {sportName} can be expanded")
  public void verifySportContainerCanBeExpanded(String sportName) {
    SelenideElement selectedSport = BASE_PAGE.getSportContainer(sportName);
    selectedSport.click();
    selectedSport.shouldHave(attribute("aria-expanded", "true"));
  }

  @Step("Click on Sport : {sportName}, and league: {league}")
  public void verifyClickingOnLeagueWillOpenDedicatedPage(String sportName, String league) {
    BASE_PAGE.getSportByHeading(sportName).click();
    BASE_PAGE.getElementByText(league).click();
    String leagueWithReplacedWhiteSpace = league.replace(" ", "-");
    String expectedURl = "https://sportsbook.draftkings.com/leagues/esports/" + leagueWithReplacedWhiteSpace;
    Assertions.assertEquals(WebDriverRunner.url(), expectedURl);

  }
}
