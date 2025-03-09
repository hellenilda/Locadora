package locadora.view;

import locadora.controller.LocacaoController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;
import locadora.controller.ClienteController;
import javax.swing.*;
import java.awt.*;

public class TelaRelatorios extends JFrame {
	LocacaoDAO locacaoDAO = new LocacaoDAO();
	VeiculoDAO veiculoDAO = new VeiculoDAO();
	ClienteDAO clienteDAO = new ClienteDAO();
	
    public TelaRelatorios() {
        setTitle("Relatórios");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnRelatorioLocacoes = new JButton("Relatório de Veículos Locados");
        JButton btnRelatorioFaturamento = new JButton("Relatório de Faturamento");
        JButton btnRelatorioClientes = new JButton("Relatório de Clientes");

        btnRelatorioLocacoes.addActionListener(e -> gerarRelatorioLocacoes());
        btnRelatorioFaturamento.addActionListener(e -> gerarRelatorioFaturamento());
        btnRelatorioClientes.addActionListener(e -> gerarRelatorioClientes());

        painel.add(btnRelatorioLocacoes);
        painel.add(btnRelatorioFaturamento);
        painel.add(btnRelatorioClientes);

        add(painel);
    }

    private void gerarRelatorioLocacoes() {
        LocacaoController locacaoController = new LocacaoController(locacaoDAO, veiculoDAO);
        // Implemente a lógica para gerar o relatório de locações
        JOptionPane.showMessageDialog(this, "Relatório de locações gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void gerarRelatorioFaturamento() {
        LocacaoController locacaoController = new LocacaoController(locacaoDAO, veiculoDAO);
        // Implemente a lógica para gerar o relatório de faturamento
        JOptionPane.showMessageDialog(this, "Relatório de faturamento gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void gerarRelatorioClientes() {
        ClienteController clienteController = new ClienteController(clienteDAO);
        // Implemente a lógica para gerar o relatório de clientes
        JOptionPane.showMessageDialog(this, "Relatório de clientes gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaRelatorios().setVisible(true));
    }
}
