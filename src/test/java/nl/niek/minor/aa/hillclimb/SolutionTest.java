package nl.niek.minor.aa.hillclimb;

import static org.junit.Assert.*;

import nl.niek.minor.aa.hillclimb.Solution.MoveDirection;

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
		solution.addRight();// 0
		solution.addDown();// 1
		solution.addDown();// 2
		solution.addDown();// 3
		solution.addDown();// 4
		solution.addRight();// 5
		solution.addRight();// 6
		solution.addRight();// 7
		solution.addDown();// 8
		solution.addRight();// 9

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
		solution.addRight();
		solution.addRight();
		solution.addRight();
		solution.addRight();
		solution.addRight();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();

		solution.addDown();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMoveRightMovesFull()
	{
		solution = new Solution(5, 5);
		solution.addRight();
		solution.addRight();
		solution.addRight();
		solution.addRight();
		solution.addRight();

		solution.addRight();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMoveDownMovesFull()
	{
		solution = new Solution(5, 5);
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();

		solution.addDown();
	}
}
