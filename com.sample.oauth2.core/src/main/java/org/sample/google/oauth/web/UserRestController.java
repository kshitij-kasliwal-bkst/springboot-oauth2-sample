package org.sample.google.oauth.web;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Rest controller for getting logged user details
 * 
 * @author kk
 *
 */
@CrossOrigin(value = "*", origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
public class UserRestController {

    @CrossOrigin(value = "*", origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Principal sayHello(Principal principal) {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        return principal;
    }

    @RequestMapping(value = "/user", method = RequestMethod.OPTIONS)
    public void corsHeaders(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.addHeader("Access-Control-Max-Age", "3600");
    }
}
