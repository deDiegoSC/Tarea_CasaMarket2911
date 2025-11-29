package org.example.strategy;


import org.example.entities.Notificacion;

import javax.swing.*;

public class Sms implements Notificador {
    @Override
    public void notificar(Notificacion notificacion) {
        String mensaje = "📱 SMS\n\n" +
                "Para: " + notificacion.getDestinatario() + "\n" +
                "Mensaje: " + notificacion.getMensaje() + "\n" +
                "SMS enviado correctamente";

        JOptionPane.showMessageDialog(null, mensaje, "SMS-CasaMarket", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String getNombreCanal() {
        return "SMS";
    }
}