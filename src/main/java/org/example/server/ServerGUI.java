package org.example.server;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame implements IServerView {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Старт");
    private final JButton btnStop = new JButton("Стоп");
    private final JTextArea log = new JTextArea();
    private IServerController serverController;

    public ServerGUI() {
        btnStop.setEnabled(false);

        btnStop.addActionListener(e -> serverController.stopServer());
        btnStart.addActionListener(e -> serverController.startServer());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Чат");
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(btnStart);
        topPanel.add(btnStop);
        add(topPanel, BorderLayout.NORTH);

        log.setEditable(true);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void showMessage(String message) {
        log.append(message + "\n");
    }

    @Override
    public void setController(IServerController controller) {
        this.serverController = controller;
    }

    @Override
    public void enableStopButton(boolean enable) {
        btnStop.setEnabled(enable);
    }
}
