package org.example.client;

public interface IClientController {
    void connect();
    void sendMessage(String message);
    void receiveMessage(String message);
    void disconnect();
}
