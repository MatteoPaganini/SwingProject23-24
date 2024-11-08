import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JScrollPane scrollPane;


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

        //defining new buttons and text areas
        JButton button4 = new JButton("Start! ");
        textArea1 = new JTextArea("Search term: ");
        textArea2 = new JTextArea("Links: ");
        textArea3 = new JTextArea("URL: ");
        scrollPane = new JScrollPane(textArea2);

        button4.setActionCommand("Button 4");


        // labels don't need ActionCommand or ActionListener... buttons do
        button4.addActionListener(new ButtonClickListener());

        //control panel is the grid layout
        //mainFrame is the broder layout
        //add functions to CP and MF
        controlPanel.add(textArea3);
        controlPanel.add(button4);
        mainFrame.add(textArea1, BorderLayout.NORTH);
        controlPanel.add(scrollPane);

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

            if (command.equals("Button 4")) { //start button is clicked
                System.out.println("Starting ");

                System.out.println(textArea3.getText()); //gets text from textArea3
                int inputtedurl = textArea3.getText().indexOf(":") + 2;
                String newUrl = textArea3.getText().substring(inputtedurl);


                System.out.println(textArea1.getText()); //gets text from textArea3
                int inputtedSearchTerm = textArea1.getText().indexOf(":") + 2;
                String newSearchTerm = textArea1.getText().substring(inputtedSearchTerm);
                System.out.println(newSearchTerm);

                //html reader code

                    try {
                        System.out.println();
                        System.out.print("hello \n");
                        URL url = new URL(newUrl);
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(url.openStream())
                        );
                        String line;
                        while ( (line = reader.readLine()) != null ) {

                            if((line.contains("href=")))  {
                                //   System.out.println("og line: " + line);

                                //methods needed: indexof, substring
                                int indexhttp = line.indexOf("href=") +6;
                                String newLine = line.substring(indexhttp);

                                int end = newLine.indexOf("\"");
                                int oEnd = newLine.indexOf("\'");

                               if(oEnd == -1){

                                   if (newLine.substring(0,end).contains(newSearchTerm)) {
                                       System.out.println(newLine.substring(0, end)); //prints links to dosWindow
                                       textArea2.append((newLine.substring(0, end) + "\n")); //appends links to "links" in the layout
                                   }

                               }else if (end == -1){

                                   if (newLine.substring(0,oEnd).contains(newSearchTerm)) {
                                       System.out.println(newLine.substring(0, oEnd));
                                       textArea2.append(newLine.substring(0, oEnd) + "\n"); //hidden character makes links print under
                                   }

                               }else {
                                   if(oEnd > end){

                                       if (newLine.substring(0,end).contains(newSearchTerm)) {
                                           System.out.println(newLine.substring(0, end));
                                           textArea2.append(newLine.substring(0, end) + "\n");
                                       }

                                   }else{

                                       if (newLine.substring(0,oEnd).contains(newSearchTerm)) {
                                           System.out.println(newLine.substring(0, oEnd));
                                           textArea2.append(newLine.substring(0, oEnd) + "\n");
                                       }
                                    }
                                }

                            }



                        }

                        reader.close();
                    } catch(Exception ex) {
                        System.out.println(ex);
                    } //end html reader code
            }



        }
    }


}

