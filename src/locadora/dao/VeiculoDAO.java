package locadora.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import locadora.model.Veiculo;

public class VeiculoDAO implements Persistencia<Veiculo> {
	private static final String ARQUIVO = "veiculos.java";

	public void salvar(List<Veiculo> veiculos) {
		try (FileWriter writer = new FileWriter(ARQUIVO)) {
			// A implementar
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Veiculo> carregar() {
		try (FileReader reader = new FileReader(ARQUIVO)) {
			// A implementar
			return null;
		} catch (Exception e) {
			return null; // A implementar
		}
	}
}