@Images
Feature: User upload image functionality
  As a user I want to log in to my account
  So that I can access the application features
  and upload one and multiple images

@Images
Scenario: Successful upload an image file(png)
When I navigate to the first open tab
And I am on the login page
And I refresh the page
And I enter a valid email "kitsos212@test.com"
And I click the continue button
And I enter a valid password "Marcfit21@"
And I click the login button
Then I should be logged in successfully
When I navigate to image subsection and try to upload an image

@Regression
Scenario:Adding image settings in order to ensure that values will be updated
  When I try to add some "Kitsos" description
  And I try to add "7" as a default duration
  And I try to add some "test" tags
  And I try to update the from availability
  Then I save the updated values and check if everything updated as expected with name "HappyNewYear.webp", description "KitsosSET", and duration "7"

@Regression
Scenario: Unsuccessful upload an image file(html)
  When I navigate to image subsection and try to upload an unsupported image type
  Then I see an error message related to unsupported type should be displayed

@ImageImportFromURL
Scenario: Importing a photo from URL
  When I navigate back to the image importing and try to upload a photo from a url
  And I try to add some "KitsosNamePhotoFromUrl" name
  And I try to add some "KitsosDescriptionPhotoFromUrl" description
  And I try to add "17" as a default duration
  And I try to add some "testTagFromImportPhoto" tags
  And I try to update the from availability
