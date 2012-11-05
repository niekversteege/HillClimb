package nl.niek.minor.aa.hillclimb;

import nl.niek.minor.aa.hillclimb.field.RightDownField;

public class RightDown
{
	private static final int	DEFAULT_ITERATIONS	= 100;
	private int					iterations			= 0;
	private RightDownField		field;

	public RightDown(RightDownField field)
	{
		this.field = field;
	}

	public RightDown(RightDownField field, final int iterations)
	{
		this(field);
		this.iterations = iterations;
	}

	/**
	 * Apply the hill climb algorithm to the constructed field and find a
	 * solution.
	 */
	public void go()
	{
		// create a field with numbers
		// choose a hillclimb strategy: next-ascent or random-mutation
		// choose a random solution for the given right-down field
		// apply algorithm for X amount of times
		// print result
		// print the time it took
	}
}
