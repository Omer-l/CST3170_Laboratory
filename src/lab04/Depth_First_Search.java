package lab04;

import java.util.List;

public class Depth_First_Search {

	
	public static State getPossibleMoves(State currentState, int index) {
		System.out.println(index);
		if(currentState.finished())
			return currentState;
		else if(index >= 30)
			return null;
		else {
			List<State> successors = currentState.generateSuccessors();
			System.out.println(successors + "\t-->PARENT: " + successors.get(0).getParentState());
			for(State childState : successors) {
				State result = getPossibleMoves(childState, index + 1);
				if(result != null)
					return result;
			}
			
			return null;
		}
	}
	
}
