package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;

/***
 * Controller class which provides control logic for the Add Part Form.
 */
public class AddPartController {

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
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    @FXML void saveButtonAction(ActionEvent event) throws IOException {
        int id = Inventory.getPartId();
        String name = txtName.getText();
        int inventory = Integer.parseInt(txtInventory.getText());
        double price = Double.parseDouble(txtPrice.getText());
        int max = Integer.parseInt(txtMax.getText());
        int min = Integer.parseInt(txtMin.getText());
        String source = txtSource.getText();

        if (inHouse) {
            Part part = new InHouse(id, name, price, inventory, min, max, Integer.parseInt(source));
            Inventory.addPart(part);
        } else {
            Part part = new Outsourced(id, name, price, inventory, min, max, source);
            Inventory.addPart(part);
        }
        returnToMainForm(event);
    }



}
