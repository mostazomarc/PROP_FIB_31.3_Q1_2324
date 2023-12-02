package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
public class VistaTeclat extends JFrame {
    private JButton Enrere = new JButton("Tornar al menú principal");
    private JPanel panelContenidos = new JPanel();

    public VistaTeclat (char[][] teclat) {
        setVisible(true);
        iniComponents(teclat);
    }

    private void iniComponents(char[][] teclat) {
        iniFrame();
        iniButtons();
        iniTeclat(teclat);

        //inicialitzar la resta


        //assignar listeneres a cada component
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

    private void iniButtons() {

        panelContenidos.setLayout(new BoxLayout(panelContenidos, BoxLayout.Y_AXIS));
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(Box.createHorizontalGlue());

        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);

        add(panelContenidos, BorderLayout.CENTER);
    }

    private void iniTeclat(char[][] teclat) {
        int rows = teclat.length;
        int cols = teclat[0].length;

        JPanel panelTeclat = new JPanel(new GridLayout(rows, cols, 3, 3)); // Grid layout con separación de 5 pixels

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton btn = new JButton(String.valueOf(teclat[i][j]));
                panelTeclat.add(btn);
            }
        }

        // Agrega el panelTeclat al panelContenidos
        panelContenidos.add(Box.createVerticalGlue()); // Esto añade un espacio en blanco
        panelContenidos.add(panelTeclat);
        panelContenidos.add(Box.createVerticalGlue()); // Esto añade otro espacio en blanco

        // Agrega el panelContenidos al JFrame
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
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.vistaPrincipal();
            setVisible(false);
        }
    }

}
