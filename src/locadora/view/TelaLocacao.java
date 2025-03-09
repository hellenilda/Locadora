package locadora.view;

import locadora.controller.LocacaoController;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.Veiculo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaLocacao extends JFrame {
	private JTextField campoCliente;
	private JTextField campoVeiculo;
	private JTextField campoDias;

	public TelaLocacao() {
		setTitle("Locação de Veículo");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 2, 10, 10));

		// Campos de texto
		campoCliente = new JTextField();
		campoVeiculo = new JTextField();
		campoDias = new JTextField();

		add(new JLabel("Cliente:"));
		add(campoCliente);
		add(new JLabel("Veículo:"));
		add(campoVeiculo);
		add(new JLabel("Dias de Locação:"));
		add(campoDias);

		// Botões
		JButton btnConfirmar = new JButton("Confirmar");
		JButton btnCancelar = new JButton("Cancelar");

		btnConfirmar.addActionListener(e -> confirmarLocacao());
		btnCancelar.addActionListener(e -> dispose());

		add(btnConfirmar);
		add(btnCancelar);

		setVisible(true);
	}

	private void confirmarLocacao() {
		String cpfCliente = campoCliente.getText();
		String placaVeiculo = campoVeiculo.getText();
		int dias = Integer.parseInt(campoDias.getText());

		try {
	        LocacaoDAO locacaoDAO = new LocacaoDAO();
	        VeiculoDAO veiculoDAO = new VeiculoDAO();
			
			LocacaoController locacaoController = new LocacaoController(locacaoDAO, veiculoDAO);
			
	        Veiculo veiculo = veiculoDAO.buscarVeiculoPorPlaca(placaVeiculo);

	        if (veiculo == null) {
	            JOptionPane.showMessageDialog(this, "Veículo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Registra a locação
	        locacaoController.registrarLocacao(veiculo, cpfCliente, LocalDate.now(), LocalDate.now().plusDays(dias));

	        JOptionPane.showMessageDialog(this, "Locação registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	        dispose(); // Fecha a tela após a locação
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Erro ao registrar locação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public static void main(String[] args) {
		new TelaLocacao();
	}
}
