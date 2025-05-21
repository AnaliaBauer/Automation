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
    static Random random;

    //Antes de empezar a correr los tests, se va a realizar login
    @BeforeAll
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
       // driver.get("https://seguridad-test.ubicuo.com.ar");
        driver.get("http://localhost:3300/");
        driver.manage().window().maximize();
        random = new Random();

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
        //driver.get("https://seguridad-test.ubicuo.com.ar/Incidente/NuevoIncidenteContratista/1285");
        driver.get("http://localhost:3300/Incidente/NuevoIncidenteContratista/1277");
        title = driver.getTitle();
        assertEquals("La Plata - Nuevo Accidente Laboral Contratista", title);

        ProcesarComboEmpresa();
        ProcesarComboNombreObra();
//        ProcesarComboCodigoObra();
//        ProcesarComboNroContrato();
        ProcesarSeccionDatosAfectado();
        ProcesarSeccionTipoAccidente();

        driver.findElement(By.xpath("//input[@value='Siguiente']")).click();

    }

    //METODOS
    private static void ProcesarComboEmpresa() {
//        WebElement comboEmpresa = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/form/div/div[2]/div[1]/div[2]/div[1]/div/a")));

        WebElement comboEmpresa = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("s2id_nombre-empresa")));

        comboEmpresa.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-drop")));
        List<WebElement> opcionesEmpresa = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'select2-result-label')]")
        ));
        opcionesEmpresa.get(random.nextInt(opcionesEmpresa.size())).click();
    }

    private static void ProcesarComboNombreObra(){
        WebElement comboNombreObra = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("s2id_obra-contratista")));
        comboNombreObra.click();

        List<WebElement> opcionesComboObra = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.id("s2id_obra-contratista") , 0));
        int opcion = random.nextInt(opcionesComboObra.size());
        opcionesComboObra.get(1).click();
     }

    private static void ProcesarComboCodigoObra(){

        WebElement comboCodigoObra = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("")));
        comboCodigoObra.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("")));
        List<WebElement> opcionesComboCodigoObra = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("")
        ));
        opcionesComboCodigoObra.get(random.nextInt(opcionesComboCodigoObra.size())).click();
    }

    private static void ProcesarComboNroContrato(){
        WebElement comboComboNroContrato = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("")));
        comboComboNroContrato.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("")));
        List<WebElement> opcionesComboNroContrato = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("")
        ));
        opcionesComboNroContrato.get(random.nextInt(opcionesComboNroContrato.size())).click();


    }

    private static void ProcesarSeccionDatosAfectado(){

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
    }

    private static void     ProcesarSeccionTipoAccidente(){
        WebElement relacionOcasion = driver.findElement(By.xpath("//*[@id=\"s2id_taccidente-ocasion\"]/a"));
        relacionOcasion.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("select2-drop")));
        WebElement opcionRelacionOcasion = driver.findElement(By.id("select2-result-label-277"));
        opcionRelacionOcasion.click();
        WebElement releacionConsecuencias = driver.findElement(By.xpath("//*[@id=\"s2id_taccidente-consecuencias\"]/a"));
        releacionConsecuencias.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"select2-drop\"]")));
        WebElement opcionRelacionConsecuencias = driver.findElement(By.xpath("select2-result-label-21"));
        opcionRelacionConsecuencias.click();

    }



    //Despues de terminar los tests cierra el navegador
//    @AfterAll
//    public static void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
