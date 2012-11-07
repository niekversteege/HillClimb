package nl.niek.minor.aa.hillclimb;

import nl.niek.minor.aa.hillclimb.cli.RDPrinter;
import nl.niek.minor.aa.hillclimb.field.RightDownField;

public class RightDown
{
	private static final int	DEFAULT_ITERATIONS	= 100;
	private static final int	RANDOM_REPEATS		= 50;
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
		// TODO
		// for iterations
		// - create a random solution currentHillTop
		// - for iterations
		// - - every possible change to solution
		// - - -

	}

	private void runNextAscent()
	{
		// PSEUDEOCODE.
		// for iterations
		// - create a random solution better solution
		// - for iterations
		// - - for every change possible, while !bettersolutionfound, try it
		// - - - if it's better, replace better solution
		// - - - break while loop
		// - - if bettersolution is better than best, replace

		for (int i = 0; i < maxIterations; i++)
		{
			Solution betterSolution = field.createEmptySolution();
			betterSolution.randomizeSolution();

			boolean betterSolutionFound = false;
			int maxNrOfChanges = betterSolution.getMaxSize() - 1;
			int currentChange = 0;

			do
			{
				Solution newSolution = betterSolution.copy();
				newSolution.swapDirectionAndRebuild(currentChange);
				currentChange++;

				if (newSolution.betterThan(betterSolution))
				{
					betterSolution = newSolution.copy();
				}
			}
			while (betterSolutionFound && currentChange < maxNrOfChanges);

			tryToReplaceBest(betterSolution);
		}
	}

	private void runRandomMutation()
	{
		// PSEUDEOCODE.
		// for iterations
		// - create a random solution better solution
		// - for R
		// - - swap a direction at a random position
		
		for (int i = 0; i < maxIterations; i++)
		{
			Solution betterSolution = field.createEmptySolution();
			betterSolution.randomizeSolution();

			for (int j = 0; j < RANDOM_REPEATS; j++)
			{
				Solution newSolution = betterSolution.copy();
				newSolution.swapRandomDirection();

				if (newSolution.betterThan(betterSolution))
				{
					betterSolution = newSolution.copy();
				}
			}

			tryToReplaceBest(betterSolution);
		}
	}

	private void tryToReplaceBest(Solution solution)
	{
		if (bestSolution != null)
		{
			if (solution.betterThan(bestSolution))
			{
				RDPrinter
						.println("Found a better solution than the best so far.");
				bestSolution = solution.copy();
			}
		}
		else
		{
			bestSolution = solution.copy();
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
