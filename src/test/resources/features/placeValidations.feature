Feature: Validating Place API's

  @AddPlace
  Scenario Outline: verify if Place is been successfully added using AddPlaceAPI
    Given Add Place  with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with "post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify "place_id" created maps to "<name>" using "getPlaceAPI"

  Examples:
    |name   | language | address           |
    |AAHouse|  English | World Cross Center|
  #  |BBHouse| Spanish  |Sea cross Center   |

  @DeletePlace
  Scenario: Verify if delete place functionality is working

    Given DeletePlace Payload
    When User calls "deletePlaceAPI" with "post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"

