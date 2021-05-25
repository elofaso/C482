import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

 /** This Inventory Management program provides a system for
  * managing an inventory of parts and products built from
  * those parts.
  *
  * @author Eric Lofaso
  *
  */
public class Main extends Application {

     /**
      * The start method creates the FXML stage and loads the initial scene.
      *
      * @param primaryStage Primary JavaFX Stage of application.
      * @throws Exception Exception.
      */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainForm.fxml"));
        primaryStage.setScene(new Scene(root, 900, 300));
        primaryStage.show();
    }

     /**
      * The main method is the entry point of the application.
      *
      * @param args Argument list.
      */
    public static void main(String[] args) {

        int id = Inventory.getPartId();
        Part part1 = new InHouse(id,"widget", 9.99, 10, 1, 20, 101);
        id = Inventory.getPartId();
        Part part2 = new Outsourced(id, "bolt", 0.05, 30, 20, 100, "Global");

        Product product1 = new Product(1, "bicycle", 99.95, 2, 0, 10);


        Inventory.addPart(part1);
        Inventory.addPart(part2);

        Inventory.addProduct(product1);

        launch(args);
    }

}
