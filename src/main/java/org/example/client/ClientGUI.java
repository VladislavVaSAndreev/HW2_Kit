package org.example.client;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame implements IClientView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private IClientController clientController;

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin;
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private final JButton btnLogin = new JButton("Подключиться");
    private final JButton btnDisconnect = new JButton("Отключиться");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Отправить");

    public ClientGUI(String clientName) {
        tfLogin = new JTextField(clientName);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Клиент: " + clientName);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelTop.add(btnDisconnect);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        btnLogin.addActionListener(e -> {
            clientController.connect();
        });

        btnSend.addActionListener(e -> {
            clientController.sendMessage(tfMessage.getText());
            tfMessage.setText("");
        });

        btnDisconnect.addActionListener(e -> {
            clientController.disconnect();
        });

        setVisible(true);
    }

    @Override
    public void showMessage(String message) {
        log.append(message + "\n");
    }

    @Override
    public void setController(IClientController controller) {
        this.clientController = controller;
    }
}
