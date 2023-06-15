import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CircleFile {
    private final String inputFilePath;
    private final String outputFilePath;

    public CircleFile(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public static void main(String[] args) {
        CircleFile circleFile = new CircleFile("src/circleinput.txt", "src/circleoutput.txt");
        Circle[] circles = circleFile.fillMyArray();
        circleFile.printMyArray(circles);
    }

    public Circle[] fillMyArray() {
        Circle[] circles = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            int count = 0;
            while ((reader.readLine()) != null)
            {
                count++;
            }
            circles = new Circle[count];
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            int index = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                double radius = Double.parseDouble(line);
                Circle circle = new Circle(radius);
                circles[index] = circle;
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return circles;
    }

    public void printMyArray(Circle[] circles) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            for (Circle circle : circles) {
                String line = "Circle with radius: " + circle.getRadius();
                writer.println(line);
                writer.println("Circumference: " + circle.calculateCircumference());
                writer.println("Area: %.2%" + circle.calculateArea());
                writer.println();
                System.out.println(line);
                System.out.printf("Circumference: %.2f", circle.calculateCircumference());
                System.out.printf("Area: %.2f" , circle.calculateArea());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Circle {
        private double radius;

        public Circle() {
            this(1.0);
        }

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public double calculateCircumference() {
            return 2 * Math.PI * radius;
        }

        public double calculateArea() {
            return Math.PI * Math.pow(radius, 2);
        }
    }
}
