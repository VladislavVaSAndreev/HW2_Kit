package org.example;

import org.example.client.ClientController;
import org.example.client.ClientGUI;
import org.example.server.ServerController;
import org.example.server.ServerGUI;
import org.example.server.ServerRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ServerRepository serverRepository = new ServerRepository();
        ServerGUI serverGUI = new ServerGUI();
        ServerController serverController = new ServerController(serverGUI, serverRepository);

        ClientGUI clientGUI1 = new ClientGUI("Геннадий Иванов");
        ClientController clientController1 = new ClientController(serverController, clientGUI1, "Геннадий Иванов");

        ClientGUI clientGUI2 = new ClientGUI("Игорь Петров");
        ClientController clientController2 = new ClientController(serverController, clientGUI2, "Игорь Петров");

        ClientGUI clientGUI3 = new ClientGUI("Семен Милехин");
        ClientController clientController3 = new ClientController(serverController, clientGUI3, "Семен Милехин");

        ClientGUI clientGUI4 = new ClientGUI("Зинаида Мшта");
        ClientController clientController4 = new ClientController(serverController, clientGUI4, "Зинаида Мшта");

        System.out.println("Method main() is over");
        }
    }
