package Presentacio.views;

import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.ControladorPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Aquesta vista és l’encarregada de mostrar el menú principal del sistema i apareix just després de triar un perfil.
 * Aquesta està formada per un conjunt de 7 botons. Els 4 primers et porten a vistes diferents per gestionar (crear/eliminar/modificar)
 * les diferents dades del sistema (Teclats, Llistes de freqüències, Idiomes i Alfabets), el cinquè permet tornar a la
 * vista dels perfils per canviar de perfil, el sisè per a eliminar el perfil l i l’últim permet sortir de l’aplicació.
 *
 * @author Agustí Costabella Moreno (agusti.costabella@estudiantat.upc.edu)
 */
public class VistaPrincipal extends JFrame{

    /**
     * Panell de continguts.
     */
    private JPanel panellContinguts = new JPanel();

    /**
     * Botó per anar a la vista de teclats.
     */
    private JButton Teclats = new JButton("Teclats");

    /**
     * Botó per anar a la vista de llistes.
     */
    private JButton Llistes = new JButton("Llistes de freqüències");

    /**
     * Botó per anar a la vista d’Idiomes
     */
    private JButton Idiomes = new JButton("Idiomes");

    /**
     * Botó per anar a la vista d’alfabets.
     */
    private JButton Alfabets = new JButton("Alfabets");

    /**
     *  Botó per canviar de perfil.
     */
    private JButton CanviarPerfil = new JButton("Canviar Perfil");

    /**
     *  Botó per eliminar el perfil.
     */
    private JButton EliminarPerfil = new JButton("Eliminar Perfil");

    /**
     * Text que mostra el perfil que ha obert la sessió.
     */
    private JLabel labelSessio;

    /**
     * Botó per sortir del sistema.
     */
    private JButton Sortir = new JButton("Sortir");

    /**
     * Constructora de la vista.
     */
    public VistaPrincipal () {
        setVisible(true);
        iniComponents();
    }

    /**
     * Defineix i afegeix els components i els seus contenidors, les característiques del JFrame i associa els listeners
     * corresponents.
     */
    private void iniComponents() {
        iniFrame();
        iniClose();
        iniButtons();
        assign_listenerComponents();
    }

    /**
     * Inicialitza el marc de la vista.
     */
    private void iniFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - 1000) / 2;
        int y = (pantalla.height - 600) / 2;
        setLocation(x, y);
        setResizable(false);
        setTitle("Menú Principal");
    }

    /**
     * Inicialitza el botó per sortir del programa.
     */
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

    /**
     * Inicialitza els set botons del menú principal
     */
    private void iniButtons() {
        panellContinguts.setLayout(new BoxLayout(panellContinguts, BoxLayout.Y_AXIS));
        panellContinguts.add(Box.createVerticalGlue());
        panellContinguts.add(Box.createHorizontalGlue());
        labelSessio = new JLabel("Sessió inciada com a: " + ControladorPresentacio.getPerfilActual());
        labelSessio.setBounds(5, 0, 200, 20);
        add(labelSessio);

        panellContinguts.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;

        constraints.gridy = 1;
        panellContinguts.add(Teclats, constraints);

        constraints.gridy = 2;
        panellContinguts.add(Llistes, constraints);

        constraints.gridy = 3;
        panellContinguts.add(Idiomes, constraints);

        constraints.gridy = 4;
        panellContinguts.add(Alfabets, constraints);

        constraints.gridy = 5;
        panellContinguts.add(CanviarPerfil, constraints);

        constraints.gridy = 6;
        EliminarPerfil.setFont(new Font("Lucida Grande",Font.BOLD, 12));
        panellContinguts.add(EliminarPerfil, constraints);

        Sortir.setFont(new Font("Lucida Grande", Font.BOLD, 12));
        constraints.gridy = 7;
        panellContinguts.add(Sortir, constraints);

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
        Teclats.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Llistes.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Idiomes.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Alfabets.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        CanviarPerfil.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        EliminarPerfil.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Sortir.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Dirigeix les accions en funció del botó premut.
     * @param e L'esdeveniment que activa aquesta funció
     * @throws Exception
     */
    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        Object source = e.getSource();
        if (Teclats.equals(source)) {
            ControladorPresentacio.vistaElements("Teclats");
            setVisible(false);
        }
        else if (Llistes.equals(source)) {
            ControladorPresentacio.vistaElements("Llistes");
            setVisible(false);
        }
        else if (Idiomes.equals(source)) {
            ControladorPresentacio.vistaElements("Idiomes");
            setVisible(false);
        }
        else if (Alfabets.equals(source)) {
            ControladorPresentacio.vistaElements("Alfabets");
            setVisible(false);
        }
        else if (CanviarPerfil.equals(source)) {
            ControladorPresentacio.vistaPerfils();
            setVisible(false);
        }

        else if (EliminarPerfil.equals(source)) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Estàs segur de que vols eliminar el perfil?", "Confirmació d'eliminació de perfil",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                try {
                    ControladorPresentacio.eliminaPerfil();
                    ControladorPresentacio.vistaPerfils();
                    setVisible(false);
                } catch (ExcepcionsCreadorTeclat e1) {
                    ControladorPresentacio.mostraError(e1.getMessage());
                }
            }
        }

        else if (Sortir.equals(source)) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Estas segur de que vols sortir?", "Confirmació de tancament",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                ControladorPresentacio.guardaEstat();
                dispose();
                System.exit(0);
            }
        }

    }
}
