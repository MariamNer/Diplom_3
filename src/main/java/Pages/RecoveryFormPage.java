package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoveryFormPage {

    private WebDriver driver;

    // Заголовок "Восстановление пароля"
    public By recoveryFormPassword = By.xpath(".//main/div/h2[text()='Восстановление пароля']");
    // Ссылка "Войти"
    public By loginLink = By.xpath(".//div/p/a[@href = '/login' and text() = 'Войти']");


    public RecoveryFormPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnLoginLink(){
        driver.findElement(loginLink).click();
    }

    public void waitForLoadedRecoveryForm(){
        new WebDriverWait(driver,Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(recoveryFormPassword));
    }
}
