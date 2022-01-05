package lab12;

public class Lab12Application {

    public static void main(String[] args) {
        FileReaderLab12 fileReader = new FileReaderLab12("dataset1.csv");
        Row[] points = fileReader.getData();

        for(Row point : points) {
            System.out.println(point);
        }
    }


}
