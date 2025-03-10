package locadora.controller;

import java.util.List;

import locadora.dao.VeiculoDAO;
import locadora.model.Caminhao;
import locadora.model.Carro;
import locadora.model.Moto;
import locadora.model.Veiculo;

public class VeiculoController {
	private VeiculoDAO veiculoDAO;

	public VeiculoController() {
		this.veiculoDAO = new VeiculoDAO();
	}

	public void cadastrarVeiculo(String tipo, String placa, String modelo, int ano) {
		Veiculo veiculo = null;

		switch (tipo.toUpperCase()) {
		case "CARRO":
			veiculo = new Carro(placa, tipo, modelo, ano);
			break;
		case "MOTO":
			veiculo = new Moto(placa, tipo, modelo, ano);
			break;
		case "CAMINHAO":
			veiculo = new Caminhao(placa, tipo, modelo, ano);
			break;
		default:
			System.err.println("Tipo de veículo inválido!");
			return;
		}

		veiculoDAO.adicionarVeiculo(veiculo);
	}

	public Veiculo buscarVeiculo(String placa) {
		return veiculoDAO.buscarVeiculoPorPlaca(placa);
	}

	public void atualizarVeiculo(Veiculo veiculoAtualizado) {
		veiculoDAO.atualizarVeiculo(veiculoAtualizado);
	}

	public void removerVeiculo(String placa) {
		veiculoDAO.removerVeiculo(placa);
	}

	public List<Veiculo> listarVeiculos() {
		return veiculoDAO.listarVeiculos();
	}
	
	public List<Veiculo> listarVeiculosDisponiveis() {
		return veiculoDAO.listarVeiculos().stream().filter(v -> v.isDisponivel()).toList();
	}

	public List<Veiculo> listarVeiculosLocados() {
		return veiculoDAO.listarVeiculos().stream().filter(v -> !v.isDisponivel()).toList();
	}
}