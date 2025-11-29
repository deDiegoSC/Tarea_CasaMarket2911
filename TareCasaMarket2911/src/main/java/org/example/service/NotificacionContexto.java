package org.example.service;


import org.example.entities.Notificacion;
import org.example.strategy.Notificador;

import javax.swing.*;
import java.util.List;

public class NotificacionContexto {
    private Notificador estrategia;

    public void setEstrategia(Notificador estrategia)
    {
        this.estrategia = estrategia;
    }

    public void enviarNotificacion(Notificacion notificacion)
    {
        estrategia.notify(notificacion);
    } //DELEGAMOS A LA ESTRATEGIA EL TRABAJO LLAMANDO AL METODO CORRESPONDIENTE

    public void enviarMulticanal(Notificacion noti, List<Notificador> canales) {
        String info = "Los canles son:\n";
        for (Notificador canal : canales) {
            info += "-" + canal.getCanal() + "\n";
        }
        JOptionPane.showMessageDialog(null, info,null, JOptionPane.INFORMATION_MESSAGE);
        for (Notificador canal : canales) {//LISTA DE CANALES
            canal.notify(noti); //PIDE QUE ENVIE LA NOTIFICACION A TODOS LOS CANALES QUE SE VAN A RECORRER
        }
    }
}
