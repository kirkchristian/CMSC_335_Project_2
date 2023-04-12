/*  *
 * JAVA Shapes Program with GUI
 *
 * This program implements inheritance and polymorphism as well as a GUI for Project 2.
 *
 * This program is based on Project 1, but now uses the JavaFX GUI toolkit.
 * Instead of using the console for input and output, the program uses a GUI.
 * The program uses the JavaFX GUI toolkit to create a GUI that allows the user to
 * construct various shapes and displays the volume and area of the shape.
 * It also displays the shape from a local file.
 * The program uses the abstract classes Shape, TwoDimensionalShape, and ThreeDimensionalShape
 * and its concrete subclasses to construct the shapes.
 * All shapes are stored in an ArrayList and displayed at the end of the program.
 * To run the program, build and run the ShapeMenuGUI class.
 *
 * Author:  Christian D Kirk
 * Date:    2023-04-11
 * Class:   UMGC CMSC 335
 * Professor: Dr. Douglas Mujeye
 *
 *   */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Triangle extends TwoDimensionalShape {
    private double side1;
    private double side2;
    private double side3;
    private ImageIcon imageIcon;

    //constructor and calls setArea to pass the area
    //assume triangle is valid and three sides are positive
    public Triangle(double side1, double side2, double side3) {
        setNumberOfDimensions(3);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        setArea(getArea());
        // Heron's formula for calculating the area of a triangle
        double s = (side1 + side2 + side3) / 2;
        setArea(Math.sqrt(s * (s - side1) * (s - side2) * (s - side3)));
        setImageIcon(createImageIcon());
    }

    //encapsulate side fields
    private double getSide1() {
        return side1;
    }

    private double getSide2() {
        return side2;
    }

    private double getSide3() {
        return side3;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public ImageIcon createImageIcon() {
        try {
            Image image = ImageIO.read(new File("shapes/triangle.png"));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //override toString method in superclass and call super toString
    @Override
    public String toString() {
        return "Triangle [Side 1: " + getSide1() + ", Side 2: " + getSide2() +
                ", and Side 3: " + ", " + getSide3() + ", " + super.toString() + "]";
    }
}
