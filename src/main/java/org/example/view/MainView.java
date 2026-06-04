package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.entity.Animals;
import org.example.service.impl.AnimalServicesImpl;
import org.example.service.interfaces.IAnimalServices;
import javafx.geometry.Insets;

public class MainView {

    private ObservableList<Animals> listDates;
    private IAnimalServices animalServices = new AnimalServicesImpl();

    public void launchView(Stage stage) {
        stage.setTitle("CRUD Animales - JavaFX");
        // ... build the UI here...

        TableView<Animals> table = new TableView<>();
        listDates = FXCollections.observableArrayList(animalServices.listAll());
        table.setItems(listDates);

        TableColumn<Animals, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Animals, String> colName = new TableColumn<>("Nombre");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Animals, String> colType = new TableColumn<>("Tipo");
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Animals, String> colAge = new TableColumn<>("Edad");
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.getColumns().addAll(colId, colName, colAge, colType);

        //buttons
        HBox barraBotons = getHBox(stage, table);

        //
        VBox root = new VBox(10, barraBotons, table);
        VBox.setVgrow(table, Priority.ALWAYS);
        root.setPadding(new Insets(15));

        //scene
        Scene scene = new Scene(root, 600, 420);
        stage.setScene(scene);

        stage.show();
    }

    private HBox getHBox(Stage stage, TableView<Animals> table) {
        Button btnNew    = new Button("➕ Nuevo");
        Button btnDelete = new Button("🗑️ Eliminar");

        btnNew.setOnAction(e -> showForm(stage));

        //delete

        btnDelete.setOnAction(e -> {
            Animals seleccionated = table.getSelectionModel().getSelectedItem();
            if (seleccionated == null) {
                showAlert("Selecciona un Animal para eliminar.");
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Eliminar a " + seleccionated.getName() + "?",
                    ButtonType.YES, ButtonType.NO);

            confirm.showAndWait().ifPresent(resp -> {
                if (resp == ButtonType.YES) {
                    boolean result = animalServices.deleteAnimal(seleccionated.getId());
                    if (result) showInfo("Se ha borrado correctamente");
                    refreshTable();
                }
            });
        });

        //layout
        HBox barraBotons = new HBox(10, btnNew, btnDelete);
        barraBotons.setAlignment(Pos.CENTER_LEFT);
        barraBotons.setPadding(new Insets(10));
        return barraBotons;
    }

    //

    private void showForm(Stage stage) {
        AnimalsFormView form = new AnimalsFormView();
        form.showForm(stage, animalServices, this::refreshTable);
    }

    private void refreshTable() {
        listDates.setAll(animalServices.listAll());
    }

    private void showAlert(String message) {
        new Alert(Alert.AlertType.WARNING, message, ButtonType.OK).showAndWait();
    }

    private void showInfo(String message) {
        new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK).showAndWait();
    }



}
