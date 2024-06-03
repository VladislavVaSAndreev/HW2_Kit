package org.example.server;

import org.example.client.IClientController;

import java.util.List;

public interface IServerRepository {
    void logMessage(String message);
    void addClient(IClientController client);
    void removeClient(IClientController client);
    void saveLogToFile(String message);
    List<IClientController> getConnectedClients();
}
