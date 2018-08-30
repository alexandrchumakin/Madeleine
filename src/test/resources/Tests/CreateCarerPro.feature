Feature: CreateCarerPro
  Visit/access his own profile by the button in the header

# MADZIA-116

  Background:
    Given I am logged into site
    When I am on page "Dashboard"
    And I select "Caregivers" from sidebar

  Scenario: Carer-pro User Account page validation
    When I click on "Create carer pro account"
    And I am on page "User Account"
    Then I should see "Enter the name" enabled
    And I should see "Enter the surname" enabled
    And I should see "Job" enabled
    And I should see "Your valid e-mail" enabled
    And I should see "Phone" enabled

  Scenario: Create carer-pro with only required fields
    When I select "Create carer pro account" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Your valid e-mail  | Job    |
      | TestCarer1     | Auto              | {generateData}@a.a | doctor |
    And I click on "Create new account"
    And I am on page "Carer pro account"
    And I click on "Edit carer pro profile"
    Then I should see form with
      | First name | Last name | Job    | E-mail                  |
      | TestCarer1 | Auto      | doctor | {lastGeneratedData}@a.a |

  Scenario: Open created carer-pro from Caregivers with required fields
    When I select "All caregivers" from sidebar
    And I select "TestCarer1 Auto" from sidebar
    And I am on page "Carer pro account"
    And I click on "Edit carer pro profile"
    Then I should see "Profile" with "TestCarer1 Auto\ndoctor\n{lastGeneratedData}@a.a"

  Scenario: Create carer-pro with optional fields
    When I select "Create carer pro account" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Your valid e-mail  | Job    | Picture                | Birthday   |
      | TestCarer2     | Auto              | {generateData}@a.a | doctor | black_rage_shaking.jpg | 2010-10-10 |
    And I click on "Create new account"
    And I am on page "Carer pro account"
    Then I should see form with
      | First name | Last name | Job    | E-mail                  | Picture                | Birthday        |
      | TestCarer2 | Auto      | doctor | {lastGeneratedData}@a.a | black_rage_shaking.jpg | 10 October 2010 |

  Scenario: Open created carer-pro from Caregivers with optional fields
    When I select "All caregivers" from sidebar
    And I select "TestCarer2 Auto" from sidebar
    And I am on page "Carer pro account"
    And I click on "Edit carer pro profile"
    Then I should see "Profile" with "TestCarer2 Auto\ndoctor\n{lastGeneratedData}@a.a"

  Scenario: If user doesn't type all the compulsory information, the system displays the message on the top of the Call-to-action - First name
    When I select "Create carer pro account" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the surname | Your valid e-mail  | Job    |
      | Auto              | {generateData}@a.a | doctor |
    And I click on "Create new account"
    Then I should see "Error message" with "First name field is required."
    And I should see "Enter the name" highlighted

  Scenario: If user doesn't type all the compulsory information, the system displays the message on the top of the Call-to-action - Last name
    When I select "Create carer pro account" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Your valid e-mail  | Job    |
      | TestCarer      | {generateData}@a.a | doctor |
    And I click on "Create new account"
    Then I should see "Error message" with "Last name field is required."
    And I should see "Enter the surname" highlighted

  Scenario: If user doesn't type all the compulsory information, the system displays the message on the top of the Call-to-action - E-mail
    When I select "Create carer pro account" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Job    |
      | TestCarer      | Auto              | doctor |
    And I click on "Create new account"
    Then I should see "Error message" with "E-mail field is required."
    And I should see "Your valid e-mail" highlighted

  Scenario: If user doesn't type all the compulsory information, the system displays the message on the top of the Call-to-action - Job
    When I select "Create carer pro account" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Your valid e-mail  |
      | TestCarer      | Auto              | {generateData}@a.a |
    And I click on "Create new account"
    Then I should see "Error message" with "Job field is required."
    And I should see "Job" highlighted

  Scenario: If user doesn't type all the compulsory information, the system displays the message on the top of the Call-to-action - all the required fields
    When I select "Create carer pro account" from sidebar
    And I am on page "User Account"
    And I click on "Create new account"
    Then I should see "Error message" with "Job field is required."
    And I should see "Enter the name" highlighted
    And I should see "Enter the surname" highlighted
    And I should see "Your valid e-mail" highlighted
    And I should see "Job" highlighted

  Scenario: Delete carer-pro 1
    When I select "All caregivers" from sidebar
    And I select "TestCarer1 Auto" from sidebar
    And I am on page "Carer pro account"
    And I click on "Edit carer pro profile"
    And I click on "Cancel account"
    And I am on page "Cancel account form"
    And I click on "Cancel account"

  Scenario: Delete carer-pro 2
    When I select "All caregivers" from sidebar
    And I select "TestCarer2 Auto" from sidebar
    And I am on page "Carer pro account"
    And I click on "Edit carer pro profile"
    And I click on "Cancel account"
    And I am on page "Cancel account form"
    And I click on "Cancel account"