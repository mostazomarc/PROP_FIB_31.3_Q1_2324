package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
public class VistaTeclat extends JFrame {
    private String nom;
    private JButton Enrere = new JButton("Tornar al menú principal");
    private JPanel panellContinguts = new JPanel();
    private JPanel panelTeclat = new JPanel();
    private JButton ModificarLayout = new JButton("Modificar Layout");
    private JLabel labelNF = new JLabel("Número files:");
    private JLabel labelNC = new JLabel("Número columnes:");
    private JTextField inputNF = new JTextField(20);
    private JTextField inputNC = new JTextField(20);
    private JButton Modificar = new JButton("Modificar");
    private JButton Eliminar = new JButton("Eliminar");

    public VistaTeclat (String nomTeclat) throws ExcepcionsCreadorTeclat {
        nom = nomTeclat;
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() throws ExcepcionsCreadorTeclat {
        iniFrame();
        iniClose();
        iniEnrere();
        iniTeclat();
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
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        ModificarLayout.setPreferredSize(new Dimension(420, 30));
        buttonPanel.add(ModificarLayout);
        Eliminar.setPreferredSize(new Dimension(420, 30));
        buttonPanel.add(Eliminar);

        panellContinguts.add(buttonPanel, constraints);

        Modificar.setVisible(false);
        labelNF.setVisible(false);
        labelNC.setVisible(false);
        inputNF.setVisible(false);
        inputNC.setVisible(false);

        constraints.gridy = 2;
        labelNF.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(labelNF);
        inputNF.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(inputNF);

        panellContinguts.add(buttonPanel, constraints);

        constraints.gridy = 3;
        labelNC.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(labelNC);
        inputNC.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(inputNC);

        panellContinguts.add(buttonPanel, constraints);

        constraints.gridy = 4;
        Modificar.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(Modificar);
        panellContinguts.add(buttonPanel, constraints);
    }

    private void iniTeclat() throws ExcepcionsCreadorTeclat {
        JLabel labelNom = new JLabel(nom);
        labelNom.setFont(new Font("Monospaced", Font.BOLD, 16));
        panellContinguts.add(labelNom);


        char[][] teclat = ControladorPresentacio.consultaTeclat(nom);
        int rows = teclat.length;
        int cols = teclat[0].length;

        panelTeclat = new JPanel(new GridLayout(rows, cols, 3, 3));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton btn = new JButton(String.valueOf(teclat[i][j]));
                panelTeclat.add(btn);
            }
        }
        panellContinguts.add(Box.createVerticalGlue());
        panellContinguts.add(panelTeclat);
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

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaPrincipal();
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
            ControladorPresentacio.eliminarTeclat(nom);
            ControladorPresentacio.vistaElements("teclats");
            setVisible(false);
        }
    }
}
