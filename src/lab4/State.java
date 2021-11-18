package lab4;

import java.util.ArrayList;
import java.util.List;

/**
 * The enum values indicate the position/state of a given character.
 * 
 * @author ok243
 *
 */
public class State {
	private int cannibalLeft;
	private int missionaryLeft;
	private int cannibalRight;
	private int missionaryRight;
	private Position boat;
	private State parentState;

	public State getParentState() {
		return parentState;
	}

	public void setParentState(State parentState) {
		this.parentState = parentState;
	}

	public State(int cannibalLeft, int missionaryLeft, int cannibalRight, int missionaryRight, Position boat) {
		this.cannibalLeft = cannibalLeft;
		this.missionaryLeft = missionaryLeft;
		this.cannibalRight = cannibalRight;
		this.missionaryRight = missionaryRight;
		this.boat = boat;
	}

	public State() {

	}

	public boolean finished() {
		return (cannibalLeft == 3 && missionaryRight == 3) || (cannibalRight == 3 && missionaryLeft == 3);
	}

	/**
	 * 
	 * @param userInput
	 * @return true if the new state is a valid state and this state has been
	 *         changed.
	 * @throws CloneNotSupportedException
	 */

	public List<State> generateSuccessors() {
		List<State> successors = new ArrayList<State>();

		if (boat == Position.LEFT) {
			testAndAdd(successors,
					new State(cannibalLeft, missionaryLeft - 2, cannibalRight, missionaryRight + 2, Position.RIGHT));
			testAndAdd(successors,
					new State(cannibalLeft - 2, missionaryLeft, cannibalRight + 2, missionaryRight, Position.RIGHT));
			testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft - 1, cannibalRight + 1,
					missionaryRight + 1, Position.RIGHT));
			testAndAdd(successors,
					new State(cannibalLeft, missionaryLeft - 1, cannibalRight, missionaryRight + 1, Position.RIGHT));
			testAndAdd(successors,
					new State(cannibalLeft - 1, missionaryLeft, cannibalRight + 1, missionaryRight, Position.RIGHT));
		} else {
			testAndAdd(successors,
					new State(cannibalLeft, missionaryLeft + 2, cannibalRight, missionaryRight - 2, Position.LEFT));
			testAndAdd(successors,
					new State(cannibalLeft + 2, missionaryLeft, cannibalRight - 2, missionaryRight, Position.LEFT));
			testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft + 1, cannibalRight - 1,
					missionaryRight - 1, Position.LEFT));
			testAndAdd(successors,
					new State(cannibalLeft, missionaryLeft + 1, cannibalRight, missionaryRight - 1, Position.LEFT));
			testAndAdd(successors,
					new State(cannibalLeft + 1, missionaryLeft, cannibalRight - 1, missionaryRight, Position.LEFT));
		}

		return successors;
	}

	public void testAndAdd(List<State> successors, State newState) {
		if (newState.valid()) {
			newState.setParentState(this);
			successors.add(newState);
		}
	}

	public State processMove(String userInput) {
		State tmpState = new State(cannibalLeft, missionaryLeft, cannibalRight, missionaryRight, boat);
		for (char c : userInput.toCharArray()) {
			if (boat == (Position.LEFT)) { // then ensure character moves to the
											// right
				if (c == 'm') {
					tmpState.missionaryRight++;
					tmpState.missionaryLeft--;
				} else if (c == 'c') {
					tmpState.cannibalRight++;
					tmpState.cannibalLeft--;
				}
			} else if (boat == (Position.RIGHT)) {
				if (c == 'm') {
					tmpState.missionaryLeft++;
					tmpState.missionaryRight--;
				} else if (c == 'c') {
					tmpState.cannibalLeft++;
					tmpState.cannibalRight--;
				}
			}
		}
		tmpState.setBoat(moveBoat()); // moves boat over to the other side
		if (tmpState.valid()) { // ensures correct state is returned.
			return tmpState;
		} else {
			return this;
		}
	}

	public boolean valid() {
		if (cannibalLeft < 0 || cannibalLeft > 3 || missionaryLeft < 0 || missionaryLeft > 3 || cannibalRight < 0
				|| cannibalRight > 3 || missionaryRight < 0 || missionaryRight > 3) // are the new values within range
			return false;
		else if ((missionaryLeft < cannibalLeft) && missionaryLeft != 0) // Are there correct ratio of cannibals v
																	// missionaries?
			return false;
		else if ((missionaryRight < cannibalRight) && missionaryRight != 0)
			return false;
		else
			return true;
	}

	public int getCannibalLeft() {
		return cannibalLeft;
	}

	public void setCannibalLeft(int cannibalLeft) {
		this.cannibalLeft = cannibalLeft;
	}

	public int getMissionaryLeft() {
		return missionaryLeft;
	}

	public void setMissionaryLeft(int missionaryLeft) {
		this.missionaryLeft = missionaryLeft;
	}

	public int getCannibalRight() {
		return cannibalRight;
	}

	public void setCannibalRight(int cannibalRight) {
		this.cannibalRight = cannibalRight;
	}

	public int getMissionaryRight() {
		return missionaryRight;
	}

	public void setMissionaryRight(int missionaryRight) {
		this.missionaryRight = missionaryRight;
	}

	public Position getBoat() {
		return boat;
	}

	public void setBoat(Position boat) {
		this.boat = boat;
	}

	public Position moveBoat() {
		if (boat == (Position.RIGHT))
			return Position.LEFT;
		else
			return Position.RIGHT;
	}

	@Override
	public String toString() {
		return "C" + cannibalLeft + " M" + missionaryLeft + (boat.equals(Position.LEFT) ? "<> ~~~~~ " : " ~~~~~ <>")
				+ "C" + cannibalRight + " M" + missionaryRight;
	}
}
