import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ConfiguratorTest {
    //Consent mode closure button
    SelenideElement consentModeClosureBtn = $(byId("bannerRejectButton"));

    //Hamburger menu
    SelenideElement menuIcon = $(byXpath("//*[@id=\"reactmount\"]//header//nav/button"));

    //Models and Configurator page
    SelenideElement configuratorPage = $(byText("Modele i konfigurator"));

    //Configure Volkswagen
    SelenideElement configureVolkswagen = $(byText("Skonfiguruj Volkswagena"));

    //Polo GTI (element)
    SelenideElement poloGtiElement = $(byXpath("//*[@id=\"MOFA\"]//ul[5]/li[2]/div[1]"));

    //Engine version button
    SelenideElement engineVersionBtn = $(byCssSelector("[data-testid=clustered-trim-next-step-cta]"));

    //Next step button
    SelenideElement nextStepBtn = $(byXpath("//*[@id=\"MOFA\"]//div[3]/div[2]/div[2]/div/button\n"));

    //Price section
    SelenideElement priceSection = $(byCssSelector("[data-testid=configuration-price]"));

    //White color button
    SelenideElement whiteColorBtn = $(byXpath("//*[@id=\"MOFA\"]//dl[1]/div[3]/dd[1]/button"));

    //Wheels section
    SelenideElement wheelsSection = $(byText("Wybierz felgi"));

    //Optional wheels button
    SelenideElement optionalWheelsBtn = $(byXpath("//*[@id=\"MOFA\"]//dl[2]/div[1]/dd/button"));

    //Exterior - dropdown
    SelenideElement exteriorDropdown = $(byId("zewnętrzne_head"));

    //Exterior - sunroof
    SelenideElement sunroof = $(byXpath("//*[@id=\"zewnętrzne_body\"]//dd[1]//label//div[1]/div[1]/div"));

    //Repeatable methods
    public void nextStep() {
        nextStepBtn.shouldBe(visible, Duration.ofSeconds(5)).click();
    }

    public void sleep() {
        Selenide.sleep(2000);
    }

    @Test
    public void configuratorCheck() {
        //Open volkswagen.pl
        open("https://www.volkswagen.pl");

        //Reject consent mode
        consentModeClosureBtn.shouldBe(visible, Duration.ofSeconds(5)).click();
        System.out.println("Reject consent mode - OK");

        //Expand menu
        menuIcon.shouldBe(visible, Duration.ofSeconds(5)).click();
        System.out.println("Expand menu - OK");

        //Open Models and Configurator page
        configuratorPage.shouldBe(visible, Duration.ofSeconds(5)).click();
        System.out.println("Open Models and Configurator page - OK");

        //Open Configure Volkswagen page
        configureVolkswagen.shouldBe(visible, Duration.ofSeconds(5)).click();
        System.out.println("Open Configure Volkswagen page - OK");

        //Scroll to Polo GTI element
        poloGtiElement.shouldBe(visible, Duration.ofSeconds(5)).scrollTo().click();
        System.out.println("Click on Polo GTI element - OK");

        //Engine version button - click
        engineVersionBtn.shouldBe(visible, Duration.ofSeconds(5)).click();
        System.out.println("Click on engine version button - OK");

        //Next step button - Engine
        nextStep();
        System.out.println("Next step button click on the 'Engine' page - OK");

        //Get starting price
        System.out.println("Starting price is: " + priceSection.innerText());

        //Pick white color
        whiteColorBtn.shouldBe(visible, Duration.ofSeconds(5)).click();
        System.out.println("Pick white color - OK");
        sleep();

        //Scroll to wheels section
        wheelsSection.shouldBe(visible, Duration.ofSeconds(5)).scrollTo();
        System.out.println("Scroll to wheels section - OK");

        //Pick optional wheels
        optionalWheelsBtn.shouldBe(visible, Duration.ofSeconds(5)).click();
        System.out.println("Pick optional wheels - OK");

        //Next step button - Exterior
        nextStep();
        System.out.println("Next step button click on the 'Exterior' page - OK");
        sleep();

        //Next step button - Interior
        nextStep();
        System.out.println("Next step button click on the 'Interior' page - OK");
        sleep();

        //Exterior dropdown - click
        exteriorDropdown.shouldBe(visible, Duration.ofSeconds(5)).click();
        System.out.println("Exterior dropdown click - OK");

        //Exterior - sunroof
        sunroof.shouldBe(visible, Duration.ofSeconds(5)).click();
        sleep();
        System.out.println("Add sunroof - OK");
        sleep();

        //Next step button - Additional Equipment
        nextStep();
        System.out.println("Next step button click on the 'Additional Equipment' page - OK");

        //Get final price
        sleep();
        System.out.println("Final price is: " + priceSection.innerText());
    }
}