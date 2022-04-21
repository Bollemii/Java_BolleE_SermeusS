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
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);


        //title
        title = new JLabel("Réservation");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.NORTH;
        c.weighty = 1;
        add(title, c);


        c.weighty = 2;
        //comboBox
        comboBox = new JComboBox();
        comboBox.addItem("All");
        comboBox.addItem("Active");
        comboBox.addItem("Inactive");

        comboBox.setFont(new Font("Arial", Font.PLAIN, 20));


        add(comboBox, c);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 20));

        add(submit, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 20, 0);
        add(comboBox, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 20, 0);
        add(submit, c);

        table = new JTable();

        String[] tblHead={"Item Name","Price","Qty","Discount"};
        DefaultTableModel dtm=new DefaultTableModel(tblHead,0);
        table =new JTable(dtm);
        String[] item={"A","B","C","D"};
        dtm.addRow(item);
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 20, 0);
        add(table, c);
    }
}
