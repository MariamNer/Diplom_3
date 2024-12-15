
import Pages.MainPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

import jdk.jfr.Description;
import org.junit.After;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends ChoosingBrowser{


    public ConstructorTest(String driverType) {
        super(driverType);
    }


    @Test
    @DisplayName("Переход")
    @Description("Переход на вкладку «Булки»")
    @Step
    public void transitionBunsConstructorTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpenedUnauth();
        mainPage.waitForLoadBuns();
        assertTrue(driver.findElement(mainPage.bunsText).isDisplayed());
    }

    @Test
    @DisplayName("Переход")
    @Description("Переход на вкладку «Соусы»")
    @Step
    public void transitionSaucesConstructorTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpenedUnauth();
        mainPage.clickSauceButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertTrue(driver.findElement(mainPage.sauceButtonDiv).getAttribute("class").contains("tab_tab_type_current"));
    }

    @Test
    @DisplayName("Переход")
    @Description("Переход на вкладку «Начинки»")
    @Step
    public void transitionFillingsConstructorTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpenedUnauth();
        mainPage.clickFilingsButton();
        mainPage.waitForLoadFilling();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertTrue(driver.findElement(mainPage.filingsButtonDiv).getAttribute("class").contains("tab_tab_type_current"));

    }

    @After
    public void closeBrowser () {
        driver.quit();
    }
}
