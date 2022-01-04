package lab16;

public class Lab16SimpleVision {
    public static String DATA = "0\t0\t5\t13\t9\t1\t0\t0\t0\t0\t13\t15\t10\t15\t5\t0\t0\t3\t15\t2\t0\t11\t8\t0\t0\t4\t12\t0\t0\t8\t8\t0\t0\t5\t8\t0\t0\t9\t8\t0\t0\t4\t11\t0\t1\t12\t7\t0\t0\t2\t14\t5\t10\t12\t0\t0\t0\t0\t6\t13\t10\t0\t0\t0\t0\n" +
            "0\t0\t0\t12\t13\t5\t0\t0\t0\t0\t0\t11\t16\t9\t0\t0\t0\t0\t3\t15\t16\t6\t0\t0\t0\t7\t15\t16\t16\t2\t0\t0\t0\t0\t1\t16\t16\t3\t0\t0\t0\t0\t1\t16\t16\t6\t0\t0\t0\t0\t1\t16\t16\t6\t0\t0\t0\t0\t0\t11\t16\t10\t0\t0\t1\n" +
            "0\t0\t0\t4\t15\t12\t0\t0\t0\t0\t3\t16\t15\t14\t0\t0\t0\t0\t8\t13\t8\t16\t0\t0\t0\t0\t1\t6\t15\t11\t0\t0\t0\t1\t8\t13\t15\t1\t0\t0\t0\t9\t16\t16\t5\t0\t0\t0\t0\t3\t13\t16\t16\t11\t5\t0\t0\t0\t0\t3\t11\t16\t9\t0\t2\n" +
            "0\t0\t7\t15\t13\t1\t0\t0\t0\t8\t13\t6\t15\t4\t0\t0\t0\t2\t1\t13\t13\t0\t0\t0\t0\t0\t2\t15\t11\t1\t0\t0\t0\t0\t0\t1\t12\t12\t1\t0\t0\t0\t0\t0\t1\t10\t8\t0\t0\t0\t8\t4\t5\t14\t9\t0\t0\t0\t7\t13\t13\t9\t0\t0\t3\n" +
            "0\t0\t0\t1\t11\t0\t0\t0\t0\t0\t0\t7\t8\t0\t0\t0\t0\t0\t1\t13\t6\t2\t2\t0\t0\t0\t7\t15\t0\t9\t8\t0\t0\t5\t16\t10\t0\t16\t6\t0\t0\t4\t15\t16\t13\t16\t1\t0\t0\t0\t0\t3\t15\t10\t0\t0\t0\t0\t0\t2\t16\t4\t0\t0\t4\n" +
            "0\t0\t12\t10\t0\t0\t0\t0\t0\t0\t14\t16\t16\t14\t0\t0\t0\t0\t13\t16\t15\t10\t1\t0\t0\t0\t11\t16\t16\t7\t0\t0\t0\t0\t0\t4\t7\t16\t7\t0\t0\t0\t0\t0\t4\t16\t9\t0\t0\t0\t5\t4\t12\t16\t4\t0\t0\t0\t9\t16\t16\t10\t0\t0\t5\n" +
            "0\t0\t0\t12\t13\t0\t0\t0\t0\t0\t5\t16\t8\t0\t0\t0\t0\t0\t13\t16\t3\t0\t0\t0\t0\t0\t14\t13\t0\t0\t0\t0\t0\t0\t15\t12\t7\t2\t0\t0\t0\t0\t13\t16\t13\t16\t3\t0\t0\t0\t7\t16\t11\t15\t8\t0\t0\t0\t1\t9\t15\t11\t3\t0\t6\n" +
            "0\t0\t7\t8\t13\t16\t15\t1\t0\t0\t7\t7\t4\t11\t12\t0\t0\t0\t0\t0\t8\t13\t1\t0\t0\t4\t8\t8\t15\t15\t6\t0\t0\t2\t11\t15\t15\t4\t0\t0\t0\t0\t0\t16\t5\t0\t0\t0\t0\t0\t9\t15\t1\t0\t0\t0\t0\t0\t13\t5\t0\t0\t0\t0\t7\n" +
            "0\t0\t9\t14\t8\t1\t0\t0\t0\t0\t12\t14\t14\t12\t0\t0\t0\t0\t9\t10\t0\t15\t4\t0\t0\t0\t3\t16\t12\t14\t2\t0\t0\t0\t4\t16\t16\t2\t0\t0\t0\t3\t16\t8\t10\t13\t2\t0\t0\t1\t15\t1\t3\t16\t8\t0\t0\t0\t11\t16\t15\t11\t1\t0\t8\n" +
            "0\t0\t11\t12\t0\t0\t0\t0\t0\t2\t16\t16\t16\t13\t0\t0\t0\t3\t16\t12\t10\t14\t0\t0\t0\t1\t16\t1\t12\t15\t0\t0\t0\t0\t13\t16\t9\t15\t2\t0\t0\t0\t0\t3\t0\t9\t11\t0\t0\t0\t0\t0\t9\t15\t4\t0\t0\t0\t9\t12\t13\t3\t0\t0\t9\n" +
            "0\t0\t1\t9\t15\t11\t0\t0\t0\t0\t11\t16\t8\t14\t6\t0\t0\t2\t16\t10\t0\t9\t9\t0\t0\t1\t16\t4\t0\t8\t8\t0\t0\t4\t16\t4\t0\t8\t8\t0\t0\t1\t16\t5\t1\t11\t3\t0\t0\t0\t12\t12\t10\t10\t0\t0\t0\t0\t1\t10\t13\t3\t0\t0\t0\n" +
            "0\t0\t0\t0\t14\t13\t1\t0\t0\t0\t0\t5\t16\t16\t2\t0\t0\t0\t0\t14\t16\t12\t0\t0\t0\t1\t10\t16\t16\t12\t0\t0\t0\t3\t12\t14\t16\t9\t0\t0\t0\t0\t0\t5\t16\t15\t0\t0\t0\t0\t0\t4\t16\t14\t0\t0\t0\t0\t0\t1\t13\t16\t1\t0\t1\n" +
            "0\t0\t5\t12\t1\t0\t0\t0\t0\t0\t15\t14\t7\t0\t0\t0\t0\t0\t13\t1\t12\t0\t0\t0\t0\t2\t10\t0\t14\t0\t0\t0\t0\t0\t2\t0\t16\t1\t0\t0\t0\t0\t0\t6\t15\t0\t0\t0\t0\t0\t9\t16\t15\t9\t8\t2\t0\t0\t3\t11\t8\t13\t12\t4\t2\n" +
            "0\t2\t9\t15\t14\t9\t3\t0\t0\t4\t13\t8\t9\t16\t8\t0\t0\t0\t0\t6\t14\t15\t3\t0\t0\t0\t0\t11\t14\t2\t0\t0\t0\t0\t0\t2\t15\t11\t0\t0\t0\t0\t0\t0\t2\t15\t4\t0\t0\t1\t5\t6\t13\t16\t6\t0\t0\t2\t12\t12\t13\t11\t0\t0\t3\n" +
            "0\t0\t0\t8\t15\t1\t0\t0\t0\t0\t1\t14\t13\t1\t1\t0\t0\t0\t10\t15\t3\t15\t11\t0\t0\t7\t16\t7\t1\t16\t8\t0\t0\t9\t16\t13\t14\t16\t5\t0\t0\t1\t10\t15\t16\t14\t0\t0\t0\t0\t0\t1\t16\t10\t0\t0\t0\t0\t0\t10\t15\t4\t0\t0\t4\n" +
            "0\t5\t12\t13\t16\t16\t2\t0\t0\t11\t16\t15\t8\t4\t0\t0\t0\t8\t14\t11\t1\t0\t0\t0\t0\t8\t16\t16\t14\t0\t0\t0\t0\t1\t6\t6\t16\t0\t0\t0\t0\t0\t0\t5\t16\t3\t0\t0\t0\t1\t5\t15\t13\t0\t0\t0\t0\t4\t15\t16\t2\t0\t0\t0\t5\n" +
            "0\t0\t0\t8\t15\t1\t0\t0\t0\t0\t0\t12\t14\t0\t0\t0\t0\t0\t3\t16\t7\t0\t0\t0\t0\t0\t6\t16\t2\t0\t0\t0\t0\t0\t7\t16\t16\t13\t5\t0\t0\t0\t15\t16\t9\t9\t14\t0\t0\t0\t3\t14\t9\t2\t16\t2\t0\t0\t0\t7\t15\t16\t11\t0\t6\n" +
            "0\t0\t1\t8\t15\t10\t0\t0\t0\t3\t13\t15\t14\t14\t0\t0\t0\t5\t10\t0\t10\t12\t0\t0\t0\t0\t3\t5\t15\t10\t2\t0\t0\t0\t16\t16\t16\t16\t12\t0\t0\t1\t8\t12\t14\t8\t3\t0\t0\t0\t0\t10\t13\t0\t0\t0\t0\t0\t0\t11\t9\t0\t0\t0\t7\n" +
            "0\t0\t10\t7\t13\t9\t0\t0\t0\t0\t9\t10\t12\t15\t2\t0\t0\t0\t4\t11\t10\t11\t0\t0\t0\t0\t1\t16\t10\t1\t0\t0\t0\t0\t12\t13\t4\t0\t0\t0\t0\t0\t12\t1\t12\t0\t0\t0\t0\t1\t10\t2\t14\t0\t0\t0\t0\t0\t11\t14\t5\t0\t0\t0\t8\n" +
            "0\t0\t6\t14\t4\t0\t0\t0\t0\t0\t11\t16\t10\t0\t0\t0\t0\t0\t8\t14\t16\t2\t0\t0\t0\t0\t1\t12\t12\t11\t0\t0\t0\t0\t0\t0\t0\t11\t3\t0\t0\t0\t0\t0\t0\t5\t11\t0\t0\t0\t1\t4\t4\t7\t16\t2\t0\t0\t7\t16\t16\t13\t11\t1\t9\n";


    public static void main(String[] args) {
        displayAllNumbers();
    }
    public static void displayAllNumbers() {

        String[] lines = DATA.split("\n");

        int wholeRowCounter = 0;
        for (String line : lines) {
            String[] bits = line.split("\t");
            System.out.println("THIS IS SUPPOSE TO BE NUMBER: " + bits[bits.length-1]+ "--------------------------------------- THIS IS ROW NUMBER: " + wholeRowCounter);
            int bitCounter = 0;
            for (int row = 1; row <= 8; row++) {

                for (int col = 0; col < 8; col++) {
                    int data = Integer.parseInt(bits[bitCounter]);
                    if (data == 0)
                        System.out.print(" ");
                    else if (data > 0 && data < 6)
                        System.out.print(".");
                    else if (data > 5 && data < 12)
                        System.out.print("x");
                    else if (data > 11 && data < 16)
                        System.out.print("X");
                    bitCounter++;
                }
                System.out.println();
            }
            wholeRowCounter++;
        }
    }
}
