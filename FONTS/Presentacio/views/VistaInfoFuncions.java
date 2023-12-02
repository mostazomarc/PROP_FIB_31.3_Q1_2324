package Presentacio.views;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaInfoFuncions extends JFrame {

    private JButton Enrere = new JButton("Tornar al menú principal");
    JTextArea Info = new JTextArea();
    private JPanel panelContenidos = new JPanel();

    public VistaInfoFuncions () {
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() {
        iniFrame();
        iniEnrere();
        iniInfo();
        add(panelContenidos);

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
        setResizable(false);;
    }

    private void iniEnrere() {

        Enrere.setBounds(0, 0, 0 ,0);
        add(Enrere);
        panelContenidos.add(Enrere);
    }

    private void iniInfo() {
        Info.setBounds(0,100,1000,100);
        Info.setEditable(false);
        Info.setLineWrap(true);
        Info.setWrapStyleWord(true);

        // Informació sobre les funcions del programa
        String info = "0. Info de les funcions ---> Explicació de les funcions del programa\n" +
                "1. Gestionar teclats ---> Entra en el menú gestionar teclats que permet gestionar els teclats\n" +
                "2. Consultar teclats ---> Entra en el menú consultar teclats que permet consultar els teclats\n" +
                "3. Gestionar dades ---> Entra en el menú gestionar dades que permet gestionar les dades del sistema i el perfil\n" +
                "4. Consultar dades ---> Entra en el menú consultar dades que permet consultar les dades del sistema i el perfil\n";

        Info.setText(info);
        Info.setPreferredSize(Info.getPreferredSize());
        panelContenidos.add(Info);
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