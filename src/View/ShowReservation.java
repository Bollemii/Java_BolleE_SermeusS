package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowReservation extends JPanel {
    private JButton submit;
    private JComboBox comboBox;
    private JTable table;;
    private JLabel title;
    //constructor
    public ShowReservation() {
        //gridBagLayout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);


        //title
        title = new JLabel("Réservation");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        add(title, constraints);


        constraints.weighty = 2;
        //comboBox
        comboBox = new JComboBox();
        comboBox.addItem("All");
        comboBox.addItem("Active");
        comboBox.addItem("Inactive");

        comboBox.setFont(new Font("Arial", Font.PLAIN, 20));


        add(comboBox, constraints);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 20));

        add(submit, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 20, 0);
        add(comboBox, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 0, 20, 0);
        add(submit, constraints);

        table = new JTable();

        String[] tblHead={"Item Name","Price","Qty","Discount"};
        DefaultTableModel dtm=new DefaultTableModel(tblHead,0);
        table =new JTable(dtm);
        String[] item={"A","B","C","D"};
        dtm.addRow(item);
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 0, 20, 0);
        add(table, constraints);
    }
}
