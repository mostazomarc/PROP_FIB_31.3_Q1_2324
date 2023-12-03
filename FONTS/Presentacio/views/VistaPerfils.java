package Presentacio.views;

import Presentacio.ControladorPresentacio;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class VistaPerfils extends JFrame {
    private JButton Enrere = new JButton("Tornar al menú principal");

    private JButton AP = new JButton("+");
    private JPanel panelContenidos = new JPanel();

    public VistaPerfils () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniEnrere();
        iniButtonsPerfils();

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

    private void iniEnrere() {

        panelContenidos.setLayout(new BoxLayout(panelContenidos, BoxLayout.Y_AXIS));
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(Box.createHorizontalGlue());

        Enrere.setBounds(0, 0, 200, 20);
        add(Enrere);

        add(panelContenidos, BorderLayout.CENTER);
    }



    private void iniButtonsPerfils() {
        List<String> perfiles = ControladorPresentacio.getAllPerfils();

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for (String perfil : perfiles) {
            JButton button = new JButton(perfil);
            button.setPreferredSize(new Dimension(100, 100));
            button.setFont(new Font("Dank", Font.PLAIN, 16));

            panelBotones.add(button, constraints);

            constraints.gridx++; // Incrementar el índice X para el próximo botón
            button.addActionListener(e -> {
                try {
                    ControladorPresentacio.vistaPrincipal();
                    ControladorPresentacio.iniciaInstancia(perfil);
                    ControladorPresentacio.carregarDades();
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        AP.setPreferredSize(new Dimension(100, 100));
        AP.setFont(new Font("Dank", Font.PLAIN, 16));
        panelBotones.add(AP, constraints);


        panelContenidos.setLayout(new BorderLayout());
        panelContenidos.add(panelBotones, BorderLayout.CENTER);

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
        AP.addActionListener(e -> {
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
        if (AP.equals(source)) {

            ControladorPresentacio.vistaCrearPerfil();
            setVisible(false);
        }
    }
}
