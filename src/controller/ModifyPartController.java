package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private RadioButton btnInHouse;

    @FXML
    private RadioButton btnOutsourced;

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
        int id = part.getId();
        String name = txtName.getText();
        int inventory = Integer.parseInt(txtInventory.getText());
        double price = Double.parseDouble(txtPrice.getText());
        int max = Integer.parseInt(txtMax.getText());
        int min = Integer.parseInt(txtMin.getText());
        String source = txtSource.getText();

        Part updatedPart = null;
        if (inHouse == true) {
            updatedPart = new InHouse(id, name, price, inventory, min, max, Integer.parseInt(source));
        } else {
            updatedPart = new Outsourced(id, name, price, inventory, min, max, source);
        }
        Inventory.updatePart(partIndex, updatedPart);
        returnToMainScreen(event);
    }

    private static int partIndex = 0;
    private static Part part = null;
    public static void setPart(int index, Part selectedPart) {
        partIndex = index;
        part = selectedPart;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        if (part instanceof InHouse) {
            btnInHouse.setSelected(true);
            lblSource.setText("Machine ID");
            txtSource.setText(Integer.toString(((InHouse) part).getMachineId()));
        } else if (part instanceof Outsourced) {
            btnOutsourced.setSelected(true);
            lblSource.setText("Company Name");
            txtSource.setText(((Outsourced) part).getCompanyName());
        }

        txtId.setText(Integer.toString(part.getId()));
        txtName.setText(part.getName());
        txtPrice.setText(Double.toString(part.getPrice()));
        txtInventory.setText(Integer.toString(part.getStock()));
        txtMin.setText(Integer.toString(part.getMin()));
        txtMax.setText(Integer.toString(part.getMax()));
    }
}
