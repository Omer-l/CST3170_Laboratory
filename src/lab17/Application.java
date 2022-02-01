package lab17;

public class Application {
	private final static int PERCENTAGE = 100;
	private final static int bandit1 = 50;
	private final static int percentageRequiredForBandit1ToGain = 90;
	private final static int bandit2 = 5;
	private final static int percentageRequiredForBandit2ToGain = 10;
	private final static int indexOfGain = 0;
	private final static int indexOfPercentage = 1;
	private final static double expectedUtilityBandit1 = (double)bandit1 / (PERCENTAGE - (double)(percentageRequiredForBandit1ToGain));
	private final static double expectedUtilityBandit2 = bandit2 - (double)bandit2 / (double)(percentageRequiredForBandit2ToGain);
	private final static double[] expectedUtilties = {expectedUtilityBandit1, expectedUtilityBandit2};
	//Holds bandit and probability of getting bandit
	private final static int[][] bandits = {
			{bandit1, percentageRequiredForBandit1ToGain}, 
			{bandit2, percentageRequiredForBandit2ToGain},
			};
	private final static int numberOfBandits = bandits.length;
	private final static int numberOfTurns = 2000;
	
	public static void main(String[] args) {
		int[][] gains = runBandits();
		printGains(gains);
	}

	public static int[][] runBandits() {
		int numberOfBandits = bandits.length;
		int[][] gains = minusOnesExceptForFirstColumn(); //gains by each bandit, index 0 is the numberOfTimes bandit is chosen, index 1-10 is the gains each turn

		for(int turnNumber = 0; turnNumber < numberOfTurns; turnNumber++) {
			int chosenBanditIndex = (int)(Math.random() * numberOfBandits);
			
			int indexOfGainForBandit = gains[chosenBanditIndex][0];
			int gain = getGainForBandit(chosenBanditIndex);
			
			gains[chosenBanditIndex][0]++;
			gains[chosenBanditIndex][indexOfGainForBandit] = gain;
		}
		
		return gains;
	}
	
	private static int[][] minusOnesExceptForFirstColumn() {
		int[][] minusOnes = new int[numberOfBandits][numberOfTurns+1];

		for(int banditIndex = 0; banditIndex < bandits.length; banditIndex++) {
			minusOnes[banditIndex][0] = 1;
			for(int gainIndex = 1; gainIndex < minusOnes[0].length; gainIndex++) {
				minusOnes[banditIndex][gainIndex] = -1;
			}
		}
			
		return minusOnes;
	}

	private static int getGainForBandit(int chosengainIndex) {
		int rollPercentage = (int)(Math.random() * PERCENTAGE);
		int percentageRequiredForGain = bandits[chosengainIndex][indexOfPercentage];
		int maxPossibleGain = bandits[chosengainIndex][indexOfPercentage];
		int noGain = 0;
		
		if(rollPercentage >= percentageRequiredForGain) 
			return bandits[chosengainIndex][indexOfGain];
		 else 
			return noGain;
	}
	
	private static void printGains(int[][] gains) {
		for(int banditIndex = 0; banditIndex < gains.length; banditIndex++) {
			int numberOfTimesBanditChosen = (gains[banditIndex][0]-1);
			System.out.print("\nNumber of times chosen: " + numberOfTimesBanditChosen + " GAINS PER CHOSEN TIME: ");
			for(int gainIndex = 1; gainIndex < gains[0].length; gainIndex++) {
				int gain = gains[banditIndex][gainIndex];
				System.out.print(gain + ", ");
			}
			int totalGain = getGain(gains[banditIndex]);
			double expectedUtilityOfBandit = expectedUtilties[banditIndex];
			System.out.print("\n = " + totalGain);
			System.out.print("... Actual Utility: "  + calculateUtilityForBandit(gains[banditIndex]) + ". Expected utility: " + expectedUtilityOfBandit);
		}
	}
	
	private static double[] calculateUtilityForAllBandits(int[][] gains) {
		
		double utilityOfEachBandit[] = new double[bandits.length];
		for(int banditIndex = 0; banditIndex < gains.length; banditIndex++) {
			int numberOfTimesBanditChosen = (gains[banditIndex][0]-1);
			System.out.print("\nNumber of times chosen: " + numberOfTimesBanditChosen + " GAINS PER CHOSEN TIME: ");
			int totalGain = getGain(gains[banditIndex]);
		}
		
		return utilityOfEachBandit;
	}
	
	private static double calculateUtilityForBandit(int[] gains) {
		int numberOfTurns = gains[0];
		int totalGain = getGain(gains);
		return (double) totalGain / (double)numberOfTurns;
	}
	
	private static int getGain(int[] gains) {
		int sum = 0;

		for(int gainIndex = 1; gainIndex < gains.length && gains[gainIndex] != -1; gainIndex++) {
			int gain = gains[gainIndex];
			sum += gain;
		}
		return sum;
	}
	

}
