package Presentacio.views;

import java.util.List;
import java.util.ArrayList;


import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

/**
 * Aquesta vista és l’encarregada de crear un teclat. S’indica un camp clarament per introduir el nom del teclat que es
 * vol crear. També hi ha un desplegable que permet seleccionar l’idioma del teclat. També s’ha de seleccionar amb 2
 * radiobuttons es vol crear el teclat amb una llista de freqüències pròpia o no. En cas de seleccionar Sí, apareix un
 * desplegable que permet seleccionar la llista de freqüències. A continuació hi ha dos camps per introduir el nombre
 * de files i columnes del teclat. També hi ha un desplegable que permet seleccionar quins dels dos algorismes utilitzar
 * per a la creació del teclat. Finalment, es pot fer click a un altre botó per crear el teclat. També s’inclou un botó
 * que permet tornar a la vista anterior.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaCrearTeclat extends JFrame {

    private JLabel labelIntro = new JLabel("Introdueix les següents dades:");
    private JLabel labelNomTeclat = new JLabel("Nom del teclat");
    private JTextField inputNomTeclat = new JTextField(20);
    private JLabel labelNomIdioma = new JLabel("Selecciona l'idioma");
    private JComboBox inputNomIdioma;
    private JLabel labelULL = new JLabel("Vol seleccionar una llista de freqüències pròpia?");
    private ButtonGroup resposta = new ButtonGroup();
    private JRadioButton Si = new JRadioButton("Sí");
    private JRadioButton No = new JRadioButton("No");
    private JLabel labelNomLl = new JLabel("Selecciona la llista de freqüències");
    private JComboBox<String> inputNomLl = new JComboBox<>();
    private JLabel labelNF = new JLabel("Nombre de files del layout");
    private JTextField inputNF = new JTextField(20);
    private JLabel labelNC = new JLabel("Nombre de columnes del layout");
    private JTextField inputNC = new JTextField(20);
    private JLabel labelAlgorisme = new JLabel("Algorisme");
    private JComboBox inputAlgorisme;
    private JButton Enrere = new JButton("Tornar enrere");
    private JButton Crear = new JButton("Crear teclat");
    private JPanel panellContinguts = new JPanel();

    /**
     * Constructora de la vista.
     */
    public VistaCrearTeclat() {
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
        setTitle("Crear Teclat");
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
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 0, 10);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panellContinguts.add(labelIntro, constraints);
        constraints.gridy = 2;
        panellContinguts.add(labelNomTeclat, constraints);
        constraints.gridy = 3;
        panellContinguts.add(inputNomTeclat, constraints);
        constraints.gridy = 4;
        panellContinguts.add(labelNomIdioma, constraints);
        constraints.gridy = 5;
        List<String> idiomes = ControladorPresentacio.getNomsIdiomes();
        inputNomIdioma = new JComboBox<>(idiomes.toArray(new String[0]));
        inputNomIdioma.setSelectedIndex(-1);
        panellContinguts.add(inputNomIdioma, constraints);
        constraints.gridy = 6;
        panellContinguts.add(labelULL, constraints);
        constraints.gridy = 7;
        resposta.add(Si);
        resposta.add(No);
        panellContinguts.add(Si, constraints);
        constraints.gridy = 8;
        panellContinguts.add(No, constraints);
        constraints.gridy = 9;
        labelNomLl.setVisible(false);
        panellContinguts.add(labelNomLl, constraints);
        constraints.gridy = 10;
        inputNomLl.setVisible(false);
        panellContinguts.add(inputNomLl, constraints);
        constraints.gridy = 11;
        panellContinguts.add(labelNF, constraints);
        constraints.gridy = 12;
        panellContinguts.add(inputNF, constraints);
        constraints.gridy = 13;
        panellContinguts.add(labelNC, constraints);
        constraints.gridy = 14;
        panellContinguts.add(inputNC, constraints);
        constraints.gridy = 15;
        panellContinguts.add(labelAlgorisme, constraints);
        constraints.gridy = 16;
        String[] algorismes = {"BranchAndBound", "GeneticAlgorithm"};
        inputAlgorisme= new JComboBox<>(algorismes);
        inputAlgorisme.setSelectedIndex(-1);
        panellContinguts.add(inputAlgorisme, constraints);
        constraints.gridy = 17;
        panellContinguts.add(Crear, constraints);
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

        Crear.addActionListener(e -> {
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
        Si.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        No.addActionListener(e -> {
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
    public void actionPerformed_buttons (ActionEvent e)  throws Exception {
        try {
            Object source = e.getSource();
            if (Si.isSelected()) {
                labelNomLl.setVisible(true);
                inputNomLl.setVisible(true);
            } else {
                labelNomLl.setVisible(false);
                inputNomLl.setVisible(false);
            }

            if (Enrere.equals(source)) {
                ControladorPresentacio.vistaElements("Teclats");
                setVisible(false);
            }
            else if (inputNomIdioma.equals(source)) {
                String nomIdioma = (String) inputNomIdioma.getSelectedItem();
                String[] llistes = ControladorPresentacio.getNomLlistesGuardades().toArray(new String[0]);

                List<String> tempList = new ArrayList<>();

                for (String nomLlista : llistes) {
                    if (ControladorPresentacio.getNomIdiomaLlista(nomLlista).equals(nomIdioma)) {
                        tempList.add(nomLlista);
                    }
                }

                String[] freqs = tempList.toArray(new String[0]);
                inputNomLl.setModel(new DefaultComboBoxModel<>(freqs));
                inputNomLl.setSelectedIndex(-1);
            }
            else if (Crear.equals(source)) {
                String nomTeclat = inputNomTeclat.getText();
                if (Objects.equals(nomTeclat, "")) {
                    ControladorPresentacio.mostraAvis("No s'ha introduït cap nom");
                    return;
                }
                String nomIdioma = (String) inputNomIdioma.getSelectedItem();
                if (Objects.equals(nomIdioma, null)) {
                    ControladorPresentacio.mostraAvis("No s'ha seleccionat cap idioma");
                    return;
                }
                int nf = Integer.parseInt(inputNF.getText());
                int nc = Integer.parseInt(inputNC.getText());
                String estrategia = (String) inputAlgorisme.getSelectedItem();
                if (Objects.equals(estrategia, null)) {
                    ControladorPresentacio.mostraAvis("No s'ha seleccionat cap estrategia");
                    return;
                }
                if (Si.isSelected()) {
                    String nomLl = (String) inputNomLl.getSelectedItem();
                    ControladorPresentacio.crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLl, nf, nc, estrategia);
                } else {
                    ControladorPresentacio.crearTeclatLlistaIdioma(nomTeclat, nomIdioma, nf, nc, estrategia);
                }
                ControladorPresentacio.vistaTeclat(nomTeclat);
                setVisible(false);
            }
        }
        catch (ExcepcionsCreadorTeclat ex) {
            ControladorPresentacio.mostraError(ex.getMessage());
        } catch (NumberFormatException ex) {
            ControladorPresentacio.mostraError("No és correcte el format del número de files i/o columnes, ha d'introduir un número en els dos camps");
        }
    }
}
