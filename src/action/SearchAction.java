package action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SearchAction extends AbstractAction {

    private static SearchAction search;

    public static SearchAction getInstance(String name) {
        if (search == null) {
            search = new SearchAction(name);
        }
        return search;
    }

    private SearchAction(String name) {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
