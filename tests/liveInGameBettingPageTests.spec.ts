import { test, expect} from '@playwright/test';
import { betCell, checkPageForVisibleSportToBet, getAllVisibleSportTabs, getFirstAvailableSportTab } from '../HelperSteps';
import { chromium } from 'playwright';

let browser;
let context;
let page;

test.beforeEach(async () => {
  // Prepare browser in headless mode {in this Task, I decided it will be better to show the tests in headless mode}
  browser = await chromium.launch({ headless: false });
  context = await browser.newContext();
  page = await context.newPage();

  // Navigate to Home Page and verify the URL
  await page.goto('https://sportsbook.draftkings.com/');
  await expect(page).toHaveURL('https://sportsbook.draftkings.com/');

  // Navigate to Live In Game Section and verify the URL
  await page.locator('[data-test-id="Live In-Game-link"]').click();
  console.log('Clicked the Live In-Game Button');
  await expect(page).toHaveURL('https://sportsbook.draftkings.com/live');
});

test.afterEach(async () => {
  //Clean-Up after each test
  await page.close();
  await context.close();
  await browser.close();
});

test('Ensure each sport has at least one match present', async () => {
    // Navigate to Live In Game Section
    // Get all visible Sport tabs
    // Click on each of them
    // Verify Different pages with at least one sport to bet is visible
    for (const tab of await(getAllVisibleSportTabs(page))) {
    const text = (await tab.allTextContents()).toString();
    await checkPageForVisibleSportToBet(page, text);
    }
  });

  test('Ensure dropdown option re-load the content of the page', async () => {
    // Navigate to Live In Game Section
    // Get the First Availabe Sport Tab
    // Click on the Sport Bet Category Selection Drop-down menu
    // Verify there are availabe options
    // Select the first option form the drop-down
    // Verify the contnet of the page is reloaded after the seleciton
    const firstAvailableSport = await (await getFirstAvailableSportTab(page)).textContent();
    await page.locator('#subcategory_'+firstAvailableSport).click();
    await page.getByLabel('Game Subcategory Selector').locator('span').nth(1).hover();
    await page.waitForTimeout(2000);
    const dropDownOptions = await page.getByLabel('Subcategory options list').locator('li').all();
    expect(dropDownOptions.length).toBeGreaterThan(0);
    console.log("Drop-down Options are more than zero");

    await page.getByRole('tab', { name: firstAvailableSport }).hover();
    const cardsContainer = await page.locator('.cards');
    await expect(cardsContainer).toBeVisible();
    console.log("Cards section is visible before changing bet option");

    await page.getByLabel('Game Subcategory Selector').locator('span').nth(1).hover();
    await page.waitForTimeout(2000);
    const firstDropDownOption = await page.getByLabel('Subcategory options list').locator('li').first().textContent();
    await page.getByText(firstDropDownOption).click();
    expect(cardsContainer).toBeVisible();
    console.log("Cards section is visible after changing bet option");
  });

  test('Verify League component can be expanded and collapsed', async () => {
    // Navigate to Live In Game Section
    // Click on the collapse arrow 
    // Verify section is collapsed
    // Click on expand arrow
    // Verify Section is expanded
    const leagueOption = page.getByLabel('Featured Accordion').first();
    await leagueOption.getByLabel('Arrow pointing up icon').click();
    await page.waitForTimeout(1000);
    expect(leagueOption).toHaveAttribute('aria-expanded', 'false');
    await leagueOption.getByLabel('Arrow pointing up icon').click();
    await page.waitForTimeout(1000);
    expect(leagueOption).toHaveAttribute('aria-expanded', 'true');
  });

  test('Verify each sport event is described by the names of the participants', async () => {
    // Navigate to Live In Game Section
    // Verify there is match with different participants
    // Verify participants are available for betting
    const event = page.locator('.sportsbook-event-accordion__title');
    const eventText = await event.allTextContents();
    const participants = eventText?.toString().split(' vs ');
    const participantOne = participants[0];
    const participantTwo = participants[1];

    expect(event).toContainText('vs');
    console.log('Ensuring the match is between two participants');
    expect(page.getByRole('link', { name: participantOne, exact: true })).toBeVisible();
    expect(page.getByRole('link', { name: participantTwo, exact: true })).toBeVisible();
    console.log('Ensure user sees the participants');
  });

  test('Verify clicking event name is redirecting to the event routing', async () => {
    // Navigate to Live In Game Section
    // Verify after the event is clicked, the correct routing is displayed
    const event = page.locator('.sportsbook-event-accordion__title');
    const participants = (await event.allTextContents()).toString().split(' vs ');
    const participantOne = participants[0];
    const participantTwo = participants[1];
    await event.click();
  
    console.log('Ensuring when event is clicked, the correct routing is displayed');
    expect(page.url()).toContain(participantOne.toLowerCase() && participantTwo.toLowerCase());
  });

  test('Verify user can add bets to bet slip', async () => {
    // Navigate to Live In Game Section
    // Verify bet cell is not disabled
    // Click on the first bet cell 
    // Verify single bet option is present in the Bet Slip
    // Input random value {1}
    // Verify expected payload is present
    // Close the singel card bet by pressing {x} button
    // Verify no single card is available in the Bet Slip
    const firstBetCell = (await betCell(page)).first();
    await expect(firstBetCell).not.toBeDisabled();
    await firstBetCell.click();

    const singleCardContainer = page.locator('.single-card');
    await expect(singleCardContainer).toBeVisible();

    await page.locator('input[name="stake"]').fill('1');
    await expect(singleCardContainer.locator('.single-card-input__payout')).toBeVisible();

    const placeBetContainerCloseButton = singleCardContainer.locator('.sportsbook__icon--ex');
    await placeBetContainerCloseButton.click();
    await expect(singleCardContainer.locator('.single-card')).not.toBeVisible();
  });

  test('Ensure each button displays outcome and odds', async () => {    
    // Navigate to Live In Game Section
    // Verify first and the second Bet cells are not disabled and it are visible
    // Verify outcome odds are present in the cells
    const firstBetCellOdds = (await betCell(page)).first().locator('.sportsbook-odds');
    await expect(firstBetCellOdds).not.toBeDisabled();
    await expect(firstBetCellOdds).toBeVisible();
    await expect(firstBetCellOdds).toContainText(/\+|−/);

    const secondBetCellOdds = (await betCell(page)).nth(1).locator('.sportsbook-odds');
    await expect(secondBetCellOdds).not.toBeDisabled();
    await expect(secondBetCellOdds).toBeVisible();
    await expect(secondBetCellOdds).toContainText(/\+|−/);

  });