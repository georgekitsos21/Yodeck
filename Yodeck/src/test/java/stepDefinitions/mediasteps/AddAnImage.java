package stepDefinitions.mediasteps;

import base.BaseTests;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AddAnImage extends BaseTests {

    @When("I navigate to image subsection and try to upload an image")
    public void uploadAnImageFunctionality() {
        welcomePage.clickMediaMenuAndOpenImages();
        imagePage.uploadImageFile("C:\\AutomationFolderTest\\Yodeck\\test-output\\testImageForUpload\\testUpload.png");
    }

    @When("I navigate to image subsection and try to upload an unsupported image type")
    public void uploadAnImageFunctionalityWithUnsupportedImageType() {
        welcomePage.clickMediaMenuAndOpenImages();
        imagePage.uploadInvalidImageType("C:\\AutomationFolderTest\\Yodeck\\test-output\\testImageForUpload\\testHtml\\HTML_3KB.html");
    }

    @Then("I see an error message related to unsupported type should be displayed")
    public void errorMessageForInValidImageType() {

        String actualErrorMessage = imagePage.getErrorMessage();
        String expectedErrorMessage = "Please choose a valid image file";
        Assert.assertEquals("Please choose a valid image file", expectedErrorMessage, actualErrorMessage);
    }

    @When("I try to add some {string} name")
    public void testNameFunctionality(String name) {
        imagePage.addSomeName(name);
    }

    @When("I try to add some {string} description")
    public void testFunctionality(String text) {
        imagePage.addSomeDescription(text);
    }

    @When("I try to add {string} as a default duration")
    public void testDefaultDurationFunctionality(String duration) {
        imagePage.addSomeDefaultDuration(duration);
    }

    @When("I try to add some {string} tags")
    public void addTestTags(String tags) {
        imagePage.clickToAddTagsInput(tags);
    }

    @When("I try to update the from availability")
    public void addSpecificDateYearInAvailability(){
        imagePage.addSomeAvailability();
        imagePage.selectMonthAndYear();
        imagePage.clickToSaveButton();
    }

    @When("I navigate back to the image importing and try to upload a photo from a url")
    public void uploadAnImageFromImportingUrl() {
        imagePage.clickToImportUrl();
    }

    @Then("I save the updated values and check if everything updated as expected with name {string}, description {string}, and duration {string}")
    public void checkTheUpdatedValues(String expectedName, String expectedDescription, String expectedDuration) {
        imagePage.clickMostRecentPhoto();
        String actualName = imagePage.getName();
        String actualDescription = imagePage.getDescription();
        String actualDuration = imagePage.getDuration();

        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedDescription, actualDescription);
        Assert.assertEquals(expectedDuration, actualDuration);
    }

    @Then("I press the save button")
    public void saveTheImportingPhoto(){
        imagePage.clickToSaveButton();
    }
}