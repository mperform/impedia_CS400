import java.util.ArrayList;
import java.util.List;

public class ImpediaBackend implements IImpediaBackend{


    protected Graph<String> byDistance = new Graph<>();
    protected Graph<String> byPrice = new Graph<>();

    List<IFlight> flights;

    public ImpediaBackend(List<IFlight> flights){
        this.flights = flights;
        for(IFlight curr: flights){
            if(!byDistance.containsVertex(curr.getOrigin())) {
                byDistance.insertVertex(curr.getOrigin());
                byPrice.insertVertex(curr.getOrigin());
            }
            if(!byDistance.containsVertex(curr.getDestination())) {
                byDistance.insertVertex(curr.getDestination());
                byPrice.insertVertex(curr.getDestination());
            }
            byDistance.insertEdge(curr.getOrigin(), curr.getDestination(), curr.getDistance());
            byPrice.insertEdge(curr.getOrigin(), curr.getDestination(), curr.getPrice());
        }
    }

    public List<String> shortestPathByMiles(String origin, String destination){
        return byDistance.shortestPath(origin, destination);
    }

    public List<String> shortestPathByPrice(String origin, String destination){
        return byPrice.shortestPath(origin, destination);
    }

    public int getMiles(String origin, String destination){
        return byDistance.getWeight(origin, destination);
    }

    public int getPrice(String origin, String destination) {
        return byPrice.getWeight(origin, destination);
    }
}
