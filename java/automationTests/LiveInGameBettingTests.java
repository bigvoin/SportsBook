package automationTests;

import Steps.LiveInGameBettingSteps;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LiveInGameBettingTests {

  private static final LiveInGameBettingSteps LIVE_IN_GAME_BETTING_STEPS = new LiveInGameBettingSteps();

  @BeforeEach
  public void beforeEachTest() {
    Configuration.browser = "chrome";

    LIVE_IN_GAME_BETTING_STEPS
        .openSportsBookWebsite()
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