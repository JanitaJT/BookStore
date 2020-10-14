package hh.swd20.bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.SignUpForm;
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignUpForm());
		return "signup";
	}

	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signUpForm") SignUpForm signUpForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (signUpForm.getPassword().equals(signUpForm.getPasswordCheck())) {
				String pwd = signUpForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hasPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hasPwd);
				newUser.setUsername(signUpForm.getUsername());
				newUser.setEmail(signUpForm.getEmail());
				newUser.setRole("USER");
				if (userRepository.findByUsername(signUpForm.getUsername()) == null) {
					userRepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}

			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Password doesn't match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}
}