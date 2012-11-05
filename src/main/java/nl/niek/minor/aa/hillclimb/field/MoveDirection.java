package nl.niek.minor.aa.hillclimb.field;

public enum MoveDirection
{
	RIGHT("R"), DOWN("D");

	private String	s;

	private MoveDirection(String s)
	{
		this.s = s;
	}

	public final String toString()
	{
		return s;
	}
}
