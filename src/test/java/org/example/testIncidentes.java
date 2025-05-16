package org.example;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class testIncidentes   {
    static WebDriver driver;
    static String title;
    static WebDriverWait wait;

    //Antes de empezar a correr los tests, se va a realizar login
    @BeforeAll
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        driver.get("https://seguridad-test.ubicuo.com.ar");
        driver.manage().window().maximize();

        WebElement user = driver.findElement(By.id("Email"));
        WebElement pass = driver.findElement(By.id("Password"));
        user.sendKeys("ubicuotesting+JefeResponsable@gmail.com");
        pass.sendKeys("CamuzziNivel1");
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        submitButton.click();
    }

    //Obtiene el titulo de la pagina y verifica estar en el lugar correcto
    @Test
    void Ingresar() {
        title = driver.getTitle();
        assertEquals ("CAMUZZI GAS - Dashboard", title);
    }

    //Abre el formulario de accidente contratista
    @Test
    void guardarALPC() {
        //ir a la pagina de incidentes del establecimiento 1285 corroborando estar en la pagina correcta
        driver.get("https://seguridad-test.ubicuo.com.ar/Establecimiento/Incidentes/1285");
        title = driver.getTitle();
        assertEquals("Buenos Aires Sur - Incidentes Tres Arroyos", title);

        //Buscar elementos menu
        //menu hamburguesa
        WebElement hamburguerMenu = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/a/i"));
        hamburguerMenu.click();
        //opcion nuevo
        WebElement incidentesMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#menu-incidentes']")));
        incidentesMenu.click();
        //opcion Accidente Laboral Contratista
        WebElement contratista = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("ACCIDENTE LABORAL CONTRATISTA")));
        contratista.click();

        //Combo empresa
        WebElement comboEmpresa = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/form/div/div[2]/div[1]/div[2]/div[1]/div/a")));
        comboEmpresa.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-drop")));

        List<WebElement> opcionesEmpresa = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'select2-result-label')]")
        ));

        // Seleccionar un índice aleatorio
        Random rand = new Random();
        int indiceAleatorio = rand.nextInt(opcionesEmpresa.size());

        // Hacer clic en la opción seleccionada
        opcionesEmpresa.get(indiceAleatorio).click();

 //        //Combo nombre obra
        WebElement comboNombreObra = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("s2id_obra-contratista")));
        comboNombreObra.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("s2id_obra-contratista")));
        List<WebElement> opcionesComboObra = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'select2-result-label')]")
        ));

        // Seleccionar un índice aleatorio
        rand = new Random();
        indiceAleatorio = rand.nextInt(opcionesComboObra.size());

        // Hacer clic en la opción seleccionada
        opcionesComboObra.get(indiceAleatorio).click();


//        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"select2-drop\"]")));
//
//        List<WebElement> opcionesNombreObra = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//                By.id("select2-results-4020")
//        ));
//
//       int indiceNombreObra = rand.nextInt(opcionesNombreObra.size());
//       opcionesNombreObra.get(indiceNombreObra).click();

        // Esperar hasta que el combo sea visible




//        WebElement opcionObra = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'select2-result-label') and normalize-space(text())='PROTAN S.A']")));
//        opcionObra.click();
//
//        WebElement comboCodigoObra = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/form/div/div[2]/div[1]/div[2]/div[1]/div/a")));
//        comboCodigoObra.click();
//        WebElement opcionCodigoObra = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'select2-result-label') and normalize-space(text())='PROTAN S.A']")));
//        opcionCodigoObra.click();
//
//        WebElement comboContrato = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/form/div/div[2]/div[1]/div[2]/div[1]/div/a")));
//        comboContrato.click();
//        WebElement opcionContrato = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'select2-result-label') and normalize-space(text())='PROTAN S.A']")));
//        opcionContrato.click();























        //Seccion datos del afectado
        WebElement nombre = driver.findElement(By.id("personal"));
        WebElement dni = driver.findElement(By.id("dni"));
        WebElement calendario = driver.findElement(By.id("fecha-nacimiento"));
        WebElement puesto = driver.findElement(By.id("puesto-trabajo"));

        nombre.sendKeys("Analia Bauer");
        dni.sendKeys("36795310");
        calendario.sendKeys("02/04/1992");
        calendario.sendKeys(Keys.ENTER);
        puesto.sendKeys("QA");

        //Seccion Tipo de accidente
        WebElement relacionOcasion = driver.findElement(By.xpath("//*[@id=\"s2id_taccidente-ocasion\"]/a"));
        relacionOcasion.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("select2-drop")));

        WebElement opcionRelacionOcasion = driver.findElement(By.id("select2-result-label-277"));
        opcionRelacionOcasion.click();

        WebElement releacionConsecuencias = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/form/div/div[2]/div[1]/div[10]/div[2]/div/a"));
        releacionConsecuencias.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"select2-drop\"]")));

        WebElement opcionRelacionConsecuencias = driver.findElement(By.id("select2-result-label-4831"));
        opcionRelacionConsecuencias.click();

        //Localizar boton siguiente
        WebElement btnSiguiente = driver.findElement(By.xpath("//input[@value='Siguiente']"));
        btnSiguiente.click();




    }

    //Despues de terminar los tests cierra el navegador
//    @AfterAll
//    public static void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
