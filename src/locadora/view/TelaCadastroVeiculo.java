package view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroVeiculo extends JFrame {
    public TelaCadastroVeiculo() {
        setTitle("Cadastro de Veículo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));
        
        add(new JLabel("Placa:"));
        add(new JTextField());
        add(new JLabel("Modelo:"));
        add(new JTextField());
        add(new JLabel("Ano:"));
        add(new JTextField());
        add(new JLabel("Status:"));
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Disponível", "Locado"});
        add(statusBox);
        
        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);
        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);
        
        btnCancelar.addActionListener(e -> dispose());
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TelaCadastroVeiculo();
    }
}