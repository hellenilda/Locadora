package locadora.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import locadora.model.Veiculo;

public class VeiculoDAO implements Persistencia<Veiculo> {
	private static final String ARQUIVO_JSON = "veiculos.json";
	List<Veiculo> veiculos;
	private Gson gson;

	public void salvar() {
		try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
			gson.toJson(veiculos, writer);
		} catch (Exception e) {
			System.err.println("Erro ao salvar veículos no arquivo JSON: " + e.getMessage());
		}
	}

	public void carregar() {
		try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
			Type tipoLista = new TypeToken<ArrayList<Veiculo>>() {
			}.getType();
			veiculos = gson.fromJson(reader, tipoLista);
			if (veiculos == null) {
				veiculos = new ArrayList<Veiculo>(); // Se o arquivo estiver vazio, inicializa a lista
			}
		} catch (Exception e) {
			System.err.println("Erro ao carregar veículos do arquivo JSON: " + e.getMessage());
		}
	}

	public Veiculo buscarVeiculoPorPlaca(String placa) {
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				return veiculo;
			}
		}

		return null; // Caso não encontre o veículo com a placa inserida
	}

	public void adicionarVeiculo(Veiculo veiculo) {
		if (buscarVeiculoPorPlaca(veiculo.getPlaca()) == null) {
			veiculos.add(veiculo);
			salvar(); // Salva a lista em JSON
		} else {
			System.err.println("Veículo com placa " + veiculo.getPlaca() + " já existe!");
		}
	}

	public void atualizarVeiculo(Veiculo veiculoAtualizado) {
		Veiculo veiculoExistente = buscarVeiculoPorPlaca(veiculoAtualizado.getPlaca());

		if (veiculoExistente != null) {
			veiculos.remove(veiculoExistente);
			veiculos.add(veiculoAtualizado);
			salvar();
		} else {
			System.err.println("Veículo com placa " + veiculoAtualizado.getPlaca() + " não encontrado!");
		}
	}

	public void removerVeiculo(String placa) {
		Veiculo veiculo = buscarVeiculoPorPlaca(placa);

		if (veiculo != null) {
			veiculos.remove(veiculo);
			salvar();
		} else {
			System.err.println("Veiculo com placa " + placa + " não encontrado!");
		}
	}

	public List<Veiculo> listarVeiculos() {
		return veiculos;
	}
}