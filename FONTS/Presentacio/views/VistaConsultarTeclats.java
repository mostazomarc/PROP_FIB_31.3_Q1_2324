package Presentacio.views;

import Presentacio.ControladorPresentacio;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaConsultarTeclats extends JFrame {

    private JButton Enrere = new JButton("Tornar al menú principal");

    private JLabel Titol = new JLabel("Aquests són els teclats que tens actualment");
    private JPanel panelContenidos = new JPanel();

    public VistaConsultarTeclats () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniButtons();
        iniTitol();
        iniTeclats();
        Titol.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); // Puedes ajustar los valores según sea necesario
        add(Titol, BorderLayout.PAGE_START); // Agregar el label en la parte superior
        add(panelContenidos, BorderLayout.CENTER);


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

    }

    private void iniTitol() {
        // Configuración del label Titol
        Titol.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void iniTeclats() {
        List<String> l = ControladorPresentacio.getNomsTeclats();

        panelContenidos.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        int yPos = 1;
        for (String nomT : l) {
            JButton button = new JButton(nomT);
            constraints.gridx = 0;
            constraints.gridy = yPos++;
            button.setPreferredSize(new Dimension(200, 50));

            button.addActionListener(e -> {
                try {
                    ControladorPresentacio.vistaTeclat(ControladorPresentacio.consultaTeclat(nomT));
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            panelContenidos.add(button, constraints);
        }
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
