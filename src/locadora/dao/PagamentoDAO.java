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

import locadora.model.Pagamento;
import locadora.utils.LocalDateAdapter;

public class PagamentoDAO implements Persistencia<Pagamento> {
	private static final String REGISTRO_PAGAMENTOS = "registros/pagamentos.json";
	private List<Pagamento> pagamentos;
	private Gson gson;

	public PagamentoDAO() {
		this.gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();
		this.pagamentos = new ArrayList<>();
		carregar(); // Carrega os pagamentos do arquivo JSON ao iniciar
	}

	@Override
	public void salvar() {
		if (pagamentos == null) {
			System.err.println("Lista de pagamentos está nula. Nada foi salvo.");
			return;
		}

		try (FileWriter writer = new FileWriter(REGISTRO_PAGAMENTOS)) {
			gson.toJson(pagamentos, writer);
		} catch (IOException e) {
			System.err.println("Erro ao salvar pagamentos no arquivo JSON: " + e.getMessage());
		}
	}

	@Override
	public void carregar() {
		File arquivo = new File(REGISTRO_PAGAMENTOS);

		// Se o arquivo não existe ou está vazio, inicializa a lista e retorna
		if (!arquivo.exists() || arquivo.length() == 0) {
			pagamentos = new ArrayList<>();
			return;
		}

		try (FileReader reader = new FileReader(REGISTRO_PAGAMENTOS)) {
			Type tipoLista = new TypeToken<ArrayList<Pagamento>>() {
			}.getType();
			pagamentos = gson.fromJson(reader, tipoLista);
			if (pagamentos == null) {
				pagamentos = new ArrayList<>(); // Se o arquivo estiver vazio, inicializa a lista
			}
		} catch (IOException e) {
			System.err.println("Erro ao carregar pagamentos do arquivo JSON: " + e.getMessage());
		} catch (JsonSyntaxException e) {
			System.err.println("Erro na sintaxe do arquivo JSON: " + e.getMessage());
		}
	}

	public void adicionarPagamento(Pagamento pagamento) {
		if (pagamento == null) {
			System.err.println("Pagamento não pode ser nulo!");
			return;
		}

		if (buscarPagamentoPorId(pagamento.getId()) == null) {
			pagamentos.add(pagamento);
			salvar(); // Salva a lista em JSON
		} else {
			System.err.println("Pagamento com ID " + pagamento.getId() + " já existe!");
		}
	}

	public Pagamento buscarPagamentoPorId(String id) {
		for (Pagamento pagamento : pagamentos) {
			if (pagamento.getId().equals(id)) {
				return pagamento;
			}
		}
		return null; // Caso não encontre o pagamento com o ID inserido
	}

	public List<Pagamento> buscarPagamentosPorLocacao(String idLocacao) {
		List<Pagamento> pagamentosLocacao = new ArrayList<>();
		for (Pagamento pagamento : pagamentos) {
			if (pagamento.getIdLocacao().equals(idLocacao)) {
				pagamentosLocacao.add(pagamento);
			}
		}
		return pagamentosLocacao;
	}

	public void atualizarPagamento(Pagamento pagamentoAtualizado) {
		if (pagamentoAtualizado == null) {
			System.err.println("Pagamento atualizado não pode ser nulo!");
			return;
		}

		Pagamento pagamentoExistente = buscarPagamentoPorId(pagamentoAtualizado.getId());

		if (pagamentoExistente != null) {
			pagamentos.remove(pagamentoExistente);
			pagamentos.add(pagamentoAtualizado);
			salvar();
		} else {
			System.err.println("Pagamento com ID " + pagamentoAtualizado.getId() + " não encontrado!");
		}
	}

	public void removerPagamento(String id) {
		Pagamento pagamento = buscarPagamentoPorId(id);

		if (pagamento != null) {
			pagamentos.remove(pagamento);
			salvar();
			System.out.println("Pagamento com ID " + id + " removido com sucesso!");
		} else {
			System.err.println("Pagamento com ID " + id + " não encontrado!");
		}
	}

	public List<Pagamento> listarPagamentos() {
		return pagamentos;
	}
}