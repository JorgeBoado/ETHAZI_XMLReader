package controller;

import model.SQL.Query;
import model.tables.Lodging;
import view.Window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase gestiona todas las funciones de los botones de las ventanas.
 *
 * @author Jorge
 * @version 1.2.1
 * @since 2018-12-29
 */

public class ButtonController implements ActionListener {

    private static ButtonController mInstance;
    private static Query mQuery = Query.getInstance();

    public static final String ACTION_FIND = "find";
    public static final String ACTION_IMPORT = "import";

    /**
     * Al llamarlo comprueba si existe una instancia de la clase.
     * En caso de que exista devuelve la existente, en caso contrario la crea y la devuelve.
     * @return Devuelve un objeto ButtonController.
     */
    public static ButtonController getInstance() {
        if (mInstance == null) {
            mInstance = new ButtonController();
        }
        return mInstance;
    }

    /**
     * Es el constructor de la clase
     */
    private ButtonController() {

    }
    /**
     *Trata todos los eventos de los botones cuando son pulsados.
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            /**
             * El ACTION_FIND se ejecuta al pulsar el boton de busqueda
             * en la interfaz situado en la interfaz, lanza una ventana de seleccion para elegir el archivo de tipo XML.
             * De ese modo se pueden evitar los diferentes fallos que podria causar poner le link de los documentos.
             */
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
            /**
             * El ACTION_IMPORT se ejecuta al pulsar el boton de importar datos
             * situado en la interfaz, hace visible la barra de progreso y desabilita el
             * boton de busqueda.
             * Genera un nuevo hilo para leer el documento XML seleccionado anteriormente.
             * A medida que va importando los datos del XML va incrementando la barra de progreso.
             * Al acabar muestra una ventana con la cantidad de alojamientos importados y la cantidad
             * que ha omitido por falta de campos obligatorios y reinicia el contador de omitidos.
             */
            case ACTION_IMPORT:
                Window.getInstance().getProgressBar().setValue(0);
                Window.getInstance().getProgressBar().setVisible(true);
                Window.getInstance().getBtn_ImportData().setEnabled(false);
                Window.getInstance().getBtn_Find().setEnabled(false);

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
                    Window.getInstance().getBtn_Find().setEnabled(true);
                    mQuery.setErrorNumber(0);
                    //JOptionPane.showMessageDialog(null, "No se han introducido " + mQuery.getErrorNumber()+".", "Skipped rows", JOptionPane.ERROR_MESSAGE);
                }).start();

                break;

        }
    }
}
