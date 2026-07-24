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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ThankYouPage {

    public static Scene getScene(Stage stage) {

        // Main Container - Soft Lavender Theme Background
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F0EDF8;");

        // Center Floating Card Layout
        VBox card = new VBox(22);
        card.setMaxWidth(440);
        card.setMaxHeight(480);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(45, 40, 45, 40));

        // Modern Card Styling with Soft Shadow
        card.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 24px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(91, 66, 150, 0.12), 25, 0, 0, 10);"
        );

        // Success / Check Badge Icon
        Label checkIcon = new Label("✔");
        checkIcon.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        checkIcon.setStyle(
                "-fx-background-color: #DCFCE7;" +
                        "-fx-text-fill: #15803D;" +
                        "-fx-padding: 12px 20px;" +
                        "-fx-background-radius: 50px;"
        );

        // Main Header Title
        Label thankYou = new Label("Thank You!");
        thankYou.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
        thankYou.setStyle("-fx-text-fill: #221D38;");

        // Subtitle / Feedback Message
        Label message = new Label(
                "Thank you for your valuable feedback.\nYour suggestions help us continuously improve our Lost & Found Management System."
        );
        message.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
        message.setTextAlignment(TextAlignment.CENTER);
        message.setWrapText(true);
        message.setStyle("-fx-text-fill: #6E6883;");

        // Footer Text
        Label footer = new Label("We hope to serve you again!");
        footer.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
        footer.setStyle("-fx-text-fill: #5B4296;"); // Theme Primary Color

        VBox textGroup = new VBox(10, thankYou, message, footer);
        textGroup.setAlignment(Pos.CENTER);

        // Action Buttons
        Button menuBtn = new Button("Back to Main Menu");
        stylePrimaryButton(menuBtn);

        Button exitBtn = new Button("Close Application");
        styleOutlineButton(exitBtn);

        VBox buttonBox = new VBox(10, menuBtn, exitBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMaxWidth(Double.MAX_VALUE);

        // Append Components to Card Container
        card.getChildren().addAll(checkIcon, textGroup, buttonBox);
        root.getChildren().add(card);

        // Actions
        menuBtn.setOnAction(e -> {
            stage.setScene(MenuPage.getScene(stage));
            stage.setTitle("Dashboard");
        });

        exitBtn.setOnAction(e -> stage.close());

        return new Scene(root, 850, 600);
    }

    // Primary Purple Solid Button Style
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

    // Modern Soft Outline Button Style
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
