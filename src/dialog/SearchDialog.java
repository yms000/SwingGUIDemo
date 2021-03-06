package dialog;

import frame.MyFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

public class SearchDialog extends JDialog {

    private static SearchDialog dialog;

    private JTextComponent text;

    public static SearchDialog getInstance(Frame owner) {

        if (dialog == null) {
            dialog = new SearchDialog(owner);
        }
        dialog.setVisible(true);//退出之后，显示的对话框还是同一个；dialog是全局变量在对话框退出时没有立即销毁
        return dialog;
    }

    public static SearchDialog getInstance(Frame owner, JTextComponent text) {

        if (dialog == null) {
            dialog = new SearchDialog(owner, text);
        }
        dialog.setVisible(true);//退出之后，显示的对话框还是同一个；dialog是全局变量在对话框退出时没有立即销毁
        return dialog;
    }

    /**
     * Creates new form NewJDialog
     *
     * @param owner
     *         主窗口，设置主窗口之后可以跟随主窗口最小化、最大化
     */
    private SearchDialog(Frame owner) {
        super(owner);
        this.text = ((MyFrame) getParent()).getEditorPane();
        initUi();
    }

    /**
     * Creates new form NewJDialog
     *
     * @param owner
     *         主窗口
     * @param text
     *         需要查找内容的面板
     */
    private SearchDialog(
            Frame owner,
            JTextComponent text
    ) {
        super(owner);
        if (text != null) this.text = text;
        initUi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initUi() {

        label = new JLabel();
        label.setText("查找内容(N):");
        label.setSize(80, 25);

        searchField = new JTextField();
        searchField.setSize(125, 25);
        // 输入法，是否禁用输入法，false禁止（不能输入汉字）；true允许，可以输入汉字
        //searchField.enableInputMethods(true);

        // 输入法和JTextField冲突问题没有解决
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchEnable(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchEnable(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        searchButt = new JButton();
        searchButt.setSize(125, 25);
        searchButt.setText("查找下一个(F)");
        searchButt.setEnabled(false);
        searchButt.addActionListener(this::findNextPerformed);

        checkBox = new JCheckBox();
        checkBox.setText("区分大小写(C)");
        checkBox.addActionListener(this::isMatchCase);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "方向"));
        upButt = new JRadioButton();
        downButt = new JRadioButton();
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(upButt);
        btnGroup.add(downButt);
        downButt.setSelected(true);
        upButt.setText("向上");
        downButt.setText("向下");
        downButt.addItemListener(this::searchUpOrDown);
        upButt.addItemListener(this::searchUpOrDown);

        // 方向面板设置布局
        GroupLayout pLayout = new GroupLayout(panel);
        panel.setLayout(pLayout);

        // 水平方向
        pLayout.setHorizontalGroup(
                pLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                       .addGroup(pLayout.createSequentialGroup()
                                        .addComponent(upButt)
                                        .addComponent(downButt)
                                        .addGap(0, 0, 0))
        );
        // 垂直方向
        pLayout.setVerticalGroup(
                pLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                       .addGroup(pLayout.createSequentialGroup()
                                        .addGroup(pLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                         .addComponent(upButt)
                                                         .addComponent(downButt))
                                        .addGap(0, 0, 0))
        );

        cancelButt = new JButton();
        cancelButt.setSize(125, 25);
        cancelButt.setText("取消");
        cancelButt.addActionListener(this::cancel);


        // dialog设置布局
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        // 水平布局
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                                      .addContainerGap()
                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                      .addGroup(layout.createSequentialGroup()
                                                                      .addComponent(label)
                                                                      .addComponent(searchField))
                                                      .addGroup(layout.createSequentialGroup()
                                                                      .addComponent(checkBox)
                                                                      .addComponent(panel)))
                                      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                      .addComponent(searchButt, 125, 125, 125)
                                                      .addComponent(cancelButt, 125, 125, 125))
                                      .addContainerGap()
                      )
        );
        // 垂直布局
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                                      .addContainerGap()
                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                      .addComponent(label)
                                                      .addComponent(searchField)
                                                      .addComponent(searchButt))
                                      .addGap(18, 18, 18)
                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                      .addComponent(checkBox)
                                                      .addComponent(panel)
                                                      .addComponent(cancelButt))
                                      .addContainerGap())
        );

        // 不可调整大小
        setResizable(false);
        // 设置起始位置：居中
        setLocationRelativeTo(null);
        // 设置标题
        setTitle("查找");

        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //setAlwaysOnTop(true); // 总是置顶，处于整个系统窗口的最上层
        //setModal(true); // 阻塞
    }

    /**
     * 根据文本框内容，设置查询按钮是否可用
     * getText()正常情况下，返回的字符串最小长度为0，只有遇到@{BadLocationException}才会返回null
     *
     * @param e
     *         文档事件
     */
    private void searchEnable(DocumentEvent e) {
        if (searchField.getText() == null || searchField.getText().length() == 0) {
            searchButt.setEnabled(false);
        } else {
            searchButt.setEnabled(true);
        }

    }

    /**
     * 查找下一个
     *
     * @param evt
     *         ActionEvent事件
     */
    private void findNextPerformed(ActionEvent evt) {

        String content = text.getText();
        String matchStr = searchField.getText();

        if (isMatch) {
            content = content.toUpperCase();
            matchStr = matchStr.toUpperCase();
        }

        if (isDown) {
            // 是否是再次查询,getCaretPosition()返回当前光标位置
            if (text.getSelectedText() == null)
                findIndex = content.indexOf(matchStr, text.getCaretPosition() + 1);
            else
                findIndex = content.indexOf(matchStr, text.getCaretPosition() - searchField.getText().length() + 1);
            // 检测查询结果,做出相应的处理
            if (findIndex > -1) {
                // 设置光标位置
                text.setCaretPosition(findIndex);
                text.select(findIndex, findIndex + matchStr.length());
            } else {
                JOptionPane.showMessageDialog(this, "没有找到“" + searchField.getText() + "”");
            }
        } else {
            // 是否是再次查询,getCaretPosition()返回当前光标位置
            if (text.getSelectedText() == null)
                findIndex = content.lastIndexOf(matchStr, text.getCaretPosition() - 1);
            else
                findIndex = content.lastIndexOf(matchStr, text.getCaretPosition() - searchField.getText().length() - 1);
            // 检测查询结果,做出相应的处理
            if (findIndex > -1) {
                // 设置光标位置
                text.setCaretPosition(findIndex);
                text.select(findIndex, findIndex + matchStr.length());
            } else {
                JOptionPane.showMessageDialog(this, "没有找到“" + searchField.getText() + "”");
            }
        }

    }

    /**
     * 设置是否区分大小
     *
     * @param evt
     *         ActionEven
     */
    private void isMatchCase(ActionEvent evt) {
        isMatch = checkBox.isSelected();
    }

    /**
     * 设置查找方向
     *
     * @param evt
     *         ItemEven
     */
    private void searchUpOrDown(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            isDown = evt.getSource() != upButt;
        }
    }

    /**
     * 取消查找，关闭dialog
     *
     * @param evt
     *         事件
     */
    private void cancel(ActionEvent evt) {
        dispose();
    }

    private boolean isDown = true;
    private boolean isMatch = false;
    private int findIndex = 0;


//    public static void main(String args[]) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
//
//        invokeLater(() -> {
//            SearchDialog dialog = new SearchDialog();
//            dialog.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    System.exit(0);
//                }
//            });
//            dialog.setVisible(true);
//        });
//    }

    private JButton searchButt;
    private JButton cancelButt;
    private JCheckBox checkBox;
    private JLabel label;
    private JPanel panel;
    private JRadioButton upButt;
    private JRadioButton downButt;
    private JTextField searchField;
}
