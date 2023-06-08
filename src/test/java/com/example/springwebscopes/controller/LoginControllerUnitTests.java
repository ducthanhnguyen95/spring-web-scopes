package com.example.springwebscopes.controller;

import com.example.springwebscopes.scopes.LoginProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class LoginControllerUnitTests {

    @Mock
    private Model model;

    @Mock
    private LoginProcessor loginProcessor;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginPostLoginSucceedsTest() {
        BDDMockito.given(loginProcessor.login()).willReturn(true);
        String result = loginController.loginPost("username", "password", model);
        Assertions.assertEquals("login.html", result);
        Mockito.verify(model).addAttribute("message", "You are now logged in.");
    }

    @Test
    public void loginPostLoginFailsTest() {
        BDDMockito.given(loginProcessor.login()).willReturn(false);
        String result = loginController.loginPost("username", "password", model);
        Assertions.assertEquals("login.html", result);
        Mockito.verify(model).addAttribute("message", "Login failed!");
    }
}
