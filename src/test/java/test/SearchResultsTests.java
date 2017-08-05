package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import page.FirstSearchResultsPage;
import page.StartPage;

import static java.lang.Thread.sleep;

public class SearchResultsTests extends BaseTest {

    WebDriver webDriver;
    StartPage startPage;

    /**
     * Initialises FirefoxDriver and navigates to LoginPage
     */
    @Parameters({ "browserName" })
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName) {
        webDriver = StartBrowser(browserName);
        webDriver.navigate().to("https://www.google.com.ua");
        startPage = PageFactory.initElements(webDriver, StartPage.class);
        startPage.isPageLoaded();
    }

    /**
     * Closes WebDriver instance
     */
    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }


    @Test
    public void SearchTest() {

        String searchText = "ITEA";

        Assert.assertTrue(startPage.isPageLoaded(), "Search page is not loaded");

        FirstSearchResultsPage firstSearchResultsPage = startPage.search(searchText);

        Assert.assertTrue(firstSearchResultsPage.isPageLoaded("1"), "First search results page is not loaded");

        Assert.assertEquals(firstSearchResultsPage.getResultsQuantity(), 7, "Results count did not match");

        Assert.assertTrue(firstSearchResultsPage.eachResultContains(searchText), "Not every result contains search term");



    }


}
