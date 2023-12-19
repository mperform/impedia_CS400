// --== Project 3 File Header ==--
// Name: Sebastien Criqui
// CSL Username: sebastien
// Email: criqui@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: none
import edu.wisc.cs.cs400.JavaFXTester;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontendDeveloperTests extends JavaFXTester {
    public FrontendDeveloperTests() {
        super(ImpediaFrontend.class);
    }

    /**
     * Tests that origin and destination textfields store data correctly
     *
     */
    @Test
    public void test1() {

        // look up textfields from ImpediaFrontend
        TextField origin = lookup("#origin").query();
        TextField destination = lookup("#destination").query();

        // click and write text to textfields. Checks to see if strings are stored correctly
        clickOn("#origin");
        write("MSN");
        clickOn("#destination");
        write("LAX");

        assertEquals("MSN", origin.getText());
        assertEquals("LAX", destination.getText());
    }

    /**
     * Tests that checkboxes show as selected when clicked on
     *
     */
    @Test
    public void test2() {

        // lookup checkboxes from ImpediaFrontend
        CheckBox miles = lookup("#miles").query();
        CheckBox price = lookup("#price").query();

        clickOn("#miles");
        clickOn("#price");

        // checks to make sure that checkboxes are selected
        assertEquals(true, miles.isSelected());
        assertEquals(true, price.isSelected());


    }

    /**
     * Tests that text fields are set to default after restart is clicked
     *
     */
    @Test
    public void test3() {

        // lookup Textfields from ImpediaFrontend
        TextField origin = lookup("#origin").query();
        TextField destination = lookup("#destination").query();
        Button restart = lookup("#restart").query();
        CheckBox miles = lookup("#miles").query();
        CheckBox price = lookup("#price").query();

        // write in textfields and click on checkboxes and restart button
        clickOn("#origin");
        write("MSN");
        clickOn("#destination");
        write("ORD");
        clickOn("#miles");
        clickOn("#price");
        clickOn("#restart");

        // checks that textfields are reset to default
        assertEquals("Origin", origin.getText());
        assertEquals("Destination", destination.getText());
    }

    /**
     * Tests that checkboxes are set to default after restart is clicked
     *
     */
    @Test
    public void test4() {

        // lookup Textfields, restart button, and checkboxes from ImpediaFrontend
        TextField origin = lookup("#origin").query();
        TextField destination = lookup("#destination").query();
        Button restart = lookup("#restart").query();
        CheckBox miles = lookup("#miles").query();
        CheckBox price = lookup("#price").query();

        // write in textboxes and click on checkboxes and restart button
        clickOn("#origin");
        write("MSN");
        clickOn("#destination");
        write("ORD");
        clickOn("#miles");
        clickOn("#price");
        clickOn("#restart");

        // checks that checkboxes are reset to default
        assertEquals(false, miles.isSelected());
        assertEquals(false, price.isSelected());
    }

    /**
     * Tests that clicking on borderPane makes borderPane request focus
     *
     */
    @Test
    public void test5() {

        // lookup Textfields and borderPane
        TextField origin = lookup("#origin").query();
        TextField destination = lookup("#destination").query();
        BorderPane borderPane = lookup("#borderPane").query();

        clickOn("#origin");
        write("MSN");
        clickOn("#destination");
        clickOn("#borderPane");

        // checks that borderPane gets focus after clicking outside of input fields
        assertEquals(true, borderPane.isFocused());
    }

    /**
     * Tests that backend correctly creates graphs in the constructor and that it can be accessed
     * from ImpediaFrontend
     *
     * @throws FileNotFoundException if flight_clean.tsv is not found
     */
    @Test
    public void test6() throws FileNotFoundException {

        // instantiates backend with flight_clean.tsv and adds it to the backend field in
        // ImpediaFrontend
        ImpediaFrontend frontend = new ImpediaFrontend();
        List<IFlight> flights = new FlightLoader().loadFlights("flight_clean.tsv");
        ImpediaBackend backend = new ImpediaBackend(flights);
        ImpediaFrontend.backend =  backend;

        // checks that miles and price is correctly returned from backend when accessed through
        // frontend
        assertEquals( 108, ImpediaFrontend.backend.byDistance.getPathCost("MSN", "ORD"));
        assertEquals(55, ImpediaFrontend.backend.byPrice.getPathCost("MSN", "ORD"));
    }


    /**
     * Tests that shortestPathByMiles() and shortestPathByPrice() return correct values when
     * called from ImpediaFrontend
     *
     */
    @Test
    public void test7() throws FileNotFoundException{

        // instantiates backend with flight_clean.tsv and adds it to the backend field in
        // ImpediaFrontend
        ImpediaFrontend frontend = new ImpediaFrontend();
        List<IFlight> flights = new FlightLoader().loadFlights("flight_clean.tsv");
        ImpediaBackend backend = new ImpediaBackend(flights);
        ImpediaFrontend.backend =  backend;

        // checks that shortestPathByMiles() and shortestPathByPrice() return correct values when
        // called from ImpediaFrontend
        assertEquals("[MSN, ORD]",
            ImpediaFrontend.backend.shortestPathByMiles("MSN", "ORD").toString());
        assertEquals("[MSN, PHL, LAX]",
            ImpediaFrontend.backend.shortestPathByPrice("MSN", "LAX").toString());
    }
}
