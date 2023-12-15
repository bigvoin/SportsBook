package Steps;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class BasePageSteps {

  private static final String BASE_URL = "https://sportsbook.draftkings.com/";

  private static final BasePage BASE_PAGE = new BasePage();


  @Step("Open Sportsbook Website")
  public void openSportsBookWebsite() {
    open(BASE_URL);
    Selenide.Wait().until(ExpectedConditions.urlToBe(BASE_URL));
    waitPageLoaded();
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
    Assertions.assertThat(WebDriverRunner.url().equals(expectedURl));

  }


  public SelenideElement logInButton() {
    return $(Selectors.byAttribute("data-test-id", "Log In-cta-link"));
  }

  public SelenideElement sportsList() {
    return $(Selectors.byAttribute("data-testid", "sportsbook-tabbed-subheader"));
  }

  public SelenideElement featuredAccordion() {
    return $(Selectors.byAttribute("aria-label", "Featured Accordion"));
  }

  public SelenideElement gameSubcategory() {
    return $(Selectors.byAttribute("aria-label", "Game Subcategory Selector"));
  }

  /**
   * Verifies Login page is loaded by waiting all important Login page elements to be displayed.
   */
  public void waitPageLoaded() {
    logInButton().shouldBe(visible);
    sportsList().shouldBe(visible);
    featuredAccordion().shouldBe(visible);
    gameSubcategory().shouldBe(visible);
  }

  public static void configBrowserOptions() {
    Configuration.browser = "firefox";
    WebDriverManager.chromedriver().setup();
  }
}
