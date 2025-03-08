package locadora.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import locadora.model.Locacao;
import locadora.utils.LocalDateAdapter;

public class LocacaoDAO implements Persistencia<Locacao> {
	private static final String REGISTRO_LOCACOES = "registros/locacoes.json";
	private List<Locacao> locacoes;
	private Gson gson;

	public LocacaoDAO() {
		this.gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();
		this.locacoes = new ArrayList<>();
		carregar(); // Carrega as locações do arquivo JSON ao iniciar
	}

	@Override
	public void salvar() {
		if (locacoes == null) {
			System.err.println("Lista de locações está nula. Nada foi salvo.");
			return;
		}

		try (FileWriter writer = new FileWriter(REGISTRO_LOCACOES)) {
			gson.toJson(locacoes, writer);
		} catch (IOException e) {
			System.err.println("Erro ao salvar locações no arquivo JSON: " + e.getMessage());
		}
	}

	@Override
	public void carregar() {
		File arquivo = new File(REGISTRO_LOCACOES);

		// Se o arquivo não existe ou está vazio, inicializa a lista e retorna
		if (!arquivo.exists() || arquivo.length() == 0) {
			locacoes = new ArrayList<>();
			return;
		}
		try (FileReader reader = new FileReader(REGISTRO_LOCACOES)) {
			Type tipoLista = new TypeToken<ArrayList<Locacao>>() {
			}.getType();
			locacoes = gson.fromJson(reader, tipoLista);
			if (locacoes == null) {
				locacoes = new ArrayList<>(); // Se o arquivo estiver vazio, inicializa a lista
			}
		} catch (IOException e) {
			System.err.println("Erro ao carregar locações do arquivo JSON: " + e.getMessage());
		} catch (JsonSyntaxException e) {
			System.err.println("Erro na sintaxe do arquivo JSON: " + e.getMessage());
		}
	}

	public void adicionarLocacao(Locacao locacao) {
		if (locacao == null) {
			System.err.println("Locação não pode ser nula!");
			return;
		}

		if (buscarLocacaoPorId(locacao.getId()) == null) {
			locacoes.add(locacao);
			salvar(); // Salva a lista em JSON
		} else {
			System.err.println("Locação com ID " + locacao.getId() + " já existe!");
		}
	}

	public Locacao buscarLocacaoPorId(String id) {
		for (Locacao locacao : locacoes) {
			if (locacao.getId().equals(id)) {
				return locacao;
			}
		}
		return null; // Caso não encontre a locação com o ID inserido
	}

	public Locacao buscarLocacaoPorVeiculo(String placaVeiculo) {
		for (Locacao locacao : locacoes) {
			if (locacao.getVeiculo().getPlaca().equals(placaVeiculo)) {
				return locacao;
			}
		}
		return null; // Caso não encontre a locação para o veículo com a placa inserida
	}

	public void atualizarLocacao(Locacao locacaoAtualizada) {
		if (locacaoAtualizada == null) {
			System.err.println("Locação atualizada não pode ser nula!");
			return;
		}

		Locacao locacaoExistente = buscarLocacaoPorId(locacaoAtualizada.getId());

		if (locacaoExistente != null) {
			locacoes.remove(locacaoExistente);
			locacoes.add(locacaoAtualizada);
			salvar();
		} else {
			System.err.println("Locação com ID " + locacaoAtualizada.getId() + " não encontrada!");
		}
	}

	public void removerLocacao(String id) {
		Locacao locacao = buscarLocacaoPorId(id);

		if (locacao != null) {
			locacoes.remove(locacao);
			salvar();
			System.out.println("Locação com ID " + id + " removida com sucesso!");
		} else {
			System.err.println("Locação com ID " + id + " não encontrada!");
		}
	}

	public List<Locacao> listarLocacoes() {
		return locacoes;
	}
}