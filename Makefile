##default target that runs the tests for the DataWrangler code
#runTests: DataWranglerTests.class
#	java -jar junit5.jar --class-path . --scan-classpath
#
##compiles DataWranglerTests if it was modified
#DataWranglerTests.class: DataWranglerTests.java FlightLoader.class
#	javac -cp .:junit5.jar DataWranglerTests.java
#
##compiles FlightLoader if it was modified
#FlightLoader.class: FlightLoader.java IFlightLoader.class Flight.class
#	javac FlightLoader.java
#
##compiles IFlightLoader if it was modified
#IFlightLoader.class: IFlightLoader.java
#	javac IFlightLoader.java
#
##compiles Flight if it was modified
#Flight.class: Flight.java IFlight.class
#	javac Flight.java
#
##compiles IFlight if it was modified
#IFlight.class: IFlight.java
#	javac IFlight.java
#
##deletes all compiled .class files
#clean:
#	rm -f *.class

run: ImpediaFrontend.class ImpediaBackend.java
	java --module-path javafx-sdk-18.0.1/lib --add-modules javafx.controls,javafx.fxml ImpediaFrontend
ImpediaFrontend.class: ImpediaFrontend.java
	javac --module-path javafx-sdk-18.0.1/lib --add-modules javafx.controls,javafx.fxml ImpediaFrontend.java
ImpediaBackend.class: ImpediaBackend.java
	javac ImpediaBackend.java
runFrontendDeveloperTests: FrontendDeveloperTests.class
	java --module-path javafx-sdk-18.0.1/lib --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar --scan-classpath
FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac --module-path javafx-sdk-18.0.1/lib --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar FrontendDeveloperTests.java
runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	 java -jar junit5.jar --class-path . --scan-classpath
AlgorithmEngineerTests.class: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path . --scan-classpath
DataWranglerTests.class: FlightLoader.class DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java
FlightLoader.class: Flight.class FlightLoader.java
	javac FlightLoader.java
Flight.class: Flight.java
	javac Flight.java
runTests: runFrontendDeveloperTests runAlgorithmEngineerTests runDataWranglerTests
clean:
	rm -f *.class
