package action;

import frame.MyFrame;
import panel.MyPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SaveAsFileAction extends AbstractAction {

    private static SaveAsFileAction saveAsFile;

    public static SaveAsFileAction getInstance(String name) {
        if (saveAsFile == null) {
            saveAsFile = new SaveAsFileAction(name);
        }
        return  saveAsFile;
    }

    private SaveAsFileAction(String name) {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


        final int option = fileChooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                String path = file.getAbsolutePath();
                System.out.println(path);
                path = path + "\\new_file.txt";

                File tempFile = new File(path);

                MyPanel editor = MyFrame.getMainFrame().getEditorPane();

                String content = editor.getText();
                System.out.println("content:" + content);

                try {
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    PrintStream printStream = new PrintStream(fos);
                    printStream.print(content);
                    printStream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }


//                if (path.endsWith(".txt")) {
//                    System.out.println("+++++++++++++++");
//                } else {
//
//                }

            }

        }
    }
}
