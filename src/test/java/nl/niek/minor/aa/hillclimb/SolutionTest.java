package nl.niek.minor.aa.hillclimb;

import static org.junit.Assert.*;

import nl.niek.minor.aa.hillclimb.field.MoveDirection;

import org.junit.Before;
import org.junit.Test;

public class SolutionTest
{
	Solution	solution;

	@Before
	public void setUp() throws Exception
	{

	}

	@Test
	public void testAddMove()
	{
		solution = new Solution(10, 10);
		solution.addRight(1);// 0
		solution.addDown(1);// 1
		solution.addDown(1);// 2
		solution.addDown(1);// 3
		solution.addDown(1);// 4
		solution.addRight(1);// 5
		solution.addRight(1);// 6
		solution.addRight(1);// 7
		solution.addDown(1);// 8
		solution.addRight(1);// 9

		assertEquals(MoveDirection.RIGHT, solution.get(0));
		assertEquals(MoveDirection.DOWN, solution.get(1));
		assertEquals(MoveDirection.DOWN, solution.get(2));
		assertEquals(MoveDirection.DOWN, solution.get(3));
		assertEquals(MoveDirection.DOWN, solution.get(4));
		assertEquals(MoveDirection.RIGHT, solution.get(5));
		assertEquals(MoveDirection.RIGHT, solution.get(6));
		assertEquals(MoveDirection.RIGHT, solution.get(7));
		assertEquals(MoveDirection.DOWN, solution.get(8));
		assertEquals(MoveDirection.RIGHT, solution.get(9));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMoveFull()
	{
		solution = new Solution(5, 5);
		solution.addRight(1);
		solution.addRight(1);
		solution.addRight(1);
		solution.addRight(1);
		solution.addRight(1);
		solution.addDown(1);
		solution.addDown(1);
		solution.addDown(1);
		solution.addDown(1);
		solution.addDown(1);

		solution.addDown(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMoveRightMovesFull()
	{
		solution = new Solution(5, 5);
		solution.addRight(1);
		solution.addRight(1);
		solution.addRight(1);
		solution.addRight(1);
		solution.addRight(1);

		solution.addRight(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMoveDownMovesFull()
	{
		solution = new Solution(5, 5);
		solution.addDown(1);
		solution.addDown(1);
		solution.addDown(1);
		solution.addDown(1);
		solution.addDown(1);

		solution.addDown(1);
	}

	@Test
	public void testWeight()
	{
		solution = new Solution(5, 5);
		solution.addDown(5);
		solution.addDown(8);
		
		assertEquals(5 + 8, solution.getTotalWeight());
	}
}
