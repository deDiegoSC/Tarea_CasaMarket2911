package org.example.service;


import org.example.entities.Notificacion;
import org.example.strategy.Notificador;

import javax.swing.*;
import java.util.List;

public class ServicioNotificacion {
    private Notificador estrategia;

    public void setEstrategia(Notificador estrategia)
    {
        this.estrategia = estrategia;
    }

    public void enviarNotificacion(Notificacion notificacion)
    {
        estrategia.notificar(notificacion);
    }

    public void enviarMulticanal(Notificacion noti, List<Notificador> canales) {
        String info = "Los canles son:\n";
        for (Notificador canal : canales) {
            info += "-" + canal.getNombreCanal() + "\n";
        }
        JOptionPane.showMessageDialog(null, info,null, JOptionPane.INFORMATION_MESSAGE);
        for (Notificador canal : canales) {
            canal.notificar(noti);
        }
    }
}
