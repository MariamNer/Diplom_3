
import Pages.LoginPage;
import Pages.MainPage;
import Pages.RegistrationPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
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
        System.out.println(driver.findElement(mainPage.sauceButtonDiv).getAttribute("class"));
        System.out.println(driver.findElement(mainPage.sauceButtonDiv).getAttribute("class").contains("tab_tab_type_current"));

        assertTrue(driver.findElement(mainPage.sauceButtonDiv).getAttribute("class").contains("tab_tab_type_current"));
    }

    @Test
    @DisplayName("Переход")
    @Description("Переход на вкладку «Начинки»")
    @Step
    public void transitionFillingsConstructorTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpenedUnauth();
//        mainPage.clickFilingsButton();
        mainPage.waitForLoadFilling();
        assertTrue(driver.findElement(mainPage.fillingsImg).isDisplayed());

    }

    @After
    public void closeBrowser () {
        driver.quit();
    }
}
