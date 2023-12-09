package Presentacio.views;

import java.io.File;
import java.util.HashMap;
import java.util.List;


import Presentacio.ControladorPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Map;

public class VistaAfegirLlista extends JFrame {

    private JLabel labelIntro = new JLabel("Introdueix les següents dades:");
    private JLabel labelTipusInput = new JLabel("Com vols afegir la llista?");
    private ButtonGroup tipusInput = new ButtonGroup();
    private JRadioButton rtext = new JRadioButton("Importar text");
    private JRadioButton rllista = new JRadioButton("Importar llista");
    private JRadioButton rmanual = new JRadioButton("Entrada manual");
    private JLabel labelNomIdioma = new JLabel("Selecciona l'idioma");
    private JComboBox inputNomIdioma;
    private JButton importarArxiu = new JButton("Importar arxiu");
    private JButton Enrere = new JButton("Tornar enrere");
    private JButton Afegir = new JButton("Afegir llista");
    private JPanel panelContenidos = new JPanel();
    private String filepath;

    public VistaAfegirLlista() {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniClose();
        iniEnrere();
        iniInputs();
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
        panelContenidos.setLayout(new FlowLayout());
        panelContenidos.add(Enrere);
        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);

    }

    private void iniInputs() {
        panelContenidos.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; // Fill the cell horizontally
        constraints.anchor = GridBagConstraints.CENTER; // Center the component within the cell
        constraints.insets = new Insets(10, 10, 5, 10); // Set spacing between buttons

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelContenidos.add(labelIntro, constraints);
        constraints.gridy = 2;
        panelContenidos.add(labelNomIdioma, constraints);
        constraints.gridy = 3;
        List<String> idiomes = ControladorPresentacio.getNomsIdiomes();
        inputNomIdioma = new JComboBox<>(idiomes.toArray(new String[0]));
        panelContenidos.add(inputNomIdioma, constraints);
        constraints.gridy = 4;
        panelContenidos.add(labelTipusInput, constraints);
        constraints.gridy = 5;
        tipusInput.add(rllista);
        tipusInput.add(rtext);
        tipusInput.add(rmanual);
        panelContenidos.add(rllista, constraints);
        constraints.gridy = 6;
        panelContenidos.add(rtext, constraints);
        constraints.gridy = 7;
        panelContenidos.add(rmanual, constraints);
        constraints.gridy = 8;
        importarArxiu.setEnabled(false);
        importarArxiu.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setFileFilter(new FileNameExtensionFilter("PROP", "csv", "prop","txt"));
            fileChooser.setDialogTitle("Selecciona fitxer");
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filepath = selectedFile.getAbsolutePath();
            }
        });
        panelContenidos.add(importarArxiu, constraints);
        constraints.gridy = 9;
        panelContenidos.add(Afegir, constraints);

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
        Enrere.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        inputNomIdioma.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        rllista.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        rtext.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        rmanual.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Afegir.addActionListener(e -> {
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
            ControladorPresentacio.vistaTeclats();
            setVisible(false);
        }
        if (rtext.isSelected() || rllista.isSelected()) {
            importarArxiu.setEnabled(true);
        }
        if (!rtext.isSelected() && !rllista.isSelected()) {
            importarArxiu.setEnabled(false);
        }
        else if (Afegir.equals(source)) {
            String nomIdioma = (String) inputNomIdioma.getSelectedItem();
            JRadioButton selectedButton = null;

            if (rllista.isSelected()) {
                selectedButton = rllista;
            } else if (rtext.isSelected()) {
                selectedButton = rtext;
            } else if (rmanual.isSelected()) {
                selectedButton = rmanual;
            }

            if (selectedButton != null) {
                String tipus = selectedButton.getText();
                Map<String, Integer> novesEntrades = new HashMap<>();
                if (selectedButton == rllista || selectedButton == rtext) {
                    ControladorPresentacio.novaLlistaPerfil(tipus,filepath,nomIdioma,novesEntrades);
                }
                else {
                    //ControladorPresentacio.novaLlistaPerfil();
                }
                ControladorPresentacio.vistaLlistes();
                setVisible(false);
            }
        }
    }
}


