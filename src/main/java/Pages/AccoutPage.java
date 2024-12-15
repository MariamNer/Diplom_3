package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccoutPage {

    private WebDriver driver;

    // Кнопка Выход
    private By exitButton = By.xpath(".//li/button[text()='Выход']");
    // Текст для проверки перехода в accountPage
    public By textOnProfilePage = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");


    public AccoutPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickexitButton(){
        driver.findElement(exitButton).click();
    }

    public void waitForLoadProfilePage(){
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(textOnProfilePage));
    }


}
