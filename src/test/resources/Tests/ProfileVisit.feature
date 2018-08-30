Feature: ProfileVisit
  Visit/access his own profile by the button in the header

# Important: your user in configuration-file should be 'admin-pro, superadmin' and have 'Masquerade' functionality
# MADZIA-114

  Scenario: Create and verify admin-pro
    Given I am logged into site "http://madeleinedev.devcloud.acquia-sites.com/en/admin/people/p2rp-create/admin-pro"
    When I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Birthday   | Your valid e-mail  |
      | TestAdminPro   | Auto              | 1985-10-11 | {generateData}@a.a |
    And I click on "Create new account"
    And I am on page "Admin pro account"
    And I click on "Masquerade as ..."
    And I click on "Edit admin pro profile"
    Then I should see "Profile" with "TestAdminPro Auto\n{lastGeneratedData}@a.a"
    And I should see form with
      | First name   | Last name | Birthday        | E-mail                  |
      | TestAdminPro | Auto      | 11 October 1985 | {lastGeneratedData}@a.a |
    When I click on "Edit this field (First name)"
    And I fill in "First name:" with "TestAdminPro1"
    And I click on "Save (First name)"
    And I click on "Edit this field (Last name)"
    And I fill in "Last name:" with "Auto1"
    And I click on "Save (Last name)"
    And I click on "Edit this field (Birthday)"
    And I fill in "Birthday:" with "1985-10-12"
    And I click on "Save (Birthday)"
    And I click on "Edit this field (E-mail)"
    And I fill in "E-mail:" with "{generateData}@a.a"
    And I click on "Save (E-mail)"
    Then I should see form with
      | First name    | Last name  | Birthday        | E-mail                  |
      | TestAdminPro1 | Auto1      | 12 October 1985 | {lastGeneratedData}@a.a |
    #cancel this account in the end
    When I click on "Cancel account"
    And I am on page "Cancel account form"
    And I click on "Cancel account"


  Scenario: Create and verify carer-pro
    Given I am logged into site
    When I am on page "Dashboard"
    And I select "Caregivers" from sidebar
    And I select "Create carer pro account" from sidebar
    And I am on page "User Account"
    And I populate form with
      | Enter the name | Enter the surname | Your valid e-mail  | Job    |
      | TestCarerPro   | Auto              | {generateData}@a.a | tester |
    And I click on "Create new account"
    And I am on page "Carer pro account"
    And I click on "Masquerade as ..."
    And I click on "Edit carer pro profile"
    Then I should see "Profile" with "TestCarerPro Auto\ntester\n{lastGeneratedData}@a.a"
    And I should see form with
      | First name   | Last name | Job    | E-mail                  |
      | TestCarerPro | Auto      | tester | {lastGeneratedData}@a.a |
    When I click on "Edit this field (First name)"
    And I fill in "First name:" with "TestCarerPro1"
    And I click on "Save (First name)"
    And I click on "Edit this field (Last name)"
    And I fill in "Last name:" with "Auto1"
    And I click on "Save (Last name)"
    And I click on "Edit this field (Job)"
    And I fill in "Job:" with "tester1"
    And I click on "Save (Job)"
    And I click on "Edit this field (E-mail)"
    And I fill in "E-mail:" with "{generateData}@a.a"
    And I click on "Save (E-mail)"
    Then I should see form with
      | First name    | Last name | Job     | E-mail                  |
      | TestCarerPro1 | Auto1     | tester1 | {lastGeneratedData}@a.a |


  Scenario: Delete carer-pro
    Given I am logged into site
    When I am on page "Dashboard"
    And I select "Caregivers" from sidebar
    And I select "All caregivers" from sidebar
    And I select "TestCarerPro1 Auto1" from sidebar
    And I am on page "Carer pro account"
    And I click on "Edit carer pro profile"
    And I click on "Cancel account"
    When I am on page "Cancel account form"
    And I click on "Cancel account"