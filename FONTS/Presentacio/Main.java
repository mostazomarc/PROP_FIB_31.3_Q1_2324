package Presentacio;
import java.io.FileNotFoundException;

import Presentacio.ControladorPresentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Main{
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        ControladorPresentacio c = null;
                        try {
                            c = new ControladorPresentacio();
                        } catch (Exception e) {
                            e.printStackTrace();

                            throw new RuntimeException(e);
                        }
                        try {
                            c.iniciaInstancia("Agus"); //comentar amb marc
                            c.carregarDades();
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
    }
}