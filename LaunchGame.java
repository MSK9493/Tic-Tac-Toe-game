package Tic_Tac_Toe_game;

import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char[][] board;
	public TicTacToe()
	{
		board = new char[3][3];     // /U0000=Null char
		initBoard();
		
	}
	
	void initBoard()
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				board[i][j] = ' ';
			}
		}
	}
	
	static void displayBoard()
	{
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) 
		{
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) 
			{
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

   static void PlaceMark(int row, int col, char mark)
	{
		if(row>=0 && row<=2 && col>=0 && col<=2)
		{
			board[row][col] = mark;
		}
		else
			System.out.println("invalid input position");
	}
	
	static boolean checkcolWin()
	{
		
		for (int j = 0; j < 2; j++) 
		{
			if(board[0][j]!=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
			{
				return true;
			}
		}
		return false;
	}
	
	static boolean checkrowWin()
	{
		for (int i = 0; i < 2; i++) 
		{
			if(board[i][0]!=' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2] )
			{
				return true;
			}
		}
		return false;
	}
	
	static boolean checkDiagWin()
	{
		if( board[0][0]!=' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] ||  board[0][2]!=' ' && 
				     board[0][2] == board[1][1] && board[1][1] == board[2][0])
		{
			return true;
		}
		return false;
	}
	
	
	static boolean checkdraw()
	{
		for (int i = 0; i < 2; i++) 
		{
			for (int j = 0; j < 2; j++) 
			{
				if(board[i][j]==' ')
				{
					return false;
				}
			}
		}
		return true;
	}
}


public class LaunchGame
{

	public static void main(String[] args) {
		TicTacToe t=new TicTacToe();
		
		HumanPlayer p1=new HumanPlayer("Bob",'x');
		AiPlayer p2 = new AiPlayer("Tap",'o');
		player cp;
		cp = p1;
		
		while(true)
		{
			System.out.println(cp.name+"turn");
			
			cp.makeMove();
			
			TicTacToe.displayBoard();
		
			if(TicTacToe.checkcolWin() || TicTacToe.checkrowWin()  || TicTacToe.checkDiagWin())
			{
				System.out.println(cp.name+ " has won");
				break;
			}
			else if(TicTacToe.checkdraw()){
				System.out.println("game is draw");
				break;
			}
			else
			{
				if(cp == p1)
				{
					cp=p2;
				}
				else {
					cp=p1;
				}
			}
		}
	}
}

abstract class player
{
	String name;
	char mark;
	abstract void makeMove();
	
	boolean isvalidMove(int row, int col)
	{
		if(row>=0 && row<=2 && col>=0 && col<=2)
		{
			if(TicTacToe.board[row][col]==' ')
			{
				return false;
			}
		}
		return true ;
		
	}
}

class HumanPlayer extends player
{
	
	
	public HumanPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()
	{
		Scanner scan =new Scanner(System.in);
		int row,col;
		do{
			System.out.println("Enter the row and col");
			 row =scan.nextInt();
			 col =scan.nextInt();
		}while(isvalidMove(row,col));
		TicTacToe.PlaceMark(row, col, mark);
		
	}
	
	
}




class AiPlayer  extends player
{
	
	
	public AiPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()
	{
		Scanner scan =new Scanner(System.in);
		int row,col;
		do{
			Random	r=new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
			
		}while(isvalidMove(row,col));
		TicTacToe.PlaceMark(row, col, mark);
		
	}
	
}









