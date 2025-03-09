package view;

import javax.swing.*;
import java.awt.*;

public class TelaListagem extends JFrame {
    public TelaListagem() {
        setTitle("Listagem de Veículos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"Placa", "Modelo", "Ano", "Status"};
        Object[][] dados = {}; // Aqui será preenchido com os dados reais

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);

        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaListagem().setVisible(true));
    }
}
