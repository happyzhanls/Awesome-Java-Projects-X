// ImageProgram.java
// User interface for image manipulation
// Original from Fritz Sieker
// Modified by Chris Wilcox
// 4/24/2013

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class ImageProgram extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L; // get rid of warning

    // Program flags
    private boolean   mIsInitialized; // GUI initialized?
    private boolean   mIsDirty;       // image modified?

    // Menu items
    private JMenuItem mOpenFile;
    private JMenuItem mSaveFile;
    private JMenuItem mExitCommand;
    
    private JMenuItem mDecode;
    private JMenuItem mSwap;
    private JMenuItem mMirror; 
    private JMenuItem mExchange;
    private JLabel    mLabel;
    
    // Transforms object from student
    private ImageInterface mStudent;

    // Array and static code are used to convert a gray scale to RGB
    private static int[] pgm2RGB;
    static {
        pgm2RGB = new int[256];

        for (int i = 0; i < 256; i++) {
            pgm2RGB[i] = (255 << 24) | (i << 16) | (i << 8) | i;
        }
    }

    // Constructor. Note that very little initialization is done here.
    // Since a derived class may override some of the initialization methods
    // these methods should NOT be called from a constructor because routines
    // in the derived class could be executed before the constructor of the super
    // class completes. In general, all code in the super class constructor
    // should be executed before ANY code in the derived class is executed.
    public ImageProgram () {
        super();
        setSize(new Dimension(400, 300));
    }

    // Satisfy the ActionListener interface. Most of the work is delegated to
    // the method doAction(). This allows a derived class to override doAction
    // (instead of actionPerformed()) and take advantage of the error handling
    // done here. If a derived class overrides actionPerformed() in may need
    // to duplicate the error handling.
    public void actionPerformed (ActionEvent actionEvent) {
        try {
            doAction(actionEvent);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // This can throw exceptions, because they are caught by performAction()
    // If you derive your own class from this class, and add new menus
    // or menu items, you would override this method to handle your new
    // menu items and delegate the work back to this method if the "action"
    // is not one of those you defined in your derived class. There are many
    // ways to dispatch from an event to the underlying code. This illustrates
    // one simple way of doing that.
    protected void doAction (ActionEvent actionEvent) throws Exception {

        Object src = actionEvent.getSource();
        if      (src == mOpenFile)      openFile();
        else if (src == mSaveFile)      saveFile();
        else if (src == mExitCommand)   exitGUI();
        else if (src == mDecode)    	decode();
        else if (src == mSwap) 			swap();
        else if (src == mMirror)    	mirror();
        else if (src == mExchange)		exchange();
        
    }

    // Override setVisible() to initialize everything the first time the
    // component becomes visible
    public void setVisible (boolean visible) {
        if (visible) {
            if (! mIsInitialized) {
                startGUI();
                mIsInitialized = true;
            }
        }
        super.setVisible(visible);
    }

    // Build the GUI.
    protected void startGUI() {
        setJMenuBar(makeMenuBar());

        addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent we) {
                mExitCommand.doClick(); // Press the Exit menu item
            }
        });

        getContentPane().add(makeMainPanel());
    }

    // Exit the GUI
    private void exitGUI() {
        if (mIsDirty) {
            if (!getYesNo("Data has not been saved.", "Continue?"))
                return;
        }
        System.exit(0);
    }


    // Creates the main panel of the GUI
    protected JPanel makeMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        mLabel = new JLabel();
        panel.add(mLabel, BorderLayout.CENTER);

        return panel;
    }

    // Created the menu bar for the GUI. Delegates most of the work to
    // methods which create the individual menus. The "adds" should remind you
    // of your work with ArrayLists. A JMenuBar is conceptually just a list of
    // menus. You will find methods on a JMenuBar to manipulate the elements
    // of the list.
    protected JMenuBar makeMenuBar() {
        JMenuBar mb = new JMenuBar();
        mb.add(makeFileMenu());
        mb.add(makeActionMenu());
        return mb;
    }

    // Create the file menu. Again, the "adds" (see makeMeniItem)should remind you
    // of list manipulation. A JMenu is conceptually a list of JMenuItems.
    // Interestingly, a JMenu is a JMenuItem. Why do you think that is??
    protected JMenu makeFileMenu() {
        JMenu menu  = makeMenu("File", 'F');
        mOpenFile     = makeMenuItem(menu, "Open...", 'O');
        mSaveFile     = makeMenuItem(menu, "Save...", 'S');
        mExitCommand  = makeMenuItem(menu, "Exit", 'X');
        return menu;
    }

    // Create the action menu.
    protected JMenu makeActionMenu() {
        JMenu menu  = makeMenu("Action", 'A');
        mDecode     = makeMenuItem(menu, "Decode"     , 'D');
        mSwap		= makeMenuItem(menu, "Swap"       , 'S');
        mMirror     = makeMenuItem(menu, "Mirror"     , 'M');
        mExchange   = makeMenuItem(menu, "Exchange"   , 'E');
        return menu;
    }

    // Convenience method for making JMenu
    protected JMenu makeMenu (String name, char mnemonic) {
        JMenu menu = new JMenu(name);
        menu.setMnemonic(mnemonic);
        return menu;
    }

    // Convenience method for making JMenuItem
    protected JMenuItem makeMenuItem (String name, char mnemonic) {
        JMenuItem mi = new JMenuItem(name, (int) mnemonic);
        mi.addActionListener(this);
        return mi;
    }

    // Convenience method for putting JMenuItem in a menu
    protected JMenuItem makeMenuItem (JMenu menu, String name, char mnemonic) {
        JMenuItem mi = makeMenuItem(name, mnemonic);
        menu.add(mi);
        return mi;
    }

    // Convenience method to get yes/no from user
    protected boolean getYesNo (String title, String message) {
        int answer = JOptionPane.showInternalConfirmDialog(getContentPane(),
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        return (answer == JOptionPane.YES_OPTION);
    }

    // Open image file
    private void openFile() throws Exception {

        // Data saved?
        if (mIsDirty) {
            if (!getYesNo("Open file", "Data has not been saved. Continue?"))
                return;
        }

        String fileName = selectFile("Select file to open", true);

        if (fileName != null) {
            mStudent = new Transforms();
            mStudent.readImage(fileName);
            resetImage();
            mIsDirty = false;
        }
    }

    // Save image file
    private void saveFile() throws Exception {
        String fileName = selectFile("Select file name to save", false);

        if (fileName != null) {
            mStudent.writeImage(fileName);
            mIsDirty = false;
        }
    }

    // Other student methods
    private void decode() {
        if (mStudent != null) {
            mStudent.decode();
            resetImage();
        }
    }

    private void swap() {
        if (mStudent != null) {
            mStudent.swap();
            resetImage();
        }
    }

    private void mirror() {
        if (mStudent != null) {
            mStudent.mirror();
            resetImage();
        }
    }
    
    private void exchange() {
        if (mStudent != null) {
            mStudent.exchange();
            resetImage();
        }
    }

    // File selector
    private String selectFile (String title, boolean open) {
        String fileName = null;

        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File("."));
        jfc.setDialogTitle(title);

        int result;
        if (open)
            result = jfc.showOpenDialog(this);
        else
            result = jfc.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            fileName = file.getAbsolutePath();
        }

        return fileName;
    }

    // Reset image
    private void resetImage() {
        if (mStudent != null) {

            // Copy the pixel values
            int image[][] = mStudent.imageData();
            int rows = image.length;
            int cols = image[0].length;
            BufferedImage buffer = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_ARGB);

            for (int row = 0; row < rows; row++) {
                for (int col=0; col < cols; col++) {
                    int rgb = pgm2RGB[image[row][col]];
                    buffer.setRGB(col, row, rgb);
                }
            }

            ImageIcon imageIcon = new ImageIcon(buffer);
            mLabel.setIcon(imageIcon);
            mIsDirty = true;
            pack(); // make window just fit image
        }
    }

    // Main program
    public static void main (String[] args) throws IOException {
        ImageProgram gui = new ImageProgram();
        gui.setVisible(true);
    }
}
