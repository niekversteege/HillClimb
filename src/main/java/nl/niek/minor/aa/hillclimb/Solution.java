package nl.niek.minor.aa.hillclimb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import nl.niek.minor.aa.hillclimb.cli.RDPrinter;
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
	private List<Move>		allMoves	= null;

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
			/*
			 * We need to increase before getting weight since we start in a
			 * corner with no weight.
			 */

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
			/*
			 * We need to increase before getting weight since we start in a
			 * corner with no weight.
			 */

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
	private List<Move> randomizeSolution(int row, int column)
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

			allMoves.add(field.createMoveObject(row, column, direction));
		}

		return newRandomMoves;
	}

	/**
	 * Apply a random change to the Solution. Change 5 directions in the
	 * Solution. Which ones are changed is determined randomly.
	 */
	private void applyRandomChanges()
	{
		int numberOfChanges = 5;
		Random r = new Random();
		/*
		 * Need to reduce by one since we cannot swap the last element in the
		 * list.
		 */
		// int nrOfCurrentMoves = getNrOfCurrentMoves() - 1;
		//
		// for (int i = 0; i < numberOfChanges; i++)
		// {
		// int randomMove = r.nextInt(nrOfCurrentMoves);
		// swapDirection(randomMove);
		// }

	}

	/**
	 * Pick a random point in the Solution and randomize the moves after that.
	 */
	public void finishSolutionRandomly()
	{
		Random r = new Random();
		finishSolutionRandomly(r.nextInt(maxSize - 1));
	}

	/**
	 * Clear the list from index and generate a new solution from index. Index
	 * must be maxSize - 1 or smaller.
	 * 
	 * @param index
	 */
	public void finishSolutionRandomly(final int index)
	{
		Move indexMove = allMoves.get(index);
		List<Move> keptMoves = new ArrayList<Move>();
		keptMoves.addAll(allMoves.subList(0, index + 1));

		allMoves.clear();

		keptMoves.addAll(randomizeSolution(indexMove.getRow(),
				indexMove.getColumn()));

		allMoves.addAll(keptMoves);
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
	 * Toggle the move at the given index between Right or Down. Also updates
	 * the total weight of the Solution. We cannot swap the last Move. This
	 * method will finish the solution from this point with a randomly generated
	 * list of moves.
	 * 
	 * @param index
	 */
	protected void swapDirection(int index)
	{
		if (index < (allMoves.size() - 1))
		{
			Move currentMove = allMoves.get(index);

			int currentColumn = currentMove.getColumn();
			int currentRow = currentMove.getRow();

			MoveDirection currentDirection = currentMove.getDirection();

			Move newMove = null;

			/*
			 * We cannot swap directions on moves on the edges of the map.
			 */
			if (isNotOnEdgeOfMap(currentRow, currentColumn))
			{
				switch (currentDirection)
				{
				case DOWN:
					if (currentRow > 0)
					{
						newMove = field.createMoveObject(currentRow - 1,
								currentColumn + 1, MoveDirection.RIGHT);
						allMoves.set(index, newMove);

						/* Finish the solution randomly from here. */
						// allMoves.addAll(randomizeSolution(newMove.getRow(),
						// newMove.getColumn()));
					}
					else
					{
						throw new IllegalStateException(
								"Down moves must be on row 1 or larger.");
					}
					break;
				case RIGHT:
					if (currentColumn > 0)
					{
						newMove = field.createMoveObject(currentRow + 1,
								currentColumn - 1, MoveDirection.DOWN);
						allMoves.set(index, newMove);

						/* Finish the solution randomly from here. */
						// allMoves.addAll(randomizeSolution(newMove.getRow(),
						// newMove.getColumn()));
					}
					else
					{
						throw new IllegalStateException(
								"Right moves must be on col 1 or larger.");
					}
					break;
				default:
					/* error */
					break;
				}
			}
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

	private boolean isNotOnEdgeOfMap(final int row, final int column)
	{
		return column < field.getWidth() - 1 && row < field.getHeight() - 1;
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

	public Solution copy()
	{
		Solution solution = new Solution(field, allMoves);
		return solution;
	}
}
