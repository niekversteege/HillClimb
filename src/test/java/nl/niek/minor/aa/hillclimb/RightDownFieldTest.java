package nl.niek.minor.aa.hillclimb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RightDownFieldTest
{
	private RightDownField	field;

	@Before
	public void setUp() throws Exception
	{
		
	}

	@Test
	public void testCreateEmptySolution()
	{
		field = new RightDownField(10, 10);
		Solution s = field.createEmptySolution();

		assertEquals(20, s.size());
	}

}
