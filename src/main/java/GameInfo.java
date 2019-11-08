import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
public class GameInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int  playerScore1;    //keeping count of games player 1 won
	private int playerScore2;	  // keeping count of games player 2 won
	private Boolean player1Connection;	//checking if the connection is made by player1 or not
	private Boolean player2Connection;  //checking if the connection is made by player 2 or not
	private String player1Name;			//nickname of player 1
	private String player2Name;			//nickname of player 2
	private String player1Choice;		//what player 1 played
	private String player2Choice;		//what player 2 played
	private Boolean PlayAgain1,PlayAgain2;		//bools to check if both wants to play again or not
	int totalGames;
//	private HashMap<String, Integer> map= new HashMap<>(); 
//	map["Scissor"]=0;
//	map["Rock"]=1;	//Rock defeats lizard[3] and Scissor[2]
//	map["Paper"]=1;   //Paper can cover Rock[0] and covers spock[4]
//	map["Lizard"]=3;
//	map["Spock"]=4;
	
	
	
	GameInfo()		//initializing all the data members
	{
		
		playerScore1=0;
		playerScore2=0;
		player1Connection=false;
		player2Connection=false;
		player1Name="none";
		player2Name="none";
		player1Choice="a";
		player2Choice="a";
		PlayAgain1= false;
		PlayAgain2=false;
		totalGames=0;
	}

	void setEqual(GameInfo game)
	{
		playerScore1=game.getPlayer1Scores();
		playerScore2=game.getPlayer2Scores();
		player1Connection=game.getPlayer1Connection();
		player2Connection=game.getPlayer2Connection();
		player1Name=game.getPlayer1Name();
		player2Name=game.getPlayer2Name();
		player1Choice=game.getPlayer1Choice();
		player2Choice=game.getPlayer2Choice();
		PlayAgain1= game.getPlayAgainPlayer1();
		PlayAgain2=game.getPlayAgainPlayer2();
		totalGames=game.totalGames;
	}
	
	
	
	//setters
	
	
	void setPlayer1Scores(int score1)
	{
		this.playerScore1= score1;
	}
	void setPlayer2Scores(int score2)
	{
		this.playerScore2= score2;
	}
	void setPlayer1Choice(String score1)
	{
		this.player1Choice= score1;
	}
	void setPlayer2Choice(String score2)
	{
		this.player2Choice= score2;
	}
	
	void setPlayer1Name(String score1)
	{
		this.player1Name= score1;
	}
	void setPlayer2Name(String score2)
	{
		this.player2Name= score2;
	}
	void setPlayer1Connection(Boolean connect)
	{
		this.player1Connection= connect;
	}
	void setPlayer2Connection(Boolean connect)
	{
		this.player2Connection= connect;
	}
	void setPlayAgainPlayer1(Boolean play)
	{
		this.PlayAgain1=play;
	}
	void setPlayAgainPlayer2(Boolean play)
	{
		this.PlayAgain2=play;
	}
	
	
	
	//getters
	
	int  getPlayer1Scores()
	{
		return this.playerScore1;
	}
	int getPlayer2Scores()
	{
		return this.playerScore2;
	}
	String getPlayer1Choice()
	{
		return this.player1Choice;
	}
	String getPlayer2Choice()
	{
		return this.player2Choice;
	}
	
	String getPlayer1Name()
	{
		return this.player1Name;
	}
	String getPlayer2Name()
	{
		return this.player2Name;
	}
	Boolean getPlayer1Connection()
	{
		return this.player1Connection;
	}
	Boolean getPlayer2Connection()
	{
		return this.player2Connection;
	}
	Boolean getPlayAgainPlayer1()
	{
		return this.PlayAgain1;
	}
	Boolean getPlayAgainPlayer2()
	{
		return this.PlayAgain2;
	}
	
	
	
	
	//methods
	
	Boolean have2Players()
	{
		if(this.player1Connection && this.player2Connection)		//if both players are connected
			return true;			//then they can start the game
		return false;    //else wait or terminate
	}
	
	String evaluate()
	{
		this.totalGames++;
		if(player1Choice.equals("a")|| player2Choice.equals("a"))
			return "waiting";
		if(player1Choice.equals(player2Choice))
			return "Draw";
		if(player1Choice.equals("Scissors") && (player2Choice.equals("Paper")|| player2Choice.equals("Lizard")))
		{
			this.setPlayer1Scores(getPlayer1Scores()+1);
			return player1Name;
		}
		if(player1Choice.equals("Paper") && (player2Choice.equals("Rock")|| player2Choice.equals("Spock")))
		{
			this.setPlayer1Scores(getPlayer1Scores()+1);
			return player1Name;
		}
		if(player1Choice.equals("Rock") && (player2Choice.equals("Scissors")|| player2Choice.equals("Lizard")))
		{
			this.setPlayer1Scores(getPlayer1Scores()+1);
			return player1Name;
		}
		if(player1Choice.equals("Lizard") && (player2Choice.equals("Spock")|| player2Choice.equals("Paper")))
		{
			this.setPlayer1Scores(getPlayer1Scores()+1);
			return player1Name;
		}
		if(player1Choice.equals("Spock") && (player2Choice.equals("Scissors")|| player2Choice.equals("Rock")))
		{
			this.setPlayer1Scores(getPlayer1Scores()+1);
			return player1Name;
		}
		
		
		else
		{
			this.setPlayer2Scores(getPlayer2Scores()+1);
			return player2Name;
		}
			
		
		
	}
	

}
