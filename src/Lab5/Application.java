package Lab5;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Application {
	private static String expectedValue = "99999999999999999999";
	private final static int SIZE = 20;
	
	public static void main(String[] args) {
//		BigDecimal[] generation = getGeneration("12345678912345678900");
//		System.out.println(Arrays.toString(generation));
//		
//		BigDecimal bestGene = getBestGene(generation); //chooses highest value out of the generation
//		
//		System.out.println("BEST GENE: " + bestGene);
		
        int numberOfCities = 10;
        int[][] travelPrices = new int[numberOfCities][numberOfCities];
        for(int i = 0; i<numberOfCities; i++){
            for(int j=0; j<=i; j++){
                Random rand = new Random();
                if(i==j)
                    travelPrices[i][j] = 0;
                else {
                    travelPrices[i][j] = rand.nextInt(100);
                    travelPrices[j][i] = travelPrices[i][j];
                }
            }
        }
        System.out.println(Arrays.deepToString(travelPrices));

        printTravelPrices(travelPrices,numberOfCities);
	}
	
    public static void printTravelPrices(int[][] travelPrices, int numberOfCities){
        for(int i = 0; i<numberOfCities; i++){
            for(int j=0; j<numberOfCities; j++){
                System.out.print(travelPrices[i][j]);
                if(travelPrices[i][j]/10 == 0)
                    System.out.print("  ");
                else
                    System.out.print(' ');
            }
            System.out.println("");
        }
    }

	public static String generateString() {
		String s = "80941951548284926663";

//		for(int i = 0; i < SIZE; i++)
//			s += "" + (int)(Math.random() * 10);

		return s;
	}

	/**
	 * evaluates values of a whole generation
	 * @param genes		Array of genes in the generation
	 * @return			Array of evaluated values.
	 */
	public static BigDecimal[] getGenerationEvaluationsList(BigDecimal[] generation) {

		BigDecimal[] evaluations = new BigDecimal[SIZE];
		
		for (int i = 0; i < generation.length; i++) 
			evaluations[i] = evaluate(generation[i]);

		return evaluations;
	}
	
	/**
	 * gets value of this gene
	 * @param gene	gene to evaluate
	 * @return		fitness? of gene.
	 */
	public static BigDecimal evaluate(BigDecimal gene) {
		return gene.divide(new BigDecimal(expectedValue), MathContext.DECIMAL128);
	}

	/**
	 * 
	 * @param startingString	beginning String
	 * @return					a whole generation of genes.
	 */
	public static BigDecimal[] getGeneration(String startingString) {
		
		BigDecimal[] generation = new BigDecimal[SIZE]; //generation of x amount of genes.
		int[] crossOverIndexes = getCrossOverIndexes(); //for each gene, a crossover point.
		
		for(int i = 0; i < SIZE; i++)
			generation[i] = new BigDecimal(getCrossOver(startingString, crossOverIndexes[i]));
		
		return generation;
	}

	public static String getCrossOver(String s, int crossOverPoint) {
		
		String tmp = s.substring(crossOverPoint, s.length());

		String result = tmp + s.substring(0, crossOverPoint);
		return result;
	}

	public static int[] getCrossOverIndexes() {
		int[] indexes = new int[SIZE];
		
		for(int i = 0; i < indexes.length; i++) 
			indexes[i] =  (int)(Math.random() * 21);
		
		return indexes;
	}
	
	public static BigDecimal getBestGene(BigDecimal[] generation) {
		BigDecimal[] evaluations = getGenerationEvaluationsList(generation);
		
		int bestEvaluationIndex = 0;
		
		for(int i = 0; i < generation.length; i++)
			if(evaluations[i].compareTo(evaluations[bestEvaluationIndex]) == 1) { //sees if this gene is larger than the current bestGene
				bestEvaluationIndex = i;
			}
		
		return generation[bestEvaluationIndex];
	}
	

}
