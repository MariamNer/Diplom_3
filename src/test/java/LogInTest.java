import Pages.LoginPage;
import Pages.MainPage;
import Pages.RecoveryFormPage;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;


public class LogInTest extends ChoosingBrowser {

    private final static String name = "TEST";
    private final static String email = "TESTTEST1"+RandomStringUtils.randomNumeric(8)+"@mail.ru";
    private final static String password = "1234567";
    private String accessToken;

    public LogInTest(String driverType) {
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
    @DisplayName("Вход")
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    @Step
    public void logInByLoginButtonTest(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginEnterFieldsAndClick(email, password);
        mainPage.waitForLoad();
        assertTrue(mainPage.isOpened());
        LoginUser loginUser = new LoginUser(email, password);
        Response response = UserApi.loginUser(loginUser);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;


    }

    @Test
    @DisplayName("Вход")
    @Description("Вход через кнопку «Личный кабинет»")
    @Step
    public void logInByPersonalAccountButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(email,password);
        loginPage.clickButtonEnter();
        mainPage.waitForLoad();
        Assert.assertTrue(mainPage.isOpened());

        LoginUser loginUser = new LoginUser(email, password);
        Response response = UserApi.loginUser(loginUser);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;

    }

    @Test
    @DisplayName("Вход")
    @Description("Вход через кнопку в форме регистрации")
    @Step
    public void logInBylogInButtonRegistrationTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickButtonFromRegistration();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.logInButtonClick();
        loginPage.waitForLoadEntrance();
        Assert.assertTrue(loginPage.isOpened());

        LoginUser loginUser = new LoginUser(email, password);
        Response response = UserApi.loginUser(loginUser);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;
    }

    @Test
    @DisplayName("Вход")
    @Description("Вход через кнопку в форме восстановления пароля")
    @Step
    public void logInBylogInButtonPasswordRecoveryFormTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickbuttonRecoveryPassword();
        RecoveryFormPage recoveryFormPage = new RecoveryFormPage(driver);
        recoveryFormPage.waitForLoadedRecoveryForm();
        recoveryFormPage.clickOnLoginLink();
        loginPage.waitForLoadEntrance();
        loginPage.authorization(email, password);
        loginPage.clickButtonEnter();
        mainPage.waitForLoad();
        Assert.assertTrue(mainPage.isOpened());

        LoginUser loginUser = new LoginUser(email, password);
        Response response = UserApi.loginUser(loginUser);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;
    }

    @After
    public void tearDown() {
        UserApi.deleteUser(accessToken);
        driver.quit();
    }
}
