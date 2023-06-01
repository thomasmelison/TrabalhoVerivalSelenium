package com.vvs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Este teste simula o cadastro de um novo owner e depois o ato de encontrar o
     * owner cadastrado
     */

    private String ownerFirstName = "Thomas";
    private String ownerLastName = "Mello";
    private String ownersAddress = "Av. Ipiranga, 6681 - Partenon";
    private String ownersCity = "Porto Alegre";
    private String ownersPhone = "975837465";
    @Test
    public void AddOwnerTest()
    {

        WebDriver browser = new ChromeDriver();
        addOwner(browser);

        // Checa as owners infomation
        WebElement ownersNameInformation = browser.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[1]/td/b"));
        Assertions.assertEquals(ownerFirstName + " " + ownerLastName,ownersNameInformation.getText());

        WebElement ownersAddressInformation = browser.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[2]/td"));
        Assertions.assertEquals(ownersAddress,ownersAddressInformation.getText());

        WebElement ownersCityInformation = browser.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[3]/td"));
        Assertions.assertEquals(ownersCity,ownersCityInformation.getText());

        WebElement ownersTelephoneInformation = browser.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[4]/td"));
        Assertions.assertEquals(ownersPhone,ownersTelephoneInformation.getText());

        browser.quit();
    }


    @Test
    public void FindOwner()
    {

        WebDriver browser = new ChromeDriver();

        addOwner(browser);

        // Depois de adicionar um owner, procuramos ele

        //clica no botão FindOwners.
        WebElement findOwnersButton = browser.findElement(By.xpath("//*[@id=\"main-navbar\"]/ul/li[2]/a/span[2]"));
        findOwnersButton.click();

        //digita no campo de last name
        WebElement lastNameTextField = browser.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastNameTextField.clear();
        lastNameTextField.sendKeys(ownerLastName);

        //clica no botão findOwners na pagina find owners após ter digitado o sobrenome do individuo
        WebElement findOwnersButtonOnPage = browser.findElement(By.xpath("//*[@id=\"search-owner-form\"]/div[2]/div/button"));
        findOwnersButtonOnPage.click();

        //Pega o PageSource da página para ver se existem campos contendo o nome do usuário cadastrado
        String pageText = browser.getPageSource();

        //Checa se encontrou o nome do owner
        Assertions.assertTrue(pageText.contains(ownerFirstName + " " + ownerLastName));

        //Checa se encontrou o endereço do owner
        Assertions.assertTrue(pageText.contains(ownersAddress));

        //checa se encontrou a cidade do owner
        Assertions.assertTrue(pageText.contains(ownersCity));

        //checa se encontrou o telefone do owner
        Assertions.assertTrue(pageText.contains(ownersPhone));

        browser.quit();
    }


    // Função que adiciona o user, o parametro é o browser, que foi inicializado no teste.
    public void addOwner(WebDriver browser ){

        browser.get("http://localhost:8080");

        // acha o elemento Find Owners no header e clica
        WebElement findOwnerHeaderButton = browser.findElement(By.xpath("//*[@id=\"main-navbar\"]/ul/li[2]/a/span[2]"));
        findOwnerHeaderButton.click();

        // Acha o botão Add Owner e clica
        WebElement addOwnerButton = browser.findElement(By.xpath("//*[@id=\"search-owner-form\"]/a"));
        addOwnerButton.click();

        //Acha os campos de texto e adiciona as informações do Owner
        WebElement firstNameTextField = browser.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstNameTextField.clear();
        firstNameTextField.sendKeys(ownerFirstName);

        WebElement lastNameTextField = browser.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastNameTextField.clear();
        lastNameTextField.sendKeys(ownerLastName);

        WebElement addressTextField = browser.findElement(By.xpath("//*[@id=\"address\"]"));
        addressTextField.clear();
        addressTextField.sendKeys(ownersAddress);

        WebElement cityTextField = browser.findElement(By.xpath("//*[@id=\"city\"]"));
        cityTextField.clear();
        cityTextField.sendKeys(ownersCity);

        WebElement telephoneTextField = browser.findElement(By.xpath("//*[@id=\"telephone\"]"));
        telephoneTextField.clear();
        telephoneTextField.sendKeys(ownersPhone);

        // Clica no botão de adicionar
        addOwnerButton = browser.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button"));
        addOwnerButton.click();
    }
}