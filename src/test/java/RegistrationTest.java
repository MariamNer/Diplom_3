
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
import org.junit.Assert;
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

public class RegistrationTest extends ChoosingBrowser {


    private String accessToken;

    public RegistrationTest(String driverType) {
        super(driverType);
    }


    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Успешная регистрация")
    @Step
    public void registrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickButtonFromRegistration();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String email = "as"+ RandomStringUtils.randomAlphanumeric(5)+ "@mail.ru";
        String password = RandomStringUtils.randomAlphanumeric(6);
        registrationPage.fill_out_form(RandomStringUtils.randomAlphabetic(5),
                email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        registrationPage.registerButtonClick();
        assertTrue(loginPage.isOpened());
        LoginUser loginUser = new LoginUser(email, password);
        Response response = UserApi.loginUser(loginUser);
        response.then().log().all();
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;
        UserApi.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Ошибку для некорректного пароля")
    @Step
    public void failedRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickButtonFromRegistration();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fill_out_form(RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphanumeric(5)+ "@mail.ru",
                RandomStringUtils.randomAlphanumeric(5));
        new WebDriverWait(driver, Duration.ofSeconds(10));
        registrationPage.registerButtonClick();
        Assert.assertEquals("Некорректный пароль",
                driver.findElement(loginPage.messageErrorPassword).getText());
    }

    @After
    public void closeBrowser () {
        driver.quit();
    }
}
