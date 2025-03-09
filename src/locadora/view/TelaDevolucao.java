package locadora.view;

import locadora.controller.LocacaoController;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaDevolucao extends JFrame {
	private JTextField campoVeiculo;
	private JTextField campoDataDevolucao;

	public TelaDevolucao() {
		setTitle("Devolução de Veículo");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 2, 10, 10));

		// Campos de texto
		campoVeiculo = new JTextField();
		campoDataDevolucao = new JTextField();

		add(new JLabel("Veículo:"));
		add(campoVeiculo);
		add(new JLabel("Data de Devolução:"));
		add(campoDataDevolucao);

		// Botões
		JButton btnFinalizar = new JButton("Finalizar");
		JButton btnCancelar = new JButton("Cancelar");

		btnFinalizar.addActionListener(e -> finalizarDevolucao());
		btnCancelar.addActionListener(e -> dispose());

		add(btnFinalizar);
		add(btnCancelar);

		setVisible(true);
	}

	private void finalizarDevolucao() {
		String placaVeiculo = campoVeiculo.getText();
		LocalDate dataDevolucao = LocalDate.parse(campoDataDevolucao.getText());

		try {
	        LocacaoDAO locacaoDAO = new LocacaoDAO();
	        VeiculoDAO veiculoDAO = new VeiculoDAO();
			
			LocacaoController locacaoController = new LocacaoController(locacaoDAO, veiculoDAO);
			double valorTotal = locacaoController.registrarDevolucao(placaVeiculo, dataDevolucao);

			JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso! Valor total: R$ " + valorTotal,
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			dispose(); // Fecha a tela após a devolução
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao registrar devolução: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new TelaDevolucao();
	}
}
