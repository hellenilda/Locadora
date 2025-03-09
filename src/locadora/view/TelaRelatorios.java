package view;

import javax.swing.*;
import java.awt.*;

public class TelaRelatorios extends JFrame {
    public TelaRelatorios() {
        setTitle("Relatórios");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnRelatorioLocacoes = new JButton("Relatório de Veículos Locados");
        JButton btnRelatorioFaturamento = new JButton("Relatório de Faturamento");
        JButton btnRelatorioClientes = new JButton("Relatório de Clientes");

        painel.add(btnRelatorioLocacoes);
        painel.add(btnRelatorioFaturamento);
        painel.add(btnRelatorioClientes);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaRelatorios().setVisible(true));
    }
}
