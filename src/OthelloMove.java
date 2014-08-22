// OthelloMove.java
//
// ICS 23 / CSE 23 Fall 2007
// Project #2: Black and White
//
// The chooseMove() method in your AI class should return an OthelloMove
// object as its result.


public class OthelloMove
{
	private int row;
	private int col;
	
	
	public OthelloMove(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	
	public int getRow()
	{
		return row;
	}
	
	
	public int getColumn()
	{
		return col;
	}
}
