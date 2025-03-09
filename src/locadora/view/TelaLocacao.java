package view;

import javax.swing.*;
import java.awt.*;

public class TelaLocacao extends JFrame {
    public TelaLocacao() {
        setTitle("Locação de Veículo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));
        
        add(new JLabel("Cliente:"));
        add(new JTextField());
        add(new JLabel("Veículo:"));
        add(new JTextField());
        add(new JLabel("Dias de Locação:"));
        add(new JTextField());
        
        JButton btnConfirmar = new JButton("Confirmar");
        add(btnConfirmar);
        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);
        
        btnCancelar.addActionListener(e -> dispose());
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TelaLocacao();
    }
}
