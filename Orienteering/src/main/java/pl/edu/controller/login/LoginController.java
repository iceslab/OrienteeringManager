package pl.edu.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.controller.BaseController;
import pl.edu.controller.login.form.LoginForm;
import pl.edu.controller.login.form.LoginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by bartosz on 11.04.16.
 */
@Controller("loginController")
public class LoginController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @ModelAttribute("loginForm")
    public LoginForm form() {
        return new LoginForm();
    }

    @RequestMapping(value = { "/login", "/login/" })
    public String homepage() {
        return "login";
    }

    @RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.POST)
    public String login(Model model, LoginForm loginForm, BindingResult result, HttpServletRequest request) {
        LoginValidator validator = new LoginValidator();
        validator.validate(loginForm, result);
        String resultView = "login";
        String errorString = "";
        if (!validator.hasErrors()) {
            try {
                // sprawdzamy czy haslo i user sie zgadza
                Authentication authenticate = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginForm.getUser().getEmail(),
                                loginForm.getUser().getPassword()));

                if(authenticate.isAuthenticated()){
                    SecurityContextHolder.getContext().setAuthentication(authenticate);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
                    resultView = "redirect:/";
                }
            } catch (BadCredentialsException e) {
                errorString = "Niepoprawny login lub hasło";
                resultView = "login";

            } catch (AccountExpiredException e){
                errorString = "Konto wygasło";
                resultView = "login";
            } catch (LockedException e){
                errorString = "Konto jest zablokowane";
                resultView = "login";
            } catch (Exception e){
                errorString = "Nieznany błąd";
                resultView = "login";
            }
        }
        else {
            errorString = "Uzupełnij wszystkie pola";
        }
        model.addAttribute("errorString", errorString);
        return resultView;
    }

    @RequestMapping(value = { "/logout", "/logout/" }, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login";
    }
}
