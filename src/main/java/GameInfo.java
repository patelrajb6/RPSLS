import java.io.Serializable;
import java.util.ArrayList;

public class GameInfo implements Serializable {
	private int  playerScore1;    //keeping count of games player 1 won
	private int playerScore2;	  // keeping count of games player 2 won
	private Boolean player1Connection;	//checking if the connection is made by player1 or not
	private Boolean player2Connection;  //checking if the connection is made by player 2 or not
	private String player1Name;			//nickname of player 1
	private String player2Name;			//nickname of player 2
	private String player1Choice;		//what player 1 played
	private String player2Choice;		//what player 2 played
	private Boolean PlayAgain1,PlayAgain2;		//bools to check if both wants to play again or not
	private int totalGames;
	enum Choices
	{
		Rock, Paper, Scissors, Lizard, Spock
	}
	
	GameInfo()		//initializing all the data members
	{
		playerScore1=0;
		playerScore2=0;
		player1Connection=false;
		player2Connection=false;
		player1Name="none";
		player2Name="none";
		player1Choice=" no choice";
		player2Choice=" no choice";
		PlayAgain1= false;
		PlayAgain2=false;
		totalGames=0;
	}
	
	
	
	
	//setters
	
	void setPlayer1Scores(int score1)
	{
		this.playerScore1= score1;
	}
	void setPlayer2Scores(int score2)
	{
		this.playerScore1= score2;
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
		return this.playerScore1;
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
		
		return "none";
	}
	void updateScores(int score1, int score2)
	{
		this.setPlayer1Scores(score1);
		this.setPlayer2Scores(score2);
	}

}
