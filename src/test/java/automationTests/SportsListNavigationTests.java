package automationTests;


import Steps.BasePageSteps;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
      "TENNIS"
  };

  @BeforeClass
  public static void setUp() {
    BasePageSteps.configBrowserOptions();
  }

  @Before
  public void beforeEach() {
    BASE_PAGE_STEPS.openSportsBookWebsite();
  }

  @Test
  @DisplayName("Ensure A-Z Sports displays all available sports, currently available")
  public void ensureSportsListDisplaysAllAvailableSports() {
    BASE_PAGE_STEPS.verifySportsListContainsAllSports(availableSports);
  }

  @Test
  @DisplayName("Ensure Any Sport (sport name = Cricket) can be expanded")
  public void ensureAnySportCanBeExpanded() {
    BASE_PAGE_STEPS.verifySportContainerCanBeExpanded("CRICKET");
  }

  @Test
  @DisplayName("Ensure clicking on League name will navigate to dedicated page of the selected league")
  public void ensureClickingOnLeagueNameWillNavigateToDedicatedPage() {
    String sportName = "E-SPORTS";
    String leagueName = "Call of Duty League";
    BASE_PAGE_STEPS.verifyClickingOnLeagueWillOpenDedicatedPage(sportName, leagueName);
  }
}
