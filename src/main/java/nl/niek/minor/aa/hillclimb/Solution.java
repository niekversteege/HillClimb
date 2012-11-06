package nl.niek.minor.aa.hillclimb;

import nl.niek.minor.aa.hillclimb.field.MoveDirection;
import nl.niek.minor.aa.hillclimb.field.RightDownField;

/**
 * Class that contains a sequence of (representations of) right or down
 * movements that lead to the most right and most down tile.
 * 
 * @author Niek
 * 
 */
public class Solution
{
	private RightDownField	field;
	private int				size;
	private int				weight;
	private MoveDirection[]	solutionSequence;

	private int				nrOfDownMoves;
	private int				nrOfRightMoves;

	final private int		maxDownMoves;
	final private int		maxRightMoves;

	/**
	 * Create a solution for a field with the given dimensions. It needs a field
	 * to be able to swap moves. Solutions should only be created through the
	 * method in RightDownField.
	 * 
	 * @param field
	 * 
	 * @param height
	 * @param width
	 */
	public Solution(RightDownField field, final int height, final int width)
	{
		this.field = field;
		this.size = (height + width);
		this.weight = 0;

		this.nrOfDownMoves = 0;
		this.nrOfRightMoves = 0;

		this.maxDownMoves = height;
		this.maxRightMoves = width;

		solutionSequence = new MoveDirection[size];
	}

	private final int totalMoves()
	{
		return nrOfRightMoves + nrOfDownMoves;
	}

	/**
	 * Move to the right. Gets the weight of that move from the field and adds
	 * it to the Solutions weight.
	 * 
	 */
	public void addRight()
	{
		checkFull();

		if (nrOfRightMoves <= maxRightMoves)
		{
			solutionSequence[totalMoves()] = MoveDirection.RIGHT;
			/*
			 * We need to increase before getting weight since we start in a
			 * corner with no weight.
			 */
			nrOfRightMoves++;

			this.weight += field.getWeight(nrOfDownMoves, nrOfRightMoves);
		}
		else
		{
			throw new IllegalArgumentException("Cannot go farther down.");
		}
	}

	/**
	 * Move down. Gets the weight of that move from the field and adds it to the
	 * Solutions weight.
	 * 
	 */
	public void addDown()
	{
		checkFull();

		if (nrOfDownMoves <= maxDownMoves)
		{
			solutionSequence[totalMoves()] = MoveDirection.DOWN;
			/*
			 * We need to increase before getting weight since we start in a
			 * corner with no weight.
			 */
			nrOfDownMoves++;

			this.weight += field.getWeight(nrOfDownMoves, nrOfRightMoves);
		}
		else
		{
			throw new IllegalArgumentException("Cannot go farther right.");
		}
	}

	private void checkFull()
	{
		if (totalMoves() >= size)
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
	 * Apply a random change to the Solution. Only changes a set of moves, not
	 * the entire solution.
	 */
	public void applyRandomChange()
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
			if (index <= totalMoves())
			{
				retVal = solutionSequence[index];
			}
			else
			{
				throw new IllegalArgumentException(
						"Solution has not been set for this point yet.");
			}
		}
		else
		{
			throw new IllegalArgumentException("Solution size is too small.");
		}

		return retVal;
	}

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer("[");

		for (MoveDirection m : solutionSequence)
		{
			if (m != null)
			{
				buffer.append(m.toString());
			}
		}

		buffer.append("] - " + weight);

		return buffer.toString();
	}

	public final int getTotalWeight()
	{
		return weight;
	}

	/**
	 * 
	 * @param anotherSolution
	 * @return
	 */
	public boolean betterThan(Solution anotherSolution)
	{
		return this.weight < anotherSolution.getTotalWeight();
	}

	/**
	 * Toggle the move at the given index between Right or Down. Also updates
	 * the total weight of the Solution.
	 * 
	 * @param index
	 */
	public void swap(int index)
	{
		if (index <= totalMoves())
		{
			// delete weight from current move
			// delete move from array
			// replace it with the new move (toggle direction)
			// add new weight
		}
		else
		{
			throw new IllegalArgumentException(
					"This move has not been set yet.");
		}
	}
}
