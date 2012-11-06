package nl.niek.minor.aa.hillclimb;

import nl.niek.minor.aa.hillclimb.field.RightDownField;

public class RightDown
{
	private static final int	DEFAULT_ITERATIONS	= 100;
	private int					maxIterations		= 0;
	private RightDownField		field;
	private Solution			bestSolution;

	/**
	 * Create a RightDown solver with a default number of iterations to run to
	 * algorithm for. Default is 100.
	 * 
	 * @param field
	 */
	public RightDown(RightDownField field)
	{
		this(field, DEFAULT_ITERATIONS);
	}

	/**
	 * Create a RightDown solver that will run the algorithm for the given
	 * amount of iterations.
	 * 
	 * @param field
	 * @param iterations
	 */
	public RightDown(RightDownField field, final int iterations)
	{
		this.field = field;
		this.bestSolution = null;
		this.maxIterations = iterations;
	}

	/**
	 * Apply the hill climb algorithm to the constructed field and find a
	 * solution.
	 */
	public void run(HillclimbMethod method)
	{
		Solution currentSolution = field.createEmptySolution();
		currentSolution.randomizeSolution();

		for (int i = 0; i < maxIterations; i++)
		{
			// TODO: applying changes to solution requires new weight data.
		}
	}

	/**
	 * Get the best solution that was found. Can return null if run() method is
	 * not yet run.
	 * 
	 * @return
	 */
	public final Solution getBestSolution()
	{
		return bestSolution;
	}
}
