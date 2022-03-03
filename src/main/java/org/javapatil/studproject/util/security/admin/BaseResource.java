package org.javapatil.studproject.util.security.admin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public abstract class BaseResource {

    protected User getUser(HttpServletRequest request) {
        return (User) request.getAttribute("user");
    }
}
