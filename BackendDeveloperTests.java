import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the implementation of ImpediaBackend
 *
 */
public class BackendDeveloperTests {

    /**
     * Tests the implementation of getMiles
     *
     * @author Sebastien Criqui
     */
    @Test
    public void test1() throws FileNotFoundException {

        // loads flights into backend
        List<IFlight> flights = new FlightLoader().loadFlights("flight_clean.tsv");
        ImpediaBackend backend = new ImpediaBackend(flights);

        // tests different flights to see if getMiles() returns correct value
        assertEquals(108, backend.getMiles("MSN", "ORD"));
        assertEquals(1744, backend.getMiles("ORD", "LAX"));
        assertEquals(1578, backend.getMiles("MSP", "OAK"));
    }

    /**
     * Tests the implementation of getPrice
     *
     * @author Sebastien Criqui
     */
    @Test
    public void test2() throws FileNotFoundException {

        // loads flights into backend
        List<IFlight> flights = new FlightLoader().loadFlights("flight_clean.tsv");
        ImpediaBackend backend = new ImpediaBackend(flights);

        // tests different flights to see if getPrice() returns correct value
        assertEquals(55, backend.getPrice("MSN", "ORD"));
        assertEquals(50, backend.getPrice("ORD", "LAX"));
        assertEquals(78, backend.getPrice("MSP", "OAK"));
    }
}
