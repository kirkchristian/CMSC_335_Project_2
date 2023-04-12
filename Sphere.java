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
 * Date:    2023-03-29
 * Class:   CMSC 335
 * Professor: Dr. Douglas Mujeye
 *
 *   */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class Sphere extends ThreeDimensionalShape {
    private double radius;
    private ImageIcon imageIcon;

    //constructor and calls setVolume to pass the calculated volume
    //assume sphere is a perfect sphere
    public Sphere(double radius) {
        this.radius = radius;
        setNumberOfDimensions(3);
        //formula for calculating volume of a sphere
        setVolume((4.0 / 3.0) * Math.PI * Math.pow(radius, 3));
        setImageIcon(createImageIcon());
    }

    //encapsulate radius field
    private double getRadius() {
        return radius;
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
            Image image = ImageIO.read(new File("shapes/sphere.png"));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //override toString method in superclass and call super toString
    @Override
    public String toString() {
        return "Sphere [Radius: " + getRadius() + ", " + super.toString() + "]";
    }
}