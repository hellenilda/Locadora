package locadora.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.Locacao;
import locadora.model.Veiculo;

public class LocacaoController {
	private LocacaoDAO locacaoDAO;
	private VeiculoDAO veiculoDAO;

	public LocacaoController(LocacaoDAO locacaoDAO, VeiculoDAO veiculoDAO) {
		this.locacaoDAO = locacaoDAO;
		this.veiculoDAO = veiculoDAO;
	}

	public void registrarLocacao(Veiculo veiculo, String cpfCliente, LocalDate dataRetirada,
			LocalDate dataDevolucaoPrevista) {
		if (veiculo == null || !veiculo.isDisponivel()) {
			System.err.println("Veículo não disponível para locação!");
			return;
		}

		Locacao locacao = new Locacao(veiculo, cpfCliente, dataRetirada, dataDevolucaoPrevista);
		locacaoDAO.adicionarLocacao(locacao);

		// Marca o veículo como indisponível
		veiculo.setDisponivel(false);
		veiculoDAO.atualizarVeiculo(veiculo);
	}

	public double registrarDevolucao(String placaVeiculo, LocalDate dataDevolucaoReal) {
		Locacao locacao = locacaoDAO.buscarLocacaoPorVeiculo(placaVeiculo);

		if (locacao == null) {
			System.err.println("Locação não encontrada para o veículo com placa " + placaVeiculo);
			return 0.0;
		}

		// Calcula o valor da locação
		long diasLocacao = ChronoUnit.DAYS.between(locacao.getDataRetirada(), dataDevolucaoReal);
		double valorLocacao = locacao.getVeiculo().calcularCustoLocacao((int) diasLocacao);

		// Verifica se houve atraso e aplica multa
		if (dataDevolucaoReal.isAfter(locacao.getDataDevolucaoPrevista())) {
			long diasAtraso = ChronoUnit.DAYS.between(locacao.getDataDevolucaoPrevista(), dataDevolucaoReal);
			double multa = diasAtraso * 50.0; // Multa de R$ 50 por dia de atraso
			valorLocacao += multa;
			System.out.println("Multa por atraso: R$ " + multa);
		}

		// Marca o veículo como disponível
		locacao.getVeiculo().setDisponivel(true);
		veiculoDAO.atualizarVeiculo(locacao.getVeiculo());

		// Finaliza a locação
		locacao.setDataDevolucaoReal(dataDevolucaoReal);
		locacao.setValorTotal(valorLocacao);
		locacaoDAO.atualizarLocacao(locacao);

		return valorLocacao;
	}

	public List<Locacao> listarLocacoes() {
		return locacaoDAO.listarLocacoes();
	}

	public Locacao buscarLocacaoPorVeiculo(String placaVeiculo) {
		return locacaoDAO.buscarLocacaoPorVeiculo(placaVeiculo);
	}
}