package org.example.strategy;

import org.example.entities.Notificacion;

import javax.swing.*;

public class Email implements Notificador {
    
    @Override
    public void notify(Notificacion notificacion) {
        String mensaje = "EMAIL\n" +
                        "Para: " + notificacion.getDestinatario() + "\n" +
                        "Mensaje: " + notificacion.getMensaje() + "\n\n" +
                        "mensaje enviado correactamente";
        
        JOptionPane.showMessageDialog(null, mensaje, 
            "Email - CasaMarket", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public String getCanal() {
        return "Email";
    }
}
