package org.mfsafrica.test;

import com.github.javafaker.Faker;
import org.mfsafrica.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Exercise1Test extends BaseTest {
    Faker faker = new Faker();

    /**
     * Registers new user
     *
     * @throws Exception throws Exception if config could not be retrieved
     */
    @Test(testName = "Register user")
    public void registerUser() throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.visit();
        RegistrationPage registrationPage = loginPage.clickOnNewUserButton();
        Assert.assertEquals(registrationPage.getUserFormHeaderLabelText(), "Register to Book Store");
        registrationPage.fillAndSubmitUserForm(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "P@ssword1");

        //Commenting out this part of the code as captchas won't allow us get here
        //I am leaving the code here to see how it'd have been done if the tests were run on a test environment
        // String expectedSuccessMessage = "User Register Successfully.";
        // Assert.assertEquals(registrationPage.getAlertMessage(), expectedSuccessMessage);
        // registrationPage.dismissAlert();
    }

    /**
     * Selects Text Box under Elements accordion, fills the form and submits
     *
     * @throws Exception throws Exception if config could not be retrieved
     */
    @Test(testName = "Submit address form")
    public void submitTextBoxAddressForm() throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.visit();

        LeftAccordionPage leftAccordionPage = new LeftAccordionPage();
        leftAccordionPage.navigateToPage("Elements", "Text Box");
        TextBoxPage textBoxPage = new TextBoxPage();
        textBoxPage.submitAddress(faker.name().firstName(), faker.internet().emailAddress(),
                faker.address().fullAddress(), faker.address().fullAddress());
        Assert.assertTrue(textBoxPage.classAttributeValueExists(textBoxPage.getOutputElement(), "border"));
        Assert.assertFalse(textBoxPage.classAttributeValueExists(textBoxPage.getOutputElement(), "undefined"));
    }

    /**
     * Adds and deletes a record in a table
     *
     * @throws Exception throws Exception if config could not be retrieved
     */
    @Test(testName = "Add and delete table record")
    public void addAndDeleteTableRecord() throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.visit();

        LeftAccordionPage leftAccordionPage = new LeftAccordionPage();
        leftAccordionPage.navigateToPage("Elements", "Web Tables");
        WebTablesPage webTablesPage = new WebTablesPage();
        String email = faker.internet().emailAddress();
        webTablesPage.addRecord(faker.name().firstName(), faker.name().lastName(), email, "30", "100000", faker.commerce().department());

        Assert.assertTrue(webTablesPage.recordWithEmailExists(email), "Record was not added to the table");

        webTablesPage.deleteRecordWithEmail(email);

        Assert.assertFalse(webTablesPage.recordWithEmailExists(email), "Record was still present in the table after deletion");
    }

    /**
     * Upload and download file
     * Only works on a Mac with the file names in the images
     *
     * @throws Exception throws Exception if config could not be retrieved
     */
    @Test(testName = "Upload and download file")
    public void uploadAndDownloadFile() throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.visit();

        LeftAccordionPage leftAccordionPage = new LeftAccordionPage();
        leftAccordionPage.navigateToPage("Elements", "Upload and Download");
        UploadAndDownloadPage uploadAndDownloadPage = new UploadAndDownloadPage();
        uploadAndDownloadPage.uploadFile("doc-to-upload.png");
        Assert.assertEquals(uploadAndDownloadPage.getUploadedFilePath(), "C:\\fakepath\\QA Assessment Question.pdf");

        uploadAndDownloadPage.downloadFile();
    }

    /**
     * Prints child and parent frame
     *
     * @throws Exception throws Exception if config could not be retrieved
     */
    @Test(testName = "Print child and parent frame")
    public void printChildAndParentFrame() throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.visit();

        LeftAccordionPage leftAccordionPage = new LeftAccordionPage();
        leftAccordionPage.navigateToPage("Alerts, Frame & Windows", "Nested Frames");

        NestedFramesPage nestedFramesPage = new NestedFramesPage();
        nestedFramesPage.printParentAndChildFrame();
    }

    /**
     * Drags slider by a certain value
     *
     * @throws Exception throws Exception if config could not be retrieved
     */
    @Test(testName = "Drag slider")
    public void slideByValue() throws Exception {
        int valueToDragBy = 75;
        LoginPage loginPage = new LoginPage();
        loginPage.visit();

        LeftAccordionPage leftAccordionPage = new LeftAccordionPage();
        leftAccordionPage.navigateToPage("Widgets", "Slider");
        SliderPage sliderPage = new SliderPage();
        sliderPage.dragByValue(valueToDragBy);

        Assert.assertEquals(sliderPage.getSliderValueAttribute(), valueToDragBy, "Slider wasn't dragged to correct position");
    }
}
