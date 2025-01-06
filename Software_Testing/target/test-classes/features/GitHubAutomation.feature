Feature: GitHub Repository Status Verification

  Scenario: Verify GitHub repositories' status
    Given I open GitHub
    And I log in with valid credentials
    When I navigate to "Your repositories" page
    Then I should verify the task status of each repository
