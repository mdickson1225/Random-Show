

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Mark
 */
public class stageGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Show Creator");
        primaryStage.show();
        //Button btn = new Button();
        //btn.setText("Create Show");
        //btn.setOnAction(new EventHandler<ActionEvent>() {

        //@Override
        //public void handle(ActionEvent event) {
        //System.out.println("You just created a show");
        //}
        //});
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        //StackPane root = new StackPane();
        //root.getChildren().add(btn);
        Text scenetitle = new Text("Show Generator");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(scenetitle, 0, 0, 2, 1);
        Label card = new Label("Number of Card Tricks");
        grid.add(card, 0, 1);
        TextField cardField = new TextField();
        grid.add(cardField, 1, 1);
        Label coin = new Label("Number of Coin Tricks");
        grid.add(coin, 0, 2);
        TextField coinField = new TextField();
        grid.add(coinField, 1, 2);
        Label rope = new Label("Number of Rope Tricks");
        grid.add(rope, 0, 3);
        TextField ropeField = new TextField();
        grid.add(ropeField, 1, 3);
        Label silk = new Label("Number of Silk Tricks");
        grid.add(silk, 0, 4);
        TextField silkField = new TextField();
        grid.add(silkField, 1, 4);
        Button btn = new Button();
        btn.setText("Create Show");
        grid.add(btn, 1, 5);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	
            	Get_data gd = new Get_data();
            	
            	//Replace this with data retreival from text fields
            	//and add 4 calls to "gd.getTricks(type,n)" 
            	int coin_no = 0;
            	int silk_no = 0;
            	int rope_no = 0;
            	int card_no = 0;
                String cardString = cardField.getText();
                card_no = Integer.parseInt(cardString);
                gd.getTricks(TrickType.CARD, card_no);
                gd.storeData("show.txt");
                String coinString = coinField.getText();
                coin_no = Integer.parseInt(coinString);
                gd.getTricks(TrickType.COIN, coin_no);
                gd.storeData("show.txt");
                String ropeString = ropeField.getText();
                rope_no = Integer.parseInt(ropeString);
                gd.getTricks(TrickType.ROPE, rope_no);
                gd.storeData("show.txt");
                String silkString = silkField.getText();
                silk_no = Integer.parseInt(silkString);
                gd.getTricks(TrickType.SILK, silk_no);
                gd.storeData("show.txt");
                System.out.println("You just created a show");
            }

        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Get_data gd = new Get_data();
    	launch(args);
    	gd.getTricks(TrickType.ROPE,2);
        gd.getTricks(TrickType.CARD, 2);
    	gd.storeData("show.txt");
    	/*int n = res.length;
    	for(int i = 0; i < n; i++) {
    		System.out.printf("Trick number %d\n",i);
    		System.out.printf("Trick name is: %s\n",res[i][0]);
    		System.out.printf("Trick url is: %s\n", res[i][1]);    		
    	} */
    	
    	
    	
    	
    }

}
