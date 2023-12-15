package automationTests;

import Steps.BasePageSteps;
import Steps.LiveInGameBettingSteps;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LiveInGameBettingTests {

  private static final LiveInGameBettingSteps LIVE_IN_GAME_BETTING_STEPS = new LiveInGameBettingSteps();

  private static final BasePageSteps BASE_PAGE_STEPS = new BasePageSteps();

  @BeforeClass
  public static void setUp() {
    BasePageSteps.configBrowserOptions();
  }

  @Before
  public void beforeEachTest() {
    BASE_PAGE_STEPS.openSportsBookWebsite();

    LIVE_IN_GAME_BETTING_STEPS
        .navigateToLiveInGameTab()
        .verifyAtLeastOneVisibleSportsTab();
  }

  @Test
  @DisplayName("Verify Live-In Game pages with at least one sport event are visible")
  void ensureEachSportHasAtLeastOneMatchPresentTest() {
    LIVE_IN_GAME_BETTING_STEPS.verifyEachSportsTabShowsAtLeastOneEvent();
  }

  @Test
  @DisplayName("Verify clicking event name is redirecting to the event routing")
  void verifyClickingEventNameIsRedirectingToEventRoutingTest() {
    LIVE_IN_GAME_BETTING_STEPS
        .clickOnAvailableEvent()
        .verifyCorrectRoutingIsRedirectedTo();
  }
}