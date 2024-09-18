package iftm.edu.br.trabalhoFinal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iftm.edu.br.trabalhoFinal.model.Cliente;
import iftm.edu.br.trabalhoFinal.repository.ClienteRepository;

@Service
public class ClienteService {
 private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);
 
 @Autowired
 private ClienteRepository clienteRepository;
 
 @Transactional
 public void salvar(Cliente cliente) {
     logger.debug("Salvando o cliente {}", cliente);
     clienteRepository.save(cliente);
     logger.debug("Cliente cadastrado com sucesso");
 }
 
}
