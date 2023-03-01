Feature: Validating place API's

  Scenario Outline:  Verify AddPlaceAPI functionality
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

  Examples:
    |name    |language|address           |
    |AA house|English |World cross center|
    |BB house|Spanish |Pillory          |