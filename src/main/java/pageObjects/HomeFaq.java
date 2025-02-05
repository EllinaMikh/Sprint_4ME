package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeFaq {
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

    public HomeFaq(WebDriver driver) {
        this.driver = driver;
    }
// Метод для клика на вопрос
public void clickOnQuestion(int heading) {
    driver.findElement(headingLocators[heading]).click();
}

// Метод для получения ответа
    public String getAnAnswer(int panel) {
        return driver.findElement(panelLocators[panel]).getText();
    }
       }

