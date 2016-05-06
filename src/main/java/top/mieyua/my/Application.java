/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * Created by MieYua on 2016-05-05.
 */
@Controller
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
