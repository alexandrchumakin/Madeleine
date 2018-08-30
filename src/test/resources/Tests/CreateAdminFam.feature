Feature: CreateAdminFam
  As an admin-pro, I can create an admin-fam who will manage the carer-fam account or create a carer-fam account

# MADZIA-84

  Background:
    Given I am logged into site
    When I am on page "Dashboard"
    When I select "Residents" from sidebar

  Scenario: Create new Senior for automation
    When I select "Add a resident" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname |
      | TestAutomation | Auto              |
    And I click on "Create new account"
    And I am on page "Senior account"
    Then I should see "Status message" with "Active context: admin-pro-foreign"

  Scenario: "Create carer-fam" button is enabled under "Family Page" section
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    Then I should see "Create carer-fam" enabled

  Scenario: The system adds user in database with the information which are typed
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    And I click on "Create carer-fam"
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Birthday   | Phone        | Relations | Your valid e-mail  |
      | test name01    | test surname01    | 2010-10-10 | 0590 111 111 | fils      | {generateData}@a.a |
    And I click on "Create new account"
    And I am on page "Carer fam account"
    And I click on "Edit admin carer fam profile"
    Then I should see form with
      | First name  | Last name      | Birthday        | Phone      | Relations | E-mail                  |
      | test name01 | test surname01 | 10 October 2010 | 0590111111 | fils      | {lastGeneratedData}@a.a |

  Scenario: If the checkbox "admin-fam" is selected, System adds the information "admin-fam" in the array "role" of this user and the senior's name
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    And I click on "Create carer-fam"
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Birthday   | Phone      | Relations | Your valid e-mail  |
      | test name02    | test surname02    | 2010-10-10 | 0590112112 | daughter  | {generateData}@a.a |
    And I click on "Admin-fam"
    And I click on "Create new account"
    And I am on page "Admin fam account"
    Then I should see "Status message" with "Active context: admin-pro-foreign"

  Scenario: The system runs the profile page of the user which was created
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    And I click on "Create carer-fam"
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Birthday   | Phone      | Relations | Your valid e-mail  |
      | test name03    | test surname03    | 2010-10-10 | 0590113113 | friend    | {generateData}@a.a |
    And I click on "Create new account"
    And I am on page "Carer fam account"
    Then I should see "Profile" with "test name03 test surname03\n{lastGeneratedData}@a.a"
    And I should see "Leave a message" enabled
    And I should see "Family Page" enabled
    And I should see "Edit admin carer fam profile" enabled

  Scenario: If the admin-pro tries to create user with taken email, the system displays the message on the top of the Call-to-action
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    And I click on "Create carer-fam"
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Your valid e-mail       |
      | test name03    | test surname03    | {lastGeneratedData}@a.a |
    And I click on "Create new account"
    Then I should see "Error message" with "The e-mail address {lastGeneratedData}@a3.a is already taken."

  Scenario: If the admin-pro doesn't type all the compulsory information, the system display the message on the top of the Call-to-action - the name
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    And I click on "Create carer-fam"
    And I am on page "User Account"
    And I populate form with
      | Enter the surname | Your valid e-mail  |
      | test surname04    | {generateData}@a.a |
    And I click on "Create new account"
    Then I should see "Error message" with "First name field is required."
    And I should see "Enter the name" highlighted

  Scenario: If the admin-pro doesn't type all the compulsory information, the system display the message on the top of the Call-to-action - the surname
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    And I click on "Create carer-fam"
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Your valid e-mail  |
      | test name04    | {generateData}@a.a |
    And I click on "Create new account"
    Then I should see "Error message" with "Last name field is required."
    And I should see "Enter the surname" highlighted

  Scenario: If the admin-pro doesn't type all the compulsory information, the system display the message on the top of the Call-to-action - e-mail
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    And I click on "Create carer-fam"
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname |
      | test name04    | test surname04    |
    And I click on "Create new account"
    Then I should see "Error message" with "E-mail field is required."
    And I should see "Your valid e-mail" highlighted

  Scenario: If the admin-pro doesn't type all the compulsory information, the system display the message on the top of the Call-to-action - all compulsory fields
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Family Page"
    And I click on "Create carer-fam"
    And I am on page "User Account"
    And I click on "Create new account"
    Then I should see "Error message" with "E-mail field is required.\nFirst name field is required.\nLast name field is required."
    And I should see "Enter the name" highlighted
    And I should see "Enter the surname" highlighted
    And I should see "Your valid e-mail" highlighted

  Scenario: Delete previously created Senior
    When I select "All residents" from sidebar
    And I select "TestAutomation Auto" from sidebar
    And I click on "Edit senior profile"
    And I click on "Cancel account"
    When I am on page "Cancel account form"
    And I click on "Cancel account"