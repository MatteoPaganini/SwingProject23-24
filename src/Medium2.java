import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Medium2 implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private int WIDTH=800;
    private int HEIGHT=700;


    public Medium2() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Medium2 medium2 = new Medium2();
        medium2.showMedium2();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Example with Grid inside BorderLayout");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(3, 3));

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        //end menu at top

        mainFrame.setJMenuBar(mb); //set menu bar

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout(3,3)); //set the layout of the panel

        mainFrame.add(controlPanel);

        mainFrame.setVisible(true);
    }

    private void showMedium2() {

        //JLabel label = new JLabel("label", JLabel.WEST);
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        button1.setActionCommand("Button 1");
        button2.setActionCommand("Button 2");
        button3.setActionCommand("Button 3");

        // labels don't need ActionCommand or ActionListener
        button1.addActionListener(new ButtonClickListener());
        button2.addActionListener(new ButtonClickListener());
        button3.addActionListener(new ButtonClickListener());

        mainFrame.add(button1);
        mainFrame.add(button2);
        mainFrame.add(button3);

        // then add the borderLayout inside the middle square
        //controlPanel.add(label); //label
//        mainFrame.add(button1, BorderLayout.NORTH);
//        mainFrame.add(button3, BorderLayout.SOUTH);
        //then the rest of the buttons

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Button 1")) {
                statusLabel.setText("Button 1 clicked.");
            } else if (command.equals("Button 2")) {
                statusLabel.setText("Button 2 clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }
}

