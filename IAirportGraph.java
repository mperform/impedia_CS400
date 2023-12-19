// --== CS400 File Header Information ==--
// Name: Thomas He
// Email: he289@wisc.edu
// Team: dt
// TA: Daniel Finer
// Lecturer: Florian
// Notes to Grader: none
import java.util.List;
/**
 * AlgorithmEngineer interface.
 * @author Thomas He
 */
public interface IAirportGraph {

    /**
     * This method takes the airport name and search up all the flights that flys out of the airport
     * @param name
     * @Return a list of Strings sorted by weight (distance or price) of all the airport names connected to this airport that was passed in.
     *
     */
    public List<String> searchByAirport(String name);


}
