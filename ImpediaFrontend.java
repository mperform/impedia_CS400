// --== Project 3 File Header ==--
// Name: Sebastien Criqui
// CSL Username: sebastien
// Email: criqui@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: none
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;

public class ImpediaFrontend extends Application implements IImpediaFrontend {

    static ImpediaBackend backend;

    /**
     * Method that Application will launch for Impedia
     *
     * @param stage that Impedia will be running on
     */
    @Override public void start(Stage stage) {

        // setup Scene with Borderpane
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 550);
        stage.setTitle("Impedia");

        // add title and exit button to the stage
        Label title = new Label("Impedia");
        Button exit = new Button("Exit");
        title.setFont(Font.font("Comic Sans MS", FontPosture.REGULAR, 26));
        borderPane.setTop(title);
        borderPane.setRight(exit);
        BorderPane.setAlignment(title, Pos.TOP_CENTER);
        BorderPane.setAlignment(exit, Pos.BOTTOM_RIGHT);

        // create VBox that will hold future HBoxes
        VBox allInputs = new VBox();

        // create nodes and add them to HBox. Then, add this HBox to the overarching VBox
        HBox inputs = new HBox();
        TextField origin = new TextField("Origin");
        TextField destination = new TextField("Destination");
        origin.setStyle("-fx-text-fill: grey; -fx-font-size: 12px;"); // sets text to grey and
        // font size to 12
        destination.setStyle("-fx-text-fill: grey; -fx-font-size: 12px;");
        Label to = new Label("To");
        inputs.getChildren().add(origin);
        inputs.getChildren().add(to);
        inputs.getChildren().add(destination);
        inputs.setSpacing(25);
        allInputs.getChildren().add(inputs);

        // add checkboxes and submit button to scene by adding to overarching VBox
        CheckBox miles = new CheckBox("Shortest Path By Miles");
        CheckBox price = new CheckBox("Shortest Path By Price");
        Button submitButton = new Button("Submit");
        Button restart = new Button("Restart");
        HBox checkboxes = new HBox();
        checkboxes.setSpacing(20);
        checkboxes.getChildren().add(miles);
        checkboxes.getChildren().add(price);
        checkboxes.getChildren().add(submitButton);
        checkboxes.getChildren().add(restart);
        allInputs.getChildren().add(checkboxes);

        // set spacing for VBox
        borderPane.setCenter(allInputs);
        allInputs.setAlignment(Pos.CENTER_LEFT);
        allInputs.setSpacing(25);
        Translate translateInput = new Translate(0, -130);
        allInputs.getTransforms().addAll(translateInput);

