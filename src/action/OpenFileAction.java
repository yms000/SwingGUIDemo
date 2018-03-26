package action;

import frame.MyFrame;
import panel.MyPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static javax.swing.JFileChooser.APPROVE_OPTION;

public class OpenFileAction extends AbstractAction {

    private static OpenFileAction openFile;

    public static OpenFileAction getInstance(String name) {
        if (openFile == null) {
            openFile = new OpenFileAction(name);
        }
        return openFile;
    }

    private OpenFileAction(String name) {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("open");
        fileChooser.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));

        final int result = fileChooser.showOpenDialog(null);
        if (result == APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            if (file != null) {
                String path = file.getPath();
                MyPanel editor = MyFrame.getMainFrame().getEditorPane();

//                FileInputStream fis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    editor.read(fis, null);
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//                MyFrame.getMainFrame().setContentPane(editor);

                try {
                    System.out.println("path = " + path);
                    editor.setUrl(path);
                    editor.initUI();
                    MyFrame.getMainFrame().setContentPane(editor);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
