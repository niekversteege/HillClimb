package nl.niek.minor.aa.hillclimb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	public Solution(RightDownField field)
	{
		this.field = field;
		this.maxSize = ((field.getHeight() - 1) + (field.getWidth() - 1));

		this.maxDownMoves = field.getHeight() - 1;
		this.maxRightMoves = field.getWidth() - 1;

		allMoves = new ArrayList<Move>();
	}

	protected Solution(RightDownField field, List<Move> allMoves)
	{
		this(field);
		this.allMoves.addAll(allMoves);
	}

	private void addMove(final int row, final int column,
			MoveDirection direction)
	{
		Move newMove = field.createMoveObject(row, column, direction);

		allMoves.add(newMove);
	}

	private final int countMoves(MoveDirection direction)
	{
		int moves = 0;

		for (Move m : allMoves)
		{
			if (m.getDirection() == direction)
			{
				moves++;
			}
		}

		return moves;
	}

	private final int countDownMoves()
	{
		return countMoves(MoveDirection.DOWN);
	}

	private final int countRightMoves()
	{
		return countMoves(MoveDirection.RIGHT);
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

		int rightMoves = countRightMoves();
		int downMoves = countDownMoves();

		if (rightMoves < maxRightMoves)
		{
			addMove(downMoves, rightMoves + 1, MoveDirection.RIGHT);
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

		int rightMoves = countRightMoves();
		int downMoves = countDownMoves();

		if (downMoves < maxDownMoves)
		{
			addMove(downMoves + 1, rightMoves, MoveDirection.DOWN);
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
		allMoves.clear();
		allMoves.addAll(randomizeSolution(0, 0));
	}

	/**
	 * Generate a list of random moves from the given coordinates, that reach
	 * the end properly.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	protected List<Move> randomizeSolution(int row, int column)
	{
		List<Move> newRandomMoves = new ArrayList<Move>();

		int downMovesLeft = maxDownMoves - row;
		int rightMovesLeft = maxRightMoves - column;
		int totalMovesLeft = downMovesLeft + rightMovesLeft;

		/* Create a list of directions */
		List<MoveDirection> directions = new ArrayList<MoveDirection>();
		/* Add height - 1 number of down moves */
		for (int i = 0; i < downMovesLeft; i++)
		{
			directions.add(MoveDirection.DOWN);
		}

		/* Add width - 1 number of right moves */
		for (int i = 0; i < rightMovesLeft; i++)
		{
			directions.add(MoveDirection.RIGHT);
		}

		/* Randomize the contents */
		Collections.shuffle(directions);

		/*
		 * Get the correct coordinates and weight from field and overwrite the
		 * entire list.
		 */
		for (int i = 0; i < totalMovesLeft; i++)
		{
			MoveDirection direction = directions.get(i);
			if (direction == MoveDirection.RIGHT)
			{
				column++;
			}
			else
			{
				row++;
			}

			newRandomMoves.add(field.createMoveObject(row, column, direction));
		}

		return newRandomMoves;
	}

	/**
	 * Pick a random point in the Solution and randomize the moves after that.
	 */
	public void finishSolutionRandomly()
	{
		Random r = new Random();
		int nextInt = r.nextInt(allMoves.size());
		finishSolutionRandomly(nextInt);
	}

	/**
	 * Clear the list from index and generate a new solution from index. Index
	 * must be maxSize - 1 or smaller.
	 * 
	 * @param index
	 */
	public void finishSolutionRandomly(final int index)
	{
		if (index < allMoves.size())
		{
			if (index < 0)
			{
				throw new IllegalArgumentException("Index cannot be negative.");
			}
			else
			{
				Move indexMove = allMoves.get(index);
				allMoves.subList(index + 1, allMoves.size()).clear();

				List<Move> randomizeSolution = randomizeSolution(
						indexMove.getRow(), indexMove.getColumn());

				allMoves.addAll(randomizeSolution);
			}
		}
		else
		{
			throw new IllegalArgumentException(
					"Index is too large. List is only " + allMoves.size());
		}
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

	/**
	 * Return the amount of moves saved in the solution so far.
	 * 
	 * @return
	 */
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
	public final Move get(final int index)
	{
		Move retVal = null;

		if (allMoves.size() >= index)
		{
			retVal = allMoves.get(index);
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

	/**
	 * Re-count all the Move objects for their weight and return it.
	 * 
	 * @return
	 */
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
	 * Toggle the move at the given index between Right or Down (and thus also
	 * the move at index + 1). Also updates the total weight of the Solution. We
	 * cannot swap the last Move. This method will finish the solution from this
	 * point with a randomly generated list of moves.
	 * 
	 * @param index
	 */
	protected void swapDirectionAndRebuild(int index)
	{
		if (index < (allMoves.size() - 1))
		{
			List<MoveDirection> directions = getDirectionList();

			directions.set(index, toggleDirection(directions.get(index)));

			directions.set(index + 1,
					toggleDirection(directions.get(index + 1)));

			rebuildSolutionFromDirectionList(directions);
		}
		else if (index == allMoves.size() - 1)
		{
			throw new IllegalArgumentException("Cannot swap the last Move.");
		}
		else
		{
			throw new IllegalArgumentException(
					"This move has not been set yet.");
		}
	}

	private MoveDirection toggleDirection(MoveDirection direction)
	{
		if (direction == MoveDirection.DOWN)
		{
			return MoveDirection.RIGHT;
		}

		return MoveDirection.DOWN;
	}

	/**
	 * Delete all moves and rebuild them from scratch with the given directions.
	 * 
	 * @param directions
	 */
	public void rebuildSolutionFromDirectionList(
			final List<MoveDirection> directions)
	{
		allMoves.clear();

		int column = 0;
		int row = 0;

		/*
		 * Get the correct coordinates and weight from field and overwrite the
		 * entire list.
		 */
		for (MoveDirection d : directions)
		{
			if (d == MoveDirection.RIGHT)
			{
				column++;
			}
			else if (d == MoveDirection.DOWN)
			{
				row++;
			}

			allMoves.add(field.createMoveObject(row, column, d));
		}
	}

	private List<MoveDirection> getDirectionList()
	{
		List<MoveDirection> directions = new ArrayList<MoveDirection>();

		for (Move m : allMoves)
		{
			directions.add(m.getDirection());
		}

		return directions;
	}

	/**
	 * Check if a move with the given coordinates is in the list of made moves.
	 * 
	 * @param nextMove
	 * @return
	 */
	public boolean containsCoordinates(final int row, final int column)
	{
		for (Move m : allMoves)
		{
			if (m.getColumn() == column && m.getRow() == row)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Solution)
		{
			Solution compare = (Solution) obj;

			if (this.getTotalWeight() != compare.getTotalWeight())
			{
				return false;
			}

			for (int i = 0; i < allMoves.size(); i++)
			{
				if (!allMoves.get(i).equals(compare.get(i)))
				{
					return false;
				}
			}

			return true;
		}

		return false;
	}

	/**
	 * Create a NEW copy of this Solution for modifying purposes.
	 * 
	 * @return
	 */
	public Solution copy()
	{
		Solution solution = new Solution(field, allMoves);
		return solution;
	}
	
	/**
	 * Swap / Switch two moves at the first point after index where two different directions
	 * follow each other.
	 */
	public void swapDirection(final int index)
	{
		for (int i = index; i < getNrOfCurrentMoves() - 1; i++)
		{
			MoveDirection currentDirection = allMoves.get(i).getDirection();
			if (allMoves.get(i + 1).getDirection() != currentDirection)
			{
				swapDirectionAndRebuild(i);
				return;
			}
		}
	}

	/**
	 * Swap / Switch two moves at the first point where two different directions
	 * follow each other.
	 */
	public void swapDirection()
	{
		swapDirection(0);
	}
}
