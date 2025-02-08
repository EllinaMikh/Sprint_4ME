package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.OrderForm;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderFormTest {


    WebDriver driver;
    WebDriverWait wait;
    OrderForm orderForm;

    private final String firstName;
    private final String secondName;
    private final String addressLine;
    private final String subway;
    private final String tel;
    private final String date;
    private final String period;
    private final String comment;

    public OrderFormTest(String firstName, String secondName, String addressLine, String subway, String tel, String date, String period, String comment) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.addressLine = addressLine;
        this.subway = subway;
        this.tel = tel;
        this.date = date;
        this.period = period;
        this.comment = comment;
    }

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        orderForm = new OrderForm(driver);

        // Подтверждаем куки
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("rcc-confirm-button")));
        cookieButton.click();
    }

    @After
    public void end() {
        if (driver != null) {
            driver.quit(); // Закрываем браузер после выполнения тестов
        }

    }

    @Parameterized.Parameters // Тестовые данные
    public static Object[][] orderButton() {
        return new Object[][]{
                {"Валентина", "Терешкова", "Проспект Гагарина", "Планерная", "89055679834", "20.06.2020", "двое суток", "оставить у ракеты"},
                {"Васисуалий", "Лоханкин", "Малая Пионерская", "Павелецкая", "89167528014", "19.10.2020", "сутки", "оставить на проходной"}
        };
    }

    @Test
    public void checkOrderByUpButton() {
        orderForm = orderForm.clickOrderButtonUp();
        ordering(orderForm);
    }

    @Test
    public void checkOrderByDownButton() {
        orderForm = orderForm.clickOrderButtonDown();
        ordering(orderForm);
    }

    private void ordering(OrderForm orderForm) {
        assertTrue(orderForm.isStageOne());

        orderForm.setFirstNameField(firstName);
        orderForm.setSecondNameField(secondName);
        orderForm.setAddressLineField(addressLine);
        orderForm.setSubwayField(subway);
        orderForm.setTelField(tel);

        orderForm.clickNextButton();
        assertTrue(orderForm.isStageTwo()); // На втором этапе заказа
        orderForm.setDateField(date);
        orderForm.choiceRentPeriod(period);
        orderForm.selectBlackCheckbox();
        orderForm.setCommentField(comment);
        orderForm.clickOrderButtonDown();
        assertTrue(orderForm.isModalOrderVisible()); // Подтверждение заказа
        orderForm.clickYesButton();
        assertTrue(orderForm.isOrderCompleted()); // Заказ успешно завершен
    }

}
