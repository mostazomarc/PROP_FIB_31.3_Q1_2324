package Presentacio.views;

import Excepcions.AlfabetNoExisteix;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaAlfabet extends JFrame {
    String nom;
    private JButton Enrere = new JButton("Tornar al menú principal");
    private JPanel panelContenidos = new JPanel();
    private JTextArea AlfabettextArea = new JTextArea(20, 40);
    private JScrollPane scrollPanel = new JScrollPane();
    private JButton Eliminar = new JButton("Eliminar alfabet");

    public VistaAlfabet (String nomA) throws AlfabetNoExisteix {
        nom = nomA;
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() throws AlfabetNoExisteix {
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


    private void iniIdioma() throws AlfabetNoExisteix {
        String info = ControladorPresentacio.consultaAlfabet(nom);
        AlfabettextArea.setText(info); // Establecer el texto en el JTextArea
        AlfabettextArea.setEditable(false); // Para evitar la edición
        AlfabettextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Cambia la fuente si es necesario
        AlfabettextArea.setWrapStyleWord(true); // Ajusta el ajuste de palabras
        AlfabettextArea.setLineWrap(true); // Permite el ajuste de línea
        AlfabettextArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineación horizontal
        AlfabettextArea.setAlignmentY(Component.CENTER_ALIGNMENT); // Alineación vertical

        scrollPanel.setViewportView(AlfabettextArea);
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
            ControladorPresentacio.eliminarAlfabet(nom);
            ControladorPresentacio.vistaAlfabets();
            setVisible(false);
        }
    }
}
