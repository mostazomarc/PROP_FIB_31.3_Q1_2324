package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
public class VistaTeclat extends JFrame {
    private String nom;
    private JButton Enrere = new JButton("Tornar al menú principal");
    private JPanel panelContenidos = new JPanel();
    private JButton ModificarLayout = new JButton("Modificar Layout teclat");
    private JButton Eliminar = new JButton("Eliminar teclat");

    public VistaTeclat (char[][] teclat, String nomTeclat) {
        nom = nomTeclat;
        setVisible(true);
        iniComponents(teclat);
    }

    private void iniComponents(char[][] teclat) {
        iniFrame();
        iniEnrere();
        iniTeclat(teclat);
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
        ModificarLayout.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(ModificarLayout);
        Eliminar.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(Eliminar);
        panelContenidos.add(buttonPanel, constraints);
    }


    private void iniTeclat(char[][] teclat) {
        int rows = teclat.length;
        int cols = teclat[0].length;

        JPanel panelTeclat = new JPanel(new GridLayout(rows, cols, 3, 3));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton btn = new JButton(String.valueOf(teclat[i][j]));
                panelTeclat.add(btn);
            }
        }
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(panelTeclat);
        panelContenidos.add(Box.createVerticalGlue());
        add(panelContenidos, BorderLayout.CENTER);
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
            ControladorPresentacio.vistaModificarLayoutTeclat();
            setVisible(false);
        }
        else if (Enrere.equals(source)) {
            ControladorPresentacio.eliminarTeclat(nom);
            setVisible(false);
        }
    }

}
