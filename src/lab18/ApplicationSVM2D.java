package lab18;

public class ApplicationSVM2D {

    public static void main(String[] args) {
        FileReaderLab18 fileReader = new FileReaderLab18("LineClassSVM_Tutorial.txt");
        PointLab18[] allPoints = fileReader.getData();
        PointLab18[] A = PointLab18.getClassifiedPoints(allPoints, 1);
        PointLab18[] B = PointLab18.getClassifiedPoints(allPoints, -1);
        System.out.println("A POINTS:");
        System.out.println(A);
        System.out.println("B POINTS:");
        System.out.println(B);
    }
}
