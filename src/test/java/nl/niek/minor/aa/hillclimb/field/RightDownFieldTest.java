package nl.niek.minor.aa.hillclimb.field;

import static org.junit.Assert.*;

import nl.niek.minor.aa.hillclimb.Move;
import nl.niek.minor.aa.hillclimb.Solution;
import nl.niek.minor.aa.hillclimb.field.MoveDirection;
import nl.niek.minor.aa.hillclimb.field.RightDownField;

import org.junit.Before;
import org.junit.Test;

public class RightDownFieldTest
{
	private RightDownField	field;
	private int[]			testRow0;

	// private int[] testRow1;
	// private int[] testRow2;
	// private int[] testRow3;
	// private int[] testRow4;

	@Before
	public void setUp() throws Exception
	{
		testRow0 = new int[] { 3, 5, 2, 1, 6 };
		// testRow1 = new int[] { 4, 9, 3, 5, 7 };
		// testRow2 = new int[] { 8, 3, 9, 4, 2 };
		// testRow3 = new int[] { 2, 4, 5, 1, 4 };
		// testRow4 = new int[] { 1, 9, 2, 6, 0 };
	}

	@Test
	public void testCreateEmptySolution()
	{
		field = new RightDownField(10, 10);
		Solution s = field.createEmptySolution();

		/* field with 10*10 has 18 moves to get across */
		assertEquals(18, s.getMaxSize());
	}

	@Test
	public void testGetWeight()
	{
		field = new RightDownField(5, 5);
		field.addRow(testRow0);

		assertEquals(3, field.getWeight(0, 0));
	}

	@Test
	public void testAddRow()
	{
		field = new RightDownField(5, 5);
		field.addRow(testRow0);

		assertEquals(3, field.getWeight(0, 0));
		assertEquals(5, field.getWeight(0, 1));
		assertEquals(2, field.getWeight(0, 2));
		assertEquals(1, field.getWeight(0, 3));
		assertEquals(6, field.getWeight(0, 4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddRowFull()
	{
		field = new RightDownField(3, 5);
		field.addRow(testRow0);
		field.addRow(testRow0);
		field.addRow(testRow0);
		field.addRow(testRow0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddRowTooWide()
	{
		field = new RightDownField(5, 3);
		field.addRow(testRow0);
	}

	@Test
	public void testCreateMoveObject()
	{
		field = new RightDownField(5, 5);
		field.addRow(testRow0);

		Move move = field.createMoveObject(0, 1, MoveDirection.RIGHT);

		assertEquals(1, move.getColumn());
		assertEquals(0, move.getRow());
		assertEquals(5, move.getWeight());
		assertEquals(MoveDirection.RIGHT, move.getDirection());
	}
}
