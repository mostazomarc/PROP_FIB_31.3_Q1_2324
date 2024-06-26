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
import java.util.List;

/**
 * Aquesta vista és l’encarregada de crear un idioma. S’indica un camp clarament per introduir el nom de l’idioma que
 * es vol crear. També mitjançant un botó es permet importar l’arxiu que conté la llista de freqüències predeterminada
 * de l’idioma i seleccionar amb 2 radiobuttons si és de tipus “Llista” o “Text”. A més a més s’inclou un desplegable
 * per poder triar l’alfabet de l’idioma. Finalment, es pot fer click a un altre botó per crear l’idioma. També s’inclou
 * un botó que permet tornar a la vista anterior.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaAfegirIdioma extends JFrame{

    /**
     * Text que demana que s’introdueixin les dades.
     */
    private JLabel labelIntro = new JLabel("Introdueix les següents dades:");

    /**
     * Text que demana el nom de l’idioma.
     */
    private JLabel labelNomIdioma = new JLabel("Nom de l'idioma:");

    /**
     * Camp de text per introduir l’idioma.
     */
    private JTextField inputNomIdioma = new JTextField(20);

    /**
     * Text que demana que s’importi la llista de freqüències predeterminada de l’idioma.
     */
    private JLabel labelImportarLlista = new JLabel("Importa la llista de freqüències predeterminada de l'idioma");

    /**
     * Text que pregunta de quin tipus és l’arxiu que s’importarà.
     */
    private JLabel labelTipusArxiu = new JLabel("De quin tipus és l'arxiu ?");

    /**
     * Conjunt de botons d’opció.
     */
    private ButtonGroup tipusInput = new ButtonGroup();

    /**
     * Botó d’opció text.
     */
    private JRadioButton rtext = new JRadioButton("Text");

    /**
     * Botó d’opció llista.
     */
    private JRadioButton rllista = new JRadioButton("Llista");

    /**
     * Botó per importar l’arxiu.
     */
    private JButton importarArxiu = new JButton ("Importar arxiu");

    /**
     * Vista del directori home per importar un arxiu.
     */
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /**
     * Text que demana l’alfabet de l’idioma.
     */
    private JLabel labelNomAlfabet = new JLabel("Selecciona l'alfabet corresponent");

    /**
     * Selector per a seleccionar l’alfabet.
     */
    private JComboBox inputNomAlfabet = new JComboBox();

    /**
     * Botó per tornar enrere.
     */
    private JButton Enrere = new JButton("Tornar enrere");

    /**
     * Botó per afegir l’idioma.
     */
    private JButton Afegir = new JButton("Afegir idioma");

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
    public VistaAfegirIdioma() {
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
        setTitle("Afegir Idioma");
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
        panellContinguts.add(labelNomIdioma, constraints);
        constraints.gridy = 3;
        panellContinguts.add(inputNomIdioma, constraints);
        constraints.gridy = 4;
        panellContinguts.add(labelImportarLlista, constraints);
        constraints.gridy = 5;
        panellContinguts.add(labelTipusArxiu, constraints);
        constraints.gridy = 6;
        tipusInput.add(rllista);
        tipusInput.add(rtext);
        panellContinguts.add(rllista, constraints);
        constraints.gridy = 7;
        panellContinguts.add(rtext, constraints);
        constraints.gridy = 8;
        panellContinguts.add(importarArxiu, constraints);
        constraints.gridy = 9;
        panellContinguts.add(labelNomAlfabet, constraints);
        constraints.gridy = 10;
        List<String> alfabets = ControladorPresentacio.getNomsAlfabets();
        inputNomAlfabet = new JComboBox<>(alfabets.toArray(new String[0]));
        inputNomAlfabet.setSelectedIndex(-1);
        panellContinguts.add(inputNomAlfabet, constraints);
        constraints.gridy = 11;
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
     * @throws Exception Si hi ha algun problema
     */
    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaElements("Idiomes");
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
            JRadioButton selectedButton = null;
            if (rllista.isSelected()) {
                selectedButton = rllista;
            } else if (rtext.isSelected()) {
                selectedButton = rtext;
            }
            if (selectedButton == null) {
                ControladorPresentacio.mostraAvis("Selecciona el tipus d'arxiu");
                return;
            }
            String tipus = selectedButton.getText();
            String nomI = inputNomIdioma.getText();
            if (nomI.equals("")) {
                ControladorPresentacio.mostraAvis("No has introduit cap nom d'idioma");
                return;
            }
            String nomA = (String) inputNomAlfabet.getSelectedItem();
            if (nomA == null) {
                ControladorPresentacio.mostraAvis("Selecciona un alfabet");
                return;
            }
            if (filepath == null) {
                ControladorPresentacio.mostraAvis("Importa un arxiu");
                return;
            }
            try {
                ControladorPresentacio.afegirIdioma(nomI, nomA, tipus, filepath);
            } catch (ExcepcionsCreadorTeclat e1) {
                ControladorPresentacio.mostraError(e1.getMessage());
                return;
            }
            ControladorPresentacio.vistaElements("Idiomes");
            setVisible(false);
        }
    }
}
