package locadora.dao;

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

import locadora.model.Cliente;

public class ClienteDAO implements Persistencia<Cliente> {
    private static final String REGISTRO_CLIENTES = "registros/clientes.json";
    private List<Cliente> clientes;
    private Gson gson;

    public ClienteDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.clientes = new ArrayList<>();
        carregar(); // Carrega os clientes do arquivo JSON ao iniciar
    }

    @Override
    public void salvar() {
        if (clientes == null) {
            System.err.println("Lista de clientes está nula. Nada foi salvo.");
            return;
        }

        try (FileWriter writer = new FileWriter(REGISTRO_CLIENTES)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes no arquivo JSON: " + e.getMessage());
        }
    }

    @Override
    public void carregar() {
        try (FileReader reader = new FileReader(REGISTRO_CLIENTES)) {
            Type tipoLista = new TypeToken<ArrayList<Cliente>>() {}.getType();
            clientes = gson.fromJson(reader, tipoLista);
            if (clientes == null) {
                clientes = new ArrayList<>(); // Se o arquivo estiver vazio, inicializa a lista
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes do arquivo JSON: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Erro na sintaxe do arquivo JSON: " + e.getMessage());
        }
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null; // Caso não encontre o cliente com o CPF inserido
    }

    public void cadastrarCliente(Cliente cliente) {
        if (cliente == null) {
            System.err.println("Cliente não pode ser nulo!");
            return;
        }

        if (buscarClientePorCpf(cliente.getCpf()) == null) {
            clientes.add(cliente);
            salvar(); // Salva a lista em JSON
        } else {
            System.err.println("Cliente com CPF " + cliente.getCpf() + " já existe!");
        }
    }

    public void atualizarCliente(Cliente clienteAtualizado) {
        if (clienteAtualizado == null) {
            System.err.println("Cliente atualizado não pode ser nulo!");
            return;
        }

        Cliente clienteExistente = buscarClientePorCpf(clienteAtualizado.getCpf());

        if (clienteExistente != null) {
            clientes.remove(clienteExistente);
            clientes.add(clienteAtualizado);
            salvar();
        } else {
            System.err.println("Cliente com CPF " + clienteAtualizado.getCpf() + " não encontrado!");
        }
    }

    public void removerCliente(String cpf) {
        Cliente cliente = buscarClientePorCpf(cpf);

        if (cliente != null) {
            clientes.remove(cliente);
            salvar();
            System.out.println("Cliente com CPF " + cpf + " removido com sucesso!");
        } else {
            System.err.println("Cliente com CPF " + cpf + " não encontrado!");
        }
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }
}