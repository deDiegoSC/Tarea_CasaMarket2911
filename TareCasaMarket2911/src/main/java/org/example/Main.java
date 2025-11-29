package org.example;

import org.example.entities.Notificacion;
import org.example.service.NotificacionContexto;
import org.example.strategy.*;

import javax.swing.JOptionPane;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Inicializo todos las dependencias
        NotificacionContexto servicio = new NotificacionContexto();
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
            //OPCION SALIR
            if (opcion == 2) {
                JOptionPane.showMessageDialog(null,
                        "Fin");
                break;
            }

            switch (opcion)
            {
                //OPCTION DE ENVIAR POR UN SOLO CANAL
                case 0:
                    enviarPorCanalUnico(servicio, whatsapp, email, push);
                    break;
                //OPCION DE ENVIAR POR MUCHOS CANALES O EVENTOS
                case 1:
                    enviarPorEvento(servicio, whatsapp, email, push);
                    break;
            }
        }
    }
    //FORMULARIO DE ENVIO UNICO
    private static void enviarPorCanalUnico(NotificacionContexto servicio, Notificador whatsapp, Notificador email, Notificador push) {

        //MENU
        String[] canales = {"WhatsApp","Email","Push"};//OPCTIONES
        int canal = JOptionPane.showOptionDialog(null,
                "Escoge el canal de envio:",
                "CasaMarketNotificaciones",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                canales,
                canales[0]
        );
        //OPCION INVALIDA
        if (canal == -1) return;

        //PIDE EL NUMERO O DIRECCION DEPENDIENDO DE LA OPCION ESCOGIDA
        String destinatario = JOptionPane.showInputDialog(null, "Ingresar el destino:");
        //COMPROBAR QUE NO ESTE VACIO
        if (destinatario == null) return;

        //MENSAJE A ENVIAR
        String mensaje = JOptionPane.showInputDialog(null, "Ingrese el mensaje a enviar:", "");
        //COMPROBAR QUE NO ESTE VACIO
        if (mensaje == null) return;

        //SE CREA EL CUERPO DEL MENSAJE
        Notificacion notif = new Notificacion(destinatario, mensaje);

        switch (canal) {
            //ALMACENANDO LA ESTRATEGIA REQUERIDA
            case 0:
                //WHATSAPP
                servicio.setEstrategia(whatsapp);
                break;
            case 1:
                //email
                servicio.setEstrategia(email);
                break;
            case 2:
                //push
                servicio.setEstrategia(push);
                break;
        }

        servicio.enviarNotificacion(notif);//LLAMAMOS AL CONTEXTO CON EL CUERPO DE LA ESTRATEGIA
    }

    private static void enviarPorEvento(NotificacionContexto servicio, Notificador whatsapp, Notificador email, Notificador push)
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
