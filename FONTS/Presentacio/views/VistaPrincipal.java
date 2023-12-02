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

    private JButton Sortir = new JButton("Sortir");

    private final JMenuBar menuBar = new JMenuBar();

    public VistaPrincipal () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniButtons();
        add(panelContenidos, BorderLayout.CENTER);
        //inicialitzar la resta


        //assignar listeneres a cada component
        assign_listenerComponents();
    }

    private void iniFrame() {
        setSize(1000, 600);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - 1000) / 2;
        int y = (pantalla.height - 600) / 2;
        setLocation(x, y);
        setResizable(false);
    }

    private void iniButtons() {
        panelContenidos.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelContenidos.add(Info, constraints);

        constraints.gridy = 2;
        panelContenidos.add(GT, constraints);

        constraints.gridy = 3;
        panelContenidos.add(CT, constraints);

        constraints.gridy = 4;
        panelContenidos.add(GD, constraints);

        constraints.gridy = 5;
        panelContenidos.add(CD, constraints);
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
