package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeFaqPage {
    private final WebDriver driver;

    //локатор для появляющихся вопросов
    private final By[] headingLocators = {
        By.xpath("//*[@id='accordion__heading-0']"),
                By.xpath("//*[@id='accordion__heading-1']"),
                By.xpath("//*[@id='accordion__heading-2']"),
                By.xpath("//*[@id='accordion__heading-3']"),
                By.xpath("//*[@id='accordion__heading-4']"),
                By.xpath("//*[@id='accordion__heading-5']"),
                By.xpath("//*[@id='accordion__heading-6']"),
                By.xpath("//*[@id='accordion__heading-7']")
    };

    //локатор для открывающихся ответов
    private final By[] panelLocators = {
       By.xpath("//*[@id='accordion__panel-0']/p"),
               By.xpath("//*[@id='accordion__panel-1']/p"),
               By.xpath("//*[@id='accordion__panel-2']/p"),
               By.xpath("//*[@id='accordion__panel-3']/p"),
               By.xpath("//*[@id='accordion__panel-4']/p"),
               By.xpath("//*[@id='accordion__panel-5']/p"),
               By.xpath("//*[@id='accordion__panel-6']/p"),
               By.xpath("//*[@id='accordion__panel-7']/p")
    };

    private final By orderButtonDown = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[text()='Заказать'] ");

    private final By orderButtonUp = By.xpath(".//div[starts-with(@class, 'Header_Nav')]/button[text()='Заказать']");

    public HomeFaqPage(WebDriver driver) {
        this.driver = driver;
    }
// Метод для клика на вопрос
public void clickOnQuestion(int heading) {
    driver.findElement(headingLocators[heading]).click();
}

public void cookieButtonClick (WebDriverWait wait)
{
    var cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("rcc-confirm-button")));
    cookieButton.click();
}

// Метод для получения ответа
    public String getAnAnswer(int panel) {
        return driver.findElement(panelLocators[panel]).getText();
    }

    public OrderForm clickOrderButtonUp() {
        driver.findElement(orderButtonUp).click();
        return new OrderForm(driver);
    }

    // Метод для клика на нижнюю кнопку "Заказать"
    public OrderForm clickOrderButtonDown() {
        driver.findElement(orderButtonDown).click();
        return new OrderForm(driver);
    }
       }



