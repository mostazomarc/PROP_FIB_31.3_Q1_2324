package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaPrincipal {
    private ControladorPresentacio iCtrlPresentacion;
    private JFrame frameVista = new JFrame("Vista Principal");
    private JPanel panelContenidos = new JPanel();
    private JButton Info = new JButton("Informació de les funcions");
    private JButton GT = new JButton("Gestionar Teclats");
    private JButton CT = new JButton("Consultar Teclats");
    private JButton GD = new JButton("Gestionar Dades");
    private JButton CD = new JButton("Consultar Dades");
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

        //inicialitzar la resta


        //assignar listeneres a cada component
        assign_listenerComponents();
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

    /**
     * Assigna els listeners als components corresponents.
     */
    private void assign_listenerComponents() {

        // Listener pel Frame
        frameVista.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frameVista.pack();
                frameVista.revalidate();
            }
        });

        GT.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (GT.equals(source)) {
            GestionarTeclatsVista gestionarTeclatsVista = new GestionarTeclatsVista();
            JFrame frameGestionarTeclats = gestionarTeclatsVista.getFrame();
            frameGestionarTeclats.setVisible(true);
            frameVista.setVisible(false);
        }
    }

    public void setVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }
}
