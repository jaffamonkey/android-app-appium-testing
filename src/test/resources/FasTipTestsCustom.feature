Feature:  FasTip app calculates Tips using configured Tip percentage value and displays billed amount along with tip details

  # Following values should be displayed:
  #	Tip Percentage (As configured in Settings)
  #	Tip Amount (Calculated by formula: Billed amount * Tip percentage)
  #	Total Amount (Calculated by formula: Tip Amount + Billed Amount)

  Background:
    Given FasTip app Bill Calculator page is open

  @TestWithCustomPercentage
  Scenario Outline: Verify that FasTip sets Tip percentage according to value provided in Settings

    And Settings page is opened
    And Tip percentage is set as <Tip Percent>
    Then Validate that Tip Percentage is updated on Main screen with <Tip Percent>
    When <type> value is entered in the bill amount box with <value>
    And "Calculate Tip" button is pressed
    And Validate that "Tip Amount" has correct value against provided <value>

    Examples:
      | type                                 | value    | Tip Percent |
      | positive                             | 100      | 10.50       |
      | negative                             | -100     | 0.50        |
      | zero                                 | 0        | 10.50       |
      | positive floating                    | 43.25    | 7.5         |
      | negative floating                    | 0.45     | 10.50       |
      | positive floating more than 2 digits | 34.56156 | 50          |
      | positive very large number           | 1241251  | 10.50       |