        // action on clicking text boxes
        // When text boxes are clicked, it clears the text boxes to get rid of the origin and
        // destination titles. It also sets the text to black instead of grey
        origin.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {
            origin.clear();
            origin.setStyle("-fx-text-fill: black; -fx-font-size: 12px;");
        }));

        destination.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {
            destination.clear();
            destination.setStyle("-fx-text-fill: black; -fx-font-size: 12px;");
        }));

        // action on clicking submit button
        // When submit button is clicked, shortest path is generated with the source and
        // destination airports
        HBox paths = new HBox();
        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
            boolean errorThrown = false;
            if(miles.isSelected()) {
                if(displayShortestPathByMiles(origin.getText(), destination.getText()) == null) {
                    Text errorText = new Text("Invalid airports. Click Restart");
                    errorText.setFill(Color.RED);
                    errorText.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                    allInputs.getChildren().add(1, errorText);
                    errorThrown = true;
                } else {
                    paths.getChildren().add(displayShortestPathByMiles(origin.getText(), destination.getText()));
                }
            }
            if(price.isSelected()) {
                if(displayShortestPathByPrice(origin.getText(), destination.getText()) == null) {
                    if (!errorThrown) {
                        Text errorText = new Text("Invalid airports. Click Restart");
                        errorText.setFill(Color.RED);
                        errorText.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                        allInputs.getChildren().add(1, errorText);
                    }
                } else {
                    paths.getChildren().add(displayShortestPathByPrice(origin.getText(),
                        destination.getText()));
                }
            }
            submitButton.setDisable(true);
        });

        // action on clicking restart button
        // when restart button is clicked, text boxes are cleared, checkboxes are unchecked,
        // generated paths are removed, and the submit button becomes clickable again
        restart.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
            origin.setText("Origin");
            destination.setText("Destination");
            origin.setStyle("-fx-text-fill: grey; -fx-font-size: 12px;");
            destination.setStyle("-fx-text-fill: grey; -fx-font-size: 12px;");
            if(allInputs.getChildren().size() == 4) {
                allInputs.getChildren().remove(1);
            }
            miles.setSelected(false);
            price.setSelected(false);

            for(int i = 0; i < paths.getChildren().size(); i++) {
                paths.getChildren().remove(i);
                i--;
            }
            submitButton.setDisable(false);
        });

        // action on clicking exit button
        // when exit button is clicked, program should terminate
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
            Platform.exit();
        });

        // action on clicking Stage
        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            borderPane.requestFocus();
        });

        // add Accordions to stage and set spacing
        paths.setSpacing(10);
        allInputs.getChildren().add(paths);

        // connects the scene and the stage
        stage.setScene(scene);
        stage.show();

        origin.setId("origin");
        destination.setId("destination");
        miles.setId("miles");
        price.setId("price");
        submitButton.setId("submitButton");
        restart.setId("restart");
        borderPane.setId("borderPane");
    }

    /**
     * Displays shortest path by miles using the origin and destination Strings provided. Shortest
     * path is created with an accordion. The accordion has a drop down menu that lists ticket
     * information. Origin, destination, price, and miles of each ticket in the path is contained
     * in each titledPane
     *
     * @param source airport that represents the origin
     * @param destination airport that represents the destination
     * @return an accordion containing the shortest path by miles
     */
    @Override public Accordion displayShortestPathByMiles(String source, String destination) {

        // returns null if either source or destination airports do not exist in the graph
        if(!backend.byDistance.containsVertex(source) || !backend.byPrice.containsVertex(destination)) {
            return null;
        }

        // create shortest path by miles using the source and destination airports given
        List<String> path = backend.shortestPathByMiles(source, destination);

        // accordion that will display the shortest path from source to destination. When
        // expanding the accordion, origin, destination, price, and miles of the flight will be
        // listed. Accordion holds all the titledPanes.
        Accordion airportPath = new Accordion();
        TitledPane title = new TitledPane();
        title.setText("Shortest Path By Miles");
        title.setCollapsible(false);
        airportPath.getPanes().add(title);

        // array of titledPanes. Title of each titledPane is flight source and destination in the
        // path. When expanding the titlePane, origin, destination, price, and miles will be listed
        TitledPane[] titledPanes = new TitledPane[path.size()-1];
        for(int i = 0; i < path.size()-1; i++) {

            // sets the text of each titledPane, which are the origin and destination airports
            // for a flight
            titledPanes[i] = new TitledPane();
            titledPanes[i].setText(path.get(i) + " - " + path.get(i+1));

            // creates a VBox of text containing information about each flight
            VBox text = new VBox();
            text.getChildren().add(new Text("Origin: " + path.get(i)));
            text.getChildren().add(new Text("Destination:" + path.get(i+1)));
            text.getChildren().add(new Text("Price: " + backend.getPrice(path.get(i),
                path.get(i+1))));
            text.getChildren().add(new Text("Miles: " + backend.getMiles(path.get(i),
                path.get(i+1))));
            titledPanes[i].setContent(text);

            // adds each titledPane to the accordion.
            airportPath.getPanes().add(titledPanes[i]);
        }
        return airportPath;
    }

    /**
     * Displays shortest path by price using the origin and destination Strings provided. Shortest
     * path is created with an accordion. The accordion has a drop down menu that lists ticket
     * information. Origin, destination, price, and miles of each ticket in the path is contained
     * in each titledPane
     *
     * @param source airport that represents the origin
     * @param destination airport that represents the destination
     * @return an accordion containing the shortest path by price
     */
    @Override public Accordion displayShortestPathByPrice(String source, String destination) {

        // returns null if either source or destination airports do not exist in the graph
        if(!backend.byPrice.containsVertex(source) || !backend.byPrice.containsVertex(destination)) {
            return null;
        }

        // create shortest path by price using the source and destination airports given
        List<String> path = backend.shortestPathByPrice(source, destination);

        // accordion that will display the shortest path from source to destination. When
        // expanding the accordion, origin, destination, price, and miles of the flight will be
        // listed. Accordion holds all the titledPanes.
        Accordion airportPath = new Accordion();
        TitledPane title = new TitledPane();
        title.setText("Shortest Path By Price");
        title.setCollapsible(false);
        airportPath.getPanes().add(title);

        // array of titledPanes. Title of each titledPane is flight source and destination in the
        // path. When expanding the titlePane, origin, destination, price, and miles will be listed
        TitledPane[] titledPanes = new TitledPane[path.size()-1];
        for(int i = 0; i < path.size()-1; i++) {

            // sets the text of each titledPane, which are the origin and destination airports
            // for a flight
            titledPanes[i] = new TitledPane();
            titledPanes[i].setText(path.get(i) + " - " + path.get(i+1));

            // creates a VBox of text containing information about each flight
            VBox text = new VBox();
            text.getChildren().add(new Text("Origin: " + path.get(i)));
            text.getChildren().add(new Text("Destination: " + path.get(i+1)));
            text.getChildren().add(new Text("Price: " + backend.getPrice(path.get(i),
                path.get(i+1))));
            text.getChildren().add(new Text("Miles: " + backend.getMiles(path.get(i),
                path.get(i+1))));
            titledPanes[i].setContent(text);

            // adds each titledPane to the accordion.
            airportPath.getPanes().add(titledPanes[i]);
        }
        return airportPath;
    }

    public static void main(String[] args) {
        try {
            List<IFlight> flights = new FlightLoader().loadFlights("flight_clean.tsv");
            ImpediaBackend backend = new ImpediaBackend(flights);
            ImpediaFrontend.backend = backend;
            Application.launch(); // runs application
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
