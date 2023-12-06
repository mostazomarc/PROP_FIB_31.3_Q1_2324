package Presentacio.views;

import java.util.List;
import java.util.ArrayList;


import Excepcions.LayoutNoValid;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaCrearTeclatIdioma extends JFrame {

    private JLabel labelIntro = new JLabel("Introdueix les següents dades:");
    private JLabel labelNomTeclat = new JLabel("Nom del teclat");
    private JTextField inputNomTeclat = new JTextField(20);
    private JLabel labelNomIdioma = new JLabel("Idioma");
    //private String[] idiomes = ControladorPresentacio.getNomsIdiomes().toArray(new String[0]);
    private JComboBox inputNomIdioma;
    private String[] freqs = new String[]{"Selecciona llista freqüències"};
    private JLabel labelNF = new JLabel("Nombre de files del layout");
    private JTextField inputNF = new JTextField(20);
    private JLabel labelNC = new JLabel("Nombre de columnes del layout");
    private JTextField inputNC = new JTextField(20);

    private JLabel labelAlgorisme = new JLabel("Algorisme");
    private JComboBox inputAlgorisme;
    private JButton Enrere = new JButton("Tornar enrere");
    private JButton Crear = new JButton("Crear teclat");
    private JPanel panelContenidos = new JPanel();

    public VistaCrearTeclatIdioma() {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        panelContenidos.setLayout(new FlowLayout());
        iniEnrere();
        iniInputs();
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

    private void iniInputs() {
        panelContenidos.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; // Fill the cell horizontally
        constraints.anchor = GridBagConstraints.CENTER; // Center the component within the cell
        constraints.insets = new Insets(10, 10, 5, 10); // Set spacing between buttons

        // Add the buttons to the grid layout

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelContenidos.add(labelIntro, constraints);
        constraints.gridy = 2;
        panelContenidos.add(labelNomTeclat, constraints);
        constraints.gridy = 3;
        panelContenidos.add(inputNomTeclat, constraints);
        constraints.gridy = 4;
        panelContenidos.add(labelNomIdioma, constraints);
        constraints.gridy = 5;
        List<String> idiomes = ControladorPresentacio.getNomsIdiomes();
        idiomes.add(0, "Selecciona idioma");
        inputNomIdioma = new JComboBox<>(idiomes.toArray(new String[0]));
        panelContenidos.add(inputNomIdioma, constraints);
        constraints.gridy = 6;
        panelContenidos.add(labelNF, constraints);
        constraints.gridy = 7;
        panelContenidos.add(inputNF, constraints);
        constraints.gridy = 8;
        panelContenidos.add(labelNC, constraints);
        constraints.gridy = 9;
        panelContenidos.add(inputNC, constraints);
        constraints.gridy = 10;
        panelContenidos.add(labelAlgorisme, constraints);
        constraints.gridy = 11;
        String[] algorismes = {"Selecciona algorisme", "BranchAndBound", "GeneticAlgorithm"};
        inputAlgorisme= new JComboBox<>(algorismes);
        panelContenidos.add(inputAlgorisme, constraints);
        constraints.gridy = 12;
        panelContenidos.add(Crear, constraints);

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
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaGestionarTeclats();
            setVisible(false);
        }

        else if (Crear.equals(source)) {
            // Extract user input from text fields
            String nomTeclat = inputNomTeclat.getText();

            String nomIdioma = (String) inputNomIdioma.getSelectedItem();
            int nf = Integer.parseInt(inputNF.getText());
            int nc = Integer.parseInt(inputNC.getText());
            String estrategia = (String) inputAlgorisme.getSelectedItem();
            ControladorPresentacio.crearTeclatLlistaIdioma(nomTeclat,nomIdioma,nf,nc,estrategia);
            char[][] teclat = ControladorPresentacio.consultaTeclat(nomTeclat);
            ControladorPresentacio.vistaTeclat(teclat);
            setVisible(false);
        }
    }
}
