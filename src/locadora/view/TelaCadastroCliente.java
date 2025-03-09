package locadora.view;

import javax.swing.*;
import java.awt.*;

import locadora.controller.ClienteController;
import locadora.dao.ClienteDAO;
import locadora.model.Cliente;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroCliente extends JFrame {
	private JTextField campoNome;
	private JTextField campoCpf;
	private JTextField campoTelefone;
	private JTextField campoEmail;

	public TelaCadastroCliente() {
		setTitle("Cadastro de Cliente");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(5, 2, 10, 10));

		// Campos de texto
		campoNome = new JTextField();
		campoCpf = new JTextField();
		campoTelefone = new JTextField();
		campoEmail = new JTextField();

		add(new JLabel("Nome:"));
		add(campoNome);
		add(new JLabel("CPF:"));
		add(campoCpf);
		add(new JLabel("Telefone:"));
		add(campoTelefone);
		add(new JLabel("E-mail:"));
		add(campoEmail);

		// Botões
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");

		btnSalvar.addActionListener(e -> salvarCliente());
		btnCancelar.addActionListener(e -> dispose());

		add(btnSalvar);
		add(btnCancelar);

		setVisible(true);
	}

	private void salvarCliente() {
		String nome = campoNome.getText();
		String cpf = campoCpf.getText();
		String telefone = campoTelefone.getText();
		String email = campoEmail.getText();

		if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Cliente cliente = new Cliente(nome, cpf, telefone, email);
			ClienteDAO clienteDAO = new ClienteDAO(); // Cria o DAO
			ClienteController clienteController = new ClienteController(clienteDAO); // Passa o DAO para o Controller
			clienteController.cadastrarCliente(cliente);

			JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			dispose(); // Fecha a tela após o cadastro
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new TelaCadastroCliente();
	}
}
