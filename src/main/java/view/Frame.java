package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kreisso on 02.11.2018.
 */
public abstract class Frame extends JFrame {

    private String name;

    public Frame(String name) throws HeadlessException {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}

