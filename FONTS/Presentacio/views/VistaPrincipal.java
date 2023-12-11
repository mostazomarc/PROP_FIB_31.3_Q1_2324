package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaPrincipal extends JFrame{
    private JPanel panelContenidos = new JPanel();
    private JButton Info = new JButton("Informació de les funcions");
    private JButton Teclats = new JButton("Teclats");
    private JButton Llistes = new JButton("Llistes de freqüències");
    private JButton Idiomes = new JButton("Idiomes");
    private JButton Alfabets = new JButton("Alfabets");
    private JButton CanviarPerfil = new JButton("Canviar Perfil");
    private final JMenuBar menuBar = new JMenuBar();

    public VistaPrincipal () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniClose();
        iniButtons();
        add(panelContenidos, BorderLayout.CENTER);
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
        panelContenidos.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelContenidos.add(Info, constraints);

        constraints.gridy = 2;
        panelContenidos.add(Teclats, constraints);

        constraints.gridy = 3;
        panelContenidos.add(Llistes, constraints);

        constraints.gridy = 4;
        panelContenidos.add(Idiomes, constraints);

        constraints.gridy = 5;
        panelContenidos.add(Alfabets, constraints);

        constraints.gridy = 6;
        panelContenidos.add(CanviarPerfil, constraints);
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
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Teclats.equals(source)) {
            ControladorPresentacio.vistaTeclats();
            setVisible(false);
        }
        else if (Llistes.equals(source)) {
            ControladorPresentacio.vistaLlistes();
            setVisible(false);
        }
        else if (Idiomes.equals(source)) {
            ControladorPresentacio.vistaIdiomes();
            setVisible(false);
        }
        else if (Alfabets.equals(source)) {
            ControladorPresentacio.vistaAlfabets();
            setVisible(false);
        }
        else if (CanviarPerfil.equals(source)) {
            ControladorPresentacio.vistaPerfils();
            setVisible(false);
        }
    }
}
