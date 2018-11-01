Feature:  FasTip app calculates Tips using DEFAULT Tip percentage

  # Following values should be displayed:
  #	Tip Percentage (As configured in Settings)
  #	Tip Amount (Calculated by formula: Billed amount * Tip percentage)
  #	Total Amount (Calculated by formula: Tip Amount + Billed Amount)

  Background:
    Given FasTip app Bill Calculator page is open
    And Settings page is opened
    And Tip percentage value is noted
    And "Back" key is pressed

  @DefaultPercentage
  Scenario Outline: Verify that FasTip calculates Billed and Tip amount as expected when Default Tip percentage is used

    # Test Validation Points:
      # Following values should be calculated and displayed correct when Default value of Tip Percentage is used in the App:
        #	Tip Percentage (As configured in Settings)
        #	Tip Amount (Calculated by formula: Billed amount * Tip percentage)
        #	Total Amount (Calculated by formula: Tip Amount + Billed Amount)

    # Test Scenarios: Following categories of Values will be used as Input to Bill Amount:
      # Test with a positive value (1 to 100)
      # Test with a negative value (-1 to -100)
      # Test with Zero
      # Test with Numbers with decimal part
      # Test with Numbers with decimal part having more than 2 digits
      # Test with a very large number


    When <type> value is entered in the bill amount box with <Bill Amount>
    And "Calculate Tip" button is pressed
    Then Validate that "Tip Percentage" has correct value against provided <Bill Amount>
    And Validate that "Tip Amount" has correct value against provided <Bill Amount>
    And Validate that "Total Amount" has correct value against provided <Bill Amount>

    Examples:
      | type                                 | Bill Amount |
      | positive                             | 100         |
      | negative                             | -50         |
      | zero                                 | 0           |
      | positive floating                    | 43.25       |
      | negative floating                    | 0.45        |
      | positive floating more than 2 digits | 34.56156    |
      | positive very large number           | 1241255     |