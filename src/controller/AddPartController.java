package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;

public class AddPartController {

    @FXML
    private AnchorPane root;

    @FXML
    private ToggleGroup radioButton;

    @FXML
    private Label lblSource;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtInventory;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtMax;

    @FXML
    private TextField txtSource;

    @FXML
    private TextField txtMin;

    private boolean inHouse;

    @FXML
    void inHouseRadioButtonAction(ActionEvent event) {
        lblSource.setText("Machine ID");
        inHouse = true;
    }

    @FXML
    void outsourcedRadioButtonAction(ActionEvent event) {
        lblSource.setText("Company Name");
        inHouse = false;
    }

    private void returnToMainScreen(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        returnToMainScreen(event);
    }

    @FXML void saveButtonAction(ActionEvent event) throws IOException {
        int id = Inventory.getPartId();
        String name = txtName.getText();
        int inventory = Integer.parseInt(txtInventory.getText());
        double price = Double.parseDouble(txtPrice.getText());
        int max = Integer.parseInt(txtMax.getText());
        int min = Integer.parseInt(txtMin.getText());
        String source = txtSource.getText();

        if (inHouse == true) {
            Part part = new InHouse(id, name, price, inventory, min, max, Integer.parseInt(source));
            Inventory.addPart(part);
        } else {
            Part part = new Outsourced(id, name, price, inventory, min, max, source);
            Inventory.addPart(part);
        }
        returnToMainScreen(event);
    }



}
