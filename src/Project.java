import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Project implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private int WIDTH=800;
    private int HEIGHT=700;


    public Project() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Project project = new Project();
        project.showProject();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Project Layout");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout(3, 3));

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
        controlPanel.setLayout(new GridLayout(1,2)); //set the layout of the panel

        mainFrame.add(controlPanel);

        mainFrame.setVisible(true);
    }

    private void showProject() {

//        JLabel label1 = new JLabel("label 1", JLabel.CENTER);
//        JLabel label2 = new JLabel("label 2", JLabel.CENTER);
        JButton button1 = new JButton("URL: ");
        JButton button3 = new JButton("Search: ");
        JButton button4 = new JButton("Start! ");
        JTextArea textArea1 = new JTextArea("Links: ");
        JTextArea textArea2 = new JTextArea("URL");

        button4.setActionCommand("Button 4");


        // labels don't need ActionCommand or ActionListener
        button4.addActionListener(new ButtonClickListener());

        controlPanel.add(textArea2);
        //controlPanel.add(label1); //label
        controlPanel.add(button4);
        mainFrame.add(button1, BorderLayout.NORTH);
        mainFrame.add(button3, BorderLayout.SOUTH);
       // controlPanel.add(label2); //label
        controlPanel.add(textArea1);

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

