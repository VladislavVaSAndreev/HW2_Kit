package org.example.client;

import org.example.server.IServerController;

public class ClientController implements IClientController {
    private final IServerController serverController;
    private final IClientView clientView;
    private boolean isConnected;
    private final String clientName;

    public ClientController(IServerController serverController, IClientView clientView, String clientName) {
        this.serverController = serverController;
        this.clientView = clientView;
        this.clientName = clientName;
        this.clientView.setController(this);
        this.isConnected = false;
    }

    @Override
    public void connect() {
        if (isConnected) {
            clientView.showMessage("Вы уже подключены к чату\n");
            return;
        }

        if (serverController.isServerRunning()) {
            clientView.showMessage("Вы успешно подключились к чату\n");
            serverController.logMessage(clientName + " подключился к чату");
            serverController.addClient(this);
            isConnected = true;
        } else {
            clientView.showMessage("Не удалось подключиться к чату\n");
        }
    }

    @Override
    public void sendMessage(String message) {
        if (isConnected && !serverController.isServerStopped()) {
            String fullMessage = clientName + ": " + message;
            clientView.showMessage(fullMessage + "\n");
            serverController.broadcastMessage(fullMessage, this);
        } else if (!isConnected) {
            clientView.showMessage("Вы не подключены к чату\n");
        } else {
            clientView.showMessage("Сервер остановлен. Невозможно отправить сообщение\n");
        }
    }

    @Override
    public void receiveMessage(String message) {
        clientView.showMessage(message + "\n");
    }

    @Override
    public void disconnect() {
        if (!isConnected) {
            clientView.showMessage("Вы уже отключены от чата\n");
            return;
        }

        serverController.removeClient(this);
        isConnected = false;
        clientView.showMessage("Вы отключены от чата\n");
        serverController.logMessage(clientName + " отключился от чата");
    }
}
