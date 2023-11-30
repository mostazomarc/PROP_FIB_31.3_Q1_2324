package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaGestionarTeclats extends JFrame {

    private JButton Enrere = new JButton("Tornar al menú principal");
    private JButton CLP = new JButton("Crear teclat amb llista pròpia");
    private JButton CLNP = new JButton("Crear teclat sense llista pròpia");
    private JButton ML = new JButton("Modificar Layout teclat");
    private JButton E = new JButton("Eliminar teclat");
    private JPanel panelContenidos = new JPanel();

    public VistaGestionarTeclats () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniEnrere();
        iniButtons();
        add(panelContenidos, BorderLayout.CENTER);

        //assignar listeneres a cada component
        assign_listenerComponents();
    }

    private void iniFrame() {
        setBounds(500,300,1000,600);
        setResizable(false);
    }

    private void iniEnrere() {

        panelContenidos.setLayout(new BoxLayout(panelContenidos, BoxLayout.Y_AXIS));
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(Box.createHorizontalGlue());

        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);

        //add(panelContenidos, BorderLayout.CENTER);
    }

    private void iniButtons() {
        panelContenidos.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; // Fill the cell horizontally
        constraints.anchor = GridBagConstraints.CENTER; // Center the component within the cell
        constraints.insets = new Insets(10, 10, 10, 10); // Set spacing between buttons

        // Add the buttons to the grid layout

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelContenidos.add(CLP, constraints);

        constraints.gridy = 2;
        panelContenidos.add(CLNP, constraints);

        constraints.gridy = 3;
        panelContenidos.add(ML, constraints);

        constraints.gridy = 4;
        panelContenidos.add(E, constraints);
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

        CLP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actionPerformed_buttons(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Enrere.equals(source)) {
            ControladorPresentacio.iniPresentacio();
            setVisible(false);
        }
        else if (CLP.equals(source)) {
            ControladorPresentacio.vistaCrearTeclatLlistaPropia();
            setVisible(false);
        }
    }
}
