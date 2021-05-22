package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtSearchParts;

    @FXML
    private TableView<Part> tvParts;

    @FXML
    private TableColumn<Part, Integer> colPartId;

    @FXML
    private TableColumn<Part, String> colPartName;

    @FXML
    private TableColumn<Part, Integer> colPartInventoryLevel;

    @FXML
    private TableColumn<Part, Double> colPartPerUnitCost;

    @FXML
    private Button btnAddParts;

    @FXML
    private Button btnModifyParts;

    @FXML
    private Button btnDeleteParts;

    @FXML
    private TextField txtSearchProducts;

    @FXML
    private TableView<Product> tvProducts;

    @FXML
    private TableColumn<Product, Integer> colProductId;

    @FXML
    private TableColumn<Product, String> colProductName;

    @FXML
    private TableColumn<Product, Integer> colProductInventoryLevel;

    @FXML
    private TableColumn<Product, Double> colProductPerUnitCost;

    @FXML
    private Button btnAddProducts;

    @FXML
    private Button btnModifyProducts;

    @FXML
    private Button btnDeleteProducts;

    @FXML
    private Button btnExit;

    @FXML
    void deletePartButtonAction(ActionEvent event) {
        Part selectedPart = tvParts.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Parts");
        alert.setHeaderText("DELETE");
        alert.setContentText("Do you want to delete this part");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
            tvParts.setItems(Inventory.getAllParts());
        }
    }

    @FXML
    void deleteProductButtonAction(ActionEvent event) {
        Product selectedProduct = tvProducts.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Products");
        alert.setHeaderText("DELETE");
        alert.setContentText("Do you want to delete this product");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            Inventory.deleteProduct(selectedProduct);
            tvProducts.setItems(Inventory.getAllProducts());
        }
    }

    @FXML
    void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void searchPartKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchString = txtSearchParts.getText();
            if (searchString.matches("\\d+")) {
                int id = Integer.parseInt(searchString);
                Part searchPart = Inventory.lookupPart(id);
                tvParts.getSelectionModel().select(searchPart);
            } else {
                tvParts.setItems(Inventory.lookupPart(searchString));
            }
        }
    }

    @FXML
    void searchProductKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchString = txtSearchProducts.getText();
            if (searchString.matches("\\d+")) {
                int id = Integer.parseInt(searchString);
                Product searchProduct = Inventory.lookupProduct(id);
                tvProducts.getSelectionModel().select(searchProduct);
            } else {
                tvProducts.setItems(Inventory.lookupProduct(searchString));
            }
        }
    }

    @FXML
    void addPartButtonAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddPartScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void modifyPartButtonAction(ActionEvent event) throws IOException {
        int selectedPartIndex = tvParts.getSelectionModel().getSelectedIndex();
        Part selectedPart = tvParts.getSelectionModel().getSelectedItem();
        ModifyPartController.setPart(selectedPartIndex, selectedPart);

        Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyPartScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void addProductButtonAction(ActionEvent event) {

    }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvParts.setItems(Inventory.getAllParts());
        colPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartPerUnitCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        tvProducts.setItems(Inventory.getAllProducts());
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colProductPerUnitCost.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


}
