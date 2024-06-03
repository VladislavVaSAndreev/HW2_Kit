package org.example.server;

import org.example.client.IClientController;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class ServerRepository implements IServerRepository {
    private final List<IClientController> connectedClients = new ArrayList<>();

    @Override
    public void logMessage(String message) {
        saveLogToFile(message);
    }

    @Override
    public void addClient(IClientController client) {
        connectedClients.add(client);
    }

    @Override
    public void removeClient(IClientController client) {
        connectedClients.remove(client);
    }

    @Override
    public void saveLogToFile(String message) {
        String filePath = "src/main/java/org/log.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл лога: " + e.getMessage());
        }
    }

    @Override
    public List<IClientController> getConnectedClients() {
        return new ArrayList<>(connectedClients);
    }
}
