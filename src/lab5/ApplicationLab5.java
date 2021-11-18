package lab5;

import java.util.Arrays;

public class ApplicationLab5 {
	private final static int SIZE = 30;
	private final static int NUMBER_OF_DIGITS = 100;
	private final static int PROBABILITY_OF_MUTATION = 3; // 1/100 chance of getting mutated
	private final static int MAXIMUM_GENERATIONS = 2000;
	private final static boolean MINIMISE = false; // determines whether to minimise or maximise genes.
	
	public static void main(String[] args) {
		String[] populationOfGenes = generatePopulationOfGenes();
		System.out.println(Arrays.toString(populationOfGenes));

		for (int i = 0; i < MAXIMUM_GENERATIONS; i++) {
			int[] indexesOfParents = getBest2GenesIndexes(populationOfGenes);

			System.out.println(Arrays.toString(indexesOfParents));

			String parent1 = populationOfGenes[indexesOfParents[0]];
			String parent2 = populationOfGenes[indexesOfParents[1]];

			System.out.println("PARENT 1: " + parent1 + " -> " + getSumOfString(parent1) + " \nPARENT 2: " + parent2
					+ " -> " + getSumOfString(parent2));
			System.out.println("OFFSPRINGS:");

			populationOfGenes = generateNextGenerationUsing2Parents(parent1, parent2);

			if (finished(populationOfGenes)) {
				System.out.println("FINISHED AT GENERATION: " + i);
				break;
			}
		}
	}

	/**
	 * 
	 * @return an arbitrarily created population of genes.
	 */
	public static String[] generatePopulationOfGenes() {
		String[] population = new String[SIZE]; // 20 genes in the population
		for (int i = 0; i < SIZE; i++) {
			population[i] = new String();
			for (int j = 0; j < NUMBER_OF_DIGITS; j++)
				population[i] += "" + (int) (Math.random() * 10);
		}

		return population;
	}

//    simple evaluation function that returns the sum of the digits.
	public static int getSumOfString(String s) {
		int sum = 0;

		for (int i = 0; i < s.length(); i++)
			sum += new Integer(s.charAt(i) + "");

		return sum;
	}

	/**
	 * 
	 * @return a random index to crossover
	 */
	public static int getCrossOverIndex() {
		return (int) (Math.random() * (NUMBER_OF_DIGITS + 1));
	}

	public static String getCrossover(String parent1, String parent2, int crossoverIndex) {

		String part1Parent1 = parent1.substring(0, crossoverIndex);
		String part2Parent2 = parent2.substring(crossoverIndex, parent2.length());

		String result = part1Parent1 + part2Parent2;

		return result;
	}

	/**
	 * 
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
	 * 
	 * @param parent1 best parent 1
	 * @param parent2 best parent 2.
	 * @return a generation of genes from two parents.
	 */
	public static String[] generateNextGenerationUsing2Parents(String parent1, String parent2) {
		String[] nextGeneration = new String[SIZE];

		for (int i = 0; i < SIZE; i++) {
			nextGeneration[i] = getOffspring(parent1, parent2);
			System.out.println(nextGeneration[i] + " -> SUM: " + getSumOfString(nextGeneration[i]));
		}

		return nextGeneration;
	}

	/**
	 * This function gets the two best genes, you can change the if statements in
	 * the for loop to minimise.
	 * 
	 * @param generation a generation of genes
	 * @return the two best genes to produce an offspring from.
	 */
	public static int[] getBest2GenesIndexes(String[] generation) {
		int currentMaximumSum1 = getSumOfString(generation[0]); // best of the best
		int currentBestGeneIndex1 = 0;

		int currentMaximumSum2 = getSumOfString(generation[0]); // 2nd best
		int currentBestGeneIndex2 = 0;

		if (MINIMISE) {
			for (int i = 0; i < generation.length; i++) {
				int sum = getSumOfString(generation[i]);
				System.out.println(generation[i] + " -> SUM: " + sum);

				if (sum < currentMaximumSum1) {
					currentMaximumSum2 = currentMaximumSum1;
					currentBestGeneIndex2 = currentBestGeneIndex1;

					currentMaximumSum1 = sum;
					currentBestGeneIndex1 = i;
				} else if ((sum < currentMaximumSum2) && (sum >= currentMaximumSum1)) {
					currentMaximumSum2 = sum;
					currentBestGeneIndex2 = i;
				}
			}
		} else {
			for (int i = 0; i < generation.length; i++) {
				int sum = getSumOfString(generation[i]);
				System.out.println(generation[i] + " -> SUM: " + sum);

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
		}

		return new int[] { currentBestGeneIndex1, currentBestGeneIndex2 };
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
	 * This function determines whether a digit should be mutated
	 * 
	 * @return true if a digit should be mutated.
	 */
	public static boolean mutate() {
		return ((int) (Math.random() * 100) + 1) == PROBABILITY_OF_MUTATION;
	}

	public static char[] getMutation(char[] offspring, int indexToMutate) {
		int randomNumber = (int) (Math.random() * 10);
		char[] mutation = offspring.clone();
		mutation[indexToMutate] = (char) (randomNumber + '0');
		return mutation;
	}

	public static boolean finished(String[] generation) {
		if (MINIMISE)
			for (int i = 0; i < generation.length; i++) {
				if (getSumOfString(generation[i]) == 0) {
					System.out.print(generation[i] + " = 0 ");
					return true;
				}
			}
		else
			for (int i = 0; i < generation.length; i++) {
				if (getSumOfString(generation[i]) == NUMBER_OF_DIGITS * 9) {
					System.out.print(generation[i] + " =  " + (NUMBER_OF_DIGITS * 9));
					return true;
				}
			}
		return false;
	}
}