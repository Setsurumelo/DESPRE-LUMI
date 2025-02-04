package com.vtcompany.desprelumi.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.vtcompany.desprelumi.jugadores.Jugador;
import com.vtcompany.desprelumi.monstruos.Monstruo;
import com.vtcompany.desprelumi.utiles.Utiles;

public class HiloCliente extends Thread {

    private DatagramSocket conexion;
    private InetAddress ipServer;
    private int puerto = 9001;
    private boolean fin = false;
    private int turno;
    private ScheduledExecutorService timeoutChecker;
    private boolean listoRecibido = false;

    public HiloCliente() {
        try {
            ipServer = InetAddress.getByName("255.255.255.255");
            conexion = new DatagramSocket();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
        enviarMensaje("Conexion");
        turno = 0;

        // Inicia un temporizador de 10 segundos
        timeoutChecker = Executors.newSingleThreadScheduledExecutor();
        timeoutChecker.schedule(() -> {
            if (!listoRecibido) {
            	enviarMensaje("Desconectar");
                System.out.println("No se recibió 'Listo' en 10 segundos. Cerrando conexión.");
                Utiles.volver = true;
                desconectar();
                fin = true;
            }
        }, 10, TimeUnit.SECONDS);
    }

    public void enviarMensaje(String msg) {
        byte[] data = msg.getBytes();
        DatagramPacket dp = new DatagramPacket(data, data.length, ipServer, puerto);
        try {
            conexion.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(msg + " / Enviado");
    }

    @Override
    public void run() {
        do {
            byte[] data = new byte[1024];
            DatagramPacket dp = new DatagramPacket(data, data.length);
            try {
                conexion.receive(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            procesarmsj(dp);
        } while (!fin);
    }

    private Monstruo[] monstruos = new Monstruo[3];
    private int cantM = 0;

    private void procesarmsj(DatagramPacket dp) {
        String msj = recibirMensaje(dp);
        System.out.println(msj + " / Recibido");

        if (msj.equals("OK")) {
            ipServer = dp.getAddress();
        } else if (msj.equals("Listo")) {
            listoRecibido = true; // Marca que llegó el mensaje "Listo"
            if (timeoutChecker != null) {
                timeoutChecker.shutdownNow(); // Detén el temporizador
            }
            enviarMensaje("Jugador " + Utiles.jugador.getNombre());
        } else if (Utiles.equalsIgnoreAfterWord(msj, "Jugador")) {
            String nombre = Utiles.eliminarJugadorYEspacio(msj);
            Utiles.enemigo = new Jugador(nombre);
            System.out.println("Enemigo: " + Utiles.enemigo.getNombre());
            enviarMensaje("Monstruo " + Utiles.jugador.getEquipo().getMonstruos()[0].getNombre());
            enviarMensaje("Monstruo " + Utiles.jugador.getEquipo().getMonstruos()[1].getNombre());
            enviarMensaje("Monstruo " + Utiles.jugador.getEquipo().getMonstruos()[2].getNombre());
        } else if (Utiles.equalsIgnoreAfterWord(msj, "Monstruo")) {
            String nombre = Utiles.eliminarMonstruoYEspacio(msj);
            if (cantM < 3) {
                monstruos[cantM] = Utiles.cambiarStringAMonstruo(nombre);
                cantM++;
            }
            if (cantM == 3) {
                Utiles.enemigo.crearEquipo(monstruos);
                System.out.println("Enemigo: " + Utiles.enemigo.getNombre());
                System.out.println("-Monstruos 1: " + Utiles.enemigo.getEquipo().getMonstruos()[0].getNombre());
                System.out.println("-Monstruos 2: " + Utiles.enemigo.getEquipo().getMonstruos()[1].getNombre());
                System.out.println("-Monstruos 3: " + Utiles.enemigo.getEquipo().getMonstruos()[2].getNombre());
                enviarMensaje("EnemigoCreado");
            }
        } else if (msj.equals("Empieza")) {
            Utiles.empieza = true;
        } else if (msj.equals("TuTurno")) {
            turno = 1;
            Utiles.turno = true;
            System.out.println("Es tu turno. Puedes realizar una acción.");
        } else if (msj.equals("NoEsTuTurno")) {
            System.out.println("No es tu turno. Espera a que el oponente termine.");
        } else if (msj.equals("Perdiste")) {
            Utiles.perder = true;
            Utiles.ganar = false;
        } else if (msj.equals("Ganaste")) {
            Utiles.ganar = true;
            Utiles.perder = false;
        } else if (msj.equals("Cambio1")) {
            Utiles.cambio = true;
            Utiles.numCambio = 1;
        } else if (msj.equals("Cambio2")) {
            Utiles.cambio = true;
            Utiles.numCambio = 2;
        } else if (msj.equals("FinTurno")) {
            Utiles.turno = false;
            System.out.println("Turno finalizado. Esperando el turno del oponente.");
        } else if (msj.equals("Atk1")) {
            Utiles.atkUsarEnemigo = 0;
            Utiles.enemigoAtaca = true;
        } else if (msj.equals("Atk2")) {
            Utiles.atkUsarEnemigo = 1;
            Utiles.enemigoAtaca = true;
        } else if (msj.equals("Atk3")) {
            Utiles.atkUsarEnemigo = 2;
            Utiles.enemigoAtaca = true;
        } else if (msj.equals("Murio")) {
            Utiles.murio = true;
        } else if (Utiles.equalsIgnoreAfterWord(msj, "Daño")) {
            Utiles.daño = Integer.parseInt(Utiles.eliminarDañoYEspacio(msj));
            System.out.println(Utiles.daño);
        }
    }

    public void desconectar() {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
        if (timeoutChecker != null && !timeoutChecker.isShutdown()) {
            timeoutChecker.shutdownNow();
        }
        System.out.println("Cliente desconectado.");
    }

    private String recibirMensaje(DatagramPacket dp) {
        return (new String(dp.getData(), 0, dp.getLength())).trim();
    }
}
