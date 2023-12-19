import javafx.scene.control.Accordion;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.List;

public interface IImpediaFrontend {
    void start(final Stage stage);
    Accordion displayShortestPathByMiles(String origin, String destination);
    Accordion displayShortestPathByPrice(String source, String destination);
}
