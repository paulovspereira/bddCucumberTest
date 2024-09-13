package br.ce.wcaquino.runners;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

//Junit do cucumber
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/feature/cadastro_conta.feature",
		glue = "br.ce.scaquino.steps",
		tags = {"~@ignore"},
		plugin = {"pretty", "html:target/report-html", "json:target/report-json"}, // Relatório forma de texto
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		strict = false
	)
public class Runner {

	// Essa parte vai executar antes cucumber e resetar o banco da aplicação
	@BeforeClass
	public static void reset() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me");
	    driver.findElement(By.id("email")).sendKeys("paulo");
	    driver.findElement(By.name("senha")).sendKeys("p");
	    driver.findElement(By.tagName("button")).click();
	    driver.findElement(By.linkText("reset")).click();
	    driver.quit();

	}
}
