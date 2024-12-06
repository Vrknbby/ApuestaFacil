package google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class Google {
	 WebDriver driver;
	    private WebElement searchInput;
	    private WebElement autocompleteList;

	    @Before
	    public void iniciar_driver() {
	        System.setProperty("webdriver.chrome.driver", 
	                "D:\\Programas\\chromedriver-win32\\chromedriver.exe");
	        driver = new ChromeDriver();
	    }

	    @Given("Se carga la URL de mi aplicacion")
	    public void cargar_url_aplicacion() {
	        driver.get("http://localhost:4200/listado");
	    }

	    @When("Escribo {string} en la barra de búsqueda")
	    public void escribir_en_barra_busqueda(String texto) {
	        searchInput = driver.findElement(By.id("search-bar"));
	        searchInput.sendKeys(texto);
	    }

	    @And("Verifico que el campo no está deshabilitado")
	    public void verificar_campo_habilitado() {
	        searchInput = driver.findElement(By.id("search-bar"));
	        Assert.assertTrue("El campo de búsqueda está deshabilitado", searchInput.isEnabled());
	    }

	    @Then("El modelo de Angular debe reflejar el valor {string}")
	    public void verificar_ng_reflect_model(String textoEsperado) {
	        searchInput = driver.findElement(By.id("search-bar"));
	        String ngReflectModel = searchInput.getAttribute("ng-reflect-model");
	        Assert.assertEquals("El modelo no refleja el texto esperado", textoEsperado, ngReflectModel);
	    }
	    
	    // Nuevo paso para verificar que la lista de autocompletado se muestra
	    @And("Se muestra la lista de opciones")
	    public void verificar_lista_autocompletado() {
	        autocompleteList = driver.findElement(By.cssSelector("ul.autocomplete-list"));
	        Assert.assertTrue("La lista de autocompletado no se mostró", autocompleteList.isDisplayed());
	    }

	    // Nuevo paso para seleccionar una opción de la lista de autocompletado
	    @When("Selecciono la opción {string} de la lista")
	    public void seleccionar_opcion_lista(String opcionSeleccionada) {
	        WebElement option = driver.findElement(By.xpath("//li[contains(text(), '" + opcionSeleccionada + "')]"));
	        option.click();
	    }

	    // Nuevo paso para verificar que el input se deshabilita después de la selección
	    @Then("El input de búsqueda debe estar deshabilitado")
	    public void verificar_input_deshabilitado() {
	        searchInput = driver.findElement(By.id("search-bar"));
	        Assert.assertTrue("El input debería estar deshabilitado", !searchInput.isEnabled());
	    }
	    
	    @After()
		public void cerrar_driver() {
			driver.close();
		}

	    
}
