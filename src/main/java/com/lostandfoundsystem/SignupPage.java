package com.lostandfoundsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SignupPage {

    public static Scene getScene(Stage stage) {

        // Main Root Container with Light Lavender Background (as seen in theme)
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F0EDF8;"); // Soft lavender background

        // White Center Card (Modern Glass/Card Design)
        VBox card = new VBox(18);
        card.setMaxWidth(420);
        card.setMaxHeight(520);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(35, 40, 35, 40));

        // Soft rounded corners & drop shadow for the card
        card.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(91, 66, 150, 0.12), 20, 0, 0, 8);"
        );

        // Header Section
        Label title = new Label("Create Account");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        title.setStyle("-fx-text-fill: #221D38;"); // Dark violet heading

        Label subtitle = new Label("Sign up to join Lost & Found System");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
        subtitle.setStyle("-fx-text-fill: #78748E;");

        VBox headerBox = new VBox(4, title, subtitle);
        headerBox.setPadding(new Insets(0, 0, 10, 0));

        // Form Fields Container
        VBox formBox = new VBox(12);

        TextField usernameField = createStyledTextField("Enter new username");
        TextField contactField = createStyledTextField("Enter contact info");
        PasswordField passwordField = createStyledPasswordField("Enter new password");
        PasswordField confirmPasswordField = createStyledPasswordField("Confirm password");

        formBox.getChildren().addAll(
                createFieldBlock("Username", usernameField),
                createFieldBlock("Contact", contactField),
                createFieldBlock("Password", passwordField),
                createFieldBlock("Confirm Password", confirmPasswordField)
        );

        // Buttons
        Button createAccountBtn = new Button("Create Account");
        createAccountBtn.setMaxWidth(Double.MAX_VALUE);
        createAccountBtn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));

        // Primary Purple Theme Button Style
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

        createAccountBtn.setStyle(primaryBtnStyle);
        createAccountBtn.setOnMouseEntered(e -> createAccountBtn.setStyle(primaryBtnHover));
        createAccountBtn.setOnMouseExited(e -> createAccountBtn.setStyle(primaryBtnStyle));

        Button backBtn = new Button("Back to Login");
        backBtn.setMaxWidth(Double.MAX_VALUE);
        backBtn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));

        // Secondary Outline Button Style
        String secondaryBtnStyle =
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #5B4296;" +
                        "-fx-border-color: #D3CCE3;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 10px;" +
                        "-fx-cursor: hand;";

        backBtn.setStyle(secondaryBtnStyle);

        // Message Label (Error / Success)
        Label message = new Label();
        message.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        message.setAlignment(Pos.CENTER);
        message.setMaxWidth(Double.MAX_VALUE);

        // Adding elements to Card
        card.getChildren().addAll(headerBox, formBox, createAccountBtn, backBtn, message);
        root.getChildren().add(card);

        // Action Logic
        createAccountBtn.setOnAction(e -> {
            if (usernameField.getText().isEmpty()
                    || contactField.getText().isEmpty()
                    || passwordField.getText().isEmpty()
                    || confirmPasswordField.getText().isEmpty()) {

                message.setStyle("-fx-text-fill: #D93838;"); // Subtle Red
                message.setText("Please fill in all fields.");

            } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {

                message.setStyle("-fx-text-fill: #D93838;");
                message.setText("Passwords do not match.");

            } else {

                MainSystem.savedUsername = usernameField.getText();
                MainSystem.savedPassword = passwordField.getText();
                MainSystem.savedContact = contactField.getText();

                message.setStyle("-fx-text-fill: #2E7D32;"); // Soft Green
                message.setText("Account created successfully!");
            }
        });

        backBtn.setOnAction(e -> stage.setScene(LoginPage.getScene(stage)));

        return new Scene(root, 800, 600);
    }

    // Helper method to build input field containers with labels
    private static VBox createFieldBlock(String labelText, Control inputField) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        label.setStyle("-fx-text-fill: #4A4563;");

        VBox box = new VBox(4, label, inputField);
        return box;
    }

    // Helper method for styled TextFields matching reference theme
    private static TextField createStyledTextField(String placeholder) {
        TextField tf = new TextField();
        tf.setPromptText(placeholder);
        applyInputStyle(tf);
        return tf;
    }

    // Helper method for styled PasswordFields
    private static PasswordField createStyledPasswordField(String placeholder) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(placeholder);
        applyInputStyle(pf);
        return pf;
    }

    private static void applyInputStyle(TextInputControl control) {
        String baseStyle =
                "-fx-background-color: #F9F8FC;" +
                        "-fx-border-color: #E2DFEE;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 9px 12px;" +
                        "-fx-text-fill: #221D38;";

        String focusStyle =
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #5B4296;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 9px 12px;" +
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
