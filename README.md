# Functional Tests for FasTip Android App

**Summary**: 
A simple test suite for simple Tip Calculator Android app (FasTip.apk) using Appium and Java.
All scenarios are independent and can be run in any order. All inputs are provided through Feature file examples.

## Test Summary: ##
There are two feature files:
1. Using Default Tip Percentage (FasTipTestsCustom.feature)
2. Using Custom Tip Percentage (FasTipTestsDefault.feature)

Each feature file has one scenario outline which executes for each input combination (For e.g. Positive whole numbers, negative numbers, floating point numbers, etc). More information can be found inside the feature files.

## Technology Stack:
- Java SDK (1.8)
- Appium Server (1.9.1)
- Cucumber BDD
- Junit (For Runner)
- AssertJ (For Assertions)
- PageFactory (Page Object Model implementation)

## Supported Platforms:
Tests should run in same way on Android AVD (Android Virtual Device) and any real device. 

## Pre-Requisites:
### Appium Server Initialization:
- If there is only one device connected via adb, simply run appium server with ```appium``` on Terminal or use Appium Desktop
- If there are multiple devices connected via adb:
- Run ```adb devices``` and copy the device id of desired device.
- Initialize Appium server with command ```appium -U <device_id>```

### Android Virtual Devices:
If you want to run tests on Android Emulator, create an Android Virtual Device using Android Studio and provide its name in ```config.properties``` as described later.

### APK file placement:
APK file can be found at following path: ```fasTipAndroidTests/src/test/resources/apps/```

## Running the Test Suite:

### Configuration:
To change environment configurations, you need to edit ```fasTipAndroidTests/src/test/resources/config.properties```
- ```server_address```: Appium server IP
- ```server_port```: Appium port
- ```app_name```: Apk file name
- ```avd_name```: Name of Android Virtual Device
- ```app_package_name```: Package name of the App
- ```app_activity```: Activity Name of the App

### Via IDE (IntelliJ IDEA):
- All tests: Simply run the TestRunner.java class
- Specific Feature: Change features plugin value in @CucumberOptions as below and run TestRunner.java: ```features="src/test/resources/<FeatureFileName>.feature"```

### Via Command Line (Maven):
- All tests: ```mvn test```
- Specific Features: ```mvn test -Dcucumber.options="--tags @CustomPercentage"``` (This will run scenario using custom percentage as Tip percentage)

## Reports:
For simplicity, only cucumber native report is generated at ```target/htmlreport/index.html```

