package Presentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class VistaPrincipal {
    private ControladorPresentacio iCtrlPresentacion;
    private JFrame frameVista = new JFrame("Vista Principal");
    private JPanel panelContenidos = new JPanel();
    private JButton Info = new JButton("Informació de les funcions");
    private JButton GT = new JButton("Gestionar Teclats");
    private JButton CT = new JButton("Consultar Teclats");
    private JButton GD = new JButton("Gestionar Dades");
    private JButton CD = new JButton("Consultar Dades");

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
        initializeMenuBar();
    }

    private void initializeFrame() {

        frameVista.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        frameVista.setResizable(false);
        frameVista.setLayout(new BorderLayout());
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.pack();
    }

    private void initializeMenuBar() {
        menuFile.add(menuitemQuit); // Añadir el JMenuItem al JMenu
        menubarVista.add(menuFile); // Añadir el JMenu a la JMenuBar
        frameVista.setJMenuBar(menubarVista); // Añadir la JMenuBar al JFrame

        Info.setMaximumSize(new Dimension(200, Info.getPreferredSize().height));
        GT.setMaximumSize(new Dimension(200, GT.getPreferredSize().height));
        CT.setMaximumSize(new Dimension(200, CT.getPreferredSize().height));
        GD.setMaximumSize(new Dimension(200, GD.getPreferredSize().height));
        CD.setMaximumSize(new Dimension(200, CD.getPreferredSize().height));

        Info.setAlignmentX(Component.CENTER_ALIGNMENT);
        GT.setAlignmentX(Component.CENTER_ALIGNMENT);
        CT.setAlignmentX(Component.CENTER_ALIGNMENT);
        GD.setAlignmentX(Component.CENTER_ALIGNMENT);
        CD.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Configurar el panel para alinear los botones verticalmente
        panelContenidos.setLayout(new BoxLayout(panelContenidos, BoxLayout.Y_AXIS));
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(Box.createHorizontalGlue());

        panelContenidos.add(Box.createRigidArea(new Dimension(0, 10))); // Margen superior
        panelContenidos.add(Info);
        panelContenidos.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        panelContenidos.add(GT);
        panelContenidos.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        panelContenidos.add(CT);
        panelContenidos.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        panelContenidos.add(GD);
        panelContenidos.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        panelContenidos.add(CD);
        panelContenidos.add(Box.createRigidArea(new Dimension(0, 10))); // Margen inferior

        panelContenidos.add(Box.createVerticalGlue());
        frameVista.add(panelContenidos, BorderLayout.CENTER);

    }




    public void hacerVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }
}
