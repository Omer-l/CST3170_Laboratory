package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class ApplicationUserInput {
	private final static int SIZE = 40;
	private final static int PROBABILITY_OF_MUTATION = 3; // 1/100 chance of getting mutated
	private final static int MAXIMUM_GENERATIONS = 2000;
	private static String userInput = "";
	private static int numberOfDigits = -1; //varies based on users input

	public static void main(String[] args) {
		numberOfDigits = prompt();
		String[] populationOfGenes = generatePopulationOfGenes();
		System.out.println(Arrays.toString(populationOfGenes));

		for (int i = 0; i < MAXIMUM_GENERATIONS; i++) {
			int[] indexesOfParents = getBest2GenesIndexes(populationOfGenes);

			System.out.println(Arrays.toString(indexesOfParents));

			String parent1 = populationOfGenes[indexesOfParents[0]];
			String parent2 = populationOfGenes[indexesOfParents[1]];

			System.out.println("PARENT 1: " + parent1 + " -> " + getNumberOfMatches(userInput, parent1)
					+ " \nPARENT 2: " + parent2 + " -> " + getNumberOfMatches(userInput, parent2));
			System.out.println("OFFSPRINGS:");

			populationOfGenes = generateNextGenerationUsing2Parents(parent1, parent2);

			if (finished(populationOfGenes)) {
				System.out.println("FINISHED AT GENERATION: " + i);
				break;
			}
		}
		
		main(new String[] {}); //recursive main function
	}

	/**
	 * This function takes the user's input and modifies the variables: numberOfDigits and userInput.
	 * @return size of userInput
	 */
	public static int prompt() {
		Scanner input = new Scanner(System.in);
		System.out.print("ENTER INPUT: ");
		userInput = input.next();

		return userInput.length();
	}

	/**
	 * This function generates a population of genes to start producing offsprings from.
	 * @return an arbitrarily created population of genes.
	 */
	public static String[] generatePopulationOfGenes() {
		String[] population = new String[SIZE]; // 20 genes in the population
		for (int i = 0; i < SIZE; i++) {
			population[i] = new String();
			for (int j = 0; j < numberOfDigits; j++)
				population[i] += "" + (int) (Math.random() * 10);
		}

		return population;
	}

	/**
	 * This function gets the number of matches in the gene compared to the user's input (i.e. 2 if 2 of the digits in the genes match).
	 * @param userInput 	desired result
	 * @param gene      	current gene
	 * @return 				number of matches.
	 */
	public static int getNumberOfMatches(String userInput, String gene) {
		int count = 0;

		for (int i = 0; i < userInput.length(); i++) {
			if (gene.charAt(i) == userInput.charAt(i)) {
				count++;
			}
		}
		return count;
	}
	

	/**
	 * This function gets the index to crossover
	 * @return a random index to crossover
	 */
	public static int getCrossOverIndex() {
		return (int) (Math.random() * (numberOfDigits + 1));
	}

	/**
	 * This function gets the result from exchanging the two parents' parts from the crossover index.
	 * @param parent1			best parent 1
	 * @param parent2			best parent 2
	 * @param crossoverIndex	index to exchange from
	 * @return					a new string with a part from both parents
	 */
	public static String getCrossover(String parent1, String parent2, int crossoverIndex) {

		String part1Parent1 = parent1.substring(0, crossoverIndex);
		String part2Parent2 = parent2.substring(crossoverIndex, parent2.length());

		String result = part1Parent1 + part2Parent2;

		return result;
	}

	/**
	 * This function returns an offspring from two parents.
	 * @param parent1	best parent 1
	 * @param parent2 	best parent 2.
	 * @return 			an offspring from the crossover parts of both parents
	 */
	public static String getOffspring(String parent1, String parent2) {

		int crossoverIndex = getCrossOverIndex();

		String offspring = getCrossover(parent1, parent2, crossoverIndex);
		offspring = mutateOffspring(offspring);
		return offspring;
	}


	/**
	 * This function generates the next population of genes using two parents.
	 * @param parent1 best parent 1
	 * @param parent2 best parent 2.
	 * @return a generation of genes from two parents.
	 */
	public static String[] generateNextGenerationUsing2Parents(String parent1, String parent2) {
		String[] nextGeneration = new String[SIZE];

		for (int i = 0; i < SIZE; i++) {
			nextGeneration[i] = getOffspring(parent1, parent2);
			System.out.println(nextGeneration[i] + " -> NUMBER OF MATCHES: " + getNumberOfMatches(userInput, nextGeneration[i]));
		}

		return nextGeneration;
	}

	/**
	 * This function gets the two best genes to produce offsprings from.
	 * @param 	generation 	a generation of genes
	 * @return 				the two best genes to produce an offspring from.
	 */
	public static int[] getBest2GenesIndexes(String[] generation) {
		int currentMaximumSum1 = getNumberOfMatches(userInput, generation[0]); // best of the best
		int currentBestGeneIndex1 = 0;

		int currentMaximumSum2 = getNumberOfMatches(userInput, generation[0]); // 2nd best
		int currentBestGeneIndex2 = 0;

		for (int i = 0; i < generation.length; i++) {
			int sum = getNumberOfMatches(userInput, generation[i]);
			System.out.println(generation[i] + " -> NUMBER OF MATCHES: " + sum);

			if (sum > currentMaximumSum1) {
				currentMaximumSum2 = currentMaximumSum1;
				currentBestGeneIndex2 = currentBestGeneIndex1;

				currentMaximumSum1 = sum;
				currentBestGeneIndex1 = i;
			} else if ((sum > currentMaximumSum2) && (sum <= currentMaximumSum1)) {
				currentMaximumSum2 = sum;
				currentBestGeneIndex2 = i;
			}
		}

		return new int[] { currentBestGeneIndex1, currentBestGeneIndex2 };
	}

	/**
	 * This function iterates through each digit and determines whether to mutate the digit.
	 * @param 	offspring gene to mutate
	 * @return 	possibly a mutated/different gene.
	 */
	public static boolean mutate() {
		return ((int) (Math.random() * 100) + 1) == PROBABILITY_OF_MUTATION;
	}

	/**
	 * This function replaces the given index with a random number 0-9.
	 * @return gene with a modified digit at the given index.
	 */
	public static char[] getMutation(char[] offspring, int indexToMutate) {
		int randomNumber = (int) (Math.random() * 10);
		char[] mutation = offspring.clone();
		mutation[indexToMutate] = (char) (randomNumber + '0');
		return mutation;
	}

	/**
	 * 
	 * @param offspring gene to mutate
	 * @return possibly a mutated/different gene.
	 */
	public static String mutateOffspring(String offspring) {
		char[] offspringToCharArray = offspring.toCharArray();

		// for each digit in the gene, get probability to mutate
		for (int i = 0; i < offspringToCharArray.length; i++)
			if (mutate())
				offspringToCharArray = getMutation(offspringToCharArray, i);

		return new String(offspringToCharArray);
	}

	/**
	 * This function determines whether the best possible result is found. It is generally used for testing purposes.
	 * @param generation	generation of genes to evaluate
	 * @return				true if genetic algorithm should be stopped.
	 */
	public static boolean finished(String[] generation) {
		for (int i = 0; i < generation.length; i++) {
			if (getNumberOfMatches(userInput, generation[i]) == numberOfDigits) {
				System.out.print(generation[i] + " = 0 ");
				return true;
			}
		}
		return false;
	}
}