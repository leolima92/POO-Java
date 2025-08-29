package br.senac.sp.batalha;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BatalhaController {

   
    private Jogador jogador;

    
    private Jogador inimigo;
    
    @GetMapping
    public String index() {
        
         this.jogador = null;
        
        this.inimigo = null;
        return "index";
    }

    @PostMapping("jogador")
    public String criarJogador(String nome, int vida, int ataque, int defesa, Model model) {
        
         this.jogador = new Jogador(nome, vida, ataque, defesa);
        return "redirect:batalha";
    }

    @GetMapping("batalha")
    public String batalha(Model model) {
        
        if (jogador == null) {
            return "redirect:/";
        }
       
         if (inimigo == null) {
            inimigo = randomJogador();
        }

        
        model.addAttribute("jogador", jogador);

        model.addAttribute("inimigo", inimigo);
        return "batalha";
    }

    @PostMapping("turno")
    public String turno( Model model) {
        
        model.addAttribute("jogador", jogador);
        model.addAttribute("inimigo", inimigo);
        return "batalha";
    }

    @PostMapping("batalha")
    public String batalha(RedirectAttributes redirect){
        String msg = "";
        
       msg += jogador.atacar(inimigo);
        if (inimigo.getVida() > 0) {
            msg += "<br>" + inimigo.atacar(jogador);
        }
        redirect.addFlashAttribute("msg", msg);
        return "redirect:batalha";
    }

     private Jogador randomJogador() {
         String[] nomes = {"Abysscaller", "Soulbinder", "Voxanomaly", "Galefiend", "Gigglegore"}; 
         int vida = (int) (Math.random() * 50) + 1;
         int ataque = (int) (Math.random() * 20) + 1;
         int defesa = (int) (Math.random() * 10) + 1;
         int i = (int) (Math.random() * nomes.length);
         return new Jogador(nomes[i], vida, ataque, defesa);
     }
}