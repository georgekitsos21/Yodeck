package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.*;

public class ImagePage {
    private static final Logger log = LoggerFactory.getLogger(ImagePage.class);
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final WindowManager windowManager;
    private final ActionHelper actionHelper;
    private final ElementHelper elementHelper;
    private final By addImageButton = By.cssSelector(".first-action-button");
    private final By uploadFilesButton = By.cssSelector(".upload-image");
    private final By imageInputField = By.id("image-file-uploader");
    private final By confirmUploadImageButton = By.cssSelector("[data-bb-handler='confirm']");
    private final By nameField = By.cssSelector("[name='name']");
    private final By addDescriptionField = By.cssSelector(".description-field-set");
    private final By mediaDescriptionField = By.cssSelector(".media-description-field");
    private final By defaultDurationField = By.xpath("//input[@class='suffix-spinner spinner-input form-control ui-spinner-input']");
    private final By importFromUrlButton = By.cssSelector(".import-image-from-url");
    private final By descriptionImageField = By.cssSelector(".editor-container [name='description']");
    private final By addTagsInput = By.cssSelector(".tt-input[aria-label='Add Tags']");
    private final By saveButton = By.cssSelector(".submit-model");
    private final By firstPhotoLocator = By.xpath("//table/tbody/tr[1]");
    private final By tableImageCheckBox = By.xpath("//input[@aria-label='Select All Rows']");
    private final By actionsButton = By.id("actions_dropdown");
    private final By deleteSubButton = By.cssSelector("#delete-selected");
    private final By deleteButton = By.cssSelector(".danger-button");
    private final By availabilityButton = By.cssSelector("#toggle_availability[type='checkbox']");
    private final By addImageButtonAfterOnePhotoExists = By.cssSelector(".openGallery.primary-button");
    private final By errorImageUploadMessage = By.cssSelector("#upload_image_file [data-error='image']");
    private final By closeImagePopUp = By.cssSelector(".bootbox-close-button");


