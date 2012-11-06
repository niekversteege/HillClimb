package nl.niek.minor.aa.hillclimb;

import java.util.ArrayList;
import java.util.List;

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
	private int				maxSize;
	private List<Move>		allMoves;

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
		this.maxSize = ((height - 1) + (width - 1));

		this.nrOfDownMoves = 0;
		this.nrOfRightMoves = 0;

		this.maxDownMoves = height - 1;
		this.maxRightMoves = width - 1;

		allMoves = new ArrayList<Move>();
	}

	private final int totalMoves()
	{
		return nrOfRightMoves + nrOfDownMoves;
	}

	private void addMove(final int row, final int column,
			MoveDirection direction)
	{
		Move newMove = field.createMoveObject(row, column, direction);

		allMoves.add(newMove);
	}

	/**
	 * Move to the right. Gets the weight of that move from the field and adds
	 * it to the Solutions weight.
	 * 
	 */
	public void addRight()
	{
		if (isFull())
		{
			throw new IllegalArgumentException("Solution is full.");
		}

		if (nrOfRightMoves < maxRightMoves)
		{
			/*
			 * We need to increase before getting weight since we start in a
			 * corner with no weight.
			 */
			nrOfRightMoves++;

			addMove(nrOfDownMoves, nrOfRightMoves, MoveDirection.RIGHT);
		}
		else
		{
			throw new IllegalArgumentException("Cannot go farther right.");
		}
	}

	/**
	 * Move down. Gets the weight of that move from the field and adds it to the
	 * Solutions weight.
	 * 
	 */
	public void addDown()
	{
		if (isFull())
		{
			throw new IllegalArgumentException("Solution is full.");
		}

		if (nrOfDownMoves < maxDownMoves)
		{
			/*
			 * We need to increase before getting weight since we start in a
			 * corner with no weight.
			 */
			nrOfDownMoves++;

			addMove(nrOfDownMoves, nrOfRightMoves, MoveDirection.DOWN);
		}
		else
		{
			throw new IllegalArgumentException("Cannot go farther down.");
		}
	}

	private boolean isFull()
	{
		if (allMoves.size() >= maxSize)
		{
			return true;
		}

		return false;
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
	public final int getMaxSize()
	{
		return maxSize;
	}

	public final int getNrOfCurrentMoves()
	{
		return allMoves.size();
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

		if (allMoves.size() >= index)
		{
			if (index <= totalMoves())
			{
				retVal = allMoves.get(index).getDirection();
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

		for (Move m : allMoves)
		{
			if (m != null)
			{
				buffer.append(m.getDirection().toString());
			}
		}

		buffer.append("] - " + getTotalWeight());

		return buffer.toString();
	}

	public final int getTotalWeight()
	{
		int weight = 0;

		for (Move move : allMoves)
		{
			weight += move.getWeight();
		}

		return weight;
	}

	/**
	 * 
	 * @param anotherSolution
	 * @return
	 */
	public boolean betterThan(Solution anotherSolution)
	{
		return this.getTotalWeight() < anotherSolution.getTotalWeight();
	}

	/**
	 * Toggle the move at the given index between Right or Down. Also updates
	 * the total weight of the Solution.
	 * 
	 * @param index
	 */
	public void swapDirection(int index)
	{
		if (index < allMoves.size())
		{
			Move currentMove = allMoves.get(index);

			int currentColumn = currentMove.getColumn();
			int currentRow = currentMove.getRow();

			Move newMove = null;

			if (currentMove.getDirection() == MoveDirection.DOWN)
			{
				// if down; row - 1; column + 1; to get a right move.
				newMove = field.createMoveObject(currentRow - 1,
						currentColumn + 1, MoveDirection.RIGHT);
				/* We also need to swap the direction of the move after this one */
				allMoves.get(index + 1).setDirection(MoveDirection.DOWN);
			}
			else
			{
				// else right: row + 1; column - 1; to get a down move.
				newMove = field.createMoveObject(currentRow + 1,
						currentColumn - 1, MoveDirection.DOWN);
				/* We also need to swap the direction of the move after this one */
				allMoves.get(index + 1).setDirection(MoveDirection.RIGHT);
			}

			allMoves.set(index, newMove);
		}
		else
		{
			throw new IllegalArgumentException(
					"This move has not been set yet.");
		}
	}
}
