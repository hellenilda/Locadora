package locadora.view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
	public TelaPrincipal() {
		setTitle("Sistema de Locação de Veículos");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Painel principal com BorderLayout
		JPanel painelPrincipal = new JPanel(new BorderLayout());

		// Barra lateral (menu)
		JPanel menuLateral = new JPanel();
		menuLateral.setLayout(new GridLayout(9, 1, 5, 5));
		menuLateral.setBackground(new Color(50, 50, 50));

		JButton btnCadastroCliente = new JButton("Cadastro de Clientes");
		JButton btnCadastroVeiculo = new JButton("Cadastro de Veículos");
		JButton btnListagem = new JButton("Listagem de Veículos");
		JButton btnLocacao = new JButton("Locação de Veículos");
		JButton btnDevolucao = new JButton("Devolução de Veículos");
		JButton btnPagamentos = new JButton("Registro de Pagamentos");
		JButton btnRelatorios = new JButton("Relatórios");
		JButton btnLogin = new JButton("Login");
		JButton btnSair = new JButton("Sair");

		// Adiciona os botões ao menu
		menuLateral.add(btnCadastroCliente);
		menuLateral.add(btnCadastroVeiculo);
		menuLateral.add(btnListagem);
		menuLateral.add(btnLocacao);
		menuLateral.add(btnDevolucao);
		menuLateral.add(btnPagamentos);
		menuLateral.add(btnRelatorios);
		menuLateral.add(btnLogin);
		menuLateral.add(btnSair);

		// Painel de conteúdo onde as telas serão exibidas
		JPanel painelConteudo = new JPanel();
		painelConteudo.setLayout(new BorderLayout());

		// Adiciona os painéis à tela principal
		painelPrincipal.add(menuLateral, BorderLayout.WEST);
		painelPrincipal.add(painelConteudo, BorderLayout.CENTER);
		add(painelPrincipal);

		// Eventos dos botões para abrir as telas correspondentes
		btnCadastroVeiculo.addActionListener(e -> new TelaCadastroVeiculo().setVisible(true));
		btnCadastroCliente.addActionListener(e -> new TelaCadastroCliente().setVisible(true));
		btnLocacao.addActionListener(e -> new TelaLocacao().setVisible(true));
		btnDevolucao.addActionListener(e -> new TelaDevolucao().setVisible(true));
		btnListagem.addActionListener(e -> new TelaListagem().setVisible(true));
		btnPagamentos.addActionListener(e -> new TelaPagamentos().setVisible(true));
		btnRelatorios.addActionListener(e -> new TelaRelatorios().setVisible(true));
		btnLogin.addActionListener(e -> new TelaLogin().setVisible(true));
		btnSair.addActionListener(e -> System.exit(0));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
	}
}
