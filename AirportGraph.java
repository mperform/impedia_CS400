// --== CS400 File Header Information ==--
// Name: Thomas He
// Email: he289@wisc.edu
// Team: dt
// TA: Daniel Finer
// Lecturer: Florian
// Notes to Grader: none
/**
 * AlgorithmEngineer class.
 * @author Thomas He
 */
import java.util.ArrayList;
import java.util.List;

public class AirportGraph extends Graph<Object> implements IAirportGraph{

    /***
     * This is the constructor and it initializes a new instance of this class.
     */
    public AirportGraph() {

    }

    /**
     * This method takes the airport name and search up all the flights that flys out of the airport
     * @param name
     * @Return a list of Strings sorted by weight (distance or price) of all the airport names connected to this airport that was passed in.
     *
     */
    @Override
    public List<String> searchByAirport(String name) {
        Vertex v = vertices.get(name);
        if (v==null){
            return null;
        }
        List<Edge> lst = v.edgesLeaving;
        List rtn = new ArrayList<>();
        while(lst.size()>0){
            Edge cur = lst.get(0);//stores the 1st edge
            for (int j = 0;j<lst.size();j++){//loops thru the list
                if (cur.weight>lst.get(j).weight){//if edge at index has less weight than the 1st edge
                    cur = lst.get(j); //set edge at index to cur
                }
            }
            rtn.add(cur.target.data);//add cur which is the least weight
            lst.remove(cur);//remove cur from the list and loop again
        }
        return rtn;
    }
}
