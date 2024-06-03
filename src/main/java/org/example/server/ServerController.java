package org.example.server;

import org.example.client.IClientController;

public class ServerController implements IServerController {
    private final IServerView serverView;
    private final IServerRepository serverRepository;
    private boolean isServerWorking;
    private boolean isServerStopped;

    public ServerController(IServerView serverView, IServerRepository serverRepository) {
        this.serverView = serverView;
        this.serverRepository = serverRepository;
        this.serverView.setController(this);
        this.isServerWorking = false;
        this.isServerStopped = false;
    }

    @Override
    public void startServer() {
        if (isServerRunning()) {
            serverView.showMessage("Сервер уже запущен\n");
        } else {
            isServerWorking = true;
            isServerStopped = false;
            serverView.showMessage("Сервер запущен\n");
            serverView.enableStopButton(true);
            serverRepository.logMessage("Сервер запущен");
        }
    }

    @Override
    public void stopServer() {
        if (isServerStopped) {
            serverView.showMessage("Сервер уже остановлен\n");
        } else {
            isServerStopped = true;
            isServerWorking = false;
            for (IClientController client : serverRepository.getConnectedClients()) {
                client.disconnect();
            }
            serverView.showMessage("Сервер остановлен. Все клиенты отключены\n");
            serverRepository.logMessage("Сервер остановлен. Все клиенты отключены");
        }
    }


    @Override
    public boolean isServerRunning() {
        return isServerWorking;
    }

    @Override
    public boolean isServerStopped() {
        return isServerStopped;
    }

    @Override
    public void logMessage(String message) {
        serverView.showMessage(message + "\n");
        serverRepository.saveLogToFile(message);
    }

    @Override
    public void addClient(IClientController client) {
        serverRepository.addClient(client);
    }

    @Override
    public void removeClient(IClientController client) {
        serverRepository.removeClient(client);
    }

    @Override
    public void broadcastMessage(String message, IClientController sender) {
        serverView.showMessage(message + "\n");
        for (IClientController client : serverRepository.getConnectedClients()) {
            if (client != sender) {
                client.receiveMessage(message);
            }
        }
        serverRepository.saveLogToFile(message);
    }
}
