Feature: GitHub Application Testing
  To verify the key functionalities of GitHub using automated tests.

  Scenario: Login to GitHub
    Given the user is on the GitHub login page
    When the user enters valid credentials
    Then the user should see their GitHub dashboard

  Scenario: Create a new repository
    Given the user is logged in
    When the user creates a new repository named "AutomationRepo"
    Then the repository should appear in the repository list

  Scenario: Delete a repository
    Given the repository "AutomationRepo" exists
    When the user deletes the repository
    Then the repository should no longer appear in the repository list

  Scenario: Logout from GitHub
    Given the user is logged in
    When the user logs out
    Then the user should see the GitHub login page