package Presentacio.views;

import Presentacio.ControladorPresentacio;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaCrearPerfil extends JFrame {
    private JButton Enrere = new JButton("Tornar enrere");
    private JPanel panelContenidos = new JPanel();
    private JLabel labelNomPerfil = new JLabel("Nom");
    private JTextField inputNomPerfil = new JTextField( 20);
    private JButton CP = new JButton("Crear perfil");


    public VistaCrearPerfil () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        panelContenidos.setLayout(new FlowLayout());
        iniEnrere();
        iniInput();
        add(panelContenidos, BorderLayout.CENTER);


        //inicialitzar la resta


        //assignar listeneres a cada component
        assign_listenerComponents();
    }

    private void iniFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - 1000) / 2;
        int y = (pantalla.height - 600) / 2;
        setLocation(x, y);
        setResizable(false);
    }

    private void iniEnrere() {

        panelContenidos.add(Enrere);
        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);
    }

    private void iniInput() {
        panelContenidos.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; // Fill the cell horizontally
        constraints.anchor = GridBagConstraints.CENTER; // Center the component within the cell
        constraints.insets = new Insets(10, 10, 5, 10); // Set spacing between buttons

        // Add the buttons to the grid layout

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelContenidos.add(labelNomPerfil, constraints);
        constraints.gridy = 2;
        panelContenidos.add(inputNomPerfil, constraints);
        constraints.gridy = 3;
        panelContenidos.add(CP, constraints);
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
        Enrere.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        CP.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaPerfils();
            setVisible(false);
        }
        else if (CP.equals(source)) {
            String nomP = inputNomPerfil.getText();
            ControladorPresentacio.iniciaInstancia(nomP);
            ControladorPresentacio.vistaPerfils();
            setVisible(false);
        }
    }
}
