Feature: CreateSenior
  Visit/access his own profile by the button in the header

# MADZIA-117
  Background:
    Given I am logged into site
    When I am on page "Dashboard"
    And I select "Residents" from sidebar

  Scenario: Verify create Senior page
    When I select "Add a resident" from sidebar
    And I am on page "User Account"
    Then I should see "Enter the name" enabled
    And I should see "Enter the surname" enabled
    And I should see "Birthday" enabled
    And I should see "Picture" enabled
    And I should see "Job" enabled

  Scenario: Create Senior with only required fields
    When I select "Add a resident" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name  | Enter the surname |
      | TestSenior1     | Auto              |
    And I click on "Create new account"
    And I am on page "Senior account"
    And I click on "Edit senior profile"
    Then I should see form with
      | First name  | Last name |
      | TestSenior1 | Auto      |

  Scenario: Open created senior from Residents with required fields
    When I select "All residents" from sidebar
    And I select "TestSenior1 Auto" from sidebar
    And I am on page "Senior account"
    And I click on "Edit senior profile"
    Then I should see "Profile" with "TestSenior1 Auto"

  Scenario: Create Senior with optional fields
    When I select "Add a resident" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Picture                | Birthday   | Wedding date | Date of first job |
      | TestSenior2    | Auto              | black_rage_shaking.jpg | 1980-08-02 | 2010-06-06   | 2009-03-12        |
    And I click on "Create new account"
    And I am on page "Senior account"
    And I click on "Edit senior profile"
    Then I should see form with
      | First name  | Last name | Picture                | Birthday       | Wedding date | Date of first job |
      | TestSenior2 | Auto      | black_rage_shaking.jpg | 02 August 1980 | 06 June 2010 | 12 March 2009     |

  Scenario: Open created senior from Residents with optional fields
    When I select "All residents" from sidebar
    And I select "TestSenior2 Auto" from sidebar
    And I am on page "Senior account"
    And I click on "Edit senior profile"
    Then I should see "Profile" with "TestSenior2 Auto"

  Scenario: If user doesn't type all the compulsory information, the system displays the message on the top of the Call-to-action - First name
    When I select "Add a resident" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the surname |
      | Auto              |
    And I click on "Create new account"
    Then I should see "Error message" with "First name field is required."
    And I should see "Enter the name" highlighted

  Scenario: If user doesn't type all the compulsory information, the system displays the message on the top of the Call-to-action - Last name
    When I select "Add a resident" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name |
      | TestSenior     |
    And I click on "Create new account"
    Then I should see "Error message" with "Last name field is required."
    And I should see "Enter the surname" highlighted

  Scenario: If user doesn't type all the compulsory information, the system displays the message on the top of the Call-to-action - all the required fields
    When I select "Add a resident" from sidebar
    And I am on page "User Account"
    And I click on "Create new account"
    Then I should see "Error message" with "First name field is required.\nLast name field is required."
    And I should see "Enter the name" highlighted
    And I should see "Enter the surname" highlighted

  Scenario: Delete Senior1
    When I select "All residents" from sidebar
    And I select "TestSenior1 Auto" from sidebar
    And I am on page "Senior account"
    And I click on "Edit senior profile"
    And I click on "Cancel account"
    And I am on page "Cancel account form"
    And I click on "Cancel account"

  Scenario: Delete Senior2
    When I select "All residents" from sidebar
    And I select "TestSenior2 Auto" from sidebar
    And I am on page "Senior account"
    And I click on "Edit senior profile"
    And I click on "Cancel account"
    And I am on page "Cancel account form"
    And I click on "Cancel account"