package org.example;

import org.example.entities.Notificacion;
import org.example.service.ServicioNotificacion;
import org.example.strategy.*;

import javax.swing.JOptionPane;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ServicioNotificacion servicio = new ServicioNotificacion();
        Notificador whatsapp = new Whatsapp();
        Notificador email = new Email();
        Notificador push = new Push();
//MENU
        while (true) {
            String[] opciones = {
                    "Enviar por un canal unico",
                    "Enviar por multiples canales",
                    "Salir"
            };

            int opcion = JOptionPane.showOptionDialog(null, "Notificaciones\nCasaMarket\n" + "Escoja una opcion:", null,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
            );
//FIN MENU
            if (opcion == 2) {
                JOptionPane.showMessageDialog(null,
                        "Fin");
                break;
            }

            switch (opcion)
            {
                case 0:
                    enviarPorCanalUnico(servicio, whatsapp, email, push);
                    break;
                case 1:
                    enviarPorEvento(servicio, whatsapp, email, push);
                    break;
            }
        }
    }
    private static void enviarPorCanalUnico(ServicioNotificacion servicio, Notificador whatsapp, Notificador email, Notificador push) {

        String[] canales = {"WhatsApp","Email","Push"};
        int canal = JOptionPane.showOptionDialog(null,
                "Escoge el canal de envio:",
                "CasaMarketNotificaciones",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                canales,
                canales[0]
        );
        if (canal == -1) return;

        String destinatario = JOptionPane.showInputDialog(null, "Ingresar el destino:");

        if (destinatario == null) return;

        String mensaje = JOptionPane.showInputDialog(null, "Ingrese el mensaje a enviar:", "");

        if (mensaje == null) return;

        Notificacion notif = new Notificacion(destinatario, mensaje);

        switch (canal) {
            case 0:
                servicio.setEstrategia(whatsapp);
                break;
            case 1:
                servicio.setEstrategia(email);
                break;
            case 2:
                servicio.setEstrategia(push);
                break;
        }

        servicio.enviarNotificacion(notif);
    }

    private static void enviarPorEvento(ServicioNotificacion servicio, Notificador whatsapp, Notificador email, Notificador push)
    {

        String[] eventos = {
                "PROMO (WhatsApp y Email)",
                "STOCK MINIMO (Push)",
                "MANTENIMIENTO (Email y Push)",
                "NUEVO PDIDO (Push y WhatsApp)"
        };

        int evento = JOptionPane.showOptionDialog(null, "Escoge el tipo de evento:", "EventosChaMarket",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, eventos, eventos[0]);

        if (evento == -1) return;

        String destinatario = JOptionPane.showInputDialog(null, "Ingrese el destinatario:", "dediegosc@gmail.com");

        if (destinatario == null || destinatario.trim().isEmpty()) return;

        Notificacion notif = null;

        switch (evento) {
            case 0:
                notif = new Notificacion(destinatario,
                        "50% de descuento en productos");
                servicio.enviarMulticanal(notif, Arrays.asList(whatsapp, email));
                break;

            case 1:
                notif = new Notificacion(destinatario,
                        "El stock mínimo alcanzado en el producto coca cola");
                servicio.setEstrategia(push);
                servicio.enviarNotificacion(notif);
                break;

            case 2:
                notif = new Notificacion(destinatario,
                        "Mantenimiento para el Domingo 9:00 am");
                servicio.enviarMulticanal(notif, Arrays.asList(email, push));
                break;

            case 3:
                notif = new Notificacion(destinatario,
                        "Nuevo pedido P01 recbido");
                servicio.enviarMulticanal(notif, Arrays.asList(push, whatsapp));
                break;
        }
    }
}
