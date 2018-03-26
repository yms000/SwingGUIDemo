package action;

import frame.MyFrame;
import panel.MyPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SaveFileAction extends AbstractAction {

    private static SaveFileAction saveFile;

    public static SaveFileAction getInstance(String name) {
        if (saveFile == null) {
            saveFile = new SaveFileAction(name);
        }
        return saveFile;
    }

    private SaveFileAction(String name) {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyPanel editor = MyFrame.getMainFrame().getEditorPane();
        String url =editor.getUrl();
        File tempFile = new File(url);

        String content = editor.getText();

        try {
            PrintStream ps = new PrintStream(new FileOutputStream(tempFile));
            ps.println(content);
            ps.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
