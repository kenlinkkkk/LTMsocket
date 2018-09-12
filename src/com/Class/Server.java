package com.Class;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket listener = null;
        int clientNum = 0;

        try {
            listener = new ServerSocket(4000);
            System.out.println("Server running ....");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (true){
                Socket socketOfServer = listener.accept();
                new ServiceThread(clientNum ++, socketOfServer).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ServiceThread extends Thread{
        private int clientNum;
        private Socket socketOfServer;

        public ServiceThread(int clientNum, Socket socketOfServer) {
            this.clientNum = clientNum;
            this.socketOfServer = socketOfServer;
        }

        @Override
        public void run(){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

                while (true){
                    int bk = reader.read();
                    System.out.println(bk);
                    double s = bk*bk*3.14;

                    writer.write("Diện tích: " + s);
                    writer.newLine();
                    writer.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
