package panel;

import javax.swing.*;
import java.io.IOException;

public class MyPanel extends JEditorPane {

    private String url = "C:\\Users\\Administrator\\Desktop\\new_file.txt";

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void initUI()
            throws IOException {
        setSize(640, 480);
        setContentType("text/plain");
        System.out.println("************************");
        setPage("file:" + url);

        setVisible(true);

    }
}
