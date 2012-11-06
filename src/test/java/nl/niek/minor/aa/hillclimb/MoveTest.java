package nl.niek.minor.aa.hillclimb;

import static org.junit.Assert.*;

import nl.niek.minor.aa.hillclimb.field.MoveDirection;

import org.junit.Before;
import org.junit.Test;

public class MoveTest
{
	Move	move;

	@Before
	public void setUp() throws Exception
	{
		move = new Move(45, 3, 20, MoveDirection.DOWN);
	}

	@Test
	public void testCreation()
	{
		assertNotNull(move);
	}
	
	@Test
	public void testRow()
	{
		assertEquals(45, move.getRow());
	}
	
	@Test
	public void testCol()
	{
		assertEquals(3, move.getColumn());
	}
	
	@Test
	public void testWeight()
	{
		assertEquals(20, move.getWeight());
	}
	
	@Test
	public void testDirection()
	{
		assertEquals(MoveDirection.DOWN, move.getDirection());
	}

	@Test
	public void testEqualsObject()
	{
		Move anotherMove = new Move(45, 3, 20, MoveDirection.DOWN);
		
		assertTrue(move.equals(anotherMove));
	}
	
	@Test
	public void testNotEqualsObject()
	{
		Move anotherMove = new Move(43, 53, 1, MoveDirection.DOWN);
		
		assertFalse(move.equals(anotherMove));
	}

}
