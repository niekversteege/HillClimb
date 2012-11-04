package nl.niek.minor.aa.hillclimb;

/**
 * Class that contains a sequence of (representations of) right or down
 * movements that lead to the most right and most down tile.
 * 
 * @author Niek
 * 
 */
public class Solution
{

	private int				size;
	private int				index;
	private MoveDirection[]	solutionSequence;

	private int				numberOfDownMoves;
	private int				numberOfRightMoves;

	public enum MoveDirection
	{
		RIGHT, DOWN;
	}

	/**
	 * Create a solution for a field with the given dimensions.
	 * 
	 * @param height
	 * @param width
	 */
	public Solution(int height, int width)
	{
		this.size = (height + width);
		this.index = 0;
		this.numberOfDownMoves = height;
		this.numberOfRightMoves = width;

		solutionSequence = new MoveDirection[size];
	}

	public void addMove(MoveDirection direction)
	{
		if (index != size)
		{
			if (direction == MoveDirection.DOWN)
			{
				if (numberOfDownMoves != 0)
				{
					numberOfDownMoves--;
				}
			}
			else
			{
				if (numberOfRightMoves != 0)
				{
					numberOfRightMoves--;
				}
			}
		}
		else
		{
			throw new IllegalArgumentException("Solution is full.");
		}
	}

	public void randomizeSolution()
	{

	}
}
