package Presentacio.views;

import java.io.File;
import java.util.HashMap;
import java.util.List;


import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.FormatNoValid;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Map;

/**
 * Aquesta vista és l’encarregada de crear una llista de freqüències. Aquesta està formada per un desplegable que permet
 * seleccionar l’idioma de la llista de freqüències. També s’ha de seleccionar amb 3 radiobuttons si és de tipus “importar
 * llista”, “importar text” o “manualment”. En cas de seleccionar manualment, s’obre un quadre de text per escriure la
 * llista de les paraules i freqüències. A més a més, s’indica un camp clarament per introduir el nom de la llista de
 * freqüències que es vol crear. Altrament, apareix un botó per importar l'arxiu. Finalment, es pot fer click a un altre
 * botó per crear la llista de freqüències. També s’inclou un botó que permet tornar a la vista anterior.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaAfegirLlista extends JFrame {

    private JLabel labelIntro = new JLabel("Introdueix les següents dades:");
    private JLabel labelTipusInput = new JLabel("Com vols afegir la llista?");
    private ButtonGroup tipusInput = new ButtonGroup();
    private JRadioButton rtext = new JRadioButton("Importar text");
    private JRadioButton rllista = new JRadioButton("Importar llista");
    private JRadioButton rmanual = new JRadioButton("Introduir manualment");
    private JLabel labelNomLlista = new JLabel("Nom de la llista");
    private JTextField inputNomLlista = new JTextField(20);
    private JLabel labelNomIdioma = new JLabel("Selecciona l'idioma");
    private JComboBox inputNomIdioma;
    private JButton importarArxiu = new JButton("Importar arxiu");
    private JTextArea llistaManual = new JTextArea();
    JScrollPane scrollPane = new JScrollPane();
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private JButton Enrere = new JButton("Tornar enrere");
    private JButton Afegir = new JButton("Afegir llista");
    private JPanel panellContinguts = new JPanel();
    private String filepath;
    private String tipus = " ";

    /**
     * Constructora de la vista.
     */
    public VistaAfegirLlista() {
        setVisible(true);
        iniComponents();
    }

    /**
     * Retorna ture si la paraula és un número
     * @param paraula La paraula a comprovar
     * @return True si la paraula és un número, false altrament
     */
    private static boolean esNumero(String paraula) {
        try {
            Double.parseDouble(paraula);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
        setTitle("Afegir Llista");
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
        List<String> idiomes = ControladorPresentacio.getNomsIdiomes();
        inputNomIdioma = new JComboBox<>(idiomes.toArray(new String[0]));
        inputNomIdioma.setSelectedIndex(-1);
        panellContinguts.add(inputNomIdioma, constraints);
        constraints.gridy = 4;
        tipusInput.add(rllista);
        tipusInput.add(rtext);
        tipusInput.add(rmanual);
        panellContinguts.add(labelTipusInput, constraints);
        constraints.gridy = 5;
        panellContinguts.add(rllista, constraints);
        constraints.gridy = 6;
        panellContinguts.add(rtext, constraints);
        constraints.gridy = 7;
        panellContinguts.add(rmanual, constraints);
        constraints.gridy = 8;
        importarArxiu.setVisible(false);
        panellContinguts.add(importarArxiu, constraints);
        constraints.gridy = 9;
        constraints.weighty = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER; // Opcional: hace que el componente se extienda a lo ancho
        constraints.fill = GridBagConstraints.BOTH; // Opcional: hace que el componente se extienda en ambas direcciones
        scrollPane = new JScrollPane(llistaManual);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(false);
        panellContinguts.add(scrollPane, constraints);
        constraints.weighty = 0.0;
        constraints.gridy = 10;
        labelNomLlista.setVisible(false);
        panellContinguts.add(labelNomLlista, constraints);
        constraints.gridy = 11;
        inputNomLlista.setVisible(false);
        panellContinguts.add(inputNomLlista, constraints);
        constraints.gridy = 12;
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
            ControladorPresentacio.vistaElements("Llistes");
            setVisible(false);
        }
        if (rtext.isSelected() || rllista.isSelected()) {
            scrollPane.setVisible(false);
            labelNomLlista.setVisible(false);
            inputNomLlista.setVisible(false);
            importarArxiu.setVisible(true);
            if (rtext.isSelected()) tipus = "text";
            else tipus = "llista";
        }
        else if (rmanual.isSelected()) {
            tipus = "Manual";
            importarArxiu.setVisible(false);
            scrollPane.setVisible(true);
            labelNomLlista.setVisible(true);
            inputNomLlista.setVisible(true);
        }
        else {
            importarArxiu.setVisible(false);
            scrollPane.setVisible(false);
            labelNomLlista.setVisible(false);
            inputNomLlista.setVisible(false);
        }

        if (importarArxiu.equals(source)) {
            fileChooser.setFileFilter(new FileNameExtensionFilter("PROP", "csv", "prop","txt"));
            fileChooser.setDialogTitle("Selecciona fitxer");
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filepath = selectedFile.getAbsolutePath();
            }
        }
        else if (Afegir.equals(source)) {
            String nomIdioma = (String) inputNomIdioma.getSelectedItem();
            //comprova que s'ha seleccionat un idioma
            if (nomIdioma == null) {
                ControladorPresentacio.mostraAvis("No s'ha seleccionat cap idioma");
                return;
            }
            try {
                if (tipus != " ") {
                    Map<String, Integer> novesEntrades = new HashMap<>();
                    if (tipus == "llista" || tipus == "text") {
                        //comprova si s'ha seleccionat un arxiu
                        if (filepath == null) {
                            ControladorPresentacio.mostraAvis("No s'ha seleccionat cap arxiu");
                            return;
                        }
                        ControladorPresentacio.novaLlistaPerfil(tipus, filepath, nomIdioma, novesEntrades);
                    } else if (tipus == "Manual") {
                        String nom = inputNomLlista.getText();

                        // comprova que s'ha introduit el nom
                        if (nom.equals("")) {
                            ControladorPresentacio.mostraAvis("No s'ha introduit cap nom");
                            return;
                        }
                        String[] linies = llistaManual.getText().split("\\n"); // Dividir el texto en líneas
                        for (String linea : linies) {
                            String[] parts = linea.split(" ");
                            if (parts.length == 2) {
                                String clau = parts[0].toLowerCase();
                                if (esNumero(clau)) throw new FormatNoValid("Paraula +  frequència");
                                int valor = Integer.parseInt(parts[1]);
                                if (novesEntrades.containsKey(clau)) valor += novesEntrades.get(clau);
                                novesEntrades.put(clau, valor);
                            }
                        }

                        //comprova si n'hi ha entrada manual
                        if (novesEntrades.isEmpty()) {
                            ControladorPresentacio.mostraAvis("No s'ha introduit cap entrada");
                            return;
                        }
                        ControladorPresentacio.novaLlistaPerfil(tipus, nom, nomIdioma, novesEntrades);
                    }
                    ControladorPresentacio.vistaElements("Llistes");
                    setVisible(false);
                } else ControladorPresentacio.mostraAvis("No s'ha seleccionat cap tipus d'entrada");
            } catch (ExcepcionsCreadorTeclat ex) {
                ControladorPresentacio.mostraError(ex.getMessage());
            } catch (NumberFormatException ex) {
                ControladorPresentacio.mostraError("El format de les freqüències no és correcte");
            }
        }
    }
}


