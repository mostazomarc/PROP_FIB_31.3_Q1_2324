package Presentacio.views;
import Presentacio.ControladorPresentacio;

import Presentacio.views.VistaPrincipal; // Importa la clase VistaPrincipal si es necesario

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionarTeclatsVista extends JFrame {

    private ControladorPresentacio ctrlp;
    private JFrame frame = new JFrame("Gestionar Teclats");
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public GestionarTeclatsVista() {
        // Configuración de la vista de Gestionar Teclats
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(screenSize.width, screenSize.height));

        // Panel de contenido en la vista de Gestionar Teclats
        JPanel panelContenidoGestionarTeclats = new JPanel();
        panelContenidoGestionarTeclats.setLayout(new BorderLayout());

        // Contenido de la vista de Gestionar Teclats
        JLabel label = new JLabel("Vista de Gestionar Teclats");
        panelContenidoGestionarTeclats.add(label, BorderLayout.CENTER);

        // Botón para volver a la pantalla principal
        JButton backButton = new JButton("Volver a la pantalla principal");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAPantallaPrincipal();
            }
        });
        panelContenidoGestionarTeclats.add(backButton, BorderLayout.SOUTH);

        frame.add(panelContenidoGestionarTeclats);
    }

    private void regresarAPantallaPrincipal() {
        // Cerrar la vista de Gestionar Teclats y mostrar la VistaPrincipal nuevamente
        frame.dispose(); // Cerrar la vista actual de Gestionar Teclats

        // Volver a mostrar la VistaPrincipal
        VistaPrincipal vistaPrincipal = new VistaPrincipal(ctrlp);
        vistaPrincipal.setVisible();
    }

    public JFrame getFrame() {
        return frame;
    }
}
