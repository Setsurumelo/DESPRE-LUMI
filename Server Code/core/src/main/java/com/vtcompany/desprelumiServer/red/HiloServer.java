package com.vtcompany.desprelumiServer.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.vtcompany.desprelumiServer.jugadores.Jugador;
import com.vtcompany.desprelumiServer.utiles.Utiles;

public class HiloServer extends Thread {

    private DatagramSocket conexion;
    private boolean fin = false;
    private DireccionRed[] clientes = new DireccionRed[2];
    private int cantClientes = 0;
    private boolean[] clientesListos = new boolean[2]; 
    private int turno = 0;

    // Constructor
    public HiloServer() {
        try {
            conexion = new DatagramSocket(9001);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            conexion.setReceiveBufferSize(65536);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensaje(String msg, InetAddress ip, int puerto) {
        byte[] data = msg.getBytes();
        DatagramPacket dp = new DatagramPacket(data, data.length, ip, puerto);
        try {
            conexion.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(msg+" / Enviado");
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

    private void procesarmsj(DatagramPacket dp) {
        String msj = (new String(dp.getData())).trim();
        System.out.println("Mensaje recibido: " + msj);

        if (msj.equals("Conexion")) {
            if (cantClientes < 2) {
                clientes[cantClientes] = new DireccionRed(dp.getAddress(), dp.getPort());
                enviarMensaje("OK", clientes[cantClientes].getIp(), clientes[cantClientes].getPuerto());
                clientesListos[cantClientes] = true;
                cantClientes++;
                if (clientesListos[0] && clientesListos[1]) {
                    enviarMensaje("Listo", clientes[0].getIp(), clientes[0].getPuerto());
                    enviarMensaje("Listo", clientes[1].getIp(), clientes[1].getPuerto());
                    clientesListos[0] = false;
                    clientesListos[1] = false;
                }
            }
        } else if (Utiles.equalsIgnoreAfterWord(msj, "Jugador")||Utiles.equalsIgnoreAfterWord(msj, "Monstruo")
        		||msj.equals("Cambio1")||msj.equals("Cambio2")||msj.equals("Atk1")||msj.equals("Atk2")||msj.equals("Atk3")){
            enviarMsjAlOtroCliente(dp, msj);
        } else if (msj.equals("EnemigoCreado")) {
        	if(dp.getPort() == clientes[0].getPuerto()) {
        		clientesListos[0] = true;
        	} else if(dp.getPort() == clientes[1].getPuerto()) {
        		clientesListos[1] = true;
        	}
        	if(clientesListos[0]&&clientesListos[1]) {
        		enviarMensaje("Empieza", clientes[0].getIp(), clientes[0].getPuerto());
                enviarMensaje("Empieza", clientes[1].getIp(), clientes[1].getPuerto());
                float decision = Utiles.generarRandomFloat(1f, 0);
                if(decision < 0.5f) {
                	enviarMensaje("TuTurno", clientes[0].getIp(),clientes[0].getPuerto());
                }else {
                	enviarMensaje("TuTurno", clientes[1].getIp(),clientes[1].getPuerto());
                }
        	}
        }else if (msj.equals("FinTurno")) {
            int indiceActual = getClienteIndex(dp.getAddress(), dp.getPort());
            int indiceSiguiente = (indiceActual == 0) ? 1 : 0;

            enviarMensaje("TuTurno", clientes[indiceSiguiente].getIp(), clientes[indiceSiguiente].getPuerto());
            System.out.println("Turno transferido al cliente: " + indiceSiguiente);

        } else if (msj.equals("SeRindio")) {
            int indicePerdedor = getClienteIndex(dp.getAddress(), dp.getPort());
            int indiceGanador = (indicePerdedor == 0) ? 1 : 0;
            enviarMensaje("Ganaste", clientes[indiceGanador].getIp(), clientes[indiceGanador].getPuerto());
            enviarMensaje("Perdiste", clientes[indicePerdedor].getIp(), clientes[indicePerdedor].getPuerto());
            
        } else if (msj.equals("Desconectar")) {
            int indice = getClienteIndex(dp.getAddress(), dp.getPort());
            if (indice != -1) {
                // Borrar la información del cliente desconectado
                System.out.println("Cliente desconectado: " + dp.getAddress() + ":" + dp.getPort());
                clientes[indice] = null;
                clientesListos[indice] = false;
                cantClientes--;

                // Opcionalmente, enviar un mensaje a los demás clientes indicando que el juego ha terminado
                if (cantClientes == 0) {
                    System.out.println("No hay clientes conectados. Esperando nuevos clientes.");
                }
            }
        } else if (msj.equals("FinJuego")) {
            fin = true;
        } else if (msj.equals("Gane")) {
        	int indiceGanador = getClienteIndex(dp.getAddress(), dp.getPort());
            int indicePerdedor = (indiceGanador == 0) ? 1 : 0;
            enviarMensaje("Ganaste", clientes[indiceGanador].getIp(), clientes[indiceGanador].getPuerto());
            enviarMensaje("Perdiste", clientes[indicePerdedor].getIp(), clientes[indicePerdedor].getPuerto());
        } else if (msj.equals("SeMurio")) {
        	int indiceActual = getClienteIndex(dp.getAddress(), dp.getPort());
            int indiceSiguiente = (indiceActual == 0) ? 1 : 0;

            enviarMensaje("Murio", clientes[indiceSiguiente].getIp(), clientes[indiceSiguiente].getPuerto());
        } else if (Utiles.equalsIgnoreAfterWord(msj, "Daño")){
        	enviarMsjAlOtroCliente(dp, msj);
        	
        } else if (Utiles.equalsIgnoreAfterWord(msj, "EfectoEP")) {
        	int indiceMsj = getClienteIndex(dp.getAddress(), dp.getPort());
            int indiceOtro = (indiceMsj == 0) ? 1 : 0;
        	enviarMensaje(msj, clientes[indiceMsj].getIp(), clientes[indiceMsj].getPuerto());
        	msj = Utiles.eliminarEfectoYEspacio(msj);
        	enviarMensaje("EfectoER "+msj, clientes[indiceOtro].getIp(), clientes[indiceOtro].getPuerto());
        	
        } else if (Utiles.equalsIgnoreAfterWord(msj, "EfectoER")) {
        	int indiceMsj = getClienteIndex(dp.getAddress(), dp.getPort());
            int indiceOtro = (indiceMsj == 0) ? 1 : 0;
            enviarMensaje(msj, clientes[indiceOtro].getIp(), clientes[indiceOtro].getPuerto());
            msj = Utiles.eliminarEfectoYEspacio(msj);
        	enviarMensaje("EfectoEP "+msj, clientes[indiceMsj].getIp(), clientes[indiceMsj].getPuerto());
            
        } else if (Utiles.equalsIgnoreAfterWord(msj, "EfectoMP")) {
        	int indiceMsj = getClienteIndex(dp.getAddress(), dp.getPort());
            int indiceOtro = (indiceMsj == 0) ? 1 : 0;
        	enviarMensaje(msj, clientes[indiceMsj].getIp(), clientes[indiceMsj].getPuerto());
        	msj = Utiles.eliminarEfectoYEspacio(msj);
        	enviarMensaje("EfectoMR "+msj, clientes[indiceOtro].getIp(), clientes[indiceOtro].getPuerto());
        	
        } else if (Utiles.equalsIgnoreAfterWord(msj, "EfectoMR")) {
        	int indiceMsj = getClienteIndex(dp.getAddress(), dp.getPort());
            int indiceOtro = (indiceMsj == 0) ? 1 : 0;
            enviarMensaje(msj, clientes[indiceOtro].getIp(), clientes[indiceOtro].getPuerto());
            msj = Utiles.eliminarEfectoYEspacio(msj);
        	enviarMensaje("EfectoMP "+msj, clientes[indiceMsj].getIp(), clientes[indiceMsj].getPuerto());
            
        }

    }

    private void enviarMsjAlOtroCliente(DatagramPacket dp, String msj) {
        InetAddress ipOrigen = dp.getAddress();
        int puertoOrigen = dp.getPort();

        // Determinar cuál cliente envió el mensaje
        int indiceOrigen = getClienteIndex(ipOrigen, puertoOrigen);
        if (indiceOrigen == -1) {
            System.err.println("Error: Cliente remitente no encontrado.");
            return;
        }

        // Determinar el índice del otro cliente
        int indiceDestino = (indiceOrigen == 0) ? 1 : 0;

        // Verificar que el cliente destino está conectado
        if (clientes[indiceDestino] == null) {
            System.err.println("Error: El otro cliente no está conectado.");
            return;
        }

        // Enviar el mensaje al cliente destino
        DireccionRed destino = clientes[indiceDestino];
        enviarMensaje(msj, destino.getIp(), destino.getPuerto());
        System.out.println(msj + " / Enviado al otro cliente (cliente " + indiceDestino + ")");
    }

    private int getClienteIndex(InetAddress ip, int puerto) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getIp().equals(ip) && clientes[i].getPuerto() == puerto) {
                return i;
            }
        }
        return -1; // Si no se encuentra el cliente
    }
    
	public int getCantClientes() {return cantClientes;}

	

}
