package view;

import com.bulenkov.darcula.DarculaLaf;
import controller.ButtonController;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;

public class Window {


    private static Window mInstance;

    private ButtonController mButtonController;
    private JFrame frame;
    private JTextField txf_FilePath;
    private JButton btn_ImportData;
    private JLabel txt_SelectText;
    private JButton btn_Find;
    private JProgressBar progressBar;


    public static Window getInstance() {
        if (mInstance == null) {
            mInstance = new Window();
        }
        return mInstance;
    }

    /**
     * Create the application.
     */
    public Window() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.LookAndFeel();
        mButtonController= ButtonController.getInstance();

        frame = new JFrame();
        frame.setBounds(100, 100, 440, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        txt_SelectText = new JLabel("Select the file for import to the DB");
        txt_SelectText.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txt_SelectText.setBounds(10, 11, 300, 20);
        frame.getContentPane().add(txt_SelectText);

        txf_FilePath = new JTextField();
        txf_FilePath.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txf_FilePath.setBounds(10, 42, 300, 20);
        frame.getContentPane().add(txf_FilePath);
        txf_FilePath.setColumns(10);

        btn_Find = new JButton("Find");
        btn_Find.setFont(new Font("Rockwell", Font.PLAIN, 12));
        btn_Find.setActionCommand(ButtonController.ACTION_FIND);
        btn_Find.addActionListener(mButtonController);
        btn_Find.setBounds(323, 41, 90, 23);
        frame.getContentPane().add(btn_Find);

        btn_ImportData = new JButton("Import data");
        btn_ImportData.setFont(new Font("Rockwell", Font.PLAIN, 14));
        btn_ImportData.setActionCommand(ButtonController.ACTION_IMPORT);
        btn_ImportData.addActionListener(mButtonController);
        btn_ImportData.setBounds(136, 75, 150, 40);
        btn_ImportData.setEnabled(false);
        frame.getContentPane().add(btn_ImportData);

        progressBar = new JProgressBar();
        progressBar.setFont(new Font("Rockwell", Font.PLAIN, 14));
        progressBar.setValue(50);
        progressBar.setStringPainted(true);
        progressBar.setBounds(10, 126, 400, 30);
        progressBar.setVisible(false);
        frame.getContentPane().add(progressBar);
    }


    private void LookAndFeel() {
        try {
            // Set System L&F
            BasicLookAndFeel darcula = new DarculaLaf();
            UIManager.setLookAndFeel(darcula);
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getTxf_FilePath() {
        return txf_FilePath;
    }

    public void setTxf_FilePath(JTextField txf_FilePath) {
        this.txf_FilePath = txf_FilePath;
    }

    public void setTextPath(String path) {
        this.txf_FilePath.setText(path);
        this.txf_FilePath.repaint();
        this.txf_FilePath.validate();
    }

    public JButton getBtn_ImportData() {
        return btn_ImportData;
    }

    public void setBtn_ImportData(JButton btn_ImportData) {
        this.btn_ImportData = btn_ImportData;
    }

    public JLabel getTxt_SelectText() {
        return txt_SelectText;
    }

    public void setTxt_SelectText(JLabel txt_SelectText) {
        this.txt_SelectText = txt_SelectText;
    }

    public JButton getBtn_Find() {
        return btn_Find;
    }

    public void setBtn_Find(JButton btn_Find) {
        this.btn_Find = btn_Find;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
