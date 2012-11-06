package nl.niek.minor.aa.hillclimb;

import nl.niek.minor.aa.hillclimb.cli.RDPrinter;
import nl.niek.minor.aa.hillclimb.field.RightDownField;

public class RightDown
{
	private static final int	DEFAULT_ITERATIONS	= 200;
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
		switch (method)
		{
		case NEXT_ASCENT:
			RDPrinter.println("Running Next Ascent HillClimb algorithm.");
			runNextAscent();
			break;
		case RANDOM_MUTATION:
			RDPrinter.println("Running Random Mutation HillClimb algorithm.");
			runRandomMutation();
			break;
		case STEEPEST_ASCENT:
			runSteepestAscent();
			RDPrinter.println("Running Steepest Ascent HillClimb algorithm.");
			break;
		default:
			/* Error */
			break;
		}
	}

	private void runSteepestAscent()
	{
		// TODO Auto-generated method stub

	}

	private void runNextAscent()
	{
		// TODO Auto-generated method stub

	}

	private void runRandomMutation()
	{
		Solution betterSolution = field.createEmptySolution();
		betterSolution.randomizeSolution();

		RDPrinter.println("Random solution: " + betterSolution);

		for (int i = 0; i < maxIterations; i++)
		{
			Solution newSolution = betterSolution.copy();
			newSolution.swapRandomDirection();

			if (newSolution.betterThan(betterSolution))
			{
				betterSolution = newSolution.copy();
				RDPrinter
						.println("Applying random changes produced a better solution in iteration "
								+ i
								+ " with weight: "
								+ betterSolution.getTotalWeight() + ".");
			}
		}

		if (bestSolution == null || betterSolution.betterThan(bestSolution))
		{
			bestSolution = betterSolution.copy();
		}
	}

	/**
	 * Get the best solution that was found. Can return null if run() method has
	 * not been called.
	 * 
	 * @return
	 */
	public final Solution getBestSolution()
	{
		return bestSolution;
	}
}
