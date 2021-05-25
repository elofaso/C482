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

    /***
     * InHouse button.
     */
    @FXML
    private RadioButton btnInHouse;

    /***
     * Outsourced button.
     */
    @FXML
    private RadioButton btnOutsourced;

    /***
     *  Label showing "Machine ID" for InHouse or "Company Name" for Outsourced Part.
     */
    @FXML
    private Label lblSource;

    /***
     * Text field for Part ID.
     */
    @FXML
    private TextField txtId;

    /***
     * Text filed for Part Name.
     */
    @FXML
    private TextField txtName;

    /***
     * Text field for Part Inventory.
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
     *  Label showing Machine ID for InHouse or Company Name for Outsourced Part.
     */
    @FXML
    private TextField txtSource;

    /***
     * Text field for Part Minimum Inventory.
     */
    @FXML
    private TextField txtMin;

    /***
     * Boolean indicating whether Part is InHouse or Outsourced.
     */
    private boolean inHouse;

    /***
     * InHouse button action.
     *
     * @param event Action event.
     */
    @FXML
    void inHouseRadioButtonAction(ActionEvent event) {
        lblSource.setText("Machine ID");
        inHouse = true;
    }

    /***
     * Outsource button action
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
    private void returnToMainScreen(ActionEvent event) throws IOException {
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
        returnToMainScreen(event);
    }

    /***
     * Save Button action.
     *
     * @param event Action event.
     * @throws IOException Exception from FXMLLoader.
     */
    @FXML void saveButtonAction(ActionEvent event) throws IOException {
        int id = part.getId();
        String name = txtName.getText();
        int inventory = Integer.parseInt(txtInventory.getText());
        double price = Double.parseDouble(txtPrice.getText());
        int max = Integer.parseInt(txtMax.getText());
        int min = Integer.parseInt(txtMin.getText());
        String source = txtSource.getText();

        Part updatedPart;
        if (inHouse) {
            updatedPart = new InHouse(id, name, price, inventory, min, max, Integer.parseInt(source));
        } else {
            updatedPart = new Outsourced(id, name, price, inventory, min, max, source);
        }
        Inventory.updatePart(partIndex, updatedPart);
        returnToMainScreen(event);
    }

    /***
     * Index position of Part in list.
     */
    private static int partIndex = 0;

    /***
     * Part object.
     */
    private static Part part = null;

    /***
     * Updates Part with new Part object.
     *
     * @param index Index of Part in list.
     * @param updatedPart Part object.
     */
    public static void setPart(int index, Part updatedPart) {
        partIndex = index;
        part = updatedPart;
    }

    /***
     * Initialize the controller class.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param rb  The resources used to localize the root object.
     */
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
