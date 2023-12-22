package Presentacio.views;

import Excepcions.AlfabetNoExisteix;
import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Aquesta vista és l’encarregada de mostrar la informació de l’alfabet amb el nom indicat a l’atribut de la classe.
 * Aquesta està formada per una àrea de text la qual es mostra el nom de l’alfabet, el nombre de lletres de l’alfabet i
 * aquestes. També inclou un botó per poder eliminar aquest alfabet del sistema i un altre per tornar a la vista anterior.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaAlfabet extends JFrame {

    /**
     * És el nom de l’alfabet, per a dur a terme la seva consulta.
     */
    private String nom;

    /**
     * Botó per tornar enrere.
     */
    private JButton Enrere = new JButton("Tornar enrere");

    /**
     *  Panell de continguts.
     */
    private JPanel panellContinguts = new JPanel();

    /**
     * Àrea de text que mostra l’alfabet.
     */
    private JTextArea AlfabettextArea = new JTextArea();

    /**
     * Panell que conté l’àrea de text de l’alfabet.
     */
    private JScrollPane scrollPanel = new JScrollPane();

    /**
     * Botó per eliminar l’alfabet.
     */
    private JButton Eliminar = new JButton("Eliminar");

    /**
     * Constructora de la vista.
     * @param nomA El nom de l'alfabet
     * @throws AlfabetNoExisteix Si no existeix l'alfabet identificat per nom
     */
    public VistaAlfabet (String nomA) throws AlfabetNoExisteix {
        nom = nomA;
        setVisible(true);
        iniComponents();
    }

    /**
     * Defineix i afegeix els components i els seus contenidors, les característiques del JFrame i associa els listeners
     * corresponents.
     * @throws AlfabetNoExisteix Si no existeix l'alfabet identificat per nom
     */
    private void iniComponents() throws AlfabetNoExisteix {
        iniFrame();
        iniClose();
        iniEnrere();
        iniAlfabet();
        iniButtons();
        assign_listenerComponents();
    }

    /**
     * Inicialitza el marc de la vista.
     */
    private void iniFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
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
     * Inicialitza el botó d’eliminar alfabet.
     */
    private void iniButtons() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 1;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        Eliminar.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(Eliminar);
        panellContinguts.add(buttonPanel, constraints);
    }

    /**
     * Inicialitza l’àrea de text que mostra l’alfabet
     * @throws AlfabetNoExisteix Si l'alfabet identificat per nom no existeix
     */
    private void iniAlfabet() throws AlfabetNoExisteix {
        String info = ControladorPresentacio.consultaAlfabet(nom);
        AlfabettextArea.setText(info);
        AlfabettextArea.setEditable(false);
        AlfabettextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        AlfabettextArea.setWrapStyleWord(true);
        AlfabettextArea.setLineWrap(true);
        AlfabettextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        AlfabettextArea.setAlignmentY(Component.CENTER_ALIGNMENT);

        scrollPanel.setViewportView(AlfabettextArea);
        panellContinguts.add(Box.createVerticalGlue());
        panellContinguts.add(scrollPanel);
        panellContinguts.add(Box.createVerticalGlue());
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
        Eliminar.addActionListener(e -> {
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
     * @throws Exception Si l'alfabet identificat per nom no existeix
     */
    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaElements("Alfabets");
            setVisible(false);
        }
        else if (Eliminar.equals(source)) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Estàs segur de que vols eliminar aquest alfabet?", "Confirmació d'eliminació",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                try {
                    ControladorPresentacio.eliminarAlfabet(nom);
                    ControladorPresentacio.vistaElements("Alfabets");
                    setVisible(false);
                } catch (ExcepcionsCreadorTeclat e1) {
                    ControladorPresentacio.mostraError(e1.getMessage());
                }
            }
        }
    }
}
