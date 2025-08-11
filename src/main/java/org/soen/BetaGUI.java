package org.soen;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Swing-based GUI for the Beta function calculator.
 *
 * <p>The UI is separate from computation logic in BetaCalculator.
 * Accessibility features include associated labels, tooltips, mnemonics,
 * and tab navigation.</p>
 */
public final class BetaGUI {

    /**
     * Number of columns for the input text fields.
     */
    private static final int FIELD_COLUMNS = 8;

    /**
     * External padding applied to the content panel (pixels).
     */
    private static final int PADDING = 15;

    /**
     * Insets used between controls inside layout (pixels).
     */
    private static final int INSET = 10;

    /**
     * Font size (points) used for labels.
     */
    private static final int LABEL_FONT_SIZE = 16;

    /**
     * Font size (points) used for text fields.
     */
    private static final int FIELD_FONT_SIZE = 16;

    /**
     * Font size (points) used for the result label.
     */
    private static final int RESULT_FONT_SIZE = 18;

    /**
     * Font size (points) used for the primary button.
     */
    private static final int BUTTON_FONT_SIZE = 16;

    /**
     * Red component (0-255) for the success message color.
     */
    private static final int GREEN_R = 0;

    /**
     * Green component (0-255) for the success message color.
     */
    private static final int GREEN_G = 102;

    /**
     * Blue component (0-255) for the success message color.
     */
    private static final int GREEN_B = 0;

    /**
     * Decimal format used to display Beta function values.
     */
    private static final DecimalFormat DISPLAY_FORMAT =
            new DecimalFormat("#.################");

    /**
     * Private constructor to prevent instantiation.
     */
    private BetaGUI() {
        // Utility class - no instance
    }

    /**
     * Main entry point schedules GUI creation on the Event Dispatch Thread.
     *
     * @param args unused
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(BetaGUI::createAndShowGui);
    }

    /**
     * Builds and shows the GUI.
     */
    private static void createAndShowGui() {
        final JFrame frame = new JFrame("Beta Function Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(
                PADDING, PADDING, PADDING, PADDING));

        final JPanel inputPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        final Font labelFont = new Font("SansSerif", Font.PLAIN,
                LABEL_FONT_SIZE);
        final Font fieldFont = new Font("SansSerif", Font.PLAIN,
                FIELD_FONT_SIZE);
        final Font resultFont = new Font("SansSerif", Font.BOLD,
                RESULT_FONT_SIZE);

        // X input
        final JLabel lblX = new JLabel("x (integer > 0):");
        lblX.setFont(labelFont);
        final JTextField txtX = new JTextField(FIELD_COLUMNS);
        txtX.setFont(fieldFont);
        lblX.setLabelFor(txtX);
        txtX.setToolTipText("Enter a positive integer for x");
        lblX.setDisplayedMnemonic('X');

        // Y input
        final JLabel lblY = new JLabel("y (integer > 0):");
        lblY.setFont(labelFont);
        final JTextField txtY = new JTextField(FIELD_COLUMNS);
        txtY.setFont(fieldFont);
        lblY.setLabelFor(txtY);
        txtY.setToolTipText("Enter a positive integer for y");
        lblY.setDisplayedMnemonic('Y');

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblX, gbc);

        gbc.gridx = 1;
        inputPanel.add(txtX, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblY, gbc);

        gbc.gridx = 1;
        inputPanel.add(txtY, gbc);

        final JButton btnCompute = new JButton("Compute B(x, y)");
        btnCompute.setFont(new Font("SansSerif", Font.BOLD,
                BUTTON_FONT_SIZE));
        btnCompute.setToolTipText("Click to compute the Beta function");
        btnCompute.setMnemonic('C');

        final JLabel lblResult = new JLabel("Result: \u2014");
        lblResult.setFont(resultFont);
        lblResult.setForeground(new java.awt.Color(
                GREEN_R, GREEN_G, GREEN_B));

        final JPanel resultPanel = new JPanel();
        resultPanel.setLayout(
                new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        btnCompute.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(btnCompute);
        resultPanel.add(Box.createRigidArea(new Dimension(0, INSET)));
        resultPanel.add(lblResult);

        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(resultPanel, BorderLayout.CENTER);
        frame.setContentPane(contentPanel);

        btnCompute.addActionListener(e ->
                handleComputeAction(frame, txtX, txtY, lblResult));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        txtX.requestFocusInWindow();
    }

    /**
     * Handles the compute button action: parses input, computes Beta,
     * and updates the result label or shows an error dialog.
     *
     * @param parent parent frame for dialogs
     * @param txtX text field for x input
     * @param txtY text field for y input
     * @param lblResult label to display results
     */
    private static void handleComputeAction(final JFrame parent,
                                            final JTextField txtX,
                                            final JTextField txtY,
                                            final JLabel lblResult) {
        try {
            final int x = Integer.parseInt(txtX.getText().trim());
            final int y = Integer.parseInt(txtY.getText().trim());

            final BigDecimal beta = BetaCalculator.beta(x, y);

            lblResult.setText(String.format("B(%d, %d) = %s", x, y,
                    DISPLAY_FORMAT.format(beta)));

        } catch (NumberFormatException nfe) {
            showErrorDialog(parent,
                    "Please enter valid integer values for x and y.");
        } catch (BetaException be) {
            showErrorDialog(parent, be.getMessage());
        } catch (Exception ex) {
            showErrorDialog(parent,
                    "Unexpected error occurred: " + ex.getMessage());
        }
    }

    /**
     * Shows a standardized error dialog.
     *
     * @param parent parent frame for dialog
     * @param message message to present the user
     */
    private static void showErrorDialog(final JFrame parent,
                                        final String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                "Input Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
