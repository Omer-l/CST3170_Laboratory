package lab18;

public class ApplicationSVM2D {

    public static void main(String[] args) {
        FileReaderLab18 fileReader = new FileReaderLab18("SVM_LineClass1.txt");
        PointLab18[] allPoints = fileReader.getData();
        PointLab18[] A = PointLab18.getClassifiedPoints(allPoints, 10);
        PointLab18[] B = PointLab18.getClassifiedPoints(allPoints, 20);
        System.out.println("A POINTS:");
        PointLab18.printPoints(A);
        System.out.println("B POINTS:");
        PointLab18.printPoints(B);
    }
}
