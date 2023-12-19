import java.util.List;

public interface IImpediaBackend {
    List<String> shortestPathByMiles(String origin, String destination);
    List<String> shortestPathByPrice(String origin, String destination);
    int getMiles(String origin, String destination);
    int getPrice(String origin, String destination);


}
