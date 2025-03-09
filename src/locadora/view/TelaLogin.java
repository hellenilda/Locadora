package view;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    public TelaLogin() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        painel.add(new JLabel("Usuário:"));
        JTextField txtUsuario = new JTextField();
        painel.add(txtUsuario);

        painel.add(new JLabel("Senha:"));
        JPasswordField txtSenha = new JPasswordField();
        painel.add(txtSenha);

        JButton btnLogin = new JButton("Entrar");
        painel.add(btnLogin);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
