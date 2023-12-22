package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

/**
 * Aquesta vista és l’encarregada de crear un alfabet. Aquesta està formada per un botó que permet seleccionar l’arxiu
 * que conté les lletres de l’alfabet que es vol crear. Un cop importat l’arxiu, es pot fer click a un altre botó per
 * crear l’alfabet. També s’inclou un botó que permet tornar a la vista anterior.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaAfegirAlfabet extends JFrame{

    /**
     * Text que demana que s’introdueixin les dades.
     */
    private JLabel labelIntro = new JLabel("Introdueix les següents dades:");

    /**
     * Text que demana importar un alfabet
     */
    private JLabel labelImportarAlfabet = new JLabel("Importa l'alfabet");

    /**
     * Botó per importar un arxiu.
     */
    private JButton importarArxiu = new JButton ("Importar arxiu");

    /**
     * Vista del directori home per importar un arxiu.
     */
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /**
     * Botó per tornar enrere
     */
    private JButton Enrere = new JButton("Tornar enrere");

    /**
     * Botó per afegir l’alfabet
     */
    private JButton Afegir = new JButton("Afegir alfabet");

    /**
     * Panell de continguts.
     */
    private JPanel panellContinguts = new JPanel();

    /**
     * Filepath de l’arxiu importat.
     */
    private String filepath;

    /**
     * Constructora de la vista.
     */
    public VistaAfegirAlfabet() {
        setVisible(true);
        iniComponents();
    }

    /**
     * Defineix i afegeix els components i els seus contenidors, les característiques del JFrame i associa els listeners
     * corresponents.
     */
    private void iniComponents() {
        iniFrame();
        iniClose();
        iniEnrere();
        iniInputs();
        assign_listenerComponents();
    }

    /**
     * Inicialitza el marc de la vista.
     */
    private void iniFrame() {
        setSize(1000, 600);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - 1000) / 2;
        int y = (pantalla.height - 600) / 2;
        setLocation(x, y);
        setResizable(false);
        setTitle("Afegir Alfabet");
    }

    /**
     * Inicialitza el botó per sortir del programa.
     */
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

    /**
     * Inicialitza el botó per tornar enrere.
     */
    private void iniEnrere() {
        panellContinguts.setLayout(new FlowLayout());
        panellContinguts.add(Enrere);
        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);

    }

    /**
     * Inicialitza els camps d’entrada.
     */
    private void iniInputs() {
        panellContinguts.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; // Fill the cell horizontally
        constraints.anchor = GridBagConstraints.CENTER; // Center the component within the cell
        constraints.insets = new Insets(10, 10, 5, 10); // Set spacing between buttons

        constraints.gridx = 0;
        constraints.gridy = 1;
        panellContinguts.add(labelIntro, constraints);
        constraints.gridy = 2;
        panellContinguts.add(labelImportarAlfabet, constraints);
        constraints.gridy = 3;
        panellContinguts.add(importarArxiu, constraints);
        constraints.gridy = 4;
        panellContinguts.add(Afegir, constraints);
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
        importarArxiu.addActionListener(e -> {
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

    /**
     * Dirigeix les accions en funció del botó premut.
     * @param e L'esdeveniment que activa aquesta funció
     * @throws Exception
     */
    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaElements("Alfabets");
            setVisible(false);
        }
        else if (importarArxiu.equals(source)) {
            fileChooser.setFileFilter(new FileNameExtensionFilter("PROP", "csv", "prop","txt"));
            fileChooser.setDialogTitle("Selecciona fitxer");
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filepath = selectedFile.getAbsolutePath();
            }
        }
        else if (Afegir.equals(source)) {
            if (filepath == null) {
                ControladorPresentacio.mostraAvis("No has seleccionat cap fitxer");
                return;
            }
            try {
                ControladorPresentacio.afegirAlfabet(filepath);
            } catch (ExcepcionsCreadorTeclat e1) {
                ControladorPresentacio.mostraError(e1.getMessage());
                return;
            }
            ControladorPresentacio.vistaElements("Alfabets");
            setVisible(false);
        }
    }
}
