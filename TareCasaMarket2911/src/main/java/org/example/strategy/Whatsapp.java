package org.example.strategy;

import org.example.dependencies.TwillioConfig;
import org.example.entities.Notificacion;

import javax.swing.*;

public class Whatsapp implements Notificador {
    
    @Override
    public void notificar(Notificacion notificacion) {
        String mensaje = "WHATSAP\n\n" +
                        "Para: " + notificacion.getDestinatario() + "\n" +
                        "Mensaje: " + notificacion.getMensaje() + "\n\n" +
                        "Enviado correctamente, espero...";
        TwillioConfig.SendMessage(notificacion.getMensaje());
        
        JOptionPane.showMessageDialog(null, mensaje, "WhatsApp-CasaMarket", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public String getNombreCanal() {
        return "WhatsApp";
    }
}
