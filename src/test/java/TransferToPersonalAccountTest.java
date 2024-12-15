import Pages.AccoutPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.RegistrationPage;
import dto.LoginUser;
import dto.ResponseUser;
import dto.UserApi;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TransferToPersonalAccountTest extends ChoosingBrowser{

        private final static String name = "TEST";
        private final static String email = "TESTTEST1"+ RandomStringUtils.randomNumeric(8)+"@mail.ru";
        private final static String password = "1234567";
        private String accessToken;

    public TransferToPersonalAccountTest(String driverType) {
        super(driverType);
    }


        @Before
        public void open(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickButtonFromRegistration();
            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.fill_out_form(name,email, password);
            new WebDriverWait(driver, Duration.ofSeconds(10));
            registrationPage.registerButtonClick();

        }

        @Test
        @DisplayName("Переход")
        @Description("Переход по клику на «Личный кабинет»")
        @Step
        public void transitionToPersonalAccountPageTest(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoadEntrance();
            assertTrue(loginPage.isOpened());
            LoginUser loginUser = new LoginUser(email, password);
            Response response = UserApi.loginUser(loginUser);
            ResponseUser responseUser = response.as(ResponseUser.class);
            accessToken = responseUser.accessToken;
        }

        @Test
        @DisplayName("Выход из аккаунта")
        @Description("Выход по кнопке «Выйти» в личном кабинете")
        @Step
        public void exitPersonalAccountPageTest(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoadEntrance();
            loginPage.authorization(email, password);
            loginPage.clickButtonEnter();
            mainPage.waitForLoad();
            mainPage.clickPersonalAccount();
            AccoutPage accoutPage = new AccoutPage(driver);
            accoutPage.waitForLoadProfilePage();
            accoutPage.clickexitButton();
            assertTrue(loginPage.isOpened());
            LoginUser loginUser = new LoginUser(email, password);
            Response response = UserApi.loginUser(loginUser);
            ResponseUser responseUser = response.as(ResponseUser.class);
            accessToken = responseUser.accessToken;

        }

        @Test
        @DisplayName("Переход")
        @Description("Переход по клику на логотип Stellar Burgers")
        @Step
        public void transitionToLogoStellarBurgerTest(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoadEntrance();
            loginPage.clickLogoStellarBurger();
            assertTrue(mainPage.isOpenedUnauth());
            LoginUser loginUser = new LoginUser(email, password);
            Response response = UserApi.loginUser(loginUser);
            ResponseUser responseUser = response.as(ResponseUser.class);
            accessToken = responseUser.accessToken;
        }

        @Test
        @DisplayName("Переход")
        @Description("Переход по клику на «Конструктор»")
        @Step
        public void transitionToConstructorTest(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoadEntrance();
            loginPage.clickOnConstructorButton();
            assertTrue(mainPage.isOpenedUnauth());
            LoginUser loginUser = new LoginUser(email, password);
            Response response = UserApi.loginUser(loginUser);
            ResponseUser responseUser = response.as(ResponseUser.class);
            accessToken = responseUser.accessToken;
        }

        @After
        public void closeBrowser () {
            UserApi.deleteUser(accessToken);
            driver.quit();
        }
}
