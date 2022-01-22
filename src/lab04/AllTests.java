package lab04;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class AllTests {

	@Test
	public void test() {
	}
	
	@Test
	public void testMoveBoat() {
		State testState = new State(3, 3, 0, 0, Position.LEFT);
		assertEquals(Position.RIGHT, testState.moveBoat());
	}
	
	@Test
	public void testInvalidMove1MissionaryRight() {
		State testState = new State(1, 3, 2, 0, Position.LEFT);
		testState = testState.processMove("m");
		
		assertEquals(1, testState.getCannibalLeft());
		assertEquals(3, testState.getMissionaryLeft());
		assertEquals(2, testState.getCannibalRight());
		assertEquals(0, testState.getMissionaryRight());
		assertEquals(Position.LEFT, testState.getBoat());
	}
	
	@Test
	public void testInvalidMove1CannibalRight2MissionariesRight() {
		State testState = new State(2, 3, 1, 0, Position.LEFT);
		testState = testState.processMove("cm");
		assertEquals(2, testState.getCannibalLeft());
		assertEquals(3, testState.getMissionaryLeft());
		assertEquals(1, testState.getCannibalRight());
		assertEquals(0, testState.getMissionaryRight());
		assertEquals(Position.LEFT, testState.getBoat());
	}

	@Test
	public void testvalidMove2CannibalsRight() {
		State testState = new State(3, 3, 0, 0, Position.LEFT);
		testState = testState.processMove("cc");
		assertEquals(1, testState.getCannibalLeft());
		assertEquals(3, testState.getMissionaryLeft());
		assertEquals(2, testState.getCannibalRight());
		assertEquals(0, testState.getMissionaryRight());
		assertEquals(Position.RIGHT, testState.getBoat());
	}
	
	@Test
	public void testInvalidMove2CannibalsLeft() {
		State testState = new State(0, 1, 3, 2, Position.RIGHT);
		testState = testState.processMove("cc");
		assertEquals(0, testState.getCannibalLeft());
		assertEquals(1, testState.getMissionaryLeft());
		assertEquals(3, testState.getCannibalRight());
		assertEquals(2, testState.getMissionaryRight());
		assertEquals(Position.RIGHT, testState.getBoat());
	}
	
	@Test						  
	public void testSuccessors() {
		State testState = new State(3, 0, 0, 3, Position.LEFT);
		ArrayList<State> successors = new ArrayList<>(testState.generateSuccessors());
		
		State childState = successors.get(0);
		assertEquals(1, childState.getCannibalLeft());
		assertEquals(0, childState.getMissionaryLeft());
		assertEquals(2, childState.getCannibalRight());
		assertEquals(3, childState.getMissionaryRight());
		assertEquals(Position.RIGHT, childState.getBoat());
		
		State child2State = successors.get(0);
		assertEquals(1, child2State.getCannibalLeft());
		assertEquals(0, child2State.getMissionaryLeft());
		assertEquals(2, child2State.getCannibalRight());
		assertEquals(3, child2State.getMissionaryRight());
		assertEquals(Position.RIGHT, child2State.getBoat());
	}
	
	@Test
	public void testFinish() {
		State test1State = new State(3, 0, 0, 3, Position.RIGHT);

		State test2State = new State(0, 3, 3, 0, Position.RIGHT);
		
		assertTrue(test1State.finished());
		assertTrue(test2State.finished());
	}
	
}
