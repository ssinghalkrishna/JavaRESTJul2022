@iTunesReadFromFile
Feature: iTunes scenarios with test data read from file

  @iTunesReadFromFile1
  Scenario: Look up an artist by iTunes artist ID
    Given I search for artist by iTunes artist ID

  @iTunesReadFromFile2
  Scenario: Search for all audio and video content by an artist name
    Given I search for all audio and video content by a name
