package locadora.dao;

import locadora.model.Carro;
import locadora.model.Moto;
import locadora.model.Veiculo;

public class TesteVeiculoDAO {
	public static void main(String[] args) {
		VeiculoDAO veiculoDAO = new VeiculoDAO();

		// Adicionando veículos
		veiculoDAO.adicionarVeiculo(new Carro("ABC-1234", "Fusca", 1970));
		veiculoDAO.adicionarVeiculo(new Moto("XYZ-5678", "CG 150", 2020));

		// Buscando veículo
		Veiculo veiculo = veiculoDAO.buscarVeiculoPorPlaca("ABC-1234");
		System.out.println("Veículo encontrado: " + veiculo);

		// Listando veículos
		System.out.println("Todos os veículos:");
		veiculoDAO.listarVeiculos().forEach(System.out::println);
	}
}