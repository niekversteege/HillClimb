package nl.niek.minor.aa.hillclimb;

import nl.niek.minor.aa.hillclimb.field.MoveDirection;

/**
 * Wrapping class to hold data for a move. Contains X, Y location on the board,
 * it's direction and it's weight. The coordinates this points at are where the
 * move was made to.
 * 
 * @author Niek
 * 
 */
public class Move
{
	private int				row;
	private int				column;
	private int				weight;
	private MoveDirection	direction;

	public Move(final int row, final int column, final int weight,
			MoveDirection direction)
	{
		this.row = row;
		this.column = column;
		this.weight = weight;
		this.direction = direction;
	}

	public final int getRow()
	{
		return row;
	}

	public final int getColumn()
	{
		return column;
	}

	public final int getWeight()
	{
		return weight;
	}

	public final MoveDirection getDirection()
	{
		return direction;
	}

	public void setDirection(final MoveDirection direction)
	{
		this.direction = direction;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Move)
		{
			Move compare = (Move) obj;

			if (row != compare.getRow() || column != compare.getColumn()
					|| weight != compare.getWeight()
					|| direction != compare.getDirection())
			{
				return false;
			}

			return true;
		}

		return false;
	}
}
