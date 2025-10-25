import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {

    private JComboBox<String> inputScaleCombo;
    private JComboBox<String> outputScaleCombo;
    private JTextField tempInput;
    private JButton convertButton;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        //setResizable(false);

        // Use GridBagLayout for cleaner design
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 1: Input Scale
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Input Scale:"), gbc);
        gbc.gridx = 1;
        inputScaleCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        add(inputScaleCombo, gbc);

        // Row 2: Output Scale
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Output Scale:"), gbc);
        gbc.gridx = 1;
        outputScaleCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        add(outputScaleCombo, gbc);

        // Row 3: Temperature Input
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Temperature:"), gbc);
        gbc.gridx = 1;
        tempInput = new JTextField();
        add(tempInput, gbc);

        // Row 4: Convert Button
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        convertButton = new JButton("Convert");
        add(convertButton, gbc);

        // Row 5: Result Label
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Result:"), gbc);
        gbc.gridx = 1;
        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel, gbc);

        // Action listener
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        setVisible(true);
    }

    private void convertTemperature() {
        try {
            String inputScale = (String) inputScaleCombo.getSelectedItem();
            String outputScale = (String) outputScaleCombo.getSelectedItem();
            double temp = Double.parseDouble(tempInput.getText());
            double result = 0.0;

            if (inputScale.equals("Celsius")) {
                if (outputScale.equals("Fahrenheit")) result = (temp * 9 / 5) + 32;
                else if (outputScale.equals("Kelvin")) result = temp + 273.15;
                else result = temp;
            } else if (inputScale.equals("Fahrenheit")) {
                if (outputScale.equals("Celsius")) result = (temp - 32) * 5 / 9;
                else if (outputScale.equals("Kelvin")) result = (temp - 32) * 5 / 9 + 273.15;
                else result = temp;
            } else if (inputScale.equals("Kelvin")) {
                if (outputScale.equals("Celsius")) result = temp - 273.15;
                else if (outputScale.equals("Fahrenheit")) result = (temp - 273.15) * 9 / 5 + 32;
                else result = temp;
            }

            resultLabel.setText(String.format("%.2f %s", result, outputScale));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverterGUI());
    }
}
