package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.FormatNoValid;
import Excepcions.LlistaFreqNoExisteix;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Aquesta vista és l’encarregada de mostrar la informació de l’idioma amb el nom indicat a l’atribut de la classe.
 * Aquesta està formada per un àrea de text la qual es mostren les totes les paraules i les seves freqüències de la
 * llista de freqüències. També inclou un botó per poder eliminar aquesta llista de freqüències del sistema i un altre
 * per tornar enrere.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaLlista extends JFrame {
    private String nom;
    private JButton Enrere = new JButton("Tornar enrere");
    private JPanel panellContinguts = new JPanel();
    private JTextArea LlistatextArea = new JTextArea(20, 40);
    private JScrollPane scrollPanel = new JScrollPane();
    private JButton ModificarLlista = new JButton("Modificar amb fitxer");
    private JButton Eliminar = new JButton("Eliminar");
    private JButton Editar = new JButton("Editar manualment");
    private JButton ImportarArxiu = new JButton("Importar arxiu");
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private JComboBox InputTipusArxiu;
    private JLabel labelArxiu = new JLabel("Selecciona el tipus d'arxiu i importa el fitxer");
    private JButton Modificar = new JButton("Modificar");
    private JButton Guardar = new JButton("Guardar Canvis");
    private String filepath;

    /**
     * Constructora de la vista.
     */
    public VistaLlista (String nomLl) throws LlistaFreqNoExisteix {
        nom = nomLl;
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
    private void iniComponents() throws LlistaFreqNoExisteix {
        iniFrame();
        iniClose();
        iniEnrere();
        iniLlista();
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

    private void iniButtons() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 1;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        ModificarLlista.setPreferredSize(new Dimension(300, 30));
        buttonPanel.add(ModificarLlista);
        Editar.setPreferredSize(new Dimension(300, 30));
        buttonPanel.add(Editar);
        Eliminar.setPreferredSize(new Dimension(300, 30));
        buttonPanel.add(Eliminar);
        labelArxiu.setPreferredSize(new Dimension(300, 30));
        labelArxiu.setVisible(false);
        buttonPanel.add(labelArxiu);
        String[] tipus = {"text", "llista"};
        InputTipusArxiu = new JComboBox<>(tipus);
        InputTipusArxiu.setPreferredSize(new Dimension(100, 30));
        InputTipusArxiu.setSelectedIndex(-1);
        InputTipusArxiu.setVisible(false);
        buttonPanel.add(InputTipusArxiu);
        ImportarArxiu.setPreferredSize(new Dimension(200, 30));
        ImportarArxiu.setVisible(false);
        buttonPanel.add(ImportarArxiu);
        Modificar.setPreferredSize(new Dimension(200, 30));
        Modificar.setVisible(false);
        buttonPanel.add(Modificar);
        Guardar.setPreferredSize(new Dimension(200, 30));
        Guardar.setVisible(false);
        buttonPanel.add(Guardar);
        panellContinguts.add(buttonPanel, constraints);
    }


    private void iniLlista() throws LlistaFreqNoExisteix {
        JLabel labelNom = new JLabel(nom);
        labelNom.setFont(new Font("Monospaced", Font.BOLD, 16));
        labelNom.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelNomIdioma = new JLabel("Idioma: " + ControladorPresentacio.getNomIdiomaLlista(nom));
        labelNomIdioma.setFont(new Font("Monospaced", Font.PLAIN, 12));
        labelNomIdioma.setAlignmentX(Component.CENTER_ALIGNMENT);

        panellContinguts.setLayout(new BoxLayout(panellContinguts, BoxLayout.Y_AXIS));
        panellContinguts.add(labelNom);
        panellContinguts.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre labelNom y labelNomLlista
        panellContinguts.add(labelNomIdioma);
        panellContinguts.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre labelNom y labelNomLlista

        Map<String, Integer> llista = ControladorPresentacio.consultaLlista(nom);
        LlistatextArea.setEditable(false);
        StringBuilder contingut = new StringBuilder();
        for (Map.Entry<String, Integer> entry : llista.entrySet()) {
            contingut.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        LlistatextArea.setText(contingut.toString());
        scrollPanel = new JScrollPane(LlistatextArea);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        panellContinguts.add(scrollPanel, constraints);
        add(panellContinguts, BorderLayout.CENTER);

        scrollPanel.getVerticalScrollBar().setValue(0); // Hace que la barra comience arriba

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
        ModificarLlista.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        ImportarArxiu.addActionListener(e -> {
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
        Editar.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Guardar.addActionListener(e -> {
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
        if (ModificarLlista.equals(source)) {
            boolean estatActual = ImportarArxiu.isVisible();
            ImportarArxiu.setVisible(!estatActual);
            InputTipusArxiu.setVisible(!estatActual);
            labelArxiu.setVisible(!estatActual);
            Modificar.setVisible(!estatActual);
            Guardar.setVisible(false);
            LlistatextArea.setEditable(false);
        }
        else if (Editar.equals(source)) {
            boolean estatActual = Guardar.isVisible();
            Guardar.setVisible(!estatActual);
            LlistatextArea.setEditable(!estatActual);
            ImportarArxiu.setVisible(false);
            InputTipusArxiu.setVisible(false);
            labelArxiu.setVisible(false);
            Modificar.setVisible(false);
        }
        else if (Guardar.equals(source)) {
            Map<String, Integer> novesEntrades = new HashMap<>();
            try {
                String[] linies = LlistatextArea.getText().split("\\n"); // Dividir el texto en líneas
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
                if (novesEntrades.isEmpty()) {
                    ControladorPresentacio.mostraAvis("No s'ha introduit cap entrada");
                    return;
                }
                ControladorPresentacio.modificarLlistaPerfil("Manual", filepath, nom, novesEntrades);
            } catch (ExcepcionsCreadorTeclat e1) {
                ControladorPresentacio.mostraError(e1.getMessage());
            } catch (NumberFormatException e2) {
                ControladorPresentacio.mostraError("El format de les freqüències no és correcte");
            }
            ControladorPresentacio.vistaLlista(nom);
            setVisible(false);
        }
        else if(ImportarArxiu.equals(source)) {
            fileChooser.setFileFilter(new FileNameExtensionFilter("PROP", "csv", "prop","txt"));
            fileChooser.setDialogTitle("Selecciona fitxer");
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filepath = selectedFile.getAbsolutePath();
            }
        }
        else if (Enrere.equals(source)) {
            ControladorPresentacio.vistaElements("Llistes");
            setVisible(false);
        }
        else if (Modificar.equals(source)) {
            String tipusarxiu = (String) InputTipusArxiu.getSelectedItem();
            if (tipusarxiu == null) {
                ControladorPresentacio.mostraAvis("No has seleccionat cap tipus d'arxiu");
                return;
            }
            if (filepath == null) {
                ControladorPresentacio.mostraAvis("No has seleccionat cap fitxer");
                return;
            }
            Map<String, Integer> novesEntrades = new HashMap<>();
            try {
                ControladorPresentacio.modificarLlistaPerfil(tipusarxiu, filepath, nom, novesEntrades);
            } catch (ExcepcionsCreadorTeclat e1) {
                ControladorPresentacio.mostraError(e1.getMessage());
            }
            ControladorPresentacio.vistaLlista(nom);
            setVisible(false);
        }
        else if (Eliminar.equals(source)) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Estàs segur de que vols eliminar aquesta llista?", "Confirmació d'eliminació",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                try {
                    ControladorPresentacio.eliminarLlista(nom);
                    ControladorPresentacio.vistaElements("Llistes");
                    setVisible(false);
                } catch (Exception e1) {
                    ControladorPresentacio.mostraError(e1.getMessage());
                }
            }
        }
    }
}

