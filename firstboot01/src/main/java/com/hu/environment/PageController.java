package com.hu.environment;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

@Controller
public class PageController implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        String s = environment.getProperty("JAVA_HOME");
        System.out.println("JAVA_HOME:----"+s);
    }
}
