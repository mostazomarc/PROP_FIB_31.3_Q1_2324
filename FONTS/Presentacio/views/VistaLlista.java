package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LlistaFreqNoExisteix;
import Presentacio.ControladorPresentacio;

import javax.naming.ldap.Control;
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

public class VistaLlista extends JFrame {
    private String nom;
    private JButton Enrere = new JButton("Tornar al menú principal");
    private JPanel panelContenidos = new JPanel();
    private JTextArea LlistatextArea = new JTextArea(20, 40);
    private JScrollPane scrollPanel = new JScrollPane();
    private JButton ModificarLlista = new JButton("Modificar");
    private JButton Eliminar = new JButton("Eliminar");
    private JButton Editar = new JButton("Editar");
    private JButton ImportarArxiu = new JButton("Importar arxiu");
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private JComboBox InputTipusArxiu;
    private JLabel labelArxiu = new JLabel("Selecciona el tipus d'arxiu i importa el fitxer");
    private JButton Modificar = new JButton("Modificar");
    private JButton Guardar = new JButton("Guardar Canvis");
    private String filepath;


    public VistaLlista (String nomLl) throws LlistaFreqNoExisteix {
        nom = nomLl;
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() throws LlistaFreqNoExisteix {
        iniFrame();
        iniClose();
        iniEnrere();
        iniLlista();
        iniButtons();
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
        panelContenidos.setLayout(new BoxLayout(panelContenidos, BoxLayout.Y_AXIS));
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(Box.createHorizontalGlue());
        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);
    }

    private void iniButtons() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 1;
        JPanel buttonPanel = new JPanel();
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
        String[] tipus = {"text", "llista", "Manual"};
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
        panelContenidos.add(buttonPanel, constraints);
    }


    private void iniLlista() throws LlistaFreqNoExisteix {
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

        panelContenidos.add(scrollPanel, constraints);
        add(panelContenidos, BorderLayout.CENTER);

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
            String[] linies = LlistatextArea.getText().split("\\n"); // Dividir el texto en líneas
            for (String linea : linies) {
                String[] parts = linea.split(" ");
                if (parts.length == 2) {
                    String clau = parts[0].toLowerCase();
                    int valor = Integer.parseInt(parts[1]);
                    if (novesEntrades.containsKey(clau)) valor += novesEntrades.get(clau);
                    novesEntrades.put(clau, valor);
                }
            }
            ControladorPresentacio.modificarLlistaPerfil("Manual",filepath,nom,novesEntrades);
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
            ControladorPresentacio.vistaPrincipal();
            setVisible(false);
        }
        else if (Modificar.equals(source)) {
            String tipusarxiu = (String) InputTipusArxiu.getSelectedItem();
            Map<String, Integer> novesEntrades = new HashMap<>();
            ControladorPresentacio.modificarLlistaPerfil(tipusarxiu,nom,filepath,novesEntrades);
            ControladorPresentacio.vistaLlista(nom);
            setVisible(false);
        }
        else if (Eliminar.equals(source)) {
            try {
                ControladorPresentacio.eliminarLlista(nom);
                ControladorPresentacio.vistaElements("llistes");
                setVisible(false);
            } catch (Exception e1) {
                ControladorPresentacio.mostraError(e1.getMessage());
            }
        }
    }

}

