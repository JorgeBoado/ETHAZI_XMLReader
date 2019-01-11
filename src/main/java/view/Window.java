package view;

import com.bulenkov.darcula.DarculaLaf;
import controller.ButtonController;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;
/**
 * Clase de interfaz grafica.
 *
 * @author Jorge
 * @version 1.2
 * @since 2018-12-29
 */
public class Window {


    private static Window mInstance;

    private ButtonController mButtonController;
    private JFrame frame;
    private JTextField txf_FilePath;
    private JButton btn_ImportData;
    private JLabel txt_SelectText;
    private JButton btn_Find;
    private JProgressBar progressBar;

    /**
     * En caso que no exista una instancia la crea y la devuelve.
     * En caso de existirla la devuelve.
     *
     * @return Instancia de Window.
     */
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
        mButtonController = ButtonController.getInstance();

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

    /**
     * Activa un tema oscuro, para ello hay que importar el paquete de dracula-1.0.0.jar
     * situado en resources y que no funciona la dependencia de Maven.
     */
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

    public JTextField getTxf_FilePath() {
        return txf_FilePath;
    }

    /**
     * Coloca el texto de la ruta del archivo en el textBox FilePath.
     * @param path Ruta del archivo a mostrar.
     */
    public void setTextPath(String path) {
        this.txf_FilePath.setText(path);
    }

    public JButton getBtn_ImportData() {
        return btn_ImportData;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

}
