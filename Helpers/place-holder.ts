import { type Page, Locator, expect } from '@playwright/test';

//Helper Functions / Steps

export async function betCell(page:Page): Promise<Locator> {
    return page.locator('.sportsbook-outcome-cell');
  }

export async function sportsBookNavigationContainer(page:Page) {
    return page.locator('.sportsbook-sport-navigation');
}

export async function getAllVisibleSportTabs(page:Page) {
    return page.getByTestId('sportsbook-tabbed-subheader').locator('a').all();
}

export async function getFirstAvailableSportTab(page:Page) {
    return page.getByTestId('sportsbook-tabbed-subheader').locator('a').first();
}

//Click {category} tab
// Verify container is visible
// verify .cars class is visible
export async function checkPageForVisibleSportToBet(page: Page, category: string) {
    await page.locator('#subcategory_'+category).click();
    console.log('Navigated to '+category+ ' Category');
    await expect(page.locator('.sportsbook-featured-accordion__children-container').first()).toBeVisible();
    await expect(page.locator('.cards')).toBeVisible();
    console.log("Cards component is visible in " + category + " category");
  }
