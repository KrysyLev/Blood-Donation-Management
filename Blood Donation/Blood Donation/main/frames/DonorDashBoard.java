package main.frames;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.sql.ConnectSQL;

public class DonorDashBoard extends DashBoardStruct{

    JLabel lbHistoryForm = new JLabel("History form:");
    JLabel lbHistoryDonation = new JLabel("History donation:");
    private JTextArea historyTextArea;
    private JTextArea donationTextArea;

    Font mainFont = new Font(null, Font.CENTER_BASELINE, 30);


    public DonorDashBoard(){
        super();
        System.out.println(HomeStruct.getID());
        this.togglePanel.btInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DonorInfo donorInfo = new DonorInfo();
                donorInfo.setVisible(true);
            }
        });
        this.togglePanel.btResetPassword.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showPassword.getInstance("donor").setVisible(true);
            }
            
        });
        this.togglePanel.btForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DonorForm form = new DonorForm();
                form.setVisible(true);
            }
        });

        lbHistoryForm.setBounds(50, 20, 300, 50);
        lbHistoryForm.setFont(mainFont);

        lbHistoryDonation.setBounds(600, 20, 300, 50);
        lbHistoryDonation.setFont(mainFont);

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(mainFont);
        historyTextArea.setText(ConnectSQL.loadFormHistory(HomeStruct.getID()));

        donationTextArea = new JTextArea();
        donationTextArea.setEditable(false);
        donationTextArea.setFont(mainFont);
        donationTextArea.setText(ConnectSQL.loadDonationHistory(HomeStruct.getID()));

        JScrollPane scrollPane1 = new JScrollPane(historyTextArea);
        scrollPane1.setBounds(50, 100, 500, 550);

        JScrollPane scrollPane2 = new JScrollPane(donationTextArea);
        scrollPane2.setBounds(600, 100, 500, 550);


        this.add(scrollPane1);
        this.add(lbHistoryForm);
        this.add(scrollPane2);
        this.add(lbHistoryDonation);
    }
}