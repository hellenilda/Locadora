package locadora.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import locadora.model.Veiculo;

public class VeiculoDAO implements Persistencia<Veiculo> {
	private static final String ARQUIVO = "veiculos.json";
    private static final Gson gson = new Gson();

	public void salvar(List<Veiculo> veiculos) {
		try (FileWriter writer = new FileWriter(ARQUIVO)) {
			gson.toJson(veiculos, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Veiculo> carregar() {
		try (FileReader reader = new FileReader(ARQUIVO)) {
			return gson.fromJson(reader, new TypeToken<List<Veiculo>>() {}.getType());
		} catch (Exception e) {
			return new ArrayList<>(); // Em caso de erro, retorna uma lista vazia
		}
	}
}