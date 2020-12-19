package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderFormTestSelenide {

        @Test
    void shouldSubmitRequest() {
            open("http://localhost:9999");
            SelenideElement form = $("[id='root']");
            form.$("[type=text]").setValue("Татьяна");
            form.$("[type=tel]").setValue("+79990000000");
            form.$("[class=checkbox__text]").click();
            form.$("[type=button]").click();
            $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        }
}

