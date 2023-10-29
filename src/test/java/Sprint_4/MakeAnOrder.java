package Sprint_4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Parameterized.class)
public class MakeAnOrder {
    private final String name;
    private final String surname;
    private final String adress;
    private final String metroStation;
    private final String phone;
    private final String comment;

    public MakeAnOrder(String name, String surname, String adress, String metroStation, String phone, String comment) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.metroStation = metroStation;
        this.phone = phone;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrder() {
        //Сгенерируй тестовые данные для формы 1 и формы 2
        return new Object[][]{
                {"Анита", "Корниенкова", "г.Москва ул.Урицкого д.5 кв.36", "Бульвар Рокоссовского", "+7660736003","Пзвонить за 1 час"},
                {"Ангелина", "Власова", "г.Москва ул.Борисова д.5 кв.36", "Бульвар Рокоссовского", "+7660736104","Позвонить в домофон"},
        };
    }

    private final FirefoxOptions options = new FirefoxOptions();
    private WebDriver webDriver;


    @Before
    public void mood() {
        System.setProperty("webdriver.gecko.driver", "C:\\Praktikum\\geckodriver.exe");
        //options.addArguments("--no-sandbox","--headless","--disable-dev-shm-usage");

        webDriver = new FirefoxDriver(options);
    }

    @Test
    public void makeAnOrder() {
        MainPage mainPage = new MainPage(webDriver);
        FormOrder formOrder = new FormOrder(webDriver);

        mainPage.open();
        mainPage.setClickAgreeCookieButton();
        mainPage.clickButtonForOrderInUpPage();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='* Имя']")));

        //Заполнить первую страницу формы заказа
        formOrder.firstData(name, surname, adress, metroStation, phone);

        //Кликнуть по кнопке далее
        formOrder.clickButtonNext();

        //Заполнить вторую страницу формы заказа
        formOrder.secondData(comment);

        //Ожидание кнопки "заказать"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Заказать']")));

        //Заказать
        formOrder.orderButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Да')]")));
        formOrder.yes();

        // Проверка, что появилось окно об успешном заказе
        String successfulOrder = webDriver.findElement(By.xpath(".//div[contains(@class, 'Order_ModalHeader')]")).getText();
        Assert.assertTrue("Ошибка", successfulOrder.startsWith("Заказ оформлен"));
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
