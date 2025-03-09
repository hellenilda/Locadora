package locadora.view;

import locadora.controller.PagamentoController;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaPagamentos extends JFrame {
	private JTextField campoIdLocacao;
	private JTextField campoValor;
	private JTextField campoData;
	private JComboBox<String> comboTipoPagamento;

	public TelaPagamentos() {
		setTitle("Registro de Pagamentos");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));

		// Campos de texto
		campoIdLocacao = new JTextField();
		campoValor = new JTextField();
		campoData = new JTextField();
		comboTipoPagamento = new JComboBox<>(new String[] { "Dinheiro", "Cartão" });

		painel.add(new JLabel("ID Locação:"));
		painel.add(campoIdLocacao);
		painel.add(new JLabel("Valor Pago:"));
		painel.add(campoValor);
		painel.add(new JLabel("Data Pagamento:"));
		painel.add(campoData);
		painel.add(new JLabel("Método:"));
		painel.add(comboTipoPagamento);

		// Botão
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(e -> registrarPagamento());
		painel.add(btnRegistrar);

		add(painel);
	}

	private void registrarPagamento() {
		String idLocacao = campoIdLocacao.getText();
		double valorPago = Double.parseDouble(campoValor.getText());
		LocalDate dataPagamento = LocalDate.parse(campoData.getText());
		String metodoPagamento = (String) comboTipoPagamento.getSelectedItem();

		try {
			PagamentoDAO pagamentoDAO = new PagamentoDAO();
			LocacaoDAO locacaoDAO = new LocacaoDAO();
			
			PagamentoController pagamentoController = new PagamentoController(pagamentoDAO, locacaoDAO);
			pagamentoController.registrarPagamento(idLocacao, valorPago, metodoPagamento);

			JOptionPane.showMessageDialog(this, "Pagamento registrado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			dispose(); // Fecha a tela após o registro
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao registrar pagamento: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new TelaPagamentos().setVisible(true));
	}
}
