import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewSimulationView extends BorderPane {

    public NewSimulationView() {
        configureLayout();
        createComponents();
    }

    private void configureLayout() {
        setPadding(new Insets(10));
    }

    private void createComponents() {
        VBox leftPane = new VBox();
        HBox topPane = new HBox();
        HBox centerPane = new HBox();
        VBox rightPane = new VBox();

        Button startButton = new Button("Start");
        Button pauseButton = new Button("Pause");
        Button stopButton = new Button("Stop");

        topPane.getChildren().addAll(startButton, pauseButton, stopButton);
        setTop(topPane);
        setLeft(leftPane);
        setCenter(centerPane);
        setRight(rightPane);
    }
}
