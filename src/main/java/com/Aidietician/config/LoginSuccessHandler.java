package com.Aidietician.config;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
        if (userDetails.findRole().equals("ROLE_USER")) {
           
            redirectURL = "/user/index";
        } else if (userDetails.findRole().equals("ROLE_dietician")) {
           
            redirectURL = "/dietician/index";
        } 
         
        response.sendRedirect(redirectURL);
         
    }
 
}