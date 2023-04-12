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

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShapeMenuGUI extends JFrame {
    //arraylist of shapes
    private static ArrayList<Shape> shapes = new ArrayList<>();
    private JComboBox<String> shapeComboBox;
    private JLabel detail1Label, detail2Label, detail3Label, resultLabel;
    private JTextField detail1TextField, detail2TextField, detail3TextField;

    // main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // use invokeLater to ensure that the GUI is created on the event dispatch thread
            ShapeMenuGUI frame = new ShapeMenuGUI();
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // do nothing so we can handle the window closing event in the window listener below
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null); // center the frame on screen
            frame.setVisible(true);
        });
    }

    // constructor
    public ShapeMenuGUI() {
        super("JAVA Shapes program");

        // create the menu panel
        JPanel menuPanel = new JPanel();
        setResizable(false);
        menuPanel.setLayout(new GridLayout(10, 1));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel menuLabel = new JLabel("Please choose a shape to construct:");
        shapeComboBox = new JComboBox<>();
        shapeComboBox.addItem("Select a shape");
        shapeComboBox.addItem("Circle");
        shapeComboBox.addItem("Square");
        shapeComboBox.addItem("Triangle");
        shapeComboBox.addItem("Rectangle");
        shapeComboBox.addItem("Sphere");
        shapeComboBox.addItem("Cube");
        shapeComboBox.addItem("Cone");
        shapeComboBox.addItem("Cylinder");
        shapeComboBox.addItem("Torus");
        shapeComboBox.setSelectedIndex(0);
        menuPanel.add(menuLabel);
        menuPanel.add(shapeComboBox);
        menuPanel.add(new JLabel(" ")); // empty label for spacing
        resultLabel = new JLabel();
        menuPanel.add(resultLabel);

        // create the shape details panel
        JPanel shapeDetailsPanel = new JPanel();
        shapeDetailsPanel.setLayout(new GridLayout(3, 2, 5, 5));
        detail1Label = new JLabel();
        detail1TextField = new JTextField();
        detail2Label = new JLabel();
        detail2TextField = new JTextField();
        detail3Label = new JLabel();
        detail3TextField = new JTextField();
        shapeDetailsPanel.add(detail1Label);
        shapeDetailsPanel.add(detail1TextField);
        shapeDetailsPanel.add(detail2Label);
        shapeDetailsPanel.add(detail2TextField);
        shapeDetailsPanel.add(detail3Label);
        shapeDetailsPanel.add(detail3TextField);


        // create the calculate button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> {
            try {
                Shape shape = switch (shapeComboBox.getSelectedIndex()) {
                    // start at 1 because 0 is the "Select a shape" option
                    case 1 -> new Circle(Double.parseDouble(detail1TextField.getText()));
                    case 2 -> new Square(Double.parseDouble(detail1TextField.getText()));
                    case 3 -> new Triangle(Double.parseDouble(detail1TextField.getText()), Double.parseDouble(detail2TextField.getText()), Double.parseDouble(detail3TextField.getText()));
                    case 4 -> new Rectangle(Double.parseDouble(detail1TextField.getText()), Double.parseDouble(detail2TextField.getText()));
                    case 5 -> new Sphere(Double.parseDouble(detail1TextField.getText()));
                    case 6 -> new Cube(Double.parseDouble(detail1TextField.getText()));
                    case 7 -> new Cone(Double.parseDouble(detail1TextField.getText()), Double.parseDouble(detail2TextField.getText()));
                    case 8 -> new Cylinder(Double.parseDouble(detail1TextField.getText()), Double.parseDouble(detail2TextField.getText()));
                    case 9 -> new Torus(Double.parseDouble(detail1TextField.getText()), Double.parseDouble(detail2TextField.getText()));
                    default -> null;
                };
                if (shape != null) {
                    shapes.add(shape);
                    resultLabel.setText("The " + shape.getClass().getSimpleName() + " has " + ((shape instanceof TwoDimensionalShape) ? ("an area of " + ((TwoDimensionalShape) shape).getArea()) : ("a volume of " + ((ThreeDimensionalShape) shape).getVolume())) + ".");
                    // display image if it exists
                    if (shape.getImageIcon() != null) {
                        JLabel imageLabel = new JLabel(shape.getImageIcon());
                        JOptionPane imageOptionPane = new JOptionPane(imageLabel, JOptionPane.PLAIN_MESSAGE);
                        JDialog dialog = imageOptionPane.createDialog(ShapeMenuGUI.this, "Image of " + shape.getClass().getSimpleName());
                        dialog.setLocation(ShapeMenuGUI.this.getLocation().x - dialog.getWidth(), ShapeMenuGUI.this.getLocation().y);
                        dialog.setVisible(true);
                    } else {// output message to user saying no image found
                        JOptionPane noImageOptionPane = new JOptionPane("No image found for " + shape.getClass().getSimpleName() + ".", JOptionPane.INFORMATION_MESSAGE);
                        JDialog noImageDialog = noImageOptionPane.createDialog(ShapeMenuGUI.this, "No Image Found");
                        noImageDialog.setLocation(ShapeMenuGUI.this.getLocation().x - noImageDialog.getWidth(), ShapeMenuGUI.this.getLocation().y);
                        noImageDialog.setVisible(true);

                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane invalidInputOptionPane = new JOptionPane("Invalid input. Please enter a number.", JOptionPane.ERROR_MESSAGE);
                JDialog invalidInputDialog = invalidInputOptionPane.createDialog(ShapeMenuGUI.this, "Invalid Input");
                invalidInputDialog.setLocation(ShapeMenuGUI.this.getLocation().x - invalidInputDialog.getWidth(), ShapeMenuGUI.this.getLocation().y);
                invalidInputDialog.setVisible(true);
            }
        });

        // display the shape details panel for all created shapes
        JButton displayCurrentShapes = new JButton("Display Current Shapes");
        displayCurrentShapes.addActionListener(e -> {
            if (shapes.size() == 0) {
                JOptionPane noShapesOptionPane = new JOptionPane("No shapes have been created yet.", JOptionPane.INFORMATION_MESSAGE);
                JDialog noShapesDialog = noShapesOptionPane.createDialog(ShapeMenuGUI.this, "No Shapes Created");
                noShapesDialog.setLocation(ShapeMenuGUI.this.getLocation().x - noShapesDialog.getWidth(), ShapeMenuGUI.this.getLocation().y);
                noShapesDialog.setVisible(true);
            } else {
                StringBuilder output = new StringBuilder();
                for (Shape shape : shapes) {
                    output.append(shape.toString()).append("\n");
                }
                JOptionPane shapesOptionPane = new JOptionPane(output.toString(), JOptionPane.PLAIN_MESSAGE);
                JDialog shapesDialog = shapesOptionPane.createDialog(ShapeMenuGUI.this, "Current Shapes");
                shapesDialog.setLocation(ShapeMenuGUI.this.getLocation().x - shapesDialog.getWidth(), ShapeMenuGUI.this.getLocation().y);
                shapesDialog.setVisible(true);
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(shapeDetailsPanel, BorderLayout.CENTER);
        mainPanel.add(calculateButton, BorderLayout.SOUTH);
        menuPanel.add(displayCurrentShapes, BorderLayout.EAST);

        // set the shape details panel based on the selected shape
        shapeComboBox.addActionListener(e -> {
            switch (shapeComboBox.getSelectedIndex()) {
                case 1, 5 -> { // circle and sphere
                    detail1Label.setText("Radius:");
                    detail2Label.setText("");
                    detail3Label.setText("");
                }
                case 2, 6 -> { // square and cube
                    detail1Label.setText("Side length:");
                    detail2Label.setText("");
                    detail3Label.setText("");
                }
                case 3 -> { // triangle
                    detail1Label.setText("Side 1:");
                    detail2Label.setText("Side 2:");
                    detail3Label.setText("Side 3:");
                }
                case 4 -> { // rectangle
                    detail1Label.setText("Length:");
                    detail2Label.setText("Width:");
                    detail3Label.setText("");
                }
                case 7, 8 -> { // cone and cylinder
                    detail1Label.setText("Radius:");
                    detail2Label.setText("Height:");
                    detail3Label.setText("");
                }
                // cylinder
                case 9 -> { // torus
                    detail1Label.setText("Major radius:");
                    detail2Label.setText("Minor radius:");
                    detail3Label.setText("");
                }
                default -> { // no shape selected
                    detail1Label.setText("");
                    detail2Label.setText("");
                    detail3Label.setText("");
                }
            }
        });

        // add the main panel to the frame
        add(mainPanel);

        // Thank you message, date and time, and display the shapes created
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                JFrame shapesFrame = new JFrame("Shapes Created");
                shapesFrame.setSize(600, 300);
                shapesFrame.setLocationRelativeTo(null);
                shapesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JTextArea shapesTextArea = new JTextArea();
                shapesTextArea.setEditable(false);
                shapesTextArea.append("Thank you for using the JAVA Shapes program. The current date and time is " + LocalDateTime.now() + "\n\n");
                shapesTextArea.append("The following shapes were created:\n");

                for (Shape shape : shapes) {
                    shapesTextArea.append(shape.toString() + "\n");
                }

                JScrollPane scrollPane = new JScrollPane(shapesTextArea);
                shapesFrame.add(scrollPane);
                shapesFrame.setVisible(true);
            }
        });
    }
}