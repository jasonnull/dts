package io.github.jasonnull.dts.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dty on 2018/7/21.
 */
@Controller
@RequestMapping("/error")
@Slf4j
public class ErrorPageController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "500";
    }

    @RequestMapping
    /**
     * 未来可以加添加code
     */
    public String error() {
        return getErrorPath();
    }
}