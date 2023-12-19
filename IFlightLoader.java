// --== CS400 Project One File Header ==--
// Name: Chihao Huang
// CSL Username: chihao
// Email: chuang355@wisc.edu
// Lecture #: 004
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;
import java.io.FileNotFoundException;

/**
 * Interface for FlightLoader.
 * <p>
 * loadFlights() method returns a list of flights.
 */
public interface IFlightLoader {
    /**
     * Loads flights from a tsv file.
     *
     * @param fileName the name of the file to load from
     * @return a list of flights
     * @throws FileNotFoundException if the file is not found
     */
    public List<IFlight> loadFlights(String fileName) throws FileNotFoundException;
}
