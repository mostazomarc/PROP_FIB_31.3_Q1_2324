package Presentacio.views;

import Excepcions.LlistaFreqNoExisteix;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Map;

public class VistaLlista extends JFrame {
    String nom;
    private JButton Enrere = new JButton("Tornar al menú principal");
    private JPanel panelContenidos = new JPanel();
    private JTextArea LlistatextArea = new JTextArea(20, 40);
    private JScrollPane scrollPanel = new JScrollPane();
    private JButton ModificarLlista = new JButton("Modificar llista");
    private JButton Eliminar = new JButton("Eliminar llista");

    public VistaLlista (String nomLl) throws LlistaFreqNoExisteix {
        nom = nomLl;
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() throws LlistaFreqNoExisteix {
        iniFrame();
        iniClose();
        iniEnrere();
        iniLlista();
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

    private void iniEnrere() {
        panelContenidos.setLayout(new BoxLayout(panelContenidos, BoxLayout.Y_AXIS));
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(Box.createHorizontalGlue());
        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);
    }

    private void iniButtons() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 1;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        ModificarLlista.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(ModificarLlista);
        Eliminar.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(Eliminar);
        panelContenidos.add(buttonPanel, constraints);
    }


    private void iniLlista() throws LlistaFreqNoExisteix {
        Map<String, Integer> llista = ControladorPresentacio.consultaLlista(nom);
        LlistatextArea.setEditable(false);
        StringBuilder contingut = new StringBuilder();
        for (Map.Entry<String, Integer> entry : llista.entrySet()) {
            contingut.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        LlistatextArea.setText(contingut.toString());
        scrollPanel = new JScrollPane(LlistatextArea);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        panelContenidos.add(scrollPanel, constraints);
        add(panelContenidos, BorderLayout.CENTER);

        scrollPanel.getVerticalScrollBar().setValue(0); // Hace que la barra comience arriba

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
        Enrere.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        ModificarLlista.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Eliminar.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (ModificarLlista.equals(source)) {
            LlistatextArea.setEditable(true);
        }
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaPrincipal();
            setVisible(false);
        }
        else if (Eliminar.equals(source)) {
            ControladorPresentacio.eliminarLlista(nom);
            ControladorPresentacio.vistaElements("llistes");
            setVisible(false);
        }
    }

}