    public ImagePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 20);
        this.windowManager = new WindowManager(driver);
        this.actionHelper = new ActionHelper(driver);
        this.elementHelper = new ElementHelper(driver);
    }

    public void uploadImageFile(String absolutePathOfFile) {
        try {
            waitHelper.waitForUrlToBe(ConfigReader.getProperty("imageUrl"));
            Thread.sleep(2000);
            WebElement imageButton = elementHelper.findFirstVisibleElement(
                    addImageButton,
                    addImageButtonAfterOnePhotoExists
            );
            imageButton.click();
            driver.findElement(imageInputField).sendKeys(absolutePathOfFile);
            Thread.sleep(2000);
            waitHelper.waitForElementWithFluentWait(confirmUploadImageButton, 15, 2).click();

        } catch (WebDriverException e) {
            log.error("Error while uploading a photo on attempt", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void uploadInvalidImageType(String absolutePathOfFile) {
        try {
            waitHelper.waitForUrlToBe(ConfigReader.getProperty("imageUrl"));
            WebElement imageButton = elementHelper.findFirstVisibleElement(
                    addImageButton,
                    addImageButtonAfterOnePhotoExists
            );
            imageButton.click();
            driver.findElement(imageInputField).sendKeys(absolutePathOfFile);
        } catch (WebDriverException e) {
            log.error("Error while uploading a photo on attempt", e);
        }
    }

    public void addSomeName(String testName) {
        try {
            String expectedUrl = ConfigReader.getProperty("newImageUrl");
            waitHelper.waitForUrlToBe(expectedUrl);
            driver.findElement(nameField).sendKeys(testName);
        } catch (WebDriverException e) {
            log.error("Error while adding name: ", e);
        }
    }

    public void addSomeDescription(String testDesc) {
        try {
            String expectedUrl = ConfigReader.getProperty("newImageUrl");
            waitHelper.waitForUrlToBe(expectedUrl);
            driver.findElement(addDescriptionField).click();
            WebElement descriptionField = waitHelper.waitForVisibilityOfElement(mediaDescriptionField);
            Actions actions = new Actions(driver);
            actions.moveToElement(descriptionField).click().sendKeys(testDesc).build().perform();
        } catch (WebDriverException e) {
            log.error("Error while adding description: ", e);
            throw new RuntimeException(e);
        }
    }


    public String getDescription() {
        WebElement descriptionField = driver.findElement(descriptionImageField);
        waitHelper.waitForVisibilityOfElement(descriptionImageField).getText();
        return descriptionField.getText();
    }

    public void addSomeDefaultDuration(String duration) {
        try {
            WebElement inputElement = driver.findElement(defaultDurationField);
            waitHelper.waitForVisibilityOfElement(defaultDurationField);

            if ("7".equals(duration)) {
                while (true) {
                    String currentValue = inputElement.getAttribute("aria-valuenow");
                    assert currentValue != null;
                    if (currentValue.equals("7")) {
                        log.info("The value has been set to 7 successfully.");
                        break;
                    }
                    inputElement.sendKeys(Keys.ARROW_UP);
                }
            } else {
                log.warn("Provided duration is not supported: {}", duration);
            }
        } catch (WebDriverException e) {
            log.error("Error setting the duration: ", e);
        }
    }


    public void addSomeAvailability() {
        waitHelper.waitForPresenceOfElement(availabilityButton).click();
    }

    public void selectMonthAndYear() {
        try {
            WebElement dateField = driver.findElement(By.cssSelector("input[placeholder='Set start Date | Time']"));
            dateField.click();
            String desiredDate = "02/16/2025";

            while (true) {
                WebElement displayedMonthYear = driver.findElement(By.cssSelector("[title='Select Month']"));
                String currentMonthYear = displayedMonthYear.getText();

                if (currentMonthYear.equals("February 2025")) {
                    break;
                }
                driver.findElement(By.cssSelector("[data-action='next']")).click();
            }

            String dynamicLocator = String.format("[data-day='%s']", desiredDate);
            WebElement dateElement = driver.findElement(By.cssSelector(dynamicLocator));
            dateElement.click();
        } catch (WebDriverException e) {
            log.error("Error selecting the date: ", e);
        }
    }

    public void clickToImportUrl() {
        try {
            driver.findElement(closeImagePopUp).click();
            waitHelper.waitForVisibilityOfElement(addImageButtonAfterOnePhotoExists).click();
            Thread.sleep(1000);
            waitHelper.waitForVisibilityOfElement(importFromUrlButton).click();
            driver.findElement(By.cssSelector(".import-url-input")).sendKeys("https://pixabay.com/images/search/merry%20christmas/");
            driver.findElement(confirmUploadImageButton).click();
            waitHelper.waitForUrlToBe(ConfigReader.getProperty("newImageUrl"));
        } catch (WebDriverException e) {
            log.error("Error interacting with the web driver: ", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickToAddTagsInput(String tags) {
        driver.findElement(addTagsInput).sendKeys(tags);
    }

    public void clickToSaveButton() {
        waitHelper.waitForPresenceOfElement(saveButton).click();
    }

    public void clickMostRecentPhoto() {
        try {
            waitHelper.waitForVisibilityOfElement(firstPhotoLocator);
            WebElement firstPhoto = driver.findElement(firstPhotoLocator);
            waitHelper.waitForVisibilityOfElement(firstPhotoLocator);
            actionHelper.doubleClick(firstPhoto);
            log.info("Clicked on the most recent photo successfully.");
        } catch (WebDriverException e) {
            log.error("Error clicking on the most recent photo: ", e);
        }
    }


    public String getName() {
        waitHelper.waitForVisibilityOfElement(nameField).click();
        return driver.findElement((nameField)).getText();
    }

    public String getDuration() {
        return driver.findElement(defaultDurationField).getText();
    }

    public String getErrorMessage(){
        return waitHelper.waitForVisibilityOfElement((errorImageUploadMessage)).getText();
    }

    public void deletePhotos() {
        waitHelper.waitForVisibilityOfElement(tableImageCheckBox).click();
        driver.findElement(actionsButton).click();
        waitHelper.waitForVisibilityOfElement(deleteSubButton).click();
        waitHelper.waitForVisibilityOfElement(deleteButton).click();
        waitHelper.waitForVisibilityOfElement(addImageButton);
    }
}