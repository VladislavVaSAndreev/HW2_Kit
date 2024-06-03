package org.example.server;

public interface IServerView {
    void showMessage(String message);
    void setController(IServerController controller);
    void enableStopButton(boolean enable);
}
