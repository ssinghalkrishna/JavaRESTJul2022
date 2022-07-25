@iTunes
Feature: iTunes scenarios

  @iTunes1
  Scenario: Look up an artist by iTunes artist ID
    Given I go to url and search for artist by iTunes artist ID "909253"

  @iTunes2
  Scenario: Search for all Jack Johnson audio and video content
    Given I go to url and search for all "Jack Johnson" audio and video content

  @iTunes3
  Scenario: Search for all Jack Johnson audio and video content and return only the first 25 items
    Given I go to url and search for all "Jack Johnson" audio and video content and return only the first "25" items

  @iTunes4
  Scenario: Search for only Jack Johnson music videos
    Given I search for only "music videos" of "Jack Johnson"

  @iTunes5
  Scenario: Search for all Jim Jones audio and video content and return only the results from the Canada iTunes Store
    Given I search for only "Jim Jones" audio and video content and return only the results from the "Canada" iTunes Store

  @iTunes6
  Scenario: Search for applications titled Yelp and return only the results from the United States iTunes Store
    Given I search for applications titled "Yelp" and return only the results from the "United States" iTunes Store