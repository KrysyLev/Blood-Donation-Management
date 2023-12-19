package main.frames;

import main.Frame;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.button.RoundedButton;
import main.sql.ConnectSQL;

public class CheckingForm extends Frame{

    private JTextArea formTextArea;
    RoundedButton btUpdate = new RoundedButton("Update");

    JTextField medIDField;
    JTextField statusField;
    JTextField meetingDateField;

    Font mainFont = new Font(null, Font.CENTER_BASELINE, 30);
    Font buttonFont = new Font(null, Font.CENTER_BASELINE, 30);
    Color greyColor = new Color(217, 217, 217);
    Color bloodColor = new Color(240, 132, 134);

    public CheckingForm(){
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

        formTextArea = new JTextArea();
        formTextArea.setEditable(false);
        formTextArea.setFont(mainFont);
        formTextArea.setText(ConnectSQL.loadFormHistory(0));

        medIDField = new JTextField();
        statusField = new JTextField();
        meetingDateField = new JTextField();

        int x = 600;
        int y = 100;
        int labelWidth = 300;
        int textFieldWidth = 300;
        int height = 50;
        int gap = 20;

        JLabel medIDLabel = createLabel("MedicalHistoryID:", x, y, labelWidth, height);
        medIDLabel.setFont(mainFont);
        medIDField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        medIDField.setFont(mainFont);

        JLabel statusLabel = createLabel("Status:", x, y, labelWidth, height);
        statusLabel.setFont(mainFont);
        statusField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        statusField.setFont(mainFont);

        JLabel meetingLabel = createLabel("MeetingDate:", x, y, labelWidth, height);
        meetingLabel.setFont(mainFont);
        meetingDateField.setBounds(x + labelWidth + gap, y, textFieldWidth, height);
        y += height + gap;
        meetingDateField.setFont(mainFont);

        JScrollPane scrollPane = new JScrollPane(formTextArea);
        scrollPane.setBounds(20, 100, 500, 550);

        btUpdate.setBounds(800, 600, 200, 50);
        btUpdate.setBackground(bloodColor);
        btUpdate.setFont(buttonFont);
        btUpdate.setForeground(greyColor);
        btUpdate.setFocusable(false);
        // Set action listener for the submit button
        btUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(null);
                if(medIDField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Fields can not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    if(ConnectSQL.changeStatus(Integer.parseInt(medIDField.getText()), statusField.getText(), meetingDateField.getText())){
                    btUpdate.setEnabled(true);
                    medIDField.setText(null);
                    statusField.setText(null);
                    meetingDateField.setText(null);
                    }
                }
                JTextArea newTextArea = new JTextArea();
                newTextArea.setEditable(false);
                newTextArea.setFont(mainFont);
                newTextArea.setText(ConnectSQL.loadFormHistory(0));
                scrollPane.setViewportView(newTextArea);
                scrollPane.revalidate();
                scrollPane.repaint();
            }
        });

        
        this.add(medIDLabel);
        this.add(statusLabel);
        this.add(meetingLabel);
        this.add(medIDField);
        this.add(statusField);
        this.add(meetingDateField);
        this.add(btUpdate);
        this.add(scrollPane);
    }

    public  JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }
}
