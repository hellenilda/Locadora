package locadora.controller;

import java.util.List;
import locadora.dao.ClienteDAO;
import locadora.model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.cadastrarCliente(cliente);
    }

    public Cliente buscarCliente(String cpf) {
        return clienteDAO.buscarClientePorCpf(cpf);
    }

    public void atualizarCliente(Cliente clienteAtualizado) {
        clienteDAO.atualizarCliente(clienteAtualizado);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }
}