import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
//import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener
{
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log;
    private JLabel image;
	private JButton backButton;
	private JButton lookButton;
	private JButton playerButton;
	private JButton reloadButton;
	private JButton helpButton;
	private JButton quitButton;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        createGUI();
    }

    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if(imageURL == null)
            System.out.println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
	    image.setIcon(new ImageIcon(icon.getImage().getScaledInstance(800, 800 * icon.getIconHeight() / icon.getIconWidth(), Image.SCALE_DEFAULT)));
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame = new JFrame("Residu Evil");
        entryField = new JTextField(34);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(100, 500));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        JPanel panelButton = new JPanel();
        backButton = new JButton("back");
        lookButton = new JButton("look");
        playerButton = new JButton("player");
        reloadButton = new JButton("reload");
        helpButton = new JButton("help");
        quitButton = new JButton("quit");
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(panelButton, BorderLayout.SOUTH);

        panelButton.add(entryField, BorderLayout.SOUTH);	
        panelButton.add(backButton, BorderLayout.EAST);
        panelButton.add(lookButton, BorderLayout.EAST);
        panelButton.add(playerButton, BorderLayout.EAST);
        panelButton.add(reloadButton, BorderLayout.EAST);
        panelButton.add(helpButton, BorderLayout.EAST);
        panelButton.add(quitButton, BorderLayout.EAST);

        //Definition de la fonction execute par le button
        backButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
        		engine.interpretCommand("back");
    		}
        });
        
        lookButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
        		engine.interpretCommand("look");
    		}
        });
        
        playerButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
        		engine.interpretCommand("player");
    		}
        });
        
        reloadButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
        		engine.interpretCommand("reload");
    		}
        });
        
        helpButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
        		engine.interpretCommand("help");
    		}
        });
        
        quitButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
        		engine.interpretCommand("quit");
    		}
        });
 	
        myFrame.getContentPane().add(panel, BorderLayout.CENTER);

        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        entryField.addActionListener(this);

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        processCommand();
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String input = entryField.getText();
        entryField.setText("");
        engine.interpretCommand(input);
    }
}
