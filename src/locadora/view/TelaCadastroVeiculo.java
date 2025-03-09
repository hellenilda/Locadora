package locadora.view;

import locadora.controller.VeiculoController;
import locadora.model.Veiculo;
import locadora.model.Carro;
import locadora.model.Moto;
import locadora.model.Caminhao;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroVeiculo extends JFrame {
	private JTextField campoPlaca;
	private JTextField campoModelo;
	private JTextField campoAno;
	private JComboBox<String> comboTipo;

	public TelaCadastroVeiculo() {
		setTitle("Cadastro de Veículo");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(5, 2, 10, 10));

		// Campos de texto
		campoPlaca = new JTextField();
		campoModelo = new JTextField();
		campoAno = new JTextField();
		comboTipo = new JComboBox<>(new String[] { "Carro", "Moto", "Caminhão" });

		add(new JLabel("Placa:"));
		add(campoPlaca);
		add(new JLabel("Modelo:"));
		add(campoModelo);
		add(new JLabel("Ano:"));
		add(campoAno);
		add(new JLabel("Tipo:"));
		add(comboTipo);

		// Botões
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");

		btnSalvar.addActionListener(e -> salvarVeiculo());
		btnCancelar.addActionListener(e -> dispose());

		add(btnSalvar);
		add(btnCancelar);

		setVisible(true);
	}

	private void salvarVeiculo() {
		String placa = campoPlaca.getText();
		String modelo = campoModelo.getText();
		int ano = Integer.parseInt(campoAno.getText());
		String tipo = (String) comboTipo.getSelectedItem();

		try {
			VeiculoController veiculoController = new VeiculoController();
			veiculoController.cadastrarVeiculo(tipo, placa, modelo, ano);

			JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			dispose(); // Fecha a tela após o cadastro
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar veículo: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new TelaCadastroVeiculo();
	}
}
