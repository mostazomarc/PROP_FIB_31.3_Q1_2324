package Presentacio.views;

import Presentacio.ControladorPresentacio;

import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class VistaPerfils extends JFrame {
    private JButton AP = new JButton("+");
    private JPanel panelContenidos = new JPanel();

    private JLabel titol = new JLabel("<< BENVINGUT A TECLATOR >>");
    private JLabel subtitol = new JLabel("Inicia sessió prement el perfil");

    public VistaPerfils () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniClose();
        iniButtonsPerfils();
        assignListenersComponents();
    }

    private void iniFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - 1000) / 2;
        int y = (pantalla.height - 600) / 2;
        setLocation(x, y);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(30, 0, 20, 0);

        titol.setFont(new Font("Monospaced", Font.ITALIC, 24));
        titol.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto horizontalmente
        panel.add(titol, constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);

        subtitol.setFont(new Font("Monospaced", Font.ITALIC, 16));
        subtitol.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto horizontalmente
        panel.add(subtitol, constraints);

        add(panel, BorderLayout.NORTH);
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
                    ControladorPresentacio.iniciaInstancia(perfil);
                    ControladorPresentacio.vistaPrincipal();
                    //ControladorPresentacio.carregarDades(); //quan carregues un perfil ja carrega les dades d'aquest
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
    private void assignListenersComponents() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //pack();
                revalidate();
            }
        });
        AP.addActionListener(e -> {
            try {
                actionPerformedButtons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void actionPerformedButtons(ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (AP.equals(source)) {

            ControladorPresentacio.vistaCrearPerfil();
            setVisible(false);
        }
    }
}
