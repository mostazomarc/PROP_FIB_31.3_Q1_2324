package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaEliminarTeclat extends JFrame{
    private JButton Enrere = new JButton("Tornar enrere");
    private JPanel panelContenidos = new JPanel();

    public VistaEliminarTeclat () {
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            ControladorPresentacio.vistaGestionarTeclats();
            setVisible(false);
        }
    }
}
