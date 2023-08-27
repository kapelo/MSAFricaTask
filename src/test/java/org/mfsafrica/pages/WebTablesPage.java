package org.mfsafrica.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTablesPage extends BasePage {
    private final By addRecordButtonLocator = By.id("addNewRecordButton");
    private final By firstNameFieldLocator = By.id("firstName");
    private final By lastNameFieldLocator = By.id("lastName");
    private final By emailFieldLocator = By.id("userEmail");
    private final By ageFieldLocator = By.id("age");
    private final By salaryFieldLocator = By.id("salary");
    private final By departmentFieldLocator = By.id("department");
    private final By submitButtonLocator = By.id("submit");
    private final By tableRowLocator = By.cssSelector(".rt-tr-group > .rt-tr");
    private final By deleteRecordActionButton = By.cssSelector("span[title=\"Delete\"]");

    /**
     * Add record to a table UI
     *
     * @param firstName employee first name
     * @param lastName employee last name
     * @param email employee email
     * @param age employee age
     * @param salary employee salary
     * @param department employee department
     */
    public void addRecord(String firstName, String lastName, String email, String age, String salary, String department) {
        clickElement(addRecordButtonLocator, "Add record button");
        enterText(firstNameFieldLocator, firstName, "first name field");
        enterText(lastNameFieldLocator, lastName, "last name field");
        enterText(emailFieldLocator, email, "email field");
        enterText(ageFieldLocator, age, "age field");
        enterText(salaryFieldLocator, salary, "salary field");
        enterText(departmentFieldLocator, department, "department field");
        clickElement(submitButtonLocator, "Submit button");
    }

    /**
     * Delete record using the email in the record
     *
     * @param email email contained in record to be deleted
     */
    public void deleteRecordWithEmail(String email) {
        List<WebElement> rowElements = getElements(tableRowLocator, "table row");

        for ( WebElement rowElement : rowElements ) {
            List<WebElement> columnElements = rowElement.findElements(By.xpath("./child::*"));

            if (columnElements.get(3).getText().equalsIgnoreCase(email)) {
                columnElements.get(6).findElement(deleteRecordActionButton).click();

                break;
            }
        }
    }

    /**
     * Checks if record with email exists
     *
     * @param email email contained in record
     * @return boolean True or False value indicating record has been found or not
     */
    public boolean recordWithEmailExists(String email) {
        List<WebElement> rowElements = getElements(tableRowLocator, "table row");

        for ( WebElement rowElement : rowElements ) {
            List<WebElement> columnElements = rowElement.findElements(By.xpath("./child::*"));

            if (columnElements.get(3).getText().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }
}
