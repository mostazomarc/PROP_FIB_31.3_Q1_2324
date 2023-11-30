package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaGestionarDades extends JFrame {

    private JButton Enrere = new JButton("Tornar al menú principal");
    private JPanel panelContenidos = new JPanel();

    public VistaGestionarDades () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniButtons();

        //inicialitzar la resta


        //assignar listeneres a cada component
        assign_listenerComponents();
    }

    private void iniFrame() {
        setBounds(500,300,1000,600);
        setResizable(true);
    }

    private void iniButtons() {

        panelContenidos.setLayout(new BoxLayout(panelContenidos, BoxLayout.Y_AXIS));
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(Box.createHorizontalGlue());

        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);

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
            ControladorPresentacio.iniPresentacio();
            setVisible(false);
        }
    }
}