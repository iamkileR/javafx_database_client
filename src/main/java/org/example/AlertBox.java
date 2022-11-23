package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasa sluzaca do obslugi okienka alert w przypadku potwierdzenia operacji
 */
public class AlertBox {
    /**
     *
     * @param title zmienna sluzaca do ustawienia tyutlu okienka
     * @param message zmienna sluzaca jako text w Label
     */
    public static void display(String title,String message) {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(400);
            window.setMinHeight(400);
            Label label = new Label();
            label.setText(message);
            Button closeButton = new Button("Powrót");
            closeButton.setOnAction(e-> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label,closeButton);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();

        }


}
