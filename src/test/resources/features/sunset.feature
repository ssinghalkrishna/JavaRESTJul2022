@sunset
Feature: sunset scenarios

  @sunset1
  Scenario: Find sunset time by zip code and date
    #Given I read zip and date from a csv file
    Given I geocode the zip code to get its latitude and longitude
    Then I get the sunset time in UTC given the latitude and longitude
    Then I convert UTC to local time at the zip code