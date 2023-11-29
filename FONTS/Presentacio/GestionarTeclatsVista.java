package Presentacio;

import javax.swing.*;
import java.awt.*;

public class GestionarTeclatsVista extends JFrame {
    private JFrame frame = new JFrame("Gestionar Teclats");

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public GestionarTeclatsVista() {
        // Configuración de la vista de Gestionar Teclats
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(screenSize.width, screenSize.height));
        // Otros ajustes y componentes de la vista...

        // Supongamos que tienes un panel de contenido en la vista de Gestionar Teclats
        JPanel panelContenidoGestionarTeclats = new JPanel();
        panelContenidoGestionarTeclats.add(new JLabel("Vista de Gestionar Teclats"));
        frame.add(panelContenidoGestionarTeclats);
    }

    public JFrame getFrame() {
        return frame;
    }
}
