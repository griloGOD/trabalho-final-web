package iftm.edu.br.trabalhoFinal.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import iftm.edu.br.trabalhoFinal.model.Cliente;
import iftm.edu.br.trabalhoFinal.model.Quadra;
import iftm.edu.br.trabalhoFinal.model.Reserva;
import iftm.edu.br.trabalhoFinal.repository.ClienteRepository;
import iftm.edu.br.trabalhoFinal.repository.QuadraRepository;
import iftm.edu.br.trabalhoFinal.repository.ReservaRepository;
import iftm.edu.br.trabalhoFinal.service.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

	private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private QuadraRepository quadraRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping(value = {"/reservarquadras"} )
  	public String index(Model model) {
  		logger.trace("Entrou em index");
  		List<Quadra> quadras = quadraRepository.findAll();
		model.addAttribute("quadras", quadras);
  		logger.trace("Encaminhando para a view index");
  		return "/reservas/gerarreservas";
  	}
    
    @PostMapping("/cadastrarreserva")
    public String inserirNovaReserva(RedirectAttributes atributos, Principal principal, Long quadra, Date dia, Integer horario) {
    	List<Reserva> reservas = reservaRepository.findByDia(dia);
    	List<Reserva> reservasquadra = new ArrayList<>();
    	Boolean b = false;
    	Reserva reserva = new Reserva();
    	logger.debug("lista de reservas:{}", reservas);
    	for (Reserva r: reservas) {
    		logger.debug("lista de reservas/quadra:{}", r.getQuadra().getCodigo());
    		if (r.getQuadra().getCodigo() == quadra) {
    			reservasquadra.add(r);
    		}
    	}
    	logger.debug("reservas quadra{}", reservasquadra);
    	for (Reserva r: reservasquadra) {
    		
    		if (r.getHorario() == horario) {
    			b = true;
    		}
    	}
    	if (b) {
    		logger.info("HORARIO JA RESERVADO");
            atributos.addFlashAttribute("mensagem", "Este Horario está indisponível no momento!");
    	}
    	else {
    		logger.info("HORARIO DISPONIVEL");
        	Quadra q = quadraRepository.getById(quadra);
    		Cliente cliente = clienteRepository.findByUsuario(principal.getName());
    		reserva.setDia(dia);
    		reserva.setHorario(horario);
    		reserva.setQuadra(q);
    		reserva.setCliente(cliente);
    		reservaService.salvar(reserva);
            atributos.addFlashAttribute("mensagem", "Reserva Realizada com sucesso!");
    	}
        
        return "redirect:/mostrarmensagem";
        
    } 
    
    @GetMapping("/mostrarreservas")
	public String abrirPesquisa(Model model, Long quadra) {
		List<Reserva> reservas = reservaRepository.findAll();
		model.addAttribute("reservas", reservas);
		return "reservas/mostrar_reservas";
	}
    
}
