package iftm.edu.br.trabalhoFinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import iftm.edu.br.trabalhoFinal.model.Cliente;
import iftm.edu.br.trabalhoFinal.model.Papel;
import iftm.edu.br.trabalhoFinal.repository.PapelRepository;
import iftm.edu.br.trabalhoFinal.service.CadastroUsuarioService;


@Controller
@RequestMapping("/clientes") 
public class ClienteController {
  
  private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
  
  
  @Autowired 
  private CadastroUsuarioService cadastroUsuarioService;
  
  @Autowired 
  private PapelRepository papelRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @GetMapping(value = {"/adicionacliente"} ) public String novoCliente(Cliente
  cliente) { logger.trace("Encaminhando para a view novoquadra"); return
  "/clientes/novocliente"; }
  
//  @PostMapping("/cadastrarcliente") public String inserirNovoCliente(Cliente
//  cliente, RedirectAttributes atributos) { Papel papel =
//  papelRepository.getById((long)2);
//  logger.debug("Cliente recebido para inserir: {}", cliente);
//  clienteService.salvar(cliente); atributos.addFlashAttribute("mensagem",
//  "Cliente cadastrado com sucesso!"); return "redirect:/mostrarmensagem";
//  }
  
  @GetMapping("/cadastrarcliente")
	public ModelAndView direcionarCadastroUsuario(Cliente cliente) {
		logger.trace("Entrou em direcionarCadastroUsuario");
		ModelAndView mv = new ModelAndView("cadastrousuario");
		List<Papel> papeis = papelRepository.findAll();
		logger.debug("Papeis encontrados para mostrar {}", papeis);
		mv.addObject("todosPapeis", papeis);
		logger.trace("Encaminhando para a view cadastrousuario");
		return mv;
	}
  
  @PostMapping("/cadastrarcliente")
	public ModelAndView cadastrarNovoUsuario(@Valid Cliente cliente, BindingResult result) {
		logger.trace("Entrou em cadastrarNovoUsuario");
		Papel papel = papelRepository.getById((long)2);
		logger.debug("Usuario recebido {}", cliente);
		logger.debug("Papeis recebidos para o usuario {}", cliente.getPapeis());
		ModelAndView mv;
		if (!result.hasErrors()) {
			cliente.setAtivo(true);
			cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
			cliente.adicionarPapel(papel);
			cadastroUsuarioService.salvar(cliente);
			logger.trace("Redirecionando para a URL /usuario/novo");
			mv = new ModelAndView ("redirect:/login");
		} else {
			logger.debug("O usuario recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for(FieldError erro : result.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			List<Papel> papeis = papelRepository.findAll();
			logger.debug("Papeis encontrados para mostrar {}", papeis);
			mv = new ModelAndView("/clientes/novocliente");
			mv.addObject("todosPapeis", papeis);
			logger.trace("Encaminhando para a view novocliente");
		}
		return mv;
	}
  
}
 
//@Controller
//@RequestMapping("/cliente")
//public class ClienteController {
//
//	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
//	
//	@Autowired
//	private PapelRepository papelRepository;
//	
//	@Autowired
//	private CadastroUsuarioService cadastroUsuarioService;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@GetMapping("/novo")
//	public ModelAndView direcionarCadastroUsuario(Usuario usuario) {
//		logger.trace("Entrou em direcionarCadastroUsuario");
//		ModelAndView mv = new ModelAndView("cadastrousuario");
//		List<Papel> papeis = papelRepository.findAll();
//		logger.debug("Papeis encontrados para mostrar {}", papeis);
//		mv.addObject("todosPapeis", papeis);
//		logger.trace("Encaminhando para a view cadastrousuario");
//		return mv;
//	}
//	
//	@PostMapping("/novo")
//	public ModelAndView cadastrarNovoUsuario(Usuario usuario) {
//		logger.trace("Entrou em cadastrarNovoUsuario");
//		logger.debug("Usuario recebido {}", usuario);
//		logger.debug("Papeis recebidos para o usuario {}", usuario.getPapeis());
//		ModelAndView mv;
//		if (!usuario.getPapeis().isEmpty()) {
//			usuario.setAtivo(true);
//			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
//			cadastroUsuarioService.salvar(usuario);
//			logger.trace("Redirecionando para a URL /usuario/novo");
//			mv = new ModelAndView ("redirect:/usuario/novo");
//		} else {
//			logger.error("Nenhum papel colocado no usuario");
//			List<Papel> papeis = papelRepository.findAll();
//			logger.debug("Papeis encontrados para mostrar {}", papeis);
//			mv = new ModelAndView("cadastrousuario");
//			mv.addObject("todosPapeis", papeis);
//			logger.trace("Encaminhando para a view cadastrousuario");
//		}
//		return mv;
//	}
//}


