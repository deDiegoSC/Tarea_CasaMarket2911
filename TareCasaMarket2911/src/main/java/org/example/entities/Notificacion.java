package org.example.entities;

public class Notificacion {
    private String destinatario;
    private String mensaje;
    
    public Notificacion(String destinatario, String mensaje)
    {
        this.destinatario = destinatario;
        this.mensaje = mensaje;
    }
    
    public String getDestinatario()
    {
        return destinatario;
    }
    public String getMensaje()
    {
        return mensaje;
    }
}
