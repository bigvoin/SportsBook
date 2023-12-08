import { test, expect } from '@playwright/test';
import { sportsBookNavigationContainer } from '../HelperSteps';

test.beforeEach(async ({ page }) => {
    // Navigate to Home Page and verify the URL
    await page.goto('https://sportsbook.draftkings.com');
    await expect(page).toHaveURL('https://sportsbook.draftkings.com');
  });

  //Sports Options Constants
    const AVAILABLE_SPORTS_ACADEMY_AWARDS = 'ACADEMY AWARDS';
    const AVAILABLE_SPORTS_AUSSIE_RULES = 'AUSSIE RULES';
    const AVAILABLE_SPORTS_BASEBALL = 'BASEBALL';
    const AVAILABLE_SPORTS_BASKETBALL = 'BASKETBALL';
    const AVAILABLE_SPORTS_BOXING = 'BOXING';
    const AVAILABLE_SPORTS_CRICKET = 'CRICKET';
    const AVAILABLE_SPORTS_CYCLING = 'CYCLING';
    const AVAILABLE_SPORTS_DARTS = 'DARTS';
    const AVAILABLE_SPORTS_ESPORTS = 'E-SPORTS';
    const AVAILABLE_SPORTS_EMMY_AWARDS = 'EMMY AWARDS';
    const AVAILABLE_SPORTS_FOOTBALL = 'FOOTBALL';
    const AVAILABLE_SPORTS_GOLF = 'GOLF';
    const AVAILABLE_SPORTS_HANDBALL = 'HANDBALL';
    const AVAILABLE_SPORTS_HOCKEY = 'HOCKEY';
    const AVAILABLE_SPORTS_JAI_ALAI = 'JAI ALAI';
    const AVAILABLE_SPORTS_LACROSSE = 'LACROSSE';
    const AVAILABLE_SPORTS_MMA = 'MMA';
    const AVAILABLE_SPORTS_MOTORSPORTS = 'MOTORSPORTS';
    const AVAILABLE_SPORTS_RUGBY_LEAGUE = 'RUGBY LEAGUE';
    const AVAILABLE_SPORTS_RUGBY_UNION = 'RUGBY UNION';
    const AVAILABLE_SPORTS_SNOOKER = 'SNOOKER';
    const AVAILABLE_SPORTS_SOCCER = 'SOCCER';
    const AVAILABLE_SPORTS_TENNIS = 'TENNIS';
    const AVAILABLE_SPORTS_VOLLEYBALL = 'VOLLEYBALL';

    //Array of the Sports Options
    const allSportsConstants = [
        AVAILABLE_SPORTS_ACADEMY_AWARDS,
        AVAILABLE_SPORTS_AUSSIE_RULES,
        AVAILABLE_SPORTS_BASEBALL,
        AVAILABLE_SPORTS_BASKETBALL,
        AVAILABLE_SPORTS_BOXING,
        AVAILABLE_SPORTS_CRICKET,
        AVAILABLE_SPORTS_CYCLING,
        AVAILABLE_SPORTS_DARTS,
        AVAILABLE_SPORTS_ESPORTS,
        AVAILABLE_SPORTS_EMMY_AWARDS,
        AVAILABLE_SPORTS_FOOTBALL,
        AVAILABLE_SPORTS_GOLF,
        AVAILABLE_SPORTS_HANDBALL,
        AVAILABLE_SPORTS_HOCKEY,
        AVAILABLE_SPORTS_JAI_ALAI,
        AVAILABLE_SPORTS_LACROSSE,
        AVAILABLE_SPORTS_MMA,
        AVAILABLE_SPORTS_MOTORSPORTS,
        AVAILABLE_SPORTS_RUGBY_LEAGUE,
        AVAILABLE_SPORTS_RUGBY_UNION,
        AVAILABLE_SPORTS_SNOOKER,
        AVAILABLE_SPORTS_SOCCER,
        AVAILABLE_SPORTS_TENNIS,
        AVAILABLE_SPORTS_VOLLEYBALL,
      ];

  test('Ensure A-Z Sports displays all avaialble sports, currently available', async ({ page }) => {
    for (const sportsConstant of allSportsConstants) {
        await expect((await sportsBookNavigationContainer(page)).locator('.sportsbook-expandable-shell__wrapper').nth(1))
    .toContainText(sportsConstant);
      }
  });

  test('Ensure Any Sport can be expanded', async ({ page }) => {
    const cricketCategory = (await sportsBookNavigationContainer(page)).getByLabel('Expandable container CRICKET');
    await cricketCategory.click();
    await expect(cricketCategory).toHaveAttribute('aria-expanded', 'true');
  });

  test('Ensure A-Z Sports displays available leagues of given sports', async ({ page }) => {
    const cyclingCategory = (await sportsBookNavigationContainer(page)).getByRole('heading', { name: 'CYCLING' });
    await cyclingCategory.click();
    await expect(page.getByText('Tour de France')).toBeVisible();
    await expect(page.getByText('Milano - San Remo')).toBeVisible();
    await expect(page.getByText('Paris - Roubaix')).toBeVisible();
    await expect(page.getByText('Tour of Flanders')).toBeVisible();
  });

  test('Ensure clicking on League name will navigate to dedicated page of the selected league', async ({ page }) => {

    await (await sportsBookNavigationContainer(page)).getByRole('heading', { name: 'E-SPORTS' }).click();
    await (await sportsBookNavigationContainer(page)).getByText('Call of Duty League').click();
    await expect(page).toHaveURL('https://sportsbook.draftkings.com/leagues/esports/call-of-duty-league');
  });