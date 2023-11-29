package Presentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class VistaPrincipal {
    private ControladorPresentacio iCtrlPresentacion;
    private JFrame frameVista = new JFrame("Vista Principal");
    private JPanel panelContenidos = new JPanel();
    private JButton buttonLlamadaDominio = new JButton("Llamada Dominio");
    private JLabel labelPanelInformacion1 = new JLabel("Panel Informacion 1");
    private JComboBox comboboxInformacion1 = new JComboBox();
    private JTextArea textareaInformacion1 = new JTextArea(15,25);
    private JTextField textfieldInformacion2 = new JTextField();
    private JSlider sliderInformacion2 = new JSlider();
    private JMenuBar menubarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuitemQuit = new JMenuItem("Quit");

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public VistaPrincipal (ControladorPresentacio pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        initializeFrame();
    }

    private void initializeFrame() {
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameVista.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        frameVista.setResizable(false);
        frameVista.setLayout(new BorderLayout());
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.pack();
    }


    public void hacerVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }
}
