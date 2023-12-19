// --== CS400 File Header Information ==--
// Name: Reagan Haines
// Email: rwhaines@wisc.edu
// Team: DT
// TA: Daniel Finer
// Lecturer: Florian Heimerl
// Notes to Grader: None

/**
 * Class that represents a Flight (an edge of a graph) from one airport to another. It has an in addition to an origin
 * and a destination, a price and a distance to be used as weights.
 */
public class Flight implements IFlight{

    // Airports will consist of strings of 3 letters, as is standard.
    protected String origin, destination;
    // price will be held as a protected double, but for the sake of the graph implementation, will be return as a
    // rounded int by the getter method.
    protected double price;
    // distance is an integer in miles.
    protected int distance;

    /**
     * Constructs a new Flight with an origin, destination, price, and distance.
     * @param origin Airport the flight departs from
     * @param destination Airport the flight is bound towards
     * @param price Price of the flight
     * @param distance Distance covered by the flight
     */
    public Flight(String origin, String destination, double price, int distance) {
        //sets each field
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.distance = distance;
    }

    /**
     * Returns origin
     * @return origin
     */
    @Override
    public String getOrigin() {
        //returns origin
        return origin;
    }

    /**
     * Returns destination
     * @return destination
     */
    @Override
    public String getDestination() {
        //returns destination
        return destination;
    }

    /**
     * Returns a rounded int of price
     * @return a rounded int of the price
     */
    @Override
    public int getPrice() {
        //returns a rounded int
        return (int) Math.round(price);
    }

    /**
     * Returns distance
     * @return distance
     */
    @Override
    public int getDistance() {
        //returns distance
        return distance;
    }
}
