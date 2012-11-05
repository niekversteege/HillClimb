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

	/**
	 * Move to the right.
	 */
	public void addRight()
	{
		checkFull();
		if (numberOfDownMoves != 0)
		{
			solutionSequence[index] = MoveDirection.RIGHT;
			index++;
			numberOfDownMoves--;
		}
		else
		{
			throw new IllegalArgumentException("Cannot go farther down.");
		}
	}

	/**
	 * Move down.
	 */
	public void addDown()
	{
		checkFull();
		if (numberOfRightMoves != 0)
		{
			solutionSequence[index] = MoveDirection.DOWN;
			index++;
			numberOfRightMoves--;
		}
		else
		{
			throw new IllegalArgumentException("Cannot go farther right.");
		}
	}

	private void checkFull()
	{
		if (index == size)
		{
			throw new IllegalArgumentException("Solution is full.");
		}
	}

	/**
	 * Put the elements of the solution in a random new order.
	 */
	public void randomizeSolution()
	{
		// TODO
	}

	/**
	 * Get the total amount of moves that a solution should have.
	 * 
	 * @return Total solution size.
	 */
	public final int size()
	{
		return size;
	}

	/**
	 * Get the direction at the given index.
	 * 
	 * @param index
	 * @return
	 */
	public final MoveDirection get(final int index)
	{
		MoveDirection retVal = null;

		if (solutionSequence.length >= index)
		{
			retVal = solutionSequence[index];
		}
		else
		{
			throw new IllegalArgumentException("Solution size is too small.");
		}

		return retVal;
	}
}
