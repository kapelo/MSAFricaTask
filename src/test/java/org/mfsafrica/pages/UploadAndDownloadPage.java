package org.mfsafrica.pages;

import org.mfsafrica.constants.FrameworkConstants;
import org.openqa.selenium.By;
import org.sikuli.script.*;

public class UploadAndDownloadPage extends BasePage {
    private final By downloadButtonLocator = By.id("downloadButton");
    private final By uploadedFilePathLocator = By.id("uploadedFilePath");

    /**
     * Uploads a file using sikuli
     */
    public void uploadFile(String fileToUploadNameImage) throws FindFailed {
        String sikuliImagesFolderPath = FrameworkConstants.getSikuliImagePath();

        Pattern chooseFileInputField = new Pattern(sikuliImagesFolderPath + "/choose-file-input.png");
        Pattern downloadsFolder = new Pattern(sikuliImagesFolderPath + "/downloads-folder.png");
        Pattern docToUpload = new Pattern(sikuliImagesFolderPath + "/" + fileToUploadNameImage);
        Pattern openButton = new Pattern(sikuliImagesFolderPath + "/open-button.png");

        Screen pcScreen = new Screen();
        pcScreen.wait(chooseFileInputField, 3);
        pcScreen.mouseDown(Button.LEFT);
        pcScreen.mouseUp(Button.LEFT);
        pcScreen.click(chooseFileInputField);
        pcScreen.wait(downloadsFolder, 3);
        pcScreen.click(downloadsFolder);
        pcScreen.click(docToUpload);
        pcScreen.click(openButton);
    }

    /**
     * Returns the uploaded file path
     *
     * @return String
     */
    public String getUploadedFilePath() {
        return getText(uploadedFilePathLocator, "uploaded file path label");
    }

    /**
     * Download file
     */
    public void downloadFile() {
        clickElement(downloadButtonLocator, "download button");
    }
}
