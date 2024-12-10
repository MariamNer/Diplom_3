package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoveryFormPage {

    private WebDriver driver;

    // Поле "Email"
    private By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Кнопка "Восстановить"
    private By recoveryFormButton = By.xpath(".//form/button[text()='Восстановить']");
    // Заголовок "Восстановление пароля"
    public By recoveryFormPassword = By.xpath(".//main/div/h2[text()='Восстановление пароля']");
    // Ссылка "Войти"
    public By loginLink = By.xpath(".//div/p/a[@href = '/login' and text() = 'Войти']");


    public RecoveryFormPage(WebDriver driver){
        this.driver = driver;
    }


    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickOnRecoveryFormButton(){
        driver.findElement(recoveryFormButton).click();
    }

    public void clickOnLoginLink(){
        driver.findElement(loginLink).click();
    }

    public void recoverPassword(String email){
        setEmail(email);
        clickOnRecoveryFormButton();
    }


    public void waitForLoadedRecoveryForm(){
        new WebDriverWait(driver,Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(recoveryFormPassword));
    }
}
