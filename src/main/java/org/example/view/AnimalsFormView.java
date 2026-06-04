package org.example.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.entity.Animals;
import org.example.entity.TypesAnimals;
import org.example.service.interfaces.IAnimalServices;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class AnimalsFormView {

     public void showForm(Stage owner, IAnimalServices animalServices, Runnable onSaved) {

         Stage stage = new Stage();
         stage.initOwner(owner);
         stage.initModality(Modality.WINDOW_MODAL);
         stage.setTitle("Nuevo Animal");
         stage.setResizable(false);

         TextField txtName = new TextField();
         txtName.setPromptText("Ej: Firulais");

         TextField txtAge = new TextField();
         txtAge.setPromptText("Ej: 15");

         TextField txtType = new TextField();
         txtType.setPromptText("Ej: Perro");

         // to save
         Button btnSave = new Button("Guardar");

         btnSave.setOnAction(e -> {
             String name = txtName.getText();
             TypesAnimals type = TypesAnimals.valueOf(txtType.getText().toUpperCase());
             String age = txtAge.getText();

             int edad;
             try {
                 edad = Integer.parseInt(age);
             } catch (NumberFormatException ex) {
                 showError("La edad debe ser un número válido.");
                 return;
             }
             Animals a = new Animals(0, name, type, edad);
             animalServices.saveAnimal(a);
             new Alert(Alert.AlertType.INFORMATION, "Se ha creado Correctamente", ButtonType.OK).showAndWait();
             onSaved.run();   // ← refresh the table on MainView
             stage.close();
         });

         //gridpane
         GridPane grid = new GridPane();
         grid.setHgap(10);   // horizontal space between cells
         grid.setVgap(12);   // vertical space between cells
         grid.setPadding(new Insets(20));

         grid.add(new Label("Nombre:"), 0, 0);  // column 0, line 0
         grid.add(txtName,                 1, 0);  // column 1, line 0
         grid.add(new Label("Edad:"),   0, 1);
         grid.add(txtAge,                  1, 1);
         grid.add(new Label("Tipo:"),   0, 2);
         grid.add(txtType,                 1, 2);
         grid.add(btnSave,                 0, 3, 3, 2);  // col 0, fila 3, colspan 3, rowspan 2

         ColumnConstraints col1 = new ColumnConstraints(80);   // labels
         ColumnConstraints col2 = new ColumnConstraints(200);  // camps
         grid.getColumnConstraints().addAll(col1, col2);

         stage.setScene(new Scene(grid));
         stage.showAndWait();

     }
    private void showError(String mensaje) {
        new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK).showAndWait();
    }
}
