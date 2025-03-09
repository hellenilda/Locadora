package locadora.view;

import locadora.controller.VeiculoController;
import locadora.model.Veiculo;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaListagem extends JFrame {
	public TelaListagem() {
		setTitle("Listagem de Veículos");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		String[] colunas = { "Placa", "Modelo", "Ano", "Status" };
		VeiculoController veiculoController = new VeiculoController();
		List<Veiculo> veiculos = veiculoController.listarVeiculos();

		// Converte a lista de veículos para um array de objetos
		Object[][] dados = new Object[veiculos.size()][4];
		for (int i = 0; i < veiculos.size(); i++) {
			Veiculo veiculo = veiculos.get(i);
			dados[i][0] = veiculo.getPlaca();
			dados[i][1] = veiculo.getModelo();
			dados[i][2] = veiculo.getAno();
			dados[i][3] = veiculo.isDisponivel() ? "Disponível" : "Locado";
		}

		JTable tabela = new JTable(dados, colunas);
		JScrollPane scrollPane = new JScrollPane(tabela);

		add(scrollPane, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new TelaListagem().setVisible(true));
	}
}
