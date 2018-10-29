Feature:  FasTip app calculates Tips using default or configured Tip percentage value
          and displays billed amount along with tip details

  # Following values should be displayed:
  #	Tip Percentage (As configured in Settings)
  #	Tip Amount (Calculated by formula: Billed amount * Tip percentage)
  #	Total Amount (Calculated by formula: Tip Amount + Billed Amount)

  Background:
    Given FasTip app Bill Calculator page is open

  Scenario Outline: Verify that FasTip calculates Billed and Tip amount as expected when
                    Default Tip percentage is used
    # Test with a positive value (1 to 100) - multiple scenarios
    # Test with a negative value (-1 to -100)
    # Test with Zero
    # Test with Numbers with decimal part
    # Test with Numbers with decimal part having more than 2 digits

    When Tip percentage is set as <tip percent>
    When <type> value is entered in the bill amount box with <value>
    And "Calculate Tip" button is pressed
    Then Validate that "Tip Percentage" has correct value against provided <value>
    And Validate that "Tip Amount" has correct value against provided <value>
    And Validate that "Total Amount" has correct value against provided <value>

    Examples:
      | type                                 | value    | tip percent |
      | positive                             | 100      | default  |
      | negative                             | -100     | default  |
      | zero                                 | 0        | default  |
      | positive floating                    | 43.25    | default  |
      | negative floating                    | 0.45     | default  |
      | positive floating more than 2 digits | 34.56156 | default  |
      | positive very large number           | 1241251  | default  |