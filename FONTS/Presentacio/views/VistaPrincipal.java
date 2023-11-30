package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaPrincipal extends JFrame{
    private JPanel panelContenidos = new JPanel();
    private JButton Info = new JButton("Informació de les funcions");
    private JButton GT = new JButton("Gestionar Teclats");
    private JButton CT = new JButton("Consultar Teclats");
    private JButton GD = new JButton("Gestionar Dades");
    private JButton CD = new JButton("Consultar Dades");

    public VistaPrincipal () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniButtons();

        //inicialitzar la resta


        //assignar listeneres a cada component
        assign_listenerComponents();
    }

    private void iniFrame() {
        setBounds(500,300,1000,600);
        setResizable(false);
    }

    private void iniButtons() {
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
        add(panelContenidos, BorderLayout.CENTER);
    }

    /**
     * Assigna els listeners als components corresponents.
     */
    private void assign_listenerComponents() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //pack();
                revalidate();
            }
        });
        Info.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GT.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        CT.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GD.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        CD.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Info.equals(source)) {
            ControladorPresentacio.vistaInfoFuncions();
            setVisible(false);
        }
        else if (GT.equals(source)) {
            ControladorPresentacio.vistaGestionarTeclats();
            setVisible(false);
        }
        else if (CT.equals(source)) {
            ControladorPresentacio.vistaConsultarTeclats();
            setVisible(false);
        }
        else if (GD.equals(source)) {
            ControladorPresentacio.vistaGestionarDades();
            setVisible(false);
        }
        else if (CD.equals(source)) {
            ControladorPresentacio.vistaConsultarDades();
            setVisible(false);
        }
    }
}
