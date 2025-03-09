package view;

import javax.swing.*;
import java.awt.*;

public class TelaPagamentos extends JFrame {
    public TelaPagamentos() {
        setTitle("Registro de Pagamentos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        painel.add(new JLabel("ID Locação:"));
        JTextField txtIdLocacao = new JTextField();
        painel.add(txtIdLocacao);

        painel.add(new JLabel("Valor Pago:"));
        JTextField txtValor = new JTextField();
        painel.add(txtValor);

        painel.add(new JLabel("Data Pagamento:"));
        JTextField txtData = new JTextField();
        painel.add(txtData);

        painel.add(new JLabel("Método:"));
        String[] metodos = {"Dinheiro", "Cartão"};
        JComboBox<String> cmbMetodo = new JComboBox<>(metodos);
        painel.add(cmbMetodo);

        JButton btnRegistrar = new JButton("Registrar");
        painel.add(btnRegistrar);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPagamentos().setVisible(true));
    }
}
