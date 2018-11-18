package controller;

import model.Register;
import view.loginpanel.RegisterFrame;

public class RegisterController {
    private Register register;
    private RegisterFrame registerFrame;

    public RegisterController(Register register, RegisterFrame registerFrame) {
        this.register = register;
        this.registerFrame = registerFrame;
    }
}
