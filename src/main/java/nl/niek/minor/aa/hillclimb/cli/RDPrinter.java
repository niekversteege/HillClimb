package nl.niek.minor.aa.hillclimb.cli;

import nl.niek.minor.aa.hillclimb.Solution;
import nl.niek.minor.aa.hillclimb.field.RightDownField;

public class RDPrinter
{
	public static void print(String string)
	{
		System.out.print(string);
	}

	public static void println(String string)
	{
		System.out.println(string);
	}

	public static void printField(RightDownField field)
	{

	}

	public static void printSolutionInField(RightDownField field,
			Solution solution)
	{
		for (int i = 0; i < field.getHeight(); i++)
		{
			for (int j = 0; j < field.getWidth(); j++)
			{
				if (solution.containsCoordinates(i, j))
				{
					print("[X]");
				}
				else
				{
					print("[ ]");
				}
			}
			print("\n");
		}
		println(solution.toString());
	}
}
