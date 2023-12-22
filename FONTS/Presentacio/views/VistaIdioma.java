package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.IdiomaNoExisteix;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Aquesta vista és l’encarregada de mostrar la informació de l’idioma amb el nom indicat a l’atribut de la classe.
 * Aquesta està formada per una àrea de text la qual es mostra el nom de l’idioma, el nom de l’alfabet de l’idioma i el
 * nom de la llista de freqüències predeterminada de l’idioma. També inclou un botó per poder eliminar aquest idioma
 * del sistema i un altre per tornar enrere.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaIdioma extends JFrame {
    private String nom;
    private JButton Enrere = new JButton("Tornar enrere");
    private JPanel panellContinguts = new JPanel();
    private JTextArea IdiomatextArea = new JTextArea();
    private JScrollPane scrollPanel = new JScrollPane();
    private JButton Eliminar = new JButton("Eliminar");

    public VistaIdioma (String nomI) throws IdiomaNoExisteix {
        nom = nomI;
        setVisible(true);
        iniComponents();
    }

    private void iniComponents() throws IdiomaNoExisteix {
        iniFrame();
        iniClose();
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

    private void iniEnrere() {
        panellContinguts.setLayout(new BoxLayout(panellContinguts, BoxLayout.Y_AXIS));
        panellContinguts.add(Box.createVerticalGlue());
        panellContinguts.add(Box.createHorizontalGlue());
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
        panellContinguts.add(buttonPanel, constraints);
    }


    private void iniIdioma() throws IdiomaNoExisteix {
        String info = ControladorPresentacio.consultaIdioma(nom);
        IdiomatextArea.setAlignmentX(CENTER_ALIGNMENT);
        IdiomatextArea.setText(info);
        IdiomatextArea.setEditable(false);
        IdiomatextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        IdiomatextArea.setWrapStyleWord(true);
        IdiomatextArea.setLineWrap(true);
        scrollPanel.setViewportView(IdiomatextArea);
        panellContinguts.add(Box.createVerticalGlue());
        panellContinguts.add(scrollPanel);
        panellContinguts.add(Box.createVerticalGlue());
        add(panellContinguts, BorderLayout.CENTER);
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
            ControladorPresentacio.vistaElements("Idiomes");
            setVisible(false);
        }
        else if (Eliminar.equals(source)) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Estàs segur de que vols eliminar aquest idioma?", "Confirmació d'eliminació",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                try {
                    ControladorPresentacio.eliminarIdioma(nom);
                    ControladorPresentacio.vistaElements("Idiomes");
                    setVisible(false);
                } catch (ExcepcionsCreadorTeclat e1) {
                    ControladorPresentacio.mostraError(e1.getMessage());
                }
            }
        }
    }
}

