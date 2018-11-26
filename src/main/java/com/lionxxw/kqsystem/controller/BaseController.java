package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.encode.EncodeMd5;
import com.lionxxw.kqsystem.code.web.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;


/**
 * The type Base controller.
 * Created by lionxxw on 2016-07-07 16:30:40
 */
public class BaseController {
    @Autowired
    protected SessionProvider sessionProvider;
    @Autowired
    protected EncodeMd5 encodeMd5;
}
