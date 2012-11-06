package nl.niek.minor.aa.hillclimb;

import nl.niek.minor.aa.hillclimb.cli.RDPrinter;
import nl.niek.minor.aa.hillclimb.field.FieldFactory;
import nl.niek.minor.aa.hillclimb.field.RightDownField;

public class RDMain
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		RDPrinter.println("Running hillclimb for this field: ");
		RDPrinter.printField(defaultField);
		RightDown rd = new RightDown(defaultField);
		rd.run(HillclimbMethod.RANDOM_MUTATION);
		RDPrinter.println("Best solution found:");
		Solution bestSolution = rd.getBestSolution();
		RDPrinter.println(bestSolution.toString());
		RDPrinter.println("Path in field for this solution: ");
		RDPrinter.printSolutionInField(defaultField, bestSolution);
	}

}
