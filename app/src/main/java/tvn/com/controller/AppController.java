package tvn.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tvn.com.entity.Server;
import tvn.com.entity.User;
import tvn.com.service.ServerService;
import tvn.com.service.UserService;

@Controller
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	ServerService serverService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/home")
	public String goHome(Model model) {
		model.addAttribute("list", serverService.findAll());
		return "home";
	}
	
	@GetMapping("/login")
	public String goLogin() {
		return "login";
	}
	
	@GetMapping("/register")
	public String goRegister() {
		return "register";
	}
	
	@GetMapping("/server/{id}/edit")
	public String goEditServer(@PathVariable Integer id, Model model) {
		model.addAttribute("server",serverService.findOne(id));
		return "editServer";
	}
	
	@GetMapping("/server/{id}/register")
	public String goRegisterServer(@PathVariable Integer id, Model model) {
		model.addAttribute("server",serverService.findOne(id));
		return "registerServer";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = userService.findByEmailAndPassword(email, password); 
		if(user != null) {
			session.setAttribute("user", email);
			session.setAttribute("u", user);
			return "redirect:/app/home";
		} else {
			return "login";
		}
	}
	
	@GetMapping("/doLogout")
	public String doLogout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	@PostMapping("/doRegister")
	public String doRegister(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User(email, password);
		userService.save(user);
		return "login";
	}
	
	@PostMapping("/server/register")
	public String doRegister(@Valid Server server, BindingResult result, RedirectAttributes redirect, HttpSession session) {
		
		User user = (User) session.getAttribute("u");
		server.setUser(user);
		serverService.save(server);
		return "redirect:/app/home";
	}
	
	@PostMapping("/server/save")
	public String doEditServer(@Valid Server server, BindingResult result, RedirectAttributes redirect) {
		
		serverService.save(server);
		return "redirect:/app/home";
	}
	
	@GetMapping("/server/{id}/unRegister")
	public String doUnregisterServer(@PathVariable Integer id) {
		
		Server server = serverService.findOne(id);
		server.setUser(null);
		server.setEndDate(null);
		server.setStartDate(null);
		server.setNote(null);
		
		serverService.save(server);
		return "redirect:/app/home";
	}
	
	
}
