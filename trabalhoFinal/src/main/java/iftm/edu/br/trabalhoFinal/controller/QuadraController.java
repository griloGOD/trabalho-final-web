package iftm.edu.br.trabalhoFinal.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import iftm.edu.br.trabalhoFinal.model.Quadra;
import iftm.edu.br.trabalhoFinal.repository.QuadraRepository;
import iftm.edu.br.trabalhoFinal.service.QuadraService;

@Controller
@RequestMapping("/quadras")
public class QuadraController {

	private static final Logger logger = LoggerFactory.getLogger(QuadraController.class);

    @Autowired
    private QuadraRepository quadraRepository;

    @Autowired
    private QuadraService quadraService;

    @GetMapping(value = {"/gerenciarquadras"} )
	public ModelAndView index() {
		logger.trace("Entrou em index");
		ModelAndView mv = new ModelAndView("/quadras/gerenciarquadras");
		//Se voce precisar pode inserir outros objetos no model para que sejam usados
		// na view index.html
		//mv.addObject("nome", valor);
		logger.trace("Encaminhando para a view index");
		return mv;
	}


    @PostMapping("/buscar")
    public String buscarPeloCodigo(Long codigo, Model modelo) {
        logger.trace("Entrou no método buscarPeloCodigo");
        logger.info("Código recebido: {}", codigo);

        if (codigo != null) {
            Optional<Quadra> optContato = quadraRepository.findById(codigo);
            if (optContato.isPresent()) {
                logger.info("Contato buscado no BD: {}", optContato.get());
                modelo.addAttribute("contato", optContato.get());
                logger.trace("Encaminhando para a view mostrarcontato");
                return "mostrarcontato";
            } else {
                logger.info("Não foi encontrado um contato com o codigo: {}", codigo);
                modelo.addAttribute("mensagem", "Não existe um contato com o código " + codigo);
                logger.trace("Encaminhando para a view mostrarmensagem");
                return "mostrarmensagem";
            }
        } else {
            logger.info("Digite um código para buscar");
            modelo.addAttribute("mensagem", "Digite um código para buscar");
            logger.trace("Encaminhando para a view mostrarmensagem");
            return "mostrarmensagem";
        }
    }


    
    @GetMapping(value = {"/adicionaquadra"} )
    public String novaQuadra(Quadra quadra) {
        logger.trace("Encaminhando para a view novaquadra");
        return "/quadras/adiciona";
    }
    
    @PostMapping("/cadastrarquadra")
    public String inserirNovaQuadra(Quadra quadra, RedirectAttributes atributos) {
        logger.debug("Quadra recebida para inserir: {}", quadra);
        quadraService.salvar(quadra);
        atributos.addFlashAttribute("mensagem", "Quadra cadastrada com sucesso!");
        return "redirect:/mostrarmensagem";
        
    }
    
    @PostMapping("/removerquadra")
	public String removerQuadra(Quadra quadra, RedirectAttributes atributos) {
    	logger.debug("Quadra selecionada para remover: {}", quadra);
		quadraService.remover(quadra);
		atributos.addFlashAttribute("mensagem", "Quadra removida com sucesso!");
        return "redirect:/mostrarmensagem";
	}
    
    @GetMapping("/mostrarquadra")
	public String abrirPesquisa(Model model) {
		List<Quadra> quadras = quadraRepository.findAll();
		model.addAttribute("quadras", quadras);
		return "quadras/mostrar";
	}
    @PostMapping("/confirmaremocao")
    public String confirmarRemocao(Quadra quadra) {
        logger.debug("Quadra recebida para confirmar a remocao: {}", quadra);     
        logger.trace("Encaminhando para a view confirmaremove");       
        return "quadras/confirmaremove";
    }
    
    @PostMapping("/fazeralteracao")
    public String alterarQuadra(Quadra quadra) {
        logger.debug("Quadra recebida para alteracao: {}", quadra);     
        logger.trace("Encaminhando para a view confirmaremove");       
        return "quadras/altera";
    }
    
    @PostMapping("/alterarquadra")
	public String alterarQuadra(Quadra quadra, RedirectAttributes atributos) {
    	logger.debug("Quadra selecionada para alteracao: {}", quadra);
		quadraService.salvar(quadra);
		atributos.addFlashAttribute("mensagem", "Quadra altera com sucesso!");
        return "redirect:/mostrarmensagem";
	}

}
