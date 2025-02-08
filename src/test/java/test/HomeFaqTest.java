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
import page.objects.HomeFaqPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

// значения параметров для каждого класса

public class HomeFaqTest {

    WebDriver driver;
    WebDriverWait wait;
    HomeFaqPage homeFaqPage;

    private final String question;
    private final String expectedAnswer;
    private final int questionIndex;

    public HomeFaqTest(String question, String expectedAnswer, int questionIndex) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
        this.questionIndex = questionIndex;
    }

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homeFaqPage = new HomeFaqPage(driver);

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

    // параметры для тестов
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        };
    }

    @Test
    public void testFaq() {
// клик по вопросу
        homeFaqPage.clickOnQuestion(questionIndex);

        // отображается ответ соответствующего вопроса
        String siteAnswerText = homeFaqPage.getAnAnswer(questionIndex);

        assertTrue(siteAnswerText.contains(expectedAnswer));

    }
}



