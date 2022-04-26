package View;

import Model.Match;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShowGestionMatch extends JPanel {
    private JPanel mainPanel;
    private JButton submit;
    private JComboBox comboBox;
    private JTable table;;
    private JLabel title;
    private ArrayList<Match> listMatch;
    //constructor
    public ShowGestionMatch() {
        this.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        //gridBagLayout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        mainPanel.setLayout(layout);

        //title
        title = new JLabel("Gestion des matchs", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        this.add(title, BorderLayout.NORTH);

        //comboBox
        comboBox = new JComboBox();
        comboBox.addItem("All");
        comboBox.addItem("Active");
        comboBox.addItem("Inactive");
        comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        c.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(comboBox,c);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 20));
        c.insets = new Insets(0, 20, 20, 0);
        mainPanel.add(submit, c);

        table = new JTable();
        String[] tblHead = {"Item Name","Price","Qty","Discount"};
        DefaultTableModel dtm = new DefaultTableModel(tblHead,0);
        table = new JTable(dtm);
        String[] item = {"A","B","C","D"};
        dtm.addRow(item);
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        c.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(table, c);

        this.add(mainPanel, BorderLayout.CENTER);
    }
}
