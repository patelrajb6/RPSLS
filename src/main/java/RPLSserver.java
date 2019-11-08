import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RPLSserver extends Application {

	Server svr;
	Text play1,play2, play1choice,play2choice,winner;
	Button rockBtn, spockBtn, paperBtn,scissorBtn, lizardBtn, playAgainBtn, quitBtn;
	Button startBtn, backBtn, scloseBtn;
	TextField sPortNum;
	GameInfo game;
	HashMap<String, Scene> sceneMap;
	ListView <String> items;
	
	Server createServer(){
        return new Server(sPortNum.getText(),data->{
        	Platform.runLater(()->{
        		if(data.getPlayer1Connection())
        		play1.setText(data.getPlayer1Name()+ " :connected");
        	if(data.getPlayer2Connection())
        		play2.setText(data.getPlayer2Name()+ " :connected");
        	play1choice.setText(data.getPlayer1Choice());
        	play2choice.setText(data.getPlayer2Choice()); 
        	if(!data.getPlayer1Choice().equals("a") && !data.getPlayer2Choice().equals("a"))
        		winner.setText("winner:  "+ data.evaluate());
        	});
        	
        	
        		
        	
        });
                
	}

    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Server nn= new Server();
//	   Client mm = new Client(); 
//	   mm.start();
		
	   launch(args);
	}
	
	
	Scene sStartScene(Stage primaryStage)
	{
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		
		BackgroundFill background_fill = new BackgroundFill(Color.BLACK,  
                CornerRadii.EMPTY, Insets.EMPTY); 

		// create Background 
		Background background = new Background(background_fill); 

		Text top = new Text ("ROCK PAPER SCISSORS LIZARD SPOCK");
		top.setFont(Font.font ("Verdana", 20));
		top.setFill(Color.RED);
		top.setEffect(ds);
		
		
		sPortNum = new TextField("5555");
		sPortNum.setTranslateY(200);
		
		startBtn = new Button("Start Game");
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(sSecondScene());
				svr= createServer();
			}
		});
		
		VBox store = new VBox(top,sPortNum, startBtn);
		
		store.setBackground(background);

		
		return new Scene(store,600,600);
		
	}
	
	Scene sSecondScene()
	{
		backBtn = new Button("Back");
		
		
		Label clients = new Label("Client");
		
		play1 = new Text();
		play2 = new Text();
		 play1choice= new Text();
		 play2choice= new Text();
		winner=new Text();
		
		Label status = new Label("Status");
		
		//HBox cliestus = new HBox(clients, status);	
		
		VBox store  = new VBox(backBtn,play1,play1choice,play2,play2choice,winner);
		
		return new Scene(store,600,600);
	}

	Scene sThirdScene()
	{
		Label top = new Label("Player One");
		
		Label playerNam = new Label();
		Label totalWins = new Label();
		Label totalGames = new Label();
		Label winRatio = new Label();

		return new Scene(new VBox(top,playerNam,totalWins, totalGames, winRatio),600,600);
		
	}
	
	Scene sFourthScene()

	{
		Label top = new Label("Player Two");
		
		Label playerNam = new Label();
		Label totalWins = new Label();
		Label totalGames = new Label();
		Label winRatio = new Label();
		
		return new Scene(new VBox(top,playerNam,totalWins, totalGames, winRatio),600,600);
	}
	
	//feel free to remove the starter code from this method
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		primaryStage.setTitle("RPLS!!!");
		sceneMap = new HashMap<String,Scene>();
		sceneMap.put("start", sStartScene(primaryStage));
		sceneMap.put("second", sSecondScene());
		sceneMap.put("third", sThirdScene());
		sceneMap.put("fourth", sFourthScene());
		
		primaryStage.setScene(sStartScene(primaryStage));
		primaryStage.show();
	}
	Scene startScene(Stage primaryStage)
	{   
		Text title = new Text ("Rock Paper Scissors Lizard Spock");
		title.setFont(Font.font ("Verdana", 20));
		title.setFill(Color.RED);
		
		TextField ipAdd    = new TextField();
		TextField portNum  = new TextField();
		TextField nickName = new TextField();
		
		Button startBtn =  new Button("Start Game");
		
		Button closeBtn =  new Button("Close");
		
		VBox store = new VBox(title, ipAdd, portNum,nickName, startBtn, closeBtn);
		
		return new Scene(store,600,600);
	}
	
	Scene waitingScene(Stage primaryStage)
	{
		Button backBtn =  new Button("Back");
		backBtn.setOnAction(e->primaryStage.setScene(startScene(primaryStage)));
		
		Label player1Lbl = new Label("Player 1");
		Label player2Lbl = new Label("Player 2");
		
		HBox playInfo = new HBox(player1Lbl, player2Lbl);
		
		Label player1Nck = new Label();
		Label player2Nck = new Label();
		
		HBox playerNck = new HBox(player1Nck, player2Nck);
		
		Label waiting = new Label();	
		
		VBox store = new VBox(backBtn, playInfo, playerNck, waiting);
		
		return new Scene(store,600,600);
	}
	
	Scene gamePlayScene()
	{
		rockBtn=  new Button("Rock");
		paperBtn=  new Button("Paper");
		spockBtn=  new Button("Spock");
		scissorBtn=  new Button("Scissors");
		lizardBtn=  new Button("Lizzard");
		HBox game= new HBox(rockBtn,paperBtn,spockBtn,scissorBtn,lizardBtn);
		playAgainBtn=  new Button("play Again");
		quitBtn = new Button("Quit");
		HBox bottomBtns= new HBox(playAgainBtn,quitBtn);
		Label yourChoice= new Label(" Your Choice and Opponent Choice");
		Label Player1Choice= new Label();
		Label Player2Choice= new Label();
		
		HBox Choice= new HBox(yourChoice,Player1Choice,Player2Choice);
		BorderPane pane= new BorderPane();
		pane.setCenter(game);
		pane.setBottom(bottomBtns);
		return new Scene(pane,600,600);
	}
	
	Scene summary(GameInfo game)
	{
		GridPane pane= new GridPane();
		Text session= new Text("Session Summary");
		Text totGames= new  Text("Total Games played: "+Integer.toString(game.totalGames));
		Text players= new Text("Player1       Player2");
		Text totalwins= new Text("Total wins   "+Integer.toString(game.getPlayer1Scores())+Integer.toString(game.getPlayer2Scores()));
		int lost1= game.totalGames-game.getPlayer1Scores();
		int lost2= game.totalGames-game.getPlayer2Scores();
		Text lostGames= new Text("Total losses:   "+Integer.toString(lost1)+Integer.toString(lost2));
		VBox box= new VBox(session,totGames,players,totalwins,lostGames);
		return new Scene(box,600,600);
	}

	
	
	

}