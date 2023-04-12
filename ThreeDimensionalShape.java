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
public abstract class ThreeDimensionalShape extends Shape {
    private double volume;

    //get volume
    //this method is called in the constructor of the subclass
    public double getVolume() {
        return volume;
    }
    //set volume
    //this method is called in the constructor of the subclass
    public void setVolume(double volume) {
        this.volume = volume;
    }

    //override toString method in superclass and call super toString
    @Override
    public String toString() {
        return "Volume: " + volume;
    }
}
