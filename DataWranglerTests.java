// --== CS400 File Header Information ==--
// Name: Reagan Haines
// Email: rwhaines@wisc.edu
// Team: DT
// TA: Daniel Finer
// Lecturer: Florian Heimerl
// Notes to Grader: None

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester class for the functionality provided through the classes created by the DataWrangler
 * @author reagan
 */
public class DataWranglerTests {

    /**
     * Tests the functionality of the constructor of the Flight class. Ensures that all fields are set correctly.
     */
    @Test
    public void testFlight(){
        //creates a new Flight object using constructor, and ensures that its fields match.
        Flight test = new Flight("SFO", "MSN", 492.80, 1752);
        assertEquals("SFO", test.origin);
        assertEquals("MSN", test.destination);
        assertEquals(492.80, test.price);
        assertEquals(1752, test.distance);
    }

    /**
     * Tests the functionality of the getter methods in the Flight class. Ensures that all return the correct values,
     * including getPrice, which is responsible for rounding the price field to an int value.
     */
    @Test
    public void testGetters() {
        //creates a new Flight object and ensures that its getters return expected values.
        Flight test = new Flight("SFO", "MSN", 492.80, 1752);
        assertEquals("SFO", test.getOrigin());
        assertEquals("MSN", test.getDestination());
        assertEquals(493, test.getPrice());
        assertEquals(1752, test.getDistance());
    }

    /**
     * Tests the functionality of the FlightLoader loadFlights method. Ensures that it creates a list of flights that
     * includes all flights listed in the .tsv file.
     */
    @Test
    public void testFlightLoader() {
        //ensures that 8471 flights are held in the list of flights produced by loadFlights inside FlightLoader.
        try {
            FlightLoader test = new FlightLoader();
            List<IFlight> result = test.loadFlights("flight_clean.tsv");
            assertEquals(8471, result.size());
        } catch(Exception e){
            assertTrue(false);
        }
    }

    /**
     * Tests to ensure that the loadShows method within FlightLoader throws an exception when the file does not exist.
     * A FileNotFoundException should be thrown when the file path is incorrect.
     */
    @Test
    public void testLoaderException(){
        //incorrect filepath should throw a FileNotFound exception
        FlightLoader test = new FlightLoader();
        assertThrows(FileNotFoundException.class, () -> test.loadFlights("nonexistent.tsv"));
    }

    /**
     * Tests the contents of the list of flights returned by the loadFlights method inside FlightLoader.
     * Ensures that the origin of each flight object is correct and matches what is in the file.
     */
    @Test
    public void testLoaderContents() {
        //interates through file and matches all origins to the origins recorded by loadFlights.
        try {
            File testFile = new File("flight_clean.tsv");
            Scanner testScanner = new Scanner(testFile);
            FlightLoader test = new FlightLoader();
            List<IFlight> result = test.loadFlights("flight_clean.tsv");
            testScanner.nextLine();
            for (IFlight currFlight : result) {
                String currLine = testScanner.nextLine();
                assertEquals(currLine.split("\t")[1], currFlight.getOrigin());
            }
        } catch(Exception e){
            assertTrue(false);
        }
    }

    /**
     * Test incorporates the ImpediaBackend constructor. It should construct two graphs, one corresponding to distance,
     * one to price, and it should do it with the list of flights returned by loadFlights.
     */
    @Test
    public void testLoadFlightsBackend(){
        try {
            FlightLoader test = new FlightLoader();
            List<IFlight> result = test.loadFlights("flight_clean.tsv");
            assertEquals(8471, result.size());
            //create backend with this generated list.
            ImpediaBackend testBackend = new ImpediaBackend(result);
            //the list within backend created by the constructor should have proper length
            assertEquals(8471, testBackend.flights.size());
        } catch(Exception e){
            assertTrue(false);
        }
    }

    /**
     * Test incorporates AlgorithmEngineer functionality. It ensures that after a backend has created the proper graphs,
     * these graphs can actually be used to generate correct shortest paths using Dijkstra's shortest path algorithm.
     */
    @Test
    public void testShortestPathFromFile(){
        try {
            FlightLoader test = new FlightLoader();
            List<IFlight> result = test.loadFlights("flight_clean.tsv");
            assertEquals(8471, result.size());
            ImpediaBackend testBackend = new ImpediaBackend(result);
            assertEquals(8471, testBackend.flights.size());
            assertEquals("LAX",testBackend.shortestPathByMiles("SFO","LAX").get(1));
        } catch(Exception e){
            assertTrue(false);
        }
    }

    /**
     * Test focusing on AE code, as that is who I code reviewed. This does a basic test ensuring that the AE's
     * shortest path algorithm works correctly.
     */
    @Test
    public void testAEDijkstras(){
        Graph<String> graph = new Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
        //ensure the proper path
        List<String> path = graph.shortestPath("D", "B");
        assertEquals("D", path.get(0));
        assertEquals("E", path.get(1));
        assertEquals("A", path.get(2));
        assertEquals("C", path.get(3));
        assertEquals("B", path.get(4));
    }

    /**
     * Test focusing on AE code, as that is who I code reviewed. This test ensures that getWeight works reliably, as it
     * is utilized by the frontend and the application, when a path is being displayed to the user.
     */
    @Test
    public void testAEGetWeight(){
        Graph<String> graph = new Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
        //ensure proper weights returned by getWeight.
        assertEquals(6, graph.getWeight("A","B"));
        assertEquals(5, graph.getWeight("A","D"));
        assertEquals(4, graph.getWeight("E","A"));
    }
}
