package org.example.strategy;


import org.example.entities.Notificacion;

public interface Notificador {
    void notificar(Notificacion notificacion);
    String getNombreCanal();
}
