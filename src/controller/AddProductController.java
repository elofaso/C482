package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/***
 * Controller class which provides control logic for the Add Product Form.
 */
public class AddProductController implements Initializable {

    /***
     * Text field for Product Name.
     */
    @FXML
    private TextField txtProductName;

    /***
     * Text filed for Product Inventory.
     */
    @FXML
    private TextField txtProductInventory;

    /***
     * Text field for Product Price.
     */
    @FXML
    private TextField txtProductPrice;

    /***
     * Text field for Product Maximum Inventory.
     */
    @FXML
    private TextField txtProductMax;

    /***
     * Text field for Product Minimum Inventory.
     */
    @FXML
    private TextField txtProductMin;

    /***
     * Table view for All Parts.
     */
    @FXML
    private TableView<Part> tvAllParts;

    /***
     * Part ID column.
     */
    @FXML
    private TableColumn<Part, Integer> colAllPartsId;

    /***
     * Part Name column.
     */
    @FXML
    private TableColumn<Part, String> colAllPartsName;

    /***
     * Part Inventory column.
     */
    @FXML
    private TableColumn<Part, Integer> colAllPartsInventory;

    /***
     * Part Price column.
     */
    @FXML
    private TableColumn<Part, Double> colAllPartsPrice;

    /***
     * Text field for Part ID or Part Name to search.
     */
    @FXML
    private TextField txtSearchPart;

    /***
     * Table view for Associated Parts.
     */
    @FXML
    private TableView<Part> tvAssociatedParts;

    /***
     * Part ID column.
     */
    @FXML
    private TableColumn<Part, Integer> colAssociatedPartsId;

    /***
     * Part Name column.
     */
    @FXML
    private TableColumn<Part, String> colAssociatedPartsName;

    /***
     * Part Inventory column.
     */
    @FXML
    private TableColumn<Part, Integer> colAssociatedPartsInventory;

    /***
     * Part Price column.
     */
    @FXML
    private TableColumn<Part, Double> colAssociatedPartsPrice;

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
     * Associated Parts list.
     */
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /***
     * Add Part Button action.
     *
     * @param event Action event.
     */
    @FXML
    void addPartButtonAction(ActionEvent event) {
        Part selectedPart = tvAllParts.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedPart);
        tvAssociatedParts.setItems(associatedParts);
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
     * Remove Part Button action.
     *
     * @param event Action event.
     */
    @FXML
    void removePartButtonAction(ActionEvent event) {
        Part selectedPart = tvAssociatedParts.getSelectionModel().getSelectedItem();
        associatedParts.remove(selectedPart);
    }

    /***
     * Save Button action.
     *
     * @param event Action event.
     * @throws IOException Exception from FXMLLoader.
     */
    @FXML void saveButtonAction(ActionEvent event) throws IOException {
        try {
            int id = Inventory.getProductId();
            String name = txtProductName.getText();
            int inventory = Integer.parseInt(txtProductInventory.getText());
            double price = Double.parseDouble(txtProductPrice.getText());
            int max = Integer.parseInt(txtProductMax.getText());
            int min = Integer.parseInt(txtProductMin.getText());

            if (!(min<max)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Products");
                alert.setHeaderText("ADD");
                alert.setHeaderText("Min must be less than max.");
                alert.showAndWait();
                return;
            }

            if (!(min<=inventory && inventory <=max)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Products");
                alert.setHeaderText("ADD");
                alert.setHeaderText("Inventory must be between min and max.");
                alert.showAndWait();
                return;
            }

            Product product = new Product(id, name, price, inventory, min, max);

            for (Part part : associatedParts) {
                product.addAssociatedPart(part);
            }

            Inventory.addProduct(product);
            returnToMainForm(event);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Products");
            alert.setHeaderText("ADD");
            alert.setHeaderText("Invalid input type or empty field.");
            alert.showAndWait();
        }
    }

    /***
     * Key pressed in Part Search text field action.
     *
     * @param event Action event.
     */
    @FXML
    void searchPartKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchString = txtSearchPart.getText();
            if (searchString.matches("\\d+")) {
                int id = Integer.parseInt(searchString);
                Part searchPart = Inventory.lookupPart(id);
                tvAllParts.getSelectionModel().select(searchPart);
            } else {
                tvAllParts.setItems(Inventory.lookupPart(searchString));
            }
        }
    }


    /***
     * Initialize the controller class.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param rb  The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvAllParts.setItems(Inventory.getAllParts());
        colAllPartsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAllPartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAllPartsInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colAllPartsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colAssociatedPartsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAssociatedPartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAssociatedPartsInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colAssociatedPartsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
