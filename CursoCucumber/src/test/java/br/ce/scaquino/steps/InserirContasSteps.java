package br.ce.scaquino.steps;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import junit.framework.Assert;

public class InserirContasSteps {
	
	//Conect nossos teste com brower
	private WebDriver driver;

	@Dado("^que estou acessando a aplicação$")
	public void queEstouAcessandoAAplicação() throws Throwable {
		driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me");
	}

	@Quando("^informo o usuário \"([^\"]*)\"$")
	public void informoOUsuário(String arg1) throws Throwable {
		// Botão inspecionar o elemento da tela
		//Localizar um elemento
	   driver.findElement(By.id("email")).sendKeys(arg1);
	}

	@Quando("^a senha \"([^\"]*)\"$")
	public void aSenha(String arg1) throws Throwable {
		   driver.findElement(By.name("senha")).sendKeys(arg1);

	}

	@Quando("^seleciono entrar$")
	public void selecionoEntrar() throws Throwable {
		   driver.findElement(By.tagName("button")).click();

	}

	@Então("^visualizo a página inicial$")
	public void visualizoAPáginaInicial() throws Throwable {
		   // Pegar o texto na outra tela
		   String texto = driver.findElement(By.xpath("//div[@class='alert alert-sucess']")).getText();
		   Assert.assertEquals("Bem vindo, paulo", texto);

	}

	@Quando("^seleciono Contas$")
	public void selecionoContas() throws Throwable {
			driver.findElement(By.linkText("Conta")).click();
	}

	@Quando("^seleciono Adicionar$")
	public void selecionoAdicionar() throws Throwable {
			driver.findElement(By.linkText("Adicionar")).click();
	}

	@Quando("^informo a conta \"([^\"]*)\"$")
	public void informoAConta(String arg1) throws Throwable {
		driver.findElement(By.id("nome")).sendKeys(arg1);;
	}

	@Quando("^seleciono Salvar$")
	public void selecionoSalvar() throws Throwable {
		 driver.findElement(By.tagName("button")).click();
	}

	@Então("^a conta é inserida com sucesso$")
	public void aContaÉInseridaComSucesso() throws Throwable {
		  String texto = driver.findElement(By.xpath("//div[@class='alert alert-sucess']")).getText();
		   Assert.assertEquals("Conta adicionada com sucesso", texto);

	}
	
	@Então("^sou notificar que o nome da conta é obrigatório$")
	public void souNotificarQueONomeDaContaÉObrigatório() throws Throwable {
		  	String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		   Assert.assertEquals("Informa o nome da conta", texto);
	}
	
	@Então("^sou notificado que já existe uma conta com esse nome$")
	public void souNotificadoQueJáExisteUmaContaComEsseNome() throws Throwable {
	  	String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertEquals("Já existe uma conta com esse nome", texto);
	}

	@Before
	public void inicio() {
		System.out.println("Começando aqui");
	}
	
	// Hooks sõa trechos de código que tem executar antes ou depois dos cenários de testes
	@After(order = 0)
	public void fecharBrower() {
		driver.quit();
		System.out.println("terminando");
	}
	
	// Gera imagens dos testes
	@After(order = 1)
	public void screenshot(Scenario cenario) {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(file, new File("/target/screenshot/"+cenario.getId()+"nome.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
