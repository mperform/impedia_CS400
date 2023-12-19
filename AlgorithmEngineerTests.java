// --== CS400 File Header Information ==--
// Name: Thomas He
// Email: he289@wisc.edu
// Team: dt
// TA: Daniel Finer
// Lecturer: Florian
// Notes to Grader: none
/**
 * AlgorithmEngineer test class.
 * @author Thomas He
 */
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AlgorithmEngineerTests {

    /**
     * This tests if an "airport" is correctly inserted into the graph
     */
    @Test
    protected void test1() {
        AirportGraph airport = new AirportGraph();
        IFlight f = new IFlight(){

            @Override
            public String getOrigin() {
                return "Toronto";
            }

            @Override
            public String getDestination() {
                return "Chicago";
            }

            @Override
            public int getPrice() {
                return 670;
            }

            @Override
            public int getDistance() {
                return 1200;
            }
        };
        airport.insertVertex(f);
        assertEquals(1,airport.getVertexCount());
    }

    /**
     * This tester tests if the flights are properly inserted
     */
    @Test
    protected void test2(){
        AirportGraph airport = new AirportGraph();
        airport.insertVertex("Toronto");
        airport.insertVertex("Chicago");


        airport.insertEdge("Toronto","Chicago",100);
        airport.insertVertex("Detroit");
        airport.insertEdge("Detroit", "Toronto",150);
        airport.insertEdge("Detroit", "Chicago",110);
        assertEquals(3,airport.getEdgeCount());
    }
    /**
     * This tests the searchByAirport method
     */
    @Test
    protected void test3(){
        AirportGraph airport = new AirportGraph();
        airport.insertVertex("Toronto");
        airport.insertVertex("Chicago");


        airport.insertEdge("Toronto","Chicago",100);
        airport.insertVertex("Detroit");
        airport.insertEdge("Detroit", "Toronto",150);
        airport.insertEdge("Detroit", "Chicago",110);
        List lst = airport.searchByAirport("Detroit");
        assertEquals("Chicago",lst.get(0));
    }
    /**
     * This tester inserts numerous Vertices as see if the algorithm will handle all of them and correctly inserts them into the graph
     */
    @Test
    protected void test4(){
        AirportGraph airport = new AirportGraph();
        airport.insertVertex("Toronto");
        airport.insertVertex("Chicago");
        airport.insertVertex("Los Angeles");
        airport.insertVertex("Miami");
        airport.insertVertex("Madison");
        airport.insertVertex("Montreal");
        airport.insertVertex("Calgary");
        airport.insertVertex("Denver");

        assertEquals(8,airport.getVertexCount());
    }

    /**
     * This tester tests if all the edges are inserted correctly and are correctly sorted by the search by airport method
     */
    @Test
    protected void test5(){
        AirportGraph airport = new AirportGraph();
        airport.insertVertex("Toronto");
        airport.insertVertex("Chicago");
        airport.insertVertex("Los Angeles");
        airport.insertVertex("Miami");
        airport.insertVertex("Madison");
        airport.insertVertex("Montreal");
        airport.insertVertex("Calgary");
        airport.insertVertex("Denver");
        airport.insertEdge("Chicago","Los Angeles",100);
        airport.insertEdge("Chicago","Miami",200);
        airport.insertEdge("Chicago","Madison",50);
        airport.insertEdge("Chicago","Montreal",400);
        airport.insertEdge("Chicago","Calgary",500);
        List actual = airport.searchByAirport("Chicago");
        assertEquals(5,actual.size());
        assertEquals("Madison",actual.get(0));
    }


}
