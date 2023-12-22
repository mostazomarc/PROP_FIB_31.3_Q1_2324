package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static java.lang.Math.max;

/**
 * Aquesta vista és l’encarregada de mostrar la informació del teclat amb el nom indicat a l’atribut de la classe. En
 * aquesta es mostra la disposició del teclat (formada per un conjunt de botons col·locats per n files i m columnes on
 * cadascun dels botons té una lletra de l’alfabet de l’idioma del teclat. També mostra la llista de freqüències que
 * s'ha utilitzat i el layout actual. També inclou un botó per poder eliminar aquest teclat del sistema, un altre per
 * modificar el layout i un altre per tornar enrere.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */

public class VistaTeclat extends JFrame {

    /**
     * És el nom del teclat, per a dur a terme la seva consulta.
     */
    private String nom;

    /**
     *  Botó per tornar enrere.
     */
    private JButton Enrere = new JButton("Tornar enrere");

    /**
     * Panell de continguts.
     */
    private JPanel panellContinguts = new JPanel();

    /**
     * Panell que mostra el teclat.
     */
    private JPanel panelTeclat = new JPanel();

    /**
     * Botó per a modificar el layout del teclat.
     */
    private JButton ModificarLayout = new JButton("Modificar Layout");

    /**
     * Text que demana el número de files del teclat.
     */
    private JLabel labelNF = new JLabel("Nombre files:");

    /**
     * Text que demana el número de columnes del teclat.
     */
    private JLabel labelNC = new JLabel("Nombre columnes:");

    /**
     * Camp de text per introduir el número de files del teclat.
     */
    private JTextField inputNF = new JTextField(10);

    /**
     * Camp de text per introduir el número de columnes del teclat.
     */
    private JTextField inputNC = new JTextField(10);

    /**
     * Botó per modificar el layout del teclat.
     */
    private JButton Modificar = new JButton("Modificar");

    /**
     * Botó per eliminar el teclat.
     */
    private JButton Eliminar = new JButton("Eliminar");

    /**
     * Constructora de la vista.
     */
    public VistaTeclat (String nomTeclat) throws ExcepcionsCreadorTeclat {
        nom = nomTeclat;
        setVisible(true);
        iniComponents();
    }

    /**
     * Defineix i afegeix els components i els seus contenidors, les característiques del JFrame i associa els listeners
     * corresponents.
     */
    private void iniComponents() throws ExcepcionsCreadorTeclat {
        iniFrame();
        iniClose();
        iniEnrere();
        iniTeclat();
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
     * Inicialitza els botons d’eliminar i modificar el layout del teclat
     */
    private void iniButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        JPanel upperButtonsPanel = new JPanel();
        upperButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre labelNom y labelNomLlista


        ModificarLayout.setPreferredSize(new Dimension(350, 30));
        upperButtonsPanel.add(ModificarLayout);

        Eliminar.setPreferredSize(new Dimension(350, 30));
        upperButtonsPanel.add(Eliminar);

        buttonsPanel.add(upperButtonsPanel);

        JPanel lowerButtonsPanel = new JPanel();
        lowerButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        labelNF.setPreferredSize(new Dimension(100, 30));
        labelNF.setVisible(false);
        lowerButtonsPanel.add(labelNF);

        inputNF.setPreferredSize(new Dimension(30, 30));
        inputNF.setVisible(false);
        lowerButtonsPanel.add(inputNF);
        lowerButtonsPanel.add(Box.createRigidArea(new Dimension(20,0)));

        labelNC.setPreferredSize(new Dimension(120, 30));
        labelNC.setVisible(false);
        lowerButtonsPanel.add(labelNC);

        inputNC.setPreferredSize(new Dimension(30, 30));
        inputNC.setVisible(false);
        lowerButtonsPanel.add(inputNC);

        Modificar.setPreferredSize(new Dimension(150, 30));
        Modificar.setVisible(false);
        lowerButtonsPanel.add(Modificar);

        buttonsPanel.add(lowerButtonsPanel);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre labelNom y labelNomLlista

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    /**
     * Inicialtiza el panell que representa el teclat en una matriu de botons.
     * @throws ExcepcionsCreadorTeclat Si el teclat identificat per nom no existeix
     */
    private void iniTeclat() throws ExcepcionsCreadorTeclat {
        char[][] teclat = ControladorPresentacio.consultaTeclat(nom);
        int files = teclat.length;
        int columnes = teclat[0].length;

        JLabel labelNom = new JLabel(nom);
        labelNom.setFont(new Font("Monospaced", Font.BOLD, 16));
        labelNom.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNom.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel labelNomLlista = new JLabel("Llista de freqüències: " + ControladorPresentacio.getNomLListaTeclat(nom));
        labelNomLlista.setFont(new Font("Monospaced", Font.PLAIN, 12));
        labelNomLlista.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNomLlista.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel labelLayout = new JLabel("Layout: " + files + " files x " + columnes + " columnes" );
        labelLayout.setFont(new Font("Monospaced", Font.PLAIN, 12));
        labelLayout.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelLayout.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre labelNom y labelNomLlista
        upperPanel.add(labelNom);
        upperPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre labelNom y labelNomLlista
        upperPanel.add(labelNomLlista);
        upperPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre labelNomLlista y Enrere
        upperPanel.add(labelLayout);
        upperPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre labelNomLlista y Enrere

        JPanel keyboard = new JPanel();
        keyboard.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1d;
        String v;

        for (int row = 0; row < teclat.length; ++row) {
            panelTeclat = new JPanel(new GridBagLayout());

            c.gridy = row;

            for (int col = 0; col < teclat[row].length; ++col) {
                v = String.valueOf(teclat[row][col]);
                if (!v.isBlank()) {
                    JButton b = new JButton(v);
                    b.setPreferredSize(new Dimension(60, 60));
                    panelTeclat.add(b);
                }
            }

            keyboard.add(panelTeclat, c);
        }

        JPanel keyboardPanel = new JPanel(new BorderLayout());
        keyboardPanel.add(new JScrollPane(keyboard), BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(keyboardPanel, BorderLayout.CENTER);

        add(mainPanel);
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
        ModificarLayout.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Modificar.addActionListener(e -> {
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
     * @throws Exception
     */
    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaElements("Teclats");
            setVisible(false);
        }
        else if (ModificarLayout.equals(source)) {
            boolean estatActual = Modificar.isVisible();
            Modificar.setVisible(!estatActual);
            labelNF.setVisible(!estatActual);
            labelNC.setVisible(!estatActual);
            inputNF.setVisible(!estatActual);
            inputNC.setVisible(!estatActual);
        }
        else if(Modificar.equals(source)) {
            try {
                int nf = Integer.parseInt(inputNF.getText());
                int nc = Integer.parseInt(inputNC.getText());
                ControladorPresentacio.modificarLayoutTeclat(nom, nf, nc);
                ControladorPresentacio.vistaTeclat(nom);
                setVisible(false);
            }catch (ExcepcionsCreadorTeclat ex) {
                ControladorPresentacio.mostraError(ex.getMessage());
            }catch (NumberFormatException ex) {
                ControladorPresentacio.mostraError("No és correcte el format del número de files i/o columnes, ha d'introduir un número en els dos camps");
            }
        }
        else if (Eliminar.equals(source)) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Estàs segur de que vols eliminar el teclat?", "Confirmació d'eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                ControladorPresentacio.eliminarTeclat(nom);
                ControladorPresentacio.vistaElements("Teclats");
                setVisible(false);
            }
        }
    }
}
