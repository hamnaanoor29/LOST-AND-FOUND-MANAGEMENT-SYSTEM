package com.lostandfoundsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MenuPage {

    public static Scene getScene(Stage stage) {

        // Main Base Container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F0EDF8;"); // Light Lavender Soft Background

        // ==========================================
        // 1. LEFT SIDEBAR UI
        // ==========================================
        VBox sidebar = new VBox(15);
        sidebar.setPrefWidth(240);
        sidebar.setPadding(new Insets(30, 18, 30, 18));
        sidebar.setStyle("-fx-background-color: #42327D;"); // Dark Purple Sidebar Theme

        // App Logo Header
        Label logoIcon = new Label("🔒?");
        logoIcon.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        logoIcon.setStyle("-fx-text-fill: white;");

        Label logoTitle = new Label("Lost & Found");
        logoTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        logoTitle.setStyle("-fx-text-fill: white;");

        Label logoSub = new Label("Management System");
        logoSub.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 11));
        logoSub.setStyle("-fx-text-fill: #BDB5E1;");

        VBox logoBox = new VBox(3, logoIcon, logoTitle, logoSub);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setPadding(new Insets(0, 0, 20, 0));

        // Navigation Buttons
        Button dashBtn = createSidebarButton("🏠  Dashboard", true);
        Button lostBtn = createSidebarButton("📄  Report Lost Item", false);
        Button foundBtn = createSidebarButton("🔒  Report Found Item", false);
        Button recordsBtn = createSidebarButton("📋  View Records", false);
        Button logoutBtn = createSidebarButton("🚪  Exit", false);

        sidebar.getChildren().addAll(logoBox, dashBtn, lostBtn, foundBtn, recordsBtn, logoutBtn);
        root.setLeft(sidebar);

        // ==========================================
        // 2. MAIN CENTER CONTENT (DASHBOARD & RECORDS)
        // ==========================================
        VBox centerArea = new VBox(22);
        centerArea.setPadding(new Insets(30, 35, 30, 35));

        // Top Navigation / Header Bar
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label pageTitle = new Label("Dashboard");
        pageTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        pageTitle.setStyle("-fx-text-fill: #221D38;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // REAL CURRENT SYSTEM DATE
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        Label dateLabel = new Label("📅 " + currentDate);
        dateLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        dateLabel.setStyle("-fx-text-fill: #6E6883; -fx-background-color: #FFFFFF; -fx-padding: 6 12; -fx-background-radius: 8;");

        topBar.getChildren().addAll(pageTitle, spacer, dateLabel);

        // --- STATS CARDS ROW ---
        HBox statsRow = new HBox(15);
        statsRow.setAlignment(Pos.CENTER);

        int matchedCount = CompareAndCheck.getMatchedCount();
        int totalRecords = CompareAndCheck.getTotalRecords();

        VBox card1 = createStatCard("📄", String.valueOf(CompareAndCheck.lostList.size()), "Lost Items", "#EDE9FE", "#6D28D9");
        VBox card2 = createStatCard("🔒", String.valueOf(CompareAndCheck.foundList.size()), "Found Items", "#E0F2FE", "#0284C7");
        VBox card3 = createStatCard("✔", String.valueOf(matchedCount), "Matched Items", "#DCFCE7", "#15803D");
        VBox card4 = createStatCard("📋", String.valueOf(totalRecords), "Total Records", "#FEF3C7", "#D97706");

        HBox.setHgrow(card1, Priority.ALWAYS);
        HBox.setHgrow(card2, Priority.ALWAYS);
        HBox.setHgrow(card3, Priority.ALWAYS);
        HBox.setHgrow(card4, Priority.ALWAYS);

        statsRow.getChildren().addAll(card1, card2, card3, card4);

        // --- RECENT ITEMS / VIEW RECORDS TABLE SECTION ---
        VBox tableContainer = new VBox(15);
        tableContainer.setPadding(new Insets(20));
        tableContainer.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 16px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.04), 15, 0, 0, 5);");

        Label tableTitle = new Label("All Reported Records");
        tableTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        tableTitle.setStyle("-fx-text-fill: #221D38;");

        TableView<ItemRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        TableColumn<ItemRow, String> colItem = new TableColumn<>("Item");
        colItem.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ItemRow, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<ItemRow, String> colDate = new TableColumn<>("Date / Time");
        colDate.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<ItemRow, String> colLoc = new TableColumn<>("Location");
        colLoc.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<ItemRow, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Status Column Custom Badge Styling (Matched = Green, Pending = Yellow)
        colStatus.setCellFactory(col -> new TableCell<ItemRow, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label badge = new Label(item);
                    badge.setFont(Font.font("Segoe UI", FontWeight.BOLD, 11));
                    badge.setPadding(new Insets(4, 12, 4, 12));

                    if ("Matched".equalsIgnoreCase(item)) {
                        badge.setStyle("-fx-background-color: #DCFCE7; -fx-text-fill: #15803D; -fx-background-radius: 12px;");
                    } else {
                        badge.setStyle("-fx-background-color: #FEF3C7; -fx-text-fill: #B45309; -fx-background-radius: 12px;");
                    }
                    setGraphic(badge);
                    setAlignment(Pos.CENTER);
                }
            }
        });

        table.getColumns().addAll(colItem, colType, colDate, colLoc, colStatus);

        // AUTOMATIC DATA POPULATION & REALTIME MATCH STATUS CALCULATION
        for (LostItem item : CompareAndCheck.lostList) {
            boolean isMatch = CompareAndCheck.isLostMatched(item);
            table.getItems().add(new ItemRow(
                    item.getName(),
                    item.getType(),
                    item.getTime(),
                    item.getLocation(),
                    isMatch ? "Matched" : "Pending"
            ));
        }

        for (FoundItem item : CompareAndCheck.foundList) {
            boolean isMatch = CompareAndCheck.isFoundMatched(item);
            table.getItems().add(new ItemRow(
                    item.getName(),
                    item.getType(),
                    item.getTime(),
                    item.getLocation(),
                    isMatch ? "Matched" : "Pending"
            ));
        }

        tableContainer.getChildren().addAll(tableTitle, table);
        centerArea.getChildren().addAll(topBar, statsRow, tableContainer);
        root.setCenter(centerArea);

        // Sidebar Actions
        lostBtn.setOnAction(e -> {
            stage.setScene(LostPage.getScene(stage));
            stage.setTitle("Report Lost Item");
        });

        foundBtn.setOnAction(e -> {
            stage.setScene(FoundPage.getScene(stage));
            stage.setTitle("Report Found Item");
        });

        recordsBtn.setOnAction(e -> {
            // Refreshes Scene to show updated table records
            stage.setScene(MenuPage.getScene(stage));
        });

        logoutBtn.setOnAction(e -> {
            stage.setScene(LoginPage.getScene(stage));
            stage.setTitle("Login");
        });

        return new Scene(root, 1000, 680);
    }

    private static Button createSidebarButton(String text, boolean isActive) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));

        String activeStyle = "-fx-background-color: #5D4BB3; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-padding: 10 15; -fx-cursor: hand;";
        String normalStyle = "-fx-background-color: transparent; -fx-text-fill: #C3BCE2; -fx-background-radius: 10px; -fx-padding: 10 15; -fx-cursor: hand;";
        String hoverStyle = "-fx-background-color: rgba(255,255,255,0.08); -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-padding: 10 15; -fx-cursor: hand;";

        btn.setStyle(isActive ? activeStyle : normalStyle);

        if (!isActive) {
            btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
            btn.setOnMouseExited(e -> btn.setStyle(normalStyle));
        }

        return btn;
    }

    private static VBox createStatCard(String icon, String count, String title, String bgColor, String accentColor) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(16, 20, 16, 20));
        card.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 14px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.03), 10, 0, 0, 4);");

        HBox topBox = new HBox(12);
        topBox.setAlignment(Pos.CENTER_LEFT);

        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(14));
        iconLbl.setStyle("-fx-background-color: " + bgColor + "; -fx-text-fill: " + accentColor + "; -fx-padding: 8 10; -fx-background-radius: 8px;");

        Label countLbl = new Label(count);
        countLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        countLbl.setStyle("-fx-text-fill: #221D38;");

        topBox.getChildren().addAll(iconLbl, countLbl);

        Label titleLbl = new Label(title);
        titleLbl.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        titleLbl.setStyle("-fx-text-fill: #78748E;");

        card.getChildren().addAll(topBox, titleLbl);
        return card;
    }

    public static class ItemRow {
        private final String name;
        private final String type;
        private final String time;
        private final String location;
        private final String status;

        public ItemRow(String name, String type, String time, String location, String status) {
            this.name = name;
            this.type = type;
            this.time = time;
            this.location = location;
            this.status = status;
        }

        public String getName() { return name; }
        public String getType() { return type; }
        public String getTime() { return time; }
        public String getLocation() { return location; }
        public String getStatus() { return status; }
    }
}
