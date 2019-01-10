package controller;

import model.SQL.Query;
import model.tables.Lodging;
import view.Window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {

    private static ButtonController mInstance;
    private static Query mQuery = Query.getInstance();

    public static final String ACTION_FIND = "find";
    public static final String ACTION_IMPORT = "import";

    public static ButtonController getInstance() {
        if (mInstance == null) {
            mInstance = new ButtonController();
        }
        return mInstance;
    }


    private ButtonController() {

    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_FIND:
                JFileChooser j = new JFileChooser();
                j.setAcceptAllFileFilterUsed(false);
                j.addChoosableFileFilter(new FileNameExtensionFilter(".xml", "xml"));
                int result = j.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    Window.getInstance().setTextPath(j.getSelectedFile().getPath());
                    Window.getInstance().getBtn_ImportData().setEnabled(true);
                }

                break;
            case ACTION_IMPORT:
                Window.getInstance().getProgressBar().setValue(0);
                Window.getInstance().getProgressBar().setVisible(true);
                Window.getInstance().getBtn_ImportData().setEnabled(false);

                new Thread(() -> {
                    XML_Reader mReader = new XML_Reader(Window.getInstance().getTxf_FilePath().getText());
                    mReader.readAll();

                    Window.getInstance().getProgressBar().setMaximum(mReader.getLodgings().size());

                    int prog = 1;
                    for (Lodging lodging : mReader.getLodgings()) {
                        mQuery.insertLodging(lodging);
                        Window.getInstance().getProgressBar().setValue(prog++);
                    }
                    JOptionPane.showMessageDialog(null, mReader.getLodgings().size() + " lodging(s) have been imported and " + mQuery.getErrorNumber() + " row(s) have been skipped.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Window.getInstance().getBtn_ImportData().setEnabled(true);
                    Window.getInstance().getProgressBar().setVisible(false);
                    mQuery.setErrorNumber(0);
                    //JOptionPane.showMessageDialog(null, "No se han introducido " + mQuery.getErrorNumber()+".", "Skipped rows", JOptionPane.ERROR_MESSAGE);
                }).start();

                break;

        }
    }
}
