Feature: Catalog Service

    Scenario Outline: : Correct Movie Information when request by idCatalog
      When I request with idCatalog <idCatalog>
      Then I expect to receive idMovie<idMovie>, name<name>, star<star>, count<count>
      Examples:
        | idCatalog | idMovie | name | star | count |
        | 1         | [1, 2, 3] | ["One punch man", "One piece", "Blood" ] | [4, 4, 4.3333 ] | [3, 3, 3] |
        | 2         | [] | [] | [] | [] |

      @SmokeTest
    Scenario Outline: Verify not found code when wrong idCatalog
      When I request with idCatalog <idCatalog>
      Then I expect to receive status code <errorCode>
      Examples:
        | idCatalog | errorCode |
        | 3         | 404       |