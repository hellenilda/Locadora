package locadora.controller;

import java.util.List;

import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.model.Locacao;
import locadora.model.Pagamento;

public class PagamentoController {
	private PagamentoDAO pagamentoDAO;
	private LocacaoDAO locacaoDAO;

	public PagamentoController(PagamentoDAO pagamentoDAO, LocacaoDAO locacaoDAO) {
		this.pagamentoDAO = pagamentoDAO;
		this.locacaoDAO = locacaoDAO;
	}

	// Registra um pagamento
	public void registrarPagamento(String idLocacao, double valorPago, String metodoPagamento) {
		Locacao locacao = locacaoDAO.buscarLocacaoPorId(idLocacao);

		if (locacao == null) {
			System.err.println("Locação não encontrada!");
			return;
		}

		Pagamento pagamento = new Pagamento(idLocacao, valorPago, metodoPagamento);
		pagamentoDAO.adicionarPagamento(pagamento);

		// Atualiza o status da locação
		if (valorPago >= locacao.getValorTotal()) {
			locacao.setPago(true);
			locacaoDAO.atualizarLocacao(locacao);
		}
	}

	// Verifica se uma locação está paga
	public boolean verificarPagamento(String idLocacao) {
		Locacao locacao = locacaoDAO.buscarLocacaoPorId(idLocacao);

		if (locacao == null) {
			System.err.println("Locação não encontrada!");
			return false;
		}

		return locacao.isPago();
	}

	// Lista todos os pagamentos
	public List<Pagamento> listarPagamentos() {
		return pagamentoDAO.listarPagamentos();
	}
}