package org.example.client;

public interface IClientView {
    void showMessage(String message);
    void setController(IClientController controller);
}
