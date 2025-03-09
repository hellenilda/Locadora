package view;

import javax.swing.*;
import java.awt.*;

public class TelaDevolucao extends JFrame {
    public TelaDevolucao() {
        setTitle("Devolução de Veículo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));
        
        add(new JLabel("Veículo:"));
        add(new JTextField());
        add(new JLabel("Data de Devolução:"));
        add(new JTextField());
        add(new JLabel("Multa (se houver):"));
        add(new JTextField());
        
        JButton btnFinalizar = new JButton("Finalizar");
        add(btnFinalizar);
        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);
        
        btnCancelar.addActionListener(e -> dispose());
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TelaDevolucao();
    }
}
