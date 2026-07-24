package com.lostandfoundsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FoundPage {

    public static Scene getScene(Stage stage) {

        // Main Container - Soft Lavender Background
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F0EDF8;");

        // Center Floating Card Layout
        VBox card = new VBox(18);
        card.setMaxWidth(460);
        card.setMaxHeight(640);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(35, 40, 35, 40));

        // Modern Card Style with Soft Drop Shadow
        card.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(91, 66, 150, 0.12), 20, 0, 0, 8);"
        );

        // Header Section
        Label title = new Label("Report Found Item");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: #221D38;");

        Label subtitle = new Label("Fill in the details to help locate the owner");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        subtitle.setStyle("-fx-text-fill: #78748E;");

        VBox headerBox = new VBox(4, title, subtitle);
        headerBox.setPadding(new Insets(0, 0, 5, 0));

        // Input Fields
        TextField nameField = new TextField();
        nameField.setPromptText("e.g. Wallet, ID Card, Keys");
        applyInputStyle(nameField);

        TextField locationField = new TextField();
        locationField.setPromptText("e.g. Cafeteria, Library");
        applyInputStyle(locationField);

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("e.g. Black leather, contains student ID");
        applyInputStyle(descriptionField);

        TextField timeField = new TextField();
        timeField.setPromptText("e.g. 10:30 AM / Yesterday");
        applyInputStyle(timeField);

        TextField phoneField = new TextField();
        phoneField.setPromptText("e.g. 0300-1234567");
        applyInputStyle(phoneField);

        VBox formBox = new VBox(10);
        formBox.getChildren().addAll(
                createFieldBlock("Item Name", nameField),
                createFieldBlock("Location Found", locationField),
                createFieldBlock("Description", descriptionField),
                createFieldBlock("Time Found", timeField),
                createFieldBlock("Phone / Contact Number", phoneField)
        );

        // Buttons
        Button submitBtn = new Button("Submit Report");
        stylePrimaryButton(submitBtn);

        Button backBtn = new Button("Back to Menu");
        styleOutlineButton(backBtn);

        HBox buttonBox = new HBox(12, backBtn, submitBtn);
        buttonBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(submitBtn, Priority.ALWAYS);
        HBox.setHgrow(backBtn, Priority.ALWAYS);

        // Output Result Label
        Label resultLabel = new Label();
        resultLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        resultLabel.setWrapText(true);
        resultLabel.setMaxWidth(Double.MAX_VALUE);
        resultLabel.setAlignment(Pos.CENTER);

        // Give Feedback Link Label
        Label feedbackLabel = new Label("Give Feedback →");
        feedbackLabel.setCursor(Cursor.HAND);
        feedbackLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        feedbackLabel.setStyle("-fx-text-fill: #5B4296;"); // Theme accent link color

        HBox feedbackBox = new HBox(feedbackLabel);
        feedbackBox.setAlignment(Pos.CENTER);

        // Adding elements to main card
        card.getChildren().addAll(headerBox, formBox, buttonBox, resultLabel, feedbackBox);
        root.getChildren().add(card);

        // Action Handlers
        submitBtn.setOnAction(e -> {
            try {
                if (nameField.getText().trim().isEmpty() || phoneField.getText().trim().isEmpty()) {
                    resultLabel.setStyle("-fx-text-fill: #D93838;");
                    resultLabel.setText("Please fill out required fields (Name & Contact)!");
                    return;
                }

                FoundItem found = new FoundItem(
                        nameField.getText(),
                        locationField.getText(),
                        timeField.getText(),
                        descriptionField.getText(),
                        phoneField.getText()
                );

                CompareAndCheck.foundList.add(found);
                String result = CompareAndCheck.searchMatchForFound(found);

                resultLabel.setStyle("-fx-text-fill: #2E7D32;"); // Success color
                resultLabel.setText(result);

            } catch (Exception ex) {
                resultLabel.setStyle("-fx-text-fill: #D93838;"); // Error color
                resultLabel.setText(ex.getMessage());
            }
        });

        backBtn.setOnAction(e -> stage.setScene(MenuPage.getScene(stage)));

        feedbackLabel.setOnMouseClicked(e -> {
            stage.setScene(FeedbackPage.getScene(stage));
            stage.setTitle("Feedback");
        });

        return new Scene(root, 850, 680);
    }

    // Helper to pair field labels with inputs
    private static VBox createFieldBlock(String labelText, Control inputField) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        label.setStyle("-fx-text-fill: #4A4563;");

        return new VBox(4, label, inputField);
    }

    // Style for Input Fields & Focus States
    private static void applyInputStyle(TextInputControl control) {
        String baseStyle =
                "-fx-background-color: #F9F8FC;" +
                        "-fx-border-color: #E2DFEE;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 8px 12px;" +
                        "-fx-text-fill: #221D38;";

        String focusStyle =
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #5B4296;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 8px 12px;" +
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

    // Primary Theme Button Style
    private static void stylePrimaryButton(Button btn) {
        btn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));

        String style =
                "-fx-background-color: #5B4296;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 10px 18px;" +
                        "-fx-cursor: hand;";

        String hoverStyle =
                "-fx-background-color: #4A347F;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 10px 18px;" +
                        "-fx-cursor: hand;";

        btn.setStyle(style);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(style));
    }

    // Secondary Outline Button Style
    private static void styleOutlineButton(Button btn) {
        btn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));

        String style =
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #8E89A5;" +
                        "-fx-border-color: #E2DFEE;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 10px 18px;" +
                        "-fx-cursor: hand;";

        String hoverStyle =
                "-fx-background-color: #F0EDF8;" +
                        "-fx-text-fill: #5B4296;" +
                        "-fx-border-color: #5B4296;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 10px 18px;" +
                        "-fx-cursor: hand;";

        btn.setStyle(style);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(style));
    }
}
