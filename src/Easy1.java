import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

    public class Easy1 implements ActionListener {
        private JFrame mainFrame;
        private JLabel statusLabel;
        private JPanel controlPanel;
        private JMenuBar mb;
        private JMenu file, edit, help;
        private JMenuItem cut, copy, paste, selectAll;
        private JTextArea ta; //typing area
        private int WIDTH=800;
        private int HEIGHT=700;


        public Easy1() {
            prepareGUI();
        }

        public static void main(String[] args) {
            Easy1 easy1 = new Easy1();
            easy1.showEasy1();
        }

        private void prepareGUI() {
            mainFrame = new JFrame("Example with GridLayout");
            mainFrame.setSize(WIDTH, HEIGHT);
            mainFrame.setLayout(new GridLayout(2, 3));

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
            controlPanel.setLayout(new BorderLayout()); //set the layout of the panel

            mainFrame.setVisible(true);
        }

        private void showEasy1() {

            JButton button1 = new JButton("Button 1");
            JButton button2 = new JButton("Button 2");
            JButton button3 = new JButton("Button 3");
            JButton button4 = new JButton("Button 4");
            JButton button5 = new JButton("Button 5");

            button1.setActionCommand("Button 1");
            button2.setActionCommand("Button 2");
            button3.setActionCommand("Button 3");
            button4.setActionCommand("Button 4");
            button5.setActionCommand("Button 5");


            button1.addActionListener(new ButtonClickListener());
            button2.addActionListener(new ButtonClickListener());
            button3.addActionListener(new ButtonClickListener());
            button4.addActionListener(new ButtonClickListener());
            button5.addActionListener(new ButtonClickListener());

            mainFrame.add(button1);
            mainFrame.add(button2);
            mainFrame.add(button3);
            mainFrame.add(button4);
            mainFrame.add(button5);

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