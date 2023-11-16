package Cucumber.utils.AutomationSteps;

import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class RegistrationAndValidationSteps {

    private AppiumDriver<MobileElement> driver;
    @Test
    @Dado("que estou no aplicativo de contatos")
    public void abrirAplicativoContatos() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/ViniciusFerraz/Library/Android/sdk");

        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");

            driver = new AndroidDriver<>(url, capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    @Quando("navego até a tela de adicionar um novo contato")
    public void navegarParaTelaAdicionarContato() {
        WebElement menuContatos = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=Contacts]"));
        menuContatos.click();

        WebElement botaoAdicionarContato = driver.findElement(By.id("com.android.contacts:id/add_account_button"));
        botaoAdicionarContato.click();
    }
    @Test
    @Quando("insiro as informações de contato")
    public void preencherInformacoesContato() {
        WebElement campoNome = driver.findElement(By.id("com.android.contacts:id/contact.EditText.FirstName"));
        campoNome.sendKeys("Vinicius");

        WebElement campoSobrenome = driver.findElement(By.id("com.android.contacts:id/contact.EditText.LastName"));
        campoSobrenome.sendKeys("Ferraz");

        WebElement campoTelefone = driver.findElement(By.id("com.android.contacts:id/contact.EditText.Phone"));
        campoTelefone.sendKeys("3499993478");

        WebElement campoEmail = driver.findElement(By.id("com.android.contacts:id/contact.EditText.Email"));
        campoEmail.sendKeys("viniciusferraz@teste.com");
    }
    @Test
    @Quando("confirmo a criação do contato")
    public void confirmarCriacaoContato() {
        WebElement botaoConfirmar = driver.findElement(By.id("com.android.contacts:id/editor_menu_save_button"));
        botaoConfirmar.click();
    }
    @Test
    @Entao("valido se os dados do contato conferem")
    public void validarDadosContato() {
        WebElement nomeContatoCriado = driver.findElement(By.id("com.android.contacts:id/large_title"));
        WebElement sobrenomeContatoCriado = driver.findElement(By.id("com.android.contacts:id/header"));

        assert nomeContatoCriado.getText().equals("Vinicius");
        assert sobrenomeContatoCriado.getText().equals("Ferraz");
    }
}