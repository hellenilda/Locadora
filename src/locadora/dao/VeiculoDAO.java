package locadora.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import locadora.model.Veiculo;

public class VeiculoDAO implements Persistencia<Veiculo> {
	private static final String REGISTRO_VEICULOS = "registros/veiculos.json";
	private List<Veiculo> veiculos;
	private Gson gson;

	public VeiculoDAO() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		this.veiculos = new ArrayList<>();
		carregar(); // Ao iniciar, carrega os veículos do arquivo JSON
	}

	// Salva o pagamento em JSON
	@Override
	public void salvar() {
		if (veiculos == null) {
			System.err.println("Lista de veículos está nula. Nada foi salvo.");
			return;
		}

		try (FileWriter writer = new FileWriter(REGISTRO_VEICULOS)) {
			gson.toJson(veiculos, writer);
		} catch (IOException e) {
			System.err.println("Erro ao salvar veículos no arquivo JSON: " + e.getMessage());
		}
	}

	// Carrega o registro salvo em JSON
	@Override
	public void carregar() {
		File arquivo = new File(REGISTRO_VEICULOS);

		// Se o arquivo não existe ou está vazio, inicializa a lista e retorna
		if (!arquivo.exists() || arquivo.length() == 0) {
			veiculos = new ArrayList<>();
			return;
		}

		try (FileReader reader = new FileReader(REGISTRO_VEICULOS)) {
			Type tipoLista = new TypeToken<ArrayList<Veiculo>>() {
			}.getType();
			veiculos = gson.fromJson(reader, tipoLista);
			if (veiculos == null) {
				veiculos = new ArrayList<Veiculo>(); // Se o arquivo estiver vazio, inicializa a lista
			}
		} catch (IOException e) {
			System.err.println("Erro ao carregar veículos do arquivo JSON: " + e.getMessage());
		} catch (JsonSyntaxException e) {
			System.err.println("Erro na sintaxe do arquivo JSON: " + e.getMessage());
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
		if (veiculo == null) {
			System.err.println("Veículo não pode ser nulo!");
			return;
		}

		if (buscarVeiculoPorPlaca(veiculo.getPlaca()) == null) {
			veiculos.add(veiculo);
			salvar(); // Salva a lista em JSON
		} else {
			System.err.println("Veículo com placa " + veiculo.getPlaca() + " já existe!");
		}
	}

	public void atualizarVeiculo(Veiculo veiculoAtualizado) {
		if (veiculoAtualizado == null) {
			System.err.println("Veículo atualizado não pode ser nulo!");
			return;
		}

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
			System.out.println("Veículo com placa " + placa + " removido com sucesso!");
		} else {
			System.err.println("Veículo com placa " + placa + " não encontrado!");
		}
	}

	public List<Veiculo> listarVeiculos() {
		return veiculos;
	}
}