package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaPrincipal extends JFrame{
    private JPanel panellContinguts = new JPanel();
    private JButton Teclats = new JButton("Teclats");
    private JButton Llistes = new JButton("Llistes de freqüències");
    private JButton Idiomes = new JButton("Idiomes");
    private JButton Alfabets = new JButton("Alfabets");
    private JButton CanviarPerfil = new JButton("Canviar Perfil");
    private JButton EliminarPerfil = new JButton("Eliminar Perfil");
    private JLabel labelSessio;
    private JButton Sortir = new JButton("Sortir");
    public VistaPrincipal () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniClose();
        iniButtons();
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

    private void iniButtons() {
        panellContinguts.setLayout(new BoxLayout(panellContinguts, BoxLayout.Y_AXIS));
        panellContinguts.add(Box.createVerticalGlue());
        panellContinguts.add(Box.createHorizontalGlue());
        labelSessio = new JLabel("Sessió inciada com a: " + ControladorPresentacio.getPerfilActual());
        labelSessio.setBounds(5, 0, 200, 20);
        add(labelSessio);

        panellContinguts.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;

        constraints.gridy = 1;
        panellContinguts.add(Teclats, constraints);

        constraints.gridy = 2;
        panellContinguts.add(Llistes, constraints);

        constraints.gridy = 3;
        panellContinguts.add(Idiomes, constraints);

        constraints.gridy = 4;
        panellContinguts.add(Alfabets, constraints);

        constraints.gridy = 5;
        panellContinguts.add(CanviarPerfil, constraints);

        constraints.gridy = 6;
        panellContinguts.add(EliminarPerfil, constraints);

        constraints.gridy = 7;
        panellContinguts.add(Sortir, constraints);

        add(panellContinguts, BorderLayout.CENTER);
    }

    /**
     * Assigna els listeners als components corresponents.
     */
    private void assign_listenerComponents() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
            }
        });
        Teclats.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Llistes.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Idiomes.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Alfabets.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        CanviarPerfil.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        EliminarPerfil.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Sortir.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Teclats.equals(source)) {
            ControladorPresentacio.vistaElements("teclats");
            setVisible(false);
        }
        else if (Llistes.equals(source)) {
            ControladorPresentacio.vistaElements("llistes");
            setVisible(false);
        }
        else if (Idiomes.equals(source)) {
            ControladorPresentacio.vistaElements("idiomes");
            setVisible(false);
        }
        else if (Alfabets.equals(source)) {
            ControladorPresentacio.vistaElements("alfabets");
            setVisible(false);
        }
        else if (CanviarPerfil.equals(source)) {
            ControladorPresentacio.vistaPerfils();
            setVisible(false);
        }
        else if (EliminarPerfil.equals(source)) {
            try {
                ControladorPresentacio.eliminaPerfil();
            } catch (ExcepcionsCreadorTeclat e1) {
                ControladorPresentacio.mostraError(e1.getMessage());
            }
            ControladorPresentacio.vistaPerfils();
            setVisible(false);
        }
        else if (Sortir.equals(source)) {
            ControladorPresentacio.guardaEstat();
            dispose();
            System.exit(0);
        }
    }
}
