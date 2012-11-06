package nl.niek.minor.aa.hillclimb.field;

import sun.org.mozilla.javascript.internal.ast.WithStatement;
import nl.niek.minor.aa.hillclimb.Move;
import nl.niek.minor.aa.hillclimb.Solution;

/**
 * The playing field for Right-Down algorithms. This contains a (square) field
 * of tiles, each with a number representing it's weight.
 * 
 * @author Niek
 * 
 */
public class RightDownField
{
	private static final int	DEFAULT_WIDTH	= 10;
	private static final int	DEFAULT_HEIGHT	= 10;

	private int[][]				field;
	private int					height;
	private int					width;
	private int					rowIndex		= 0;

	public RightDownField(final int height, final int width)
	{
		this.height = height;
		this.width = width;
		field = new int[height][width];
	}

	/**
	 * Construct an empty field with default sizes. In this case 10x10 tiles.
	 */
	public RightDownField()
	{
		this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
	}

	/**
	 * Add a row of weight numbers to the field. Throws an exception if it won't
	 * fit.
	 * 
	 * @param newRow
	 */
	public void addRow(final int[] newRow)
	{
		if (newRow.length > width)
		{
			throw new IllegalArgumentException("Row is too wide.");
		}

		if (rowIndex == height)
		{
			throw new IllegalArgumentException("Field is full of rows.");
		}
		else
		{
			field[rowIndex] = newRow;
			rowIndex++;
		}
	}

	/**
	 * Create an empty Solution object with the correct dimensions for this
	 * field.
	 * 
	 * @return a new empty Solution object called with the height and width of
	 *         this field.
	 */
	public Solution createEmptySolution()
	{
		return new Solution(this);
	}

	/**
	 * Get the number placed in the field at the given position.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public final int getWeight(final int row, final int column)
	{
		if (row >= height || row < 0 || column >= width || column < 0)
		{
			throw new IllegalArgumentException("Out of field bounds: " + row + ", " + column);
		}
		return field[row][column];
	}

	/**
	 * Create a Move object from the coordinates given.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public Move createMoveObject(final int row, final int column,
			final MoveDirection direction)
	{
		Move move = new Move(row, column, getWeight(row, column), direction);
		return move;
	}

	public final int getWidth()
	{
		return width;
	}
	
	public final int getHeight()
	{
		return height;
	}
}
