package Presentacio.views;

import java.util.List;
import java.util.ArrayList;


import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.TeclatJaExisteix;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
    private JPanel panelContenidos = new JPanel();

    public VistaCrearTeclat() {
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
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 0, 10);

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
        inputNomIdioma = new JComboBox<>(idiomes.toArray(new String[0]));
        inputNomIdioma.setSelectedIndex(-1);
        panelContenidos.add(inputNomIdioma, constraints);
        constraints.gridy = 6;
        panelContenidos.add(labelULL, constraints);
        constraints.gridy = 7;
        resposta.add(Si);
        resposta.add(No);
        panelContenidos.add(Si, constraints);
        constraints.gridy = 8;
        panelContenidos.add(No, constraints);
        constraints.gridy = 9;
        labelNomLl.setVisible(false);
        panelContenidos.add(labelNomLl, constraints);
        constraints.gridy = 10;
        inputNomLl.setVisible(false);
        panelContenidos.add(inputNomLl, constraints);
        constraints.gridy = 11;
        panelContenidos.add(labelNF, constraints);
        constraints.gridy = 12;
        panelContenidos.add(inputNF, constraints);
        constraints.gridy = 13;
        panelContenidos.add(labelNC, constraints);
        constraints.gridy = 14;
        panelContenidos.add(inputNC, constraints);
        constraints.gridy = 15;
        panelContenidos.add(labelAlgorisme, constraints);
        constraints.gridy = 16;
        String[] algorismes = {"BranchAndBound", "GeneticAlgorithm"};
        inputAlgorisme= new JComboBox<>(algorismes);
        inputAlgorisme.setSelectedIndex(-1);
        panelContenidos.add(inputAlgorisme, constraints);
        constraints.gridy = 17;
        panelContenidos.add(Crear, constraints);
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

    public void actionPerformed_buttons (ActionEvent e)  throws ExcepcionsCreadorTeclat {
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
                ControladorPresentacio.vistaElements("teclats");
                setVisible(false);
            } else if (inputNomIdioma.equals(source)) {
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
            } else if (Crear.equals(source)) {
                String nomTeclat = inputNomTeclat.getText();
                String nomIdioma = (String) inputNomIdioma.getSelectedItem();
                int nf = Integer.parseInt(inputNF.getText());
                int nc = Integer.parseInt(inputNC.getText());
                String estrategia = (String) inputAlgorisme.getSelectedItem();
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
        }
    }
}
