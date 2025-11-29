package org.example.strategy;

import org.example.entities.Notificacion;

import javax.swing.*;

public class Push implements Notificador {
    
    @Override
    public void notificar(Notificacion notificacion) {
        String mensaje = "PUSH\n" +
                        "Discpositvo: " + notificacion.getDestinatario() + "\n" +
                        "Mensaje: " + notificacion.getMensaje() + "\n\n" +
                        "Enviado correctamente";
        
        JOptionPane.showMessageDialog(null, mensaje, "Push-CasaMarket", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public String getNombreCanal() {
        return "Push Notification";
    }
}
