@iTunesShareDataBetweenSteps
Feature: iTunes scenarios when sharing data between steps in a scenario

  @iTunesShareDataBetweenSteps1
  Scenario: Look up an artist by iTunes artist Id using TestContext.getStringTestData()
    Given I lookup an artist by iTunes artist Id
    And I verify that result contains that artist Id

  @iTunesShareDataBetweenSteps2
  Scenario: Look up an artist by iTunes artist Id using TestContext.getJSONTestData()
    Given I lookup an artist by iTunes artist Id another way
    And I verify that result contains that artist Id another way
