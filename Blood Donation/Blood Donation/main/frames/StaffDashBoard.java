package main.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.button.RoundedButton;


public class StaffDashBoard extends DashBoardStruct{

    public RoundedButton donationBt = new RoundedButton("Donation");
    public RoundedButton checkFormBt = new RoundedButton("Checking form");

    Font buttonFont = new Font(null, Font.CENTER_BASELINE, 50);
    Color greyColor = new Color(217, 217, 217);
    Color bloodColor = new Color(240, 132, 134);

    public StaffDashBoard(){
        super();
        this.togglePanel.setSize(150, 250);
        this.togglePanel.btForm.setVisible(false);
        this.togglePanel.btInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StaffInfo staffInfo = new StaffInfo();
                staffInfo.setVisible(true);
            }
        });
        this.togglePanel.btResetPassword.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showPassword.getInstance("staff").setVisible(true);
            }
            
        });

        checkFormBt.setBounds(100, 300, 500, 100);
        checkFormBt.setBackground(bloodColor);
        checkFormBt.setForeground(Color.WHITE);
        checkFormBt.setFont(buttonFont);
        checkFormBt.setFocusable(false);
        checkFormBt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // checkFormBt.setEnabled(false);
                CheckingForm form = new CheckingForm();
                form.setVisible(true);
            }
            
        });

        donationBt.setBounds(700, 300, 500, 100);
        donationBt.setBackground(bloodColor);
        donationBt.setForeground(Color.WHITE);
        donationBt.setFont(buttonFont);
        donationBt.setFocusable(false);
        donationBt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // checkFormBt.setEnabled(false);
                CheckingForm form = new CheckingForm();
                form.setVisible(true);
            }
            
        });

        this.add(donationBt);
        this.add(checkFormBt);
    }
}