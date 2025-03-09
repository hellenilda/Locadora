package view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroCliente extends JFrame {
    public TelaCadastroCliente() {
        setTitle("Cadastro de Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));
        
        add(new JLabel("Nome:"));
        add(new JTextField());
        add(new JLabel("CPF:"));
        add(new JTextField());
        add(new JLabel("Telefone:"));
        add(new JTextField());
        add(new JLabel("E-mail:"));
        add(new JTextField());
        
        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);
        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);
        
        btnCancelar.addActionListener(e -> dispose());
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TelaCadastroCliente();
    }
}
