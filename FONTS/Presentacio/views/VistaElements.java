package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

/**
 * Aquesta vista serveix per crear i llistar verticalment els elements de les classes: Teclats, LlistaFreqüències,
 * Idiomes i Alfabets de manera que es mostren tants botons com elements de la classe hi hagi. Llista i crea els elements
 * d’una determinada classe segons el paràmetre option.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaElements extends JFrame {

    /**
     * Botó per tornar enrere.
     */
    private JButton Enrere = new JButton("Tornar al menú principal");

    /**
     * Botó per crear un nou element del tipus corresponent.
     */
    private JButton Crear;

    /**
     * Panell de continguts.
     */
    private JPanel panellContinguts = new JPanel();

    /**
     * Tipus d’element (teclat, llista, alfabets, idiomes)
     */
    private String option;

    /**
     * Constructora de la vista.
     */
    public VistaElements(String option) {
        this.option = option;
        setVisible(true);
        iniComponents(option);
    }

    /**
     * Defineix i afegeix els components i els seus contenidors, les característiques del JFrame i associa els listeners
     * corresponents.
     */
    private void iniComponents(String option) {
        this.option = option;
        iniFrame();
        iniClose();
        iniEnrere();
        iniContingut();
        assign_listenerComponents();
    }

    /**
     * Inicialitza el marc de la vista.
     */
    private void iniFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setTitle(option);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - 1000) / 2;
        int y = (pantalla.height - 600) / 2;
        setLocation(x, y);
        setResizable(false);
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

        panellContinguts.setLayout(new BoxLayout(panellContinguts, BoxLayout.Y_AXIS));
        panellContinguts.add(Box.createVerticalGlue());
        panellContinguts.add(Box.createHorizontalGlue());

        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);

    }

    /**
     * Inicialitza la llista vertical dels botons que representen un element respectivament i el botó de Crear.
     */
    private void iniContingut() {
        List<String> l = null;
        String el = "";
        switch(option) {
            case "Teclats":
                l = ControladorPresentacio.getNomsTeclats();
                el = "Teclat";
                break;
            case "Llistes":
                l = ControladorPresentacio.getNomLlistesGuardades();
                el = "Llista";
                break;
            case "Idiomes":
                l = ControladorPresentacio.getNomsIdiomes();
                el = "Idioma";
                break;
            case "Alfabets":
                l = ControladorPresentacio.getNomsAlfabets();
                el = "Alfabet";
                break;
            default:
                break;
        }
        panellContinguts.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        Crear = new JButton("Crear " + el);
        panellContinguts.add(Crear, constraints);
        int yPos = 2;

        if(l != null) {
            for (String nom : l) {
                JButton button = new JButton(nom);
                constraints.gridx = 0;
                constraints.gridy = yPos++;
                button.setPreferredSize(new Dimension(200, 50));
                button.addActionListener(e -> {
                    try {
                        switch(option) {
                            case "Teclats":
                                ControladorPresentacio.vistaTeclat(nom);
                                break;
                            case "Llistes":
                                ControladorPresentacio.vistaLlista(nom);
                                break;
                            case "Idiomes":
                                ControladorPresentacio.vistaIdioma(nom);
                                break;
                            case "Alfabets":
                                ControladorPresentacio.vistaAlfabet(nom);
                                break;
                            default:
                                break;
                        }
                        setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                panellContinguts.add(button, constraints);
            }
        }
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
    }

    /**
     * Dirigeix les accions en funció del botó premut.
     * @param e L'esdeveniment que activa aquesta funció
     * @throws Exception
     */
    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaPrincipal();
            setVisible(false);
        }
        else if (Crear.equals(source)) {
            switch(option) {
                case "Teclats":
                    ControladorPresentacio.vistaCrearTeclat();
                    break;
                case "Llistes":
                    ControladorPresentacio.vistaAfegirLlista();
                    break;
                case "Idiomes":
                    ControladorPresentacio.vistaAfegirIdioma();
                    break;
                case "Alfabets":
                    ControladorPresentacio.vistaAfegirAlfabet();
                    break;
                default:
                    break;
            }
            setVisible(false);
        }
    }
}
