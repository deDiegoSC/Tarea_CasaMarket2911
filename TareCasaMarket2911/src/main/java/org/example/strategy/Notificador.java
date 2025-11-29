package org.example.strategy;


import org.example.entities.Notificacion;

public interface Notificador {
    void notify(Notificacion notificacion);
    String getCanal();
}
