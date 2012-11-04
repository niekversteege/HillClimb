package nl.niek.minor.aa.hillclimb;

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
	 * Create an empty Solution object with the correct dimensions for this
	 * field.
	 * 
	 * @return
	 */
	public Solution createEmptySolution()
	{
		return new Solution(height, width);
	}
}
