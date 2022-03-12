package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.severx.BaseTest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SeverXTest extends BaseTest {

    @Test
    @DisplayName("Проверка заголовка страницы")
    void testTitle() {
        step("Открыть страницу:", () -> {
            open("https://severx.ru/");
        });

        step("Проверить title открытой страницы:", () -> {
            $("title").shouldHave(attribute("text", "severx.ru - выгодные скидки и бесплатные промокоды"));
        });
    }

    @Test
    @DisplayName("Проверить отображение CookieAgreement")
    void testCookieAgreement() {
        step("Открыть страницу:", () -> {
            open("https://severx.ru/");
        });

        step("Проверить отображение CookieAgreement:", () -> {
            $x("//div[contains(@class,'coockieAgreementContainer')]").shouldBe(visible);
        });

    }

    @Test
    @DisplayName("Проверить страницу 'Контакты'")
    void testContacts() {
        step("Открыть страницу:", () -> {
            open("https://severx.ru/");
        });

        step("Перейти на страницу:", () -> {
            $(byText("Принимаю")).click();
            $("[href='/contacts']").scrollIntoView(true).click();
        });

        step("Проверить email:", () -> {
            $$("main p").get(1).shouldHave(
                    text("support@severx.ru"));
        });
    }

    @ValueSource(strings = {
            "Пикер сет",
            "E-пик скидки"})
    @ParameterizedTest(name = "Перейти на соответсвующую плитку - {0}")
    void testAllLink(String name) {
        step("Открыть страницу:", () -> {
            open("https://severx.ru/");
        });

        step("Нажать на соответствующую плитку:", () -> {
            $(byText(name)).click();
        });

        step("Проверить переход:", () -> {
            $x("//div[contains(@class,'CatalogBreadcrumbs_breadcrumb')]/..").shouldHave(text(name));
        });
    }
}
