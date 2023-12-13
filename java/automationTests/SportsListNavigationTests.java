package automationTests;


import Steps.BasePageSteps;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SportsListNavigationTests {

  private static final BasePageSteps BASE_PAGE_STEPS = new BasePageSteps();

  private static final String[] availableSports = {
      "ACADEMY AWARDS",
      "AUSSIE RULES",
      "BASEBALL",
      "BASKETBALL",
      "BOXING",
      "CRICKET",
      "CYCLING",
      "DARTS",
      "E-SPORTS",
      "EMMY AWARDS",
      "FOOTBALL",
      "GOLF",
      "HANDBALL",
      "HOCKEY",
      "JAI ALAI",
      "LACROSSE",
      "MMA",
      "MOTORSPORTS",
      "RUGBY LEAGUE",
      "RUGBY UNION",
      "SNOOKER",
      "SOCCER",
      "TENNIS",
      "VOLLEYBALL"
  };

  @BeforeEach
  public void beforeEachTest() {
    Configuration.browser = "chrome";
    BASE_PAGE_STEPS.openSportsBookWebsite();
  }

  @Test
  @DisplayName("Ensure A-Z Sports displays all available sports, currently available")
  void ensureSportsListDisplaysAllAvailableSports() {
    BASE_PAGE_STEPS.verifySportsListContainsAllSports(availableSports);
  }

  @Test
  @DisplayName("Ensure Any Sport (sport name = Cricket) can be expanded")
  void ensureAnySportCanBeExpanded() {
    BASE_PAGE_STEPS.verifySportContainerCanBeExpanded("CRICKET");
  }

  @Test
  @DisplayName("Ensure clicking on League name will navigate to dedicated page of the selected league")
  void ensureClickingOnLeagueNameWillNavigateToDedicatedPage() {
    String sportName = "E-SPORTS";
    String leagueName = "Call of Duty League";
    BASE_PAGE_STEPS.verifyClickingOnLeagueWillOpenDedicatedPage(sportName, leagueName);
  }
}
