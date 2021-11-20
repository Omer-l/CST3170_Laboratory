package lab3;

public class ApplicationTreeControlFlow {

	public static void main(String[] args) {
		FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
		DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.

		for(int i : decisionTable.getCategories())
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
