package nl.niek.minor.aa.hillclimb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

		assertEquals(MoveDirection.RIGHT, solution.get(0).getDirection());
		assertEquals(MoveDirection.DOWN, solution.get(1).getDirection());
		assertEquals(MoveDirection.DOWN, solution.get(2).getDirection());
		assertEquals(MoveDirection.DOWN, solution.get(3).getDirection());
		assertEquals(MoveDirection.DOWN, solution.get(4).getDirection());
		assertEquals(MoveDirection.RIGHT, solution.get(5).getDirection());
		assertEquals(MoveDirection.RIGHT, solution.get(6).getDirection());
		assertEquals(MoveDirection.RIGHT, solution.get(7).getDirection());
		assertEquals(MoveDirection.DOWN, solution.get(8).getDirection());
		assertEquals(MoveDirection.RIGHT, solution.get(9).getDirection());
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
		solution.addRight();// 4 should become down
		solution.addDown();

		solution.swapDirectionAndRebuild(3);

		assertEquals(MoveDirection.RIGHT, solution.get(3).getDirection());
		assertEquals(MoveDirection.DOWN, solution.get(4).getDirection());
	}
	
	@Test
	public void testSwapDirections()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

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

		solution.swapRandomDirection();

		int nrOfDownMoves = 0;
		int nrOfRightMoves = 0;

		for (int i = 0; i < solution.getNrOfCurrentMoves(); i++)
		{
			if (solution.get(i).getDirection() == MoveDirection.DOWN)
			{
				nrOfDownMoves++;
			}
			else
			{
				nrOfRightMoves++;
			}
		}
		
		assertEquals(18, solution.getNrOfCurrentMoves());
		assertEquals(9, nrOfDownMoves);
		assertEquals(9, nrOfRightMoves);
	}

	@Test
	public void testSwapWeight()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

		solution.addDown();// 4
		solution.addDown();// 8
		solution.addRight();// 3
		solution.addDown();// changes from 4 to 9 in swap
		solution.addRight();// 5
		solution.addDown();// 2

		assertEquals(26, solution.getTotalWeight());

		solution.swapDirectionAndRebuild(3);

		assertEquals(31, solution.getTotalWeight());
	}

	@Test
	public void testSwapSolutionSize()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

		solution.addDown();// 4
		solution.addDown();// 8
		solution.addRight();// 3
		solution.addDown();// changes from 4 to 9 in swap
		solution.addRight();// 5
		solution.addDown();// 2
		int beforeSize = solution.getNrOfCurrentMoves();

		solution.swapDirectionAndRebuild(3);

		assertEquals(beforeSize, solution.getNrOfCurrentMoves());
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

		solution.swapDirectionAndRebuild(7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSwapAtLastMove()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

		solution.addRight();// 5
		solution.addDown();// 9
		solution.addDown();// 3
		solution.addDown();// 4
		solution.addRight(); // 5

		solution.swapDirectionAndRebuild(4);

		assertEquals(MoveDirection.DOWN, solution.get(4));
	}

	@Test
	public void testRandomizeSolution()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();
		solution.randomizeSolution();

		assertEquals(18, solution.getNrOfCurrentMoves());
	}

	@Test
	public void testRandomizeFullSolution()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

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

		solution.randomizeSolution();

		assertEquals(18, solution.getNrOfCurrentMoves());
	}
	
	@Test
	public void testRandomizeSolutionFromIndex()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

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

		Move move = solution.get(5);
		solution.randomizeSolution(move.getRow(), move.getColumn());

		assertEquals(18, solution.getNrOfCurrentMoves());
	}

	@Test
	public void testFinishSolutionRandomly()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

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

		solution.finishSolutionRandomly();

		int nrOfDownMoves = 0;
		int nrOfRightMoves = 0;

		for (int i = 0; i < solution.getNrOfCurrentMoves(); i++)
		{
			if (solution.get(i).getDirection() == MoveDirection.DOWN)
			{
				nrOfDownMoves++;
			}
			else
			{
				nrOfRightMoves++;
			}
		}
		
		assertEquals(18, solution.getNrOfCurrentMoves());
		assertEquals(9, nrOfDownMoves);
		assertEquals(9, nrOfRightMoves);
	}

	@Test
	public void testEquals()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

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

		Solution anotherSolution = defaultField.createEmptySolution();

		anotherSolution.addRight();// 5
		anotherSolution.addDown();// 9
		anotherSolution.addDown();// 3
		anotherSolution.addDown();// 4
		anotherSolution.addRight(); // 5
		anotherSolution.addDown();// 2
		anotherSolution.addDown();// 2
		anotherSolution.addDown();// 2
		anotherSolution.addRight();// 6
		anotherSolution.addRight();// 2
		anotherSolution.addRight();// 9
		anotherSolution.addDown();// 9
		anotherSolution.addRight();// 3
		anotherSolution.addRight();// 5
		anotherSolution.addDown();// 5
		anotherSolution.addRight();// 7
		anotherSolution.addRight(); // 6
		anotherSolution.addDown(); // 0

		assertTrue(solution.equals(anotherSolution));
	}

	@Test
	public void testNotEquals()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

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

		Solution anotherSolution = defaultField.createEmptySolution();

		anotherSolution.addRight();
		anotherSolution.addDown();
		anotherSolution.addDown();
		anotherSolution.addDown();
		anotherSolution.addRight();
		anotherSolution.addDown();
		anotherSolution.addRight();
		anotherSolution.addDown();
		anotherSolution.addRight();
		anotherSolution.addRight();
		anotherSolution.addRight();
		anotherSolution.addDown();
		anotherSolution.addRight();
		anotherSolution.addRight();
		anotherSolution.addDown();
		anotherSolution.addDown();
		anotherSolution.addRight();
		anotherSolution.addDown();

		assertFalse(solution.equals(anotherSolution));
	}

	@Test
	public void testCopy()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

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

		Solution anotherSolution = solution.copy();

		assertEquals(solution.getNrOfCurrentMoves(), anotherSolution.getNrOfCurrentMoves());
		assertTrue(solution.equals(anotherSolution));
	}
	
	@Test
	public void testContainsCoordinates()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

		solution.addRight();// 5
		solution.addDown();// 9
		solution.addDown();// 3
		
		assertTrue(solution.containsCoordinates(0, 1));
	}
	
	@Test
	public void testNotContainsCoordinates()
	{
		RightDownField defaultField = FieldFactory.getDefaultField();
		solution = defaultField.createEmptySolution();

		solution.addRight();// 5
		solution.addDown();// 9
		solution.addDown();// 3
		
		assertFalse(solution.containsCoordinates(3, 5));
	}
}
