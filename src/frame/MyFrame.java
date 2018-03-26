package frame;

import action.OpenFileAction;
import action.SaveAsFileAction;
import action.SaveFileAction;
import action.SearchAction;
import panel.MyPanel;

import javax.swing.*;
import java.awt.*;

public class MyFrame
        extends JFrame {

    private MyPanel editorPane = new MyPanel();
    private static MyFrame frame;

    public static MyFrame getMainFrame() {
        if (frame == null) {
            frame = new MyFrame();
        }
        return frame;
    }

    public MyPanel getEditorPane() {
        return editorPane;
    }

    @Override
    public void setContentPane(Container contentPane) {
        if (contentPane == null) {
            contentPane = new MyPanel();
        }
        super.setContentPane(contentPane);

    }

    private MyFrame() {
        initUi();
    }

    private void initUi() {
        setTitle("My text");
        setSize(640, 480);
        setLocation(800, 300);

        initMainMenu();
        setContentPane(editorPane);
    }



    private void initMainMenu() {
        final JMenuBar mainMenu = new JMenuBar();

        final JMenu menuFile = new JMenu();
        menuFile.setText("File");
        menuFile.setMnemonic('F');

        menuFile.add(OpenFileAction.getInstance("open")).setMnemonic('O');
        menuFile.add(SaveFileAction.getInstance("save")).setMnemonic('S');
        menuFile.add(SaveAsFileAction.getInstance("save as"));
        menuFile.add(SearchAction.getInstance("search"));

        mainMenu.add(menuFile);
        setJMenuBar(mainMenu);
    }


}
