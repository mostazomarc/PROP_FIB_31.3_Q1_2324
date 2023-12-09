package Presentacio.views;

import Excepcions.IdiomaNoExisteix;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaIdioma extends JFrame {
    String nom;
    private JButton Enrere = new JButton("Tornar al menú principal");
    private JPanel panelContenidos = new JPanel();
    private JTextArea IdiomatextArea = new JTextArea(20, 40);
    private JScrollPane scrollPanel = new JScrollPane();
    private JButton Eliminar = new JButton("Eliminar idioma");

    public VistaIdioma (String nomI) throws IdiomaNoExisteix {
        nom = nomI;
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() throws IdiomaNoExisteix {
        iniFrame();
        iniEnrere();
        iniIdioma();
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
        Eliminar.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(Eliminar);
        panelContenidos.add(buttonPanel, constraints);
    }


    private void iniIdioma() throws IdiomaNoExisteix {
        String info = ControladorPresentacio.consultaIdioma(nom);
        IdiomatextArea.setText(info); // Establecer el texto en el JTextArea
        IdiomatextArea.setEditable(false); // Para evitar la edición
        IdiomatextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Cambia la fuente si es necesario
        IdiomatextArea.setWrapStyleWord(true); // Ajusta el ajuste de palabras
        IdiomatextArea.setLineWrap(true); // Permite el ajuste de línea
        IdiomatextArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineación horizontal
        IdiomatextArea.setAlignmentY(Component.CENTER_ALIGNMENT); // Alineación vertical

        scrollPanel.setViewportView(IdiomatextArea);
        panelContenidos.add(Box.createVerticalGlue());
        panelContenidos.add(scrollPanel);
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
        else if (Eliminar.equals(source)) {
            ControladorPresentacio.eliminarIdioma(nom);
            ControladorPresentacio.vistaIdiomes();
            setVisible(false);
        }
    }

}

