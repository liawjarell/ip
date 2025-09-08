package mochi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mochi.Mochi;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mochi mochi;

    private Image mochiImage = new Image(this.getClass().getResourceAsStream("/images/mochi_avatar.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_icon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Mochi instance */
    public void setMochi(Mochi m) {
        mochi = m;
        // Add a welcome message from Mochi when GUI loads
        dialogContainer.getChildren().add(
                DialogBox.getMochiDialog(mochi.welcome(), mochiImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mochi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mochi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMochiDialog(response, mochiImage)
        );

        if (input.trim().equals("bye")) {
            javafx.animation.PauseTransition delay =
                    new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1));
            delay.setOnFinished(e -> javafx.application.Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}
