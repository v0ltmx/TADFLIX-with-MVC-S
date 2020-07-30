package tads.aula;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class FilmeController {

    private FilmeService filmeService;

    @Autowired
    public void setFilmeService(FilmeService filmeService){
        this.filmeService = filmeService;
    }

    @RequestMapping("/")
    public String getHome(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(); //retorna a sessao ou cria uma nova
        session.getAttribute("filmes");

        if(!session.isNew()){
            Date acessed = new Date(session.getLastAccessedTime());

            response.getWriter().println("Acesso: " + acessed);
        }
        List<Filme> filmeList = filmeService.findAll();
        model.addAttribute("filmeList", filmeList);
        return "index";
    }

    @RequestMapping("/cadastrar")
    public String getPageCadstro(Model model ,HttpServletResponse response){
        var filme = new Filme();
        model.addAttribute("filme", filme);
        return "cadastrar";

    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String addFilme(@ModelAttribute @Valid Filme filme , Errors erro, HttpServletResponse response) throws IOException {
        if(erro.hasErrors()){
            response.getWriter().println("Preencha os campos..");
            return "cadastrar";

        }
        else{
            filmeService.add(filme);
            return "send";
        }

    }

     @RequestMapping("/editar/{id}")
        public ModelAndView editFilme(@PathVariable(name = "id") Long id){
            var modelAndView = new ModelAndView("editar");
            var filme = filmeService.get(id);
            modelAndView.addObject("filme",filme);
            return modelAndView;
        }
        @RequestMapping("/deletar/{id}")
        public String deleteFilme(@PathVariable(name = "id") Long id){
            filmeService.delete(id);
            return "redirect:/";
        }
    }

