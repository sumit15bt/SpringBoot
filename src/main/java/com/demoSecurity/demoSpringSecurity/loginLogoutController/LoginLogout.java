package com.demoSecurity.demoSpringSecurity.loginLogoutController;

import com.demoSecurity.demoSpringSecurity.dbBeans.UserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.WebSession;

@Log4j2
@Controller
public class LoginLogout {



    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String init(ServerHttpRequest request, @AuthenticationPrincipal UserDetails userDetails, WebSession session, Model map) {
        try {
            String user=userDetails.getUser();
            String domain=userDetails.getDomain();
            session.start();
            session.getAttributes().put("USER", user);
            session.getAttributes().put("DOMAIN", domain);
            map.addAttribute("USER_NAME", user);
            map.addAttribute("USER_DOMAIN", domain);
            return "home";
        }catch (Exception ex){
            session.invalidate();
            log.error("Error processing login", ex);
            map.addAttribute("message", ex.getMessage());
            return "redirect:/login?error=" + ex.getMessage();
        }
    }

    @GetMapping(value = {"/logout"})
    public String logout(WebSession webSession) {
        webSession.invalidate();
        return "redirect:/login?logout";
    }
}
