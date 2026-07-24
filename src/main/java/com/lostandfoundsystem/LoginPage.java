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

public class LoginPage {

    public static Scene getScene(Stage stage) {

        // Main Background Container - Soft Lavender
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F0EDF8;");

        // Center Floating Card
        VBox card = new VBox(20);
        card.setMaxWidth(400);
        card.setMaxHeight(480);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(40, 40, 40, 40));

        // Glassmorphism Card Styling with Soft Shadow
        card.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(91, 66, 150, 0.12), 20, 0, 0, 8);"
        );

        // Header Section
        Label title = new Label("Welcome Back");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        title.setStyle("-fx-text-fill: #221D38;");

        Label subtitle = new Label("Please log in to continue");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
        subtitle.setStyle("-fx-text-fill: #78748E;");

        VBox headerBox = new VBox(4, title, subtitle);
        headerBox.setPadding(new Insets(0, 0, 8, 0));

        // Form Fields Container
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        applyInputStyle(usernameField);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        applyInputStyle(passwordField);

        Label forgotLabel = new Label("Forgot Password?");
        forgotLabel.setCursor(Cursor.HAND);
        forgotLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        forgotLabel.setStyle("-fx-text-fill: #5B4296;"); // Theme Primary Color

        HBox forgotBox = new HBox(forgotLabel);
        forgotBox.setAlignment(Pos.CENTER_RIGHT);

        VBox formBox = new VBox(14);
        formBox.getChildren().addAll(
                createFieldBlock("Username", usernameField),
                createFieldBlock("Password", passwordField),
                forgotBox
        );

        // Buttons
        Button loginBtn = new Button("Login");
        stylePrimaryButton(loginBtn);

        Button backBtn = new Button("Back to Main");
        styleOutlineButton(backBtn);

        VBox buttonBox = new VBox(10, loginBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // Feedback / Error Message
        Label message = new Label();
        message.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        message.setAlignment(Pos.CENTER);
        message.setMaxWidth(Double.MAX_VALUE);

        // Adding components to Card
        card.getChildren().addAll(headerBox, formBox, buttonBox, message);
        root.getChildren().add(card);

        // Event Handlers
        loginBtn.setOnAction(e -> {
            if (usernameField.getText().equals(MainSystem.savedUsername)
                    && passwordField.getText().equals(MainSystem.savedPassword)) {

                stage.setScene(MenuPage.getScene(stage));

            } else {
                message.setStyle("-fx-text-fill: #D93838;"); // Error Red
                message.setText("Invalid Username or Password!");
            }
        });

        forgotLabel.setOnMouseClicked(e ->
                stage.setScene(ForgetPage.getScene(stage)));

        backBtn.setOnAction(e ->
                stage.setScene(FirstPage.getScene(stage)));

        return new Scene(root, 800, 600);
    }

    // Helper method to wrap label and field together
    private static VBox createFieldBlock(String labelText, Control inputField) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        label.setStyle("-fx-text-fill: #4A4563;");

        return new VBox(5, label, inputField);
    }

    // Input Fields Style & Focus Listener
    private static void applyInputStyle(TextInputControl control) {
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

    // Primary Theme Button
    private static void stylePrimaryButton(Button btn) {
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));

        String style =
                "-fx-background-color: #5B4296;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 12px;" +
                        "-fx-cursor: hand;";

        String hoverStyle =
                "-fx-background-color: #4A347F;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 12px;" +
                        "-fx-cursor: hand;";

        btn.setStyle(style);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(style));
    }

    // Outline Secondary Button
    private static void styleOutlineButton(Button btn) {
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));

        String style =
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #8E89A5;" +
                        "-fx-border-color: #E2DFEE;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 10px;" +
                        "-fx-cursor: hand;";

        String hoverStyle =
                "-fx-background-color: #F0EDF8;" +
                        "-fx-text-fill: #5B4296;" +
                        "-fx-border-color: #5B4296;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 10px;" +
                        "-fx-cursor: hand;";

        btn.setStyle(style);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(style));
    }
}
