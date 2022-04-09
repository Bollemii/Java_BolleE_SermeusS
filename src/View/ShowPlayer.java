package View;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ShowPlayer extends JPanel {
    private JPanel row1;
    private JPanel row23;
    private JButton submit;
    private JComboBox comboBox;
    private JTable table;;
    private JLabel title;
    //constructor
    public ShowPlayer() {
        title = new JLabel("Player List");
        //gridlayout of 3 1
        title.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new GridLayout(5,1));
        add(title);
        row1 = new JPanel(new GridLayout(1,2));
        comboBox = new JComboBox();
        comboBox.addItem("All");
        comboBox.addItem("Player");
        comboBox.addItem("Team");
        submit = new JButton("Submit");
        row1.add(comboBox);
        row1.add(submit);
        row23 = new JPanel(new GridLayout(1,1));
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        row23.add(scrollPane);
        //add components
        add(row1);
        add(row23);



    }


    //painting
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //repaint components
    }
}
