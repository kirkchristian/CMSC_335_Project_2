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

public class Cube extends ThreeDimensionalShape {
    private double sideLength;
    private ImageIcon imageIcon;

    //constructor and calls setVolume to pass the calculated volume
    //assume cube is a perfect cube
    public Cube(double sideLength) {
        this.sideLength = sideLength;
        setNumberOfDimensions(3);
        //formula for calculating volume of a cube
        setVolume(Math.pow(sideLength, 3));
        setImageIcon(createImageIcon());
    }

    //encapsulate sideLength field
    private double getSideLength(){
        return sideLength;
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
            Image image = ImageIO.read(new File("shapes/cube.png"));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //override toString method in superclass and call super toString
    @Override
    public String toString() {
        return "Cube [Side length: " + getSideLength() + ", " + super.toString() + "]";
    }
}
