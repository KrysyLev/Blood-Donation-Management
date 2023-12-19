package main.frames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.Frame;
import main.button.RoundedButton;
import main.sql.ConnectSQL;

public class DonorForm extends Frame{

    public JTextField medicalConditionField;
    public JTextField medicationsField;
    public JTextField allergiesField;
    public JTextField recentIllnessField;
    public JTextField recentTravelField;
    public JTextField covid19StatusField;

    public RoundedButton submitButton;

    Font buttonFont = new Font(null, Font.CENTER_BASELINE, 50);
    Font mainFont = new Font(null, Font.CENTER_BASELINE, 30);
    Color greyColor = new Color(217, 217, 217);
    Color bloodColor = new Color(240, 132, 134);

    public DonorForm() {
        super();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit this frame?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        // Create and initialize components
        medicalConditionField = new JTextField();
        medicationsField = new JTextField();
        allergiesField = new JTextField();
        recentIllnessField = new JTextField();
        recentTravelField = new JTextField();
        covid19StatusField = new JTextField();

        submitButton = new RoundedButton("Submit");

        // Set positions and sizes for components
        int x = 150;
        int y = 100;
        int labelWidth = 300;
        int textFieldWidth = 500;
        int height = 50;
        int gap = 20;

        JLabel medicalConditionLabel = createLabel("Medical Condition:", x, y, labelWidth, height);
        medicalConditionLabel.setFont(mainFont);
        medicalConditionField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        medicalConditionField.setFont(mainFont);

        JLabel medicationsLabel = createLabel("Medications:", x, y, labelWidth, height);
        medicationsLabel.setFont(mainFont);
        medicationsField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        medicationsField.setFont(mainFont);

        JLabel allergiesLabel = createLabel("Allergies:", x, y, labelWidth, height);
        allergiesLabel.setFont(mainFont);
        allergiesField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        allergiesField.setFont(mainFont);

        JLabel recentIllnessLabel = createLabel("Recent Illness:", x, y, labelWidth, height);
        recentIllnessLabel.setFont(mainFont);
        recentIllnessField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        recentIllnessField.setFont(mainFont);

        JLabel recentTravelLabel = createLabel("Recent Travel:", x, y, labelWidth, height);
        recentTravelLabel.setFont(mainFont);
        recentTravelField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        recentTravelField.setFont(mainFont);

        JLabel covid19StatusLabel = createLabel("COVID-19 Status:", x, y, labelWidth, height);
        covid19StatusLabel.setFont(mainFont);
        covid19StatusField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        covid19StatusField.setFont(mainFont);

        submitButton.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        submitButton.setBackground(bloodColor);
        submitButton.setFont(buttonFont);
        submitButton.setForeground(greyColor);
        submitButton.setFocusable(false);
        // Set action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.setEnabled(false);
                if(ConnectSQL.addForm(HomeStruct.getID(), medicalConditionField.getText(), medicationsField.getText(), allergiesField.getText(),  recentIllnessField.getText(), recentTravelField.getText(), covid19StatusField.getText())){
                    submitButton.setEnabled(true);
                    medicalConditionField.setText(null);
                    medicationsField.setText(null);
                    allergiesField.setText(null);
                    recentIllnessField.setText(null);
                    recentTravelField.setText(null);
                    covid19StatusField.setText(null);
                }
            }
        });

        // Add components to the frame
        add(medicalConditionLabel);
        add(medicationsLabel);
        add(allergiesLabel);
        add(recentIllnessLabel);
        add(recentTravelLabel);
        add(covid19StatusLabel);

        add(medicalConditionField);
        add(medicationsField);
        add(allergiesField);
        add(recentIllnessField);
        add(recentTravelField);
        add(covid19StatusField);

        add(submitButton);
    }

    public  JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }
}