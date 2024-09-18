package iftm.edu.br.trabalhoFinal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iftm.edu.br.trabalhoFinal.model.Cliente;
import iftm.edu.br.trabalhoFinal.repository.ClienteRepository;

@Service
public class CadastroUsuarioService {

	private static final Logger logger = LoggerFactory.getLogger(CadastroUsuarioService.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public void salvar(Cliente cliente) {
		logger.trace("Entrou em salvar");
		logger.debug("Salvando o usuario {}", cliente);
		clienteRepository.save(cliente);
		logger.debug("Usuario salvo com sucesso {}", cliente);
	}
	
	@Transactional
	public boolean isNomeUsuarioJaUsado(String usuario) {
		logger.trace("Entrou em isNomeUsuarioJaUsado");
		logger.debug("nomeUsuario recebido para testar {}", usuario);
		return clienteRepository.findByUsuario(usuario) != null;
	}
}