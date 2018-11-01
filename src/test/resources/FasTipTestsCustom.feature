Feature:  FasTip app calculates Tips using CONFIGURED Tip percentage

    # Test Validation Points:
      # Following values should be calculated and displayed correct when any Customized value of Tip Percentage is used in the App:
        #	Tip Percentage (As configured in Settings)
        #	Tip Amount (Calculated by formula: Billed amount * Tip percentage)
        #	Total Amount (Calculated by formula: Tip Amount + Billed Amount)

    # Test Scenarios:

    #   Following categories of Values will be used as Input to Bill Amount:
        # Test with a positive value (1 to 100)
        # Test with a negative value (-1 to -100)
        # Test with Zero
        # Test with Numbers with decimal part
        # Test with Numbers with decimal part having more than 2 digits
        # Test with a very large number

    #   Following categoris of Values will be used as Input to Tip Percentage
        # Any positive value (Whole Numbers)
        # Any positive value with a decimal part
        # Zero


  Background:
    Given FasTip app Bill Calculator page is open

  @CustomPercentage
  Scenario Outline: Verify that FasTip uses Tip percentage configured in Settings

    And Settings page is opened
    And Tip percentage is set as <Tip Percent>
    Then Validate that Tip Percentage is updated on Main screen with <Tip Percent>
    When <type> value is entered in the bill amount box with <Bill Amount>
    And "Calculate Tip" button is pressed
    And Validate that "Tip Amount" has correct value against provided <Bill Amount>
    And Validate that "Total Amount" has correct value against provided <Bill Amount>

    Examples:
      | type                                 | Bill Amount | Tip Percent |
      | positive                             | 100         | 15.7512     |
      | negative                             | -100        | 17          |
      | zero                                 | 0           | 10.50       |
      | positive floating                    | 43.25       | 7.51        |
      | negative floating                    | 0.45        | 0.00        |
      | positive floating more than 2 digits | 34.56156    | 50.00       |
      | positive very large number           | 1241251     | 5.505       |