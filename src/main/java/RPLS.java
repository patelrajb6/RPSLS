import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//WE  took the project lightly and we were not able to produce all desirable features. but we did manage to get 
//the game up and running



public class RPLS extends Application {

	Server svr;		//server to start
	Text play1,play2, play1choice,play2choice,winner;		//texts
	Button rockBtn, spockBtn, paperBtn,scissorBtn, lizardBtn, playAgainBtn, quitBtn;	//buttons
	Button startBtn, backBtn, scloseBtn;
	TextField sPortNum;
	GameInfo game;		//gameinfo reference
	HashMap<String, Scene> sceneMap;		//hashmap to keep the scenes
	ListView <String> items;
	
	Server createServer(){			//function when called created the server 
        return new Server(sPortNum.getText(),data->{
        	Platform.runLater(()->{
        		if(data.getPlayer1Connection())
        			play1.setText(data.getPlayer1Name()+ " :connected");
        		else
        			play1.setText(data.getPlayer1Name()+ " :disconnected");
        		
        		if(data.getPlayer2Connection())
        			play2.setText(data.getPlayer2Name()+ " :connected");
        		else
        			play2.setText(data.getPlayer2Name()+ " :connected");
        		
        	
        		items.getItems().add("Player1's choice :"+data.getPlayer1Choice());
    			items.getItems().add("Player2's choice :"+data.getPlayer2Choice());
    			items.getItems().add("winner:  "+ data.evaluate());
        		
        		
        		
        	});
        	
        	
        		
        	
        });
                
	}

    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   launch(args);
	}
	
	
	Scene sStartScene(Stage primaryStage)			//Scene where the game starts
	{
		GridPane gridPane= new GridPane();		//using gridpane
		DropShadow ds = new DropShadow();		//dropping shadown on the title
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		
		BackgroundFill background_fill = new BackgroundFill(Color.rgb(200, 200, 0), CornerRadii.EMPTY, Insets.EMPTY); // color settingd

		// create Background 
		Background background = new Background(background_fill); 

		Text top = new Text ("ROCK PAPER SCISSORS LIZARD SPOCK");		//title
		top.setFont(Font.font ("Verdana", 20));		//font
		top.setFill(Color.RED);	//color
		top.setEffect(ds);
		
		Label port= new Label("Enter Port: ");
		
		sPortNum = new TextField("5555");		//for the entry of desired port with default 5555
		sPortNum.setTranslateX(sPortNum.getLayoutX()-300);
		
		startBtn = new Button("Start Game");		//button which starts the server and the game
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(sSecondScene());		//moves to the second scene
				svr= createServer();//creates the server
			}
		});
			gridPane.setMinSize(400, 200);
		
			gridPane.setPadding(new Insets(10, 10, 10, 10));
		 	gridPane.setVgap(5); 
		 	gridPane.setHgap(5);       
	      
	      //Setting the Grid alignment 
		 	gridPane.setAlignment(Pos.CENTER); 
		 	gridPane.add(top, 0, 0); 
		 	gridPane.add(port, 0, 1); 
		 	gridPane.add(sPortNum, 1,1 );
	      
	     
	       
	      gridPane.add(startBtn, 0, 2);  
		
		gridPane.setBackground(background);

		
		return new Scene(gridPane,600,600);
		
	}
	
	Scene sSecondScene()		//creating the gui for the  server to keep track of whats happening
	{
		BackgroundFill background_fill = new BackgroundFill(Color.rgb(100, 200, 10), CornerRadii.EMPTY, Insets.EMPTY);
		backBtn = new Button("Back");
		items= new ListView<String>();
		BorderPane pane= new BorderPane();
		
		items.setBackground(new Background(new BackgroundFill(Color.rgb(100, 255, 200), CornerRadii.EMPTY, Insets.EMPTY)));
		
		play1 = new Text();
		play1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		
		play2 = new Text();
		
		play2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		VBox title= new VBox(backBtn,play1,play2); 
		title.setSpacing(5);
		 play1choice= new Text();
		 play2choice= new Text();
		winner=new Text();
		
		pane.setBackground(new Background(background_fill));
		pane.setTop(title);
		pane.setCenter(items);
		
		
		
		return new Scene(pane,600,600);
	}

	
	
	//feel free to remove the starter code from this method
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		primaryStage.setTitle("RPLS!!!");
		sceneMap = new HashMap<String,Scene>();
		sceneMap.put("start", sStartScene(primaryStage));
		sceneMap.put("second", sSecondScene());
		
		
		primaryStage.setScene(sStartScene(primaryStage));
		primaryStage.show();
	}
	
	
	
	

}