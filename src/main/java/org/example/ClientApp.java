package org.example;

import org.example.client.Client;
import org.example.server.Server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ClientApp {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = Server.PORT;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));

        //Connecting to server and send request
        Client client = new Client();
        try {
            client.startConnection(SERVER_IP, SERVER_PORT);

            System.out.println("Enter positive integer and press Enter.");
            System.out.println(client.sendMessage(scanner.nextLine()));
            client.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
