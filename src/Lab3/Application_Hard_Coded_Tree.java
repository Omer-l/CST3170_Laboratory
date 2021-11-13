package Lab3;

import java.util.Arrays;

public class Application_Hard_Coded_Tree {

	public static void main(String[] args) {
		
		int[] expectedCategory = { 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 3, 3, 3, 3, 1, 3, 2, 3, 3, };
		
		for(int i : expectedCategory)
			if(i == 1)
				System.out.println("hard");
			else if(i == 2)
				System.out.println("soft");
			else if(i == 3)
				System.out.println("none");
					
	}

	public static int[] getCategory(int[][] arr) {

		int[] category = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			category[i] = getRowCategory(arr[i]);
		}

		return category;

	}

	public static int getRowCategory(int[] features) {
		int f4 = features[3];
		int f3 = features[2];
		int f2 = features[1];
		int f1 = features[0];
		
		if (f4 == 1) {
			return 3;
		} else if (f4 == 2) {
			if (f3 == 1) {
				if (f1 == 1) {
					return 2;
				} else if (f1 == 2) {
					return 2;
				} else if (f1 == 3) {
					if (f2 == 1) {
						return 3;
					} else if (f2 == 2) {
						return 2;
					}
				}
			}
			if (f3 == 2) {
				if (f2 == 1) {
					return 1;
				} else if (f2 == 2) {
					if (f1 == 1) {
						return 1;
					} else if (f1 == 2) {
						if (f2 == 1) {
							return 1;
						} else if (f2 == 2) {
							return 3;
						}
					} else if (f1 == 3) {
						if (f2 == 1) {
							return 1;
						} else if (f2 == 2) {
							return 3;
						}
					}
				}
			}
		}

		return 0;

	}

}
