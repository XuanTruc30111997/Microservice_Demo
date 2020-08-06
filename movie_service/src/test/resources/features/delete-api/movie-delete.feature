Feature: Movie

  Background: Assign permission for user

    Given movie informations are loaded from movie-information.csv
    And rating informations are loaded from rating-information.csv
    And catalog informations are loaded from catalog-information.csv
    And Setup environment

    Scenario: Correct Movie Information when is returned when request by movieId
      When Try to delete movie <idMovie>
      Then I expect to receive idMovie<idMovie>, name<name>, star<star>, count<count>
      Examples:
        | idMovie | name                  | star | count |
        | 1       | One punch mand        | 5    | 44    |

    Scenario: Verify empty response when wrong movieId