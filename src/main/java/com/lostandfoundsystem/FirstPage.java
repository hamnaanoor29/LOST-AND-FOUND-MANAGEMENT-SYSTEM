package com.lostandfoundsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FirstPage {

    public static Scene getScene(Stage stage) {

        // Main Background - Soft Lavender/Purple Tint
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F0EDF8;");

        // Center Floating Card
        VBox card = new VBox(22);
        card.setMaxWidth(420);
        card.setMaxHeight(480);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(45, 40, 45, 40));

        // Modern Card Styling with Soft Shadow
        card.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 24px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(91, 66, 150, 0.12), 25, 0, 0, 10);"
        );

        // Main App Header (Clean & Centered)
        Label mainTitle = new Label("Lost & Found");
        mainTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
        mainTitle.setStyle("-fx-text-fill: #221D38;");

        Label subTitle = new Label("Management System");
        subTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        subTitle.setStyle("-fx-text-fill: #5B4296;"); // Theme Primary Purple

        Label tagline = new Label("Report. Track. Reunite.");
        tagline.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
        tagline.setStyle("-fx-text-fill: #78748E;");

        VBox headerBox = new VBox(4, mainTitle, subTitle, tagline);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(0, 0, 10, 0));

        // Navigation Buttons
        Button loginBtn = new Button("Login");
        Button signupBtn = new Button("Sign Up");
        Button exitBtn = new Button("Exit App");

        // Applying Modern Theme Styles
        stylePrimaryButton(loginBtn);
        styleSecondaryButton(signupBtn);
        styleOutlineButton(exitBtn);

        VBox buttonBox = new VBox(12, loginBtn, signupBtn, exitBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMaxWidth(Double.MAX_VALUE);

        // Add to Card
        card.getChildren().addAll(headerBox, buttonBox);
        root.getChildren().add(card);

        // Button Actions
        loginBtn.setOnAction(e -> stage.setScene(LoginPage.getScene(stage)));
        signupBtn.setOnAction(e -> stage.setScene(SignupPage.getScene(stage)));
        exitBtn.setOnAction(e -> stage.close());

        return new Scene(root, 850, 600);
    }

    // Solid Primary Purple Button
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

    // Soft Tinted Secondary Button
    private static void styleSecondaryButton(Button btn) {
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));

        String style =
                "-fx-background-color: #EDE8F5;" +
                        "-fx-text-fill: #5B4296;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 12px;" +
                        "-fx-cursor: hand;";

        String hoverStyle =
                "-fx-background-color: #E2DAF0;" +
                        "-fx-text-fill: #5B4296;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 12px;" +
                        "-fx-cursor: hand;";

        btn.setStyle(style);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(style));
    }

    // Clean Border Outline Button
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
                "-fx-background-color: #FFF0F0;" +
                        "-fx-text-fill: #D93838;" +
                        "-fx-border-color: #F8C4C4;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 10px;" +
                        "-fx-cursor: hand;";

        btn.setStyle(style);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(style));
    }
}
