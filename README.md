# connectall-junit-adapter
JUnit test listener that acts as ConnectALL Universal adapter to report test results.

ConnectALL JUnit adapter is built on top of Universal adapter configuration.

## Usecase
JUnit test results to be pushed to an end application for creating bugs from failure or to log test runs/results. 

# How to use

> In order to use the JUnit adapter you will need to get the license from ConnectALL sales team. Please reach out to sales@connectall.com for licenses and quotes.

## Import specifications
* Import the zip from the `dist` directory in to ConnectALL using "Import Adapters Configuration" feature. 

## Define application links
* Create an application link in ConnectALL between JUnit and a destination application of your choice
* Navigate to `Configuration -> Connections` screen and create a new connection to JUnit, using an unique key for each of your integration group.

## Fields available
|Field|Remarks|
|---|---|
|id| a concatenated string of `Test Header` and current time in millis|
|modifiedDate| current time in `yyyy-MM-dd HH:mm:ss.SSS` format|
|testHeader| a concatenated string of the test class and test method names|
|message| failure/success message from the test method|
|description| description of the test method|
|exception| exception details if a test method is failed|
|trace| stack trace of the exception raised in the test method|

