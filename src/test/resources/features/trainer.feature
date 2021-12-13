Feature: Getting a five letter word
  As a User,
  I want a five letter word,
  So i can guess one of its letters

  Scenario: Start a new game
    When I start a new game,
    Then i should

  Scenario Outline: A word has been guessed
    Given I am on the English Wikipedia main page
    When I search for "<search term>"
    Then I should see the search results page
    And the search results should contain "<entry>"

    Examples:
      | search term       | entry                    |
      | Socratez          | Socrates                 |
      | Pllato            | Plato                    |
      | Greece Philosophy | Ancient Greek philosophy |