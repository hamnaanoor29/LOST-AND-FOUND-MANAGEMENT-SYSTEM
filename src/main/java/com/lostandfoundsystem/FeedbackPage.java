package com.lostandfoundsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FeedbackPage {

    public static Scene getScene(Stage stage) {

        // Main Background (Soft Lavender as per theme)
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F0EDF8;");

        // White Floating Card Layout
        VBox card = new VBox(20);
        card.setMaxWidth(460);
        card.setMaxHeight(580);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(35, 40, 35, 40));

        // Card styling with soft rounded corners & subtle drop shadow
        card.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(91, 66, 150, 0.12), 20, 0, 0, 8);"
        );

        // Header Section
        Label title = new Label("We Value Your Feedback");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: #221D38;");

        Label subtitle = new Label("Help us improve the Lost & Found Management System");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        subtitle.setStyle("-fx-text-fill: #78748E;");
        subtitle.setWrapText(true);

        VBox headerBox = new VBox(4, title, subtitle);

        // Section 1: Was this helpful?
        Label helpfulLabel = createSectionLabel("Was this system helpful?");

        RadioButton yes = new RadioButton("Yes");
        RadioButton no = new RadioButton("No");
        styleRadioButton(yes);
        styleRadioButton(no);

        ToggleGroup helpfulGroup = new ToggleGroup();
        yes.setToggleGroup(helpfulGroup);
        no.setToggleGroup(helpfulGroup);

        HBox helpfulBox = new HBox(25, yes, no);
        helpfulBox.setAlignment(Pos.CENTER_LEFT);

        VBox q1Block = new VBox(8, helpfulLabel, helpfulBox);

        // Section 2: Rating
        Label ratingLabel = createSectionLabel("Rate Your Experience (1 - 5)");

        ToggleGroup ratingGroup = new ToggleGroup();
        HBox ratingBox = new HBox(16);
        ratingBox.setAlignment(Pos.CENTER_LEFT);

        for (int i = 1; i <= 5; i++) {
            RadioButton rb = new RadioButton(String.valueOf(i));
            rb.setToggleGroup(ratingGroup);
            styleRadioButton(rb);
            ratingBox.getChildren().add(rb);
        }

        VBox q2Block = new VBox(8, ratingLabel, ratingBox);

        // Section 3: Remarks
        Label remarksLabel = createSectionLabel("Remarks / Suggestions");

        TextField remarksField = new TextField();
        remarksField.setPromptText("Write your feedback here...");
        applyInputStyle(remarksField);

        VBox q3Block = new VBox(8, remarksLabel, remarksField);

        // Submit Button
        Button submitBtn = new Button("Submit Feedback");
        submitBtn.setMaxWidth(Double.MAX_VALUE);
        submitBtn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));

        // Primary Theme Button Style
        String primaryBtnStyle =
                "-fx-background-color: #5B4296;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 12px;" +
                        "-fx-cursor: hand;";

        String primaryBtnHover =
                "-fx-background-color: #4A347F;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 12px;" +
                        "-fx-cursor: hand;";

        submitBtn.setStyle(primaryBtnStyle);
        submitBtn.setOnMouseEntered(e -> submitBtn.setStyle(primaryBtnHover));
        submitBtn.setOnMouseExited(e -> submitBtn.setStyle(primaryBtnStyle));

        // Message Label
        Label message = new Label();
        message.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        message.setAlignment(Pos.CENTER);
        message.setMaxWidth(Double.MAX_VALUE);

        // Append components to main card
        card.getChildren().addAll(headerBox, q1Block, q2Block, q3Block, submitBtn, message);
        root.getChildren().add(card);

        // Action Handler
        submitBtn.setOnAction(e -> {
            message.setStyle("-fx-text-fill: #2E7D32;"); // Success Green
            message.setText("Thank you for your feedback!");

            stage.setScene(ThankYouPage.getScene(stage));
            stage.setTitle("Thank You");
        });

        return new Scene(root, 800, 600);
    }

    // Helper method for Section Labels
    private static Label createSectionLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        label.setStyle("-fx-text-fill: #4A4563;");
        return label;
    }

    // Helper method for Radio Buttons styling
    private static void styleRadioButton(RadioButton rb) {
        rb.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
        rb.setStyle("-fx-text-fill: #221D38; -fx-cursor: hand;");
    }

    // Helper method for Styled Input Box
    private static void applyInputStyle(TextField control) {
        String baseStyle =
                "-fx-background-color: #F9F8FC;" +
                        "-fx-border-color: #E2DFEE;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 10px 12px;" +
                        "-fx-text-fill: #221D38;";

        String focusStyle =
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #5B4296;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 10px 12px;" +
                        "-fx-text-fill: #221D38;";

        control.setStyle(baseStyle);
        control.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                control.setStyle(focusStyle);
            } else {
                control.setStyle(baseStyle);
            }
        });
    }
}
