package org.soen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * BetaGUI.java

 * A Swing-based desktop application for computing the Beta function B(x, y) "from scratch,"
 * without using any external math libraries. This class handles the user interface,
 * event-driven input processing, and displays results or error messages to the user.

 * Requirements addressed:
 * R1: Provides two labeled integer input fields for x and y (>0).
 * R2: Invokes BetaCalculator.beta(x, y) to compute the function.
 * R3: Validates input and shows descriptive error dialogs for invalid entries.
 * R4: Displays the result immediately in a dedicated label.
 * R5: Implements accessibility features (setLabelFor, tooltips, mnemonics).
 * R6: Separates UI logic from computation logic in BetaCalculator.
 */
public class BetaGUI {

    /**
     * Entry point: schedules the creation of the GUI on the Event Dispatch Thread.
     * @param args unused
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BetaGUI::createAndShowGUI);
    }

    /**
     * Constructs and displays the main application window.
     * Sets up all UI components, configures layout and event handlers.
     */
    private static void createAndShowGUI() {
        // Create main application window
        JFrame frame = new JFrame("Beta Function Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and configure content panel with padding
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Input panel using GridBagLayout for flexible alignment
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Define fonts for labels, fields, and results
        Font labelFont = new Font("SansSerif", Font.PLAIN, 16);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);
        Font resultFont = new Font("SansSerif", Font.BOLD, 18);

        // --- Input Field for x ---
        JLabel lblX = new JLabel("x (integer > 0):");
        lblX.setFont(labelFont);
        JTextField txtX = new JTextField(8);
        txtX.setFont(fieldFont);
        lblX.setLabelFor(txtX);  // Accessibility: associates label with text field
        txtX.setToolTipText("Enter a positive integer for x");
        lblX.setDisplayedMnemonic(KeyEvent.VK_X);  // ALT+X focuses field

        // --- Input Field for y ---
        JLabel lblY = new JLabel("y (integer > 0):");
        lblY.setFont(labelFont);
        JTextField txtY = new JTextField(8);
        txtY.setFont(fieldFont);
        lblY.setLabelFor(txtY);
        txtY.setToolTipText("Enter a positive integer for y");
        lblY.setDisplayedMnemonic(KeyEvent.VK_Y);

        // Add input components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(lblX, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        inputPanel.add(txtX, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(lblY, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        inputPanel.add(txtY, gbc);

        // --- Compute Button and Result Label ---
        JButton btnCompute = new JButton("Compute B(x, y)");
        btnCompute.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCompute.setToolTipText("Click to compute the Beta function");
        btnCompute.setMnemonic(KeyEvent.VK_C);  // ALT+C activates button

        JLabel lblResult = new JLabel("Result: —");
        lblResult.setFont(resultFont);
        lblResult.setForeground(new Color(0, 102, 0));  // Dark green for success

        // Arrange button and result in a vertical box layout
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        btnCompute.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(btnCompute);
        resultPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultPanel.add(lblResult);

        // Assemble main content panel
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(resultPanel, BorderLayout.CENTER);
        frame.setContentPane(contentPanel);

        // Wire up button action listener
        btnCompute.addActionListener(e -> handleComputeAction(frame, txtX, txtY, lblResult));

        // Finalize window setup
        frame.pack();
        frame.setLocationRelativeTo(null);  // Center on screen
        frame.setVisible(true);

        // Set initial keyboard focus
        txtX.requestFocusInWindow();
    }

    /**
     * Event handler for the "Compute" button.
     * Parses inputs, invokes the calculator, and updates the result label or shows errors.
     *
     * @param parent the parent component for error dialogs
     * @param txtX JTextField for the x input
     * @param txtY JTextField for the y input
     * @param lblResult JLabel to display the computed Beta value
     */
    private static void handleComputeAction(Component parent,
                                            JTextField txtX,
                                            JTextField txtY,
                                            JLabel lblResult) {
        try {
            // Parse input values (R1)
            int x = Integer.parseInt(txtX.getText().trim());
            int y = Integer.parseInt(txtY.getText().trim());

            // Compute Beta function (R2, R3)
            double betaValue = BetaCalculator.beta(x, y);

            // Display result (R4)
            lblResult.setText(String.format("B(%d, %d) = %.6f", x, y, betaValue));
        } catch (NumberFormatException nfe) {
            showErrorDialog(parent,
                    "Please enter valid integer values for x and y.");
        } catch (IllegalArgumentException iae) {
            showErrorDialog(parent, iae.getMessage());
        } catch (Exception ex) {
            showErrorDialog(parent,
                    "Unexpected error occurred: " + ex.getMessage());
        }
    }

    /**
     * Displays an error dialog with a standardized title and icon.
     *
     * @param parent  the parent component for the dialog
     * @param message user-friendly error message text
     */
    private static void showErrorDialog(Component parent, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                "Input Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}