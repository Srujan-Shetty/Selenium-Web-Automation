Feature: Noren Web 2.0 Test
Scenario: Open Noren Web
    Given user opens browser
    When user goes to Noren Web
    And user enters username "SRUJANINV"
    And user enters password "Srujan@12"
    And user enters otp "2004"
    And user clicks login
    Then title should contain "Noren Web 2.0" 