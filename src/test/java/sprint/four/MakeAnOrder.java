package sprint.four;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@RunWith(Parameterized.class)
public class MakeAnOrder {
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String comment;

    public MakeAnOrder(
            String name,
            String surname,
            String address,
            String metroStation,
            String phone,
            String comment
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrder() {
        //Сгенерируй тестовые данные для формы 1 и формы 2
        return new Object[][]{
                {"Анита", "Корниенкова", "г.Москва ул.Урицкого д.5 кв.36", "Бульвар Рокоссовского", "+7660736003", "Пзвонить за 1 час"},
                {"Ангелина", "Власова", "г.Москва ул.Борисова д.5 кв.36", "Бульвар Рокоссовского", "+7660736104", "Позвонить в домофон"},
        };
    }

    private final FirefoxOptions options = new FirefoxOptions();
    private WebDriver webDriver;


    @Before
    public void mood() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        webDriver = new FirefoxDriver(options);
    }

    @Test
    public void makeAnOrder() {
        MainPage mainPage = new MainPage(webDriver);
        FormOrder formOrder = new FormOrder(webDriver);

        mainPage.open();
        mainPage.setClickAgreeCookieButton();
        mainPage.clickButtonForOrderInUpPage();

        // Ожидаем прогрузки элементов формы
        formOrder.waitForForm();

        // Заполнить первую страницу формы заказа
        formOrder.firstData(name, surname, address, metroStation, phone);

        // Кликнуть по кнопке далее
        formOrder.clickButtonNext();

        // Заполнить вторую страницу формы заказа
        formOrder.secondData(comment);

        // Ожидание кнопки "заказать"
        formOrder.waitForOrderButton();

        // Заказать
        formOrder.orderButton();
        formOrder.waitForYes();
        formOrder.yes();

        // Проверка, что появилось окно об успешном заказе
        String successfulOrder = formOrder.getOrderResultText();
        Assert.assertTrue("Ошибка", successfulOrder.startsWith("Заказ оформлен"));
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
