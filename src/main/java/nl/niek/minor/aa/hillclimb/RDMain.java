package nl.niek.minor.aa.hillclimb;

import nl.niek.minor.aa.hillclimb.field.FieldFactory;

public class RDMain
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RightDown rd = new RightDown(FieldFactory.getDefaultField());
		rd.go();
	}

}
