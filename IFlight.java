/**
 * Instances of classes that implement this interface represent a single flight (analogous to an edge) that can be
 * processed by the Backend Developer and integrated into the graph implementation created by the Algorithm Engineer.
 * Two graphs can be made, one weighted by price and one weighted by distance.
 */
public interface IFlight {

    //constructor args (String origin, String destination, double price, int distance)

    String getOrigin(); // retrieve the origin of this Flight.
    String getDestination(); // retrieve the destination of this Flight.
    int getPrice(); // retrieve the price of this Flight.
    int getDistance(); // retrieve the distance covered by this Flight.
}
