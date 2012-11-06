package nl.niek.minor.aa.hillclimb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import nl.niek.minor.aa.hillclimb.field.FieldFactory;
import nl.niek.minor.aa.hillclimb.field.MoveDirection;
import nl.niek.minor.aa.hillclimb.field.RightDownField;

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
		solution = FieldFactory.getDefaultField().createEmptySolution();
		solution.addRight();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addRight();
		solution.addRight();
		solution.addRight();
		solution.addDown();
		solution.addRight();

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

	@Test
	public void testAddMoveWeight()
	{
		solution = FieldFactory.getDefaultField().createEmptySolution();
		solution.addRight();// 5
		solution.addDown();// 9
		solution.addDown();// 3
		solution.addDown();// 4
		solution.addRight(); // 5
		solution.addDown();// 2
		solution.addDown();// 2
		solution.addDown();// 2
		solution.addRight();// 6
		solution.addRight();// 2
		solution.addRight();// 9
		solution.addDown();// 9
		solution.addRight();// 3
		solution.addRight();// 5
		solution.addDown();// 5
		solution.addRight();// 7
		solution.addRight(); // 6
		solution.addDown(); // 0

		assertEquals(84, solution.getTotalWeight());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMoveFull()
	{
		solution = FieldFactory.getDefaultField().createEmptySolution();
		solution.addRight();
		solution.addRight();
		solution.addRight();
		solution.addRight();
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
		solution.addDown();
		solution.addDown();
		solution.addDown();

		solution.addRight();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMoveRightMovesFull()
	{
		solution = FieldFactory.getDefaultField().createEmptySolution();
		solution.addRight();
		solution.addRight();
		solution.addRight();
		solution.addRight();
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
		solution = FieldFactory.getDefaultField().createEmptySolution();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();
		solution.addDown();

		solution.addDown();
	}

	@Test
	public void testWeight()
	{
		solution = FieldFactory.getDefaultField().createEmptySolution();
		solution.addDown();// 4
		solution.addDown();// 8

		assertEquals(4 + 8, solution.getTotalWeight());
	}

	@Test
	public void testBetterThan()
	{
		solution = FieldFactory.getDefaultField().createEmptySolution();
		solution.addDown();// 4
		solution.addDown();// 8
		solution.addDown();// 2

		Solution anotherSolution = FieldFactory.getDefaultField()
				.createEmptySolution();
		anotherSolution.addDown(); // 4
		anotherSolution.addDown(); // 8
		anotherSolution.addRight(); // 3

		assertTrue(solution.betterThan(anotherSolution));
	}

	@Test
	public void testSwap()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

		solution.addDown();
		solution.addDown();
		solution.addRight();
		solution.addDown();// 3 should become right
		solution.addRight();
		solution.addRight();

		solution.swapDirection(3);

		assertEquals(MoveDirection.RIGHT, solution.get(3));
	}

	@Test
	public void testSwapWeight()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

		solution.addDown();//4
		solution.addDown();//8
		solution.addRight();//3
		solution.addDown();// changes from 4 to 9 in swap
		solution.addRight();//5
		solution.addDown();//2

		assertEquals(26, solution.getTotalWeight());
		
		solution.swapDirection(3);
		
		assertEquals(31, solution.getTotalWeight());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndexTooBig()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

		solution.addDown();
		solution.addDown();
		solution.addRight();
		solution.addDown();
		solution.addRight();

		solution.swapDirection(7);
	}
}
