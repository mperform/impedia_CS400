// --== CS400 File Header Information ==--
// Name: Reagan Haines
// Email: rwhaines@wisc.edu
// Team: DT
// TA: Daniel Finer
// Lecturer: Florian Heimerl
// Notes to Grader: None

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that is used to load flights from a .tsv file into a list to be used by other classes.
 */
public class FlightLoader implements IFlightLoader{

    /**
     * Loads flights from a tsv file into a List.
     *
     * @param fileName the name of the file to load from
     * @return a list of flights
     * @throws FileNotFoundException if the file is not found
     */
    @Override
    public List<IFlight> loadFlights(String fileName) throws FileNotFoundException{
        //returned list
        List<IFlight> ret = new ArrayList<>();
        //File to be loaded into a list
        File flightFile = new File(fileName);
        //Scanner to iterate through the file
        Scanner input = new Scanner(flightFile);
        //skip the first line
        input.nextLine();
        //iterate through the whole file and create a Flight from each line.
        while(input.hasNext()) {
            String curr = input.nextLine();
            String[] tokenArr = curr.split("\t");
            ret.add(new Flight(tokenArr[1], tokenArr[2], Double.parseDouble(tokenArr[3]), Integer.parseInt(tokenArr[4])));
        }
        return ret;
    }
}
