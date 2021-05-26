package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/***
 * Controller class which provides control logic for the Add Part Form.
 */
public class AddPartController implements Initializable {

    /**
     * The in-house radio button.
     */
    @FXML
    private RadioButton btnInHouse;

    /***
     * Label showing "Machine ID" for InHouse or "Company Name" for Outsourced Part.
     */
    @FXML
    private Label lblSource;

    /***
     * Text field for Part Name.
     */
    @FXML
    private TextField txtName;

    /***
     * Text field for Part Inventory Level.
     */
    @FXML
    private TextField txtInventory;

    /***
     * Text field for Part Price.
     */
    @FXML
    private TextField txtPrice;

    /***
     * Text field for Part Maximum Inventory.
     */
    @FXML
    private TextField txtMax;

    /***
     * Text Field showing Machine ID for InHouse or Company Name for Outsourced Part.
     */
    @FXML
    private TextField txtSource;

    /***
     * Text field for Minimum Inventory.
     */
    @FXML
    private TextField txtMin;

    /***
     * Boolean indicating whether Part is InHouse or Outsourced.
     */
    private boolean inHouse;

    /***
     * InHouse Button action.
     *
     * @param event Action event.
     */
    @FXML
    void inHouseRadioButtonAction(ActionEvent event) {
        lblSource.setText("Machine ID");
        inHouse = true;
    }

    /***
     * Outsourced Button action.
     *
     * @param event Action event.
     */
    @FXML
    void outsourcedRadioButtonAction(ActionEvent event) {
        lblSource.setText("Company Name");
        inHouse = false;
    }

    /***
     * Method to return to Main Form.
     *
     * @param event Passed-through action event.
     * @throws IOException Exception from FXMLLoader.
     */
    private void returnToMainForm(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /***
     * Cancel Button action.
     *
     * @param event Action event.
     * @throws IOException Exception from FXMLLoader.
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        returnToMainForm(event);
    }

    /***
     * Save Button action.
     *
     * @param event Action event.
     * @throws IOException Exception from FXMLLoader.
     */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {
        try {
            int id = Inventory.getPartId();
            String name = txtName.getText();
            int inventory = Integer.parseInt(txtInventory.getText());
            double price = Double.parseDouble(txtPrice.getText());
            int max = Integer.parseInt(txtMax.getText());
            int min = Integer.parseInt(txtMin.getText());
            String source = txtSource.getText();

            if (!(min<max)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Parts");
                alert.setHeaderText("ADD");
                alert.setHeaderText("Min must be less than max.");
                alert.showAndWait();
                return;
            }

            if (!(min<=inventory && inventory <=max)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Parts");
                alert.setHeaderText("ADD");
                alert.setHeaderText("Inventory must be between min and max.");
                alert.showAndWait();
                return;
            }

            if (inHouse) {
                int machineId;
                try {
                    machineId = Integer.parseInt(source);
                    } catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Parts");
                        alert.setHeaderText("ADD");
                        alert.setHeaderText("Machine ID must be integer.");
                        alert.showAndWait();
                        return;
                    }
                    Part part = new InHouse(id, name, price, inventory, min, max, machineId);
                    Inventory.addPart(part);
            } else {
                Part part = new Outsourced(id, name, price, inventory, min, max, source);
                Inventory.addPart(part);
            }
            returnToMainForm(event);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Parts");
            alert.setHeaderText("ADD");
            alert.setHeaderText("Invalid input type or empty field.");
            alert.showAndWait();

        }
    }

    /**
     * Initializes controller and sets inHouse to true.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param rb The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnInHouse.setSelected(true);
        inHouse = true;
    }
}