package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaCrearPerfil extends JFrame {
    private JButton Enrere = new JButton("Tornar enrere");
    private JPanel panellContinguts = new JPanel();
    private JLabel labelNomPerfil = new JLabel("Nom");
    private JTextField inputNomPerfil = new JTextField( 20);
    private JButton CP = new JButton("Crear perfil");


    public VistaCrearPerfil () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniClose();
        iniEnrere();
        iniInput();
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
        setTitle("Crear Perfil");
    }

    private void iniClose() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evita el cierre automático

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Estas segur de que vols sortir?", "Confirmació de tancament",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    ControladorPresentacio.guardaEstat();
                    dispose();
                    System.exit(0);
                }
            }
        });
    }

    private void iniEnrere() {
        panellContinguts.setLayout(new FlowLayout());
        panellContinguts.add(Enrere);
        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);
    }

    private void iniInput() {
        panellContinguts.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; // Fill the cell horizontally
        constraints.anchor = GridBagConstraints.CENTER; // Center the component within the cell
        constraints.insets = new Insets(10, 10, 5, 10); // Set spacing between buttons
        constraints.gridx = 0;
        constraints.gridy = 1;
        panellContinguts.add(labelNomPerfil, constraints);
        constraints.gridy = 2;
        panellContinguts.add(inputNomPerfil, constraints);
        constraints.gridy = 3;
        panellContinguts.add(CP, constraints);
        add(panellContinguts, BorderLayout.CENTER);
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
            try {
                String nomP = inputNomPerfil.getText();
                if (nomP.equals("")) {
                    ControladorPresentacio.mostraAvis("No has introduit cap nom de perfil");
                    return;
                }
                ControladorPresentacio.creaPerfil(nomP);
                ControladorPresentacio.vistaPerfils();
                setVisible(false);
            } catch (ExcepcionsCreadorTeclat ex) {
                ControladorPresentacio.mostraError(ex.getMessage());
            }
        }
    }
}
