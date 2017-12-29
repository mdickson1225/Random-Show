

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
        primaryStage.getIcons().add(new Image("file:black_top_hat.png"));
        primaryStage.show();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(stageGUI.class.getResource("inputCSS.css").toExternalForm());
        primaryStage.show();

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

                Process_data pd = new Process_data();

                //Replace this with data retreival from text fields
                //and add 4 calls to "gd.getTricks(type,n)" 
                int coin_no = 0;
                int silk_no = 0;
                int rope_no = 0;
                int card_no = 0;
                String cardString = cardField.getText();
                System.out.println(cardString.isEmpty());
                if (!cardString.isEmpty()) {
                    card_no = Integer.parseInt(cardString);
                    pd.getTricks(TrickType.CARD, card_no);
                }

                String coinString = coinField.getText();
                if (!coinString.isEmpty()) {
                    coin_no = Integer.parseInt(coinString);
                    pd.getTricks(TrickType.COIN, coin_no);
                }

                String ropeString = ropeField.getText();
                if (!ropeString.isEmpty()) {
                    rope_no = Integer.parseInt(ropeString);
                    pd.getTricks(TrickType.ROPE, rope_no);
                }

                String silkString = silkField.getText();
                if (!silkString.isEmpty()) {
                    silk_no = Integer.parseInt(silkString);
                    pd.getTricks(TrickType.SILK, silk_no);
                }
                //pd.storeData("show.txt");
                pd.storeLatex("show.tex");
                System.out.println("You just created a show");
                
                /* Open the file that was just created */
                try {
                	
                	String mw_path = "C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.EXE";
                	String target_path = "show.txt";
					new ProcessBuilder(mw_path, target_path).start();
				} catch (IOException e) {
					System.out.println("Unable to open file");
				}
                
            }

        });
    }

    /* Main function launches the GUI */
    public static void main(String[] args) {
        launch(args);
    }

}
