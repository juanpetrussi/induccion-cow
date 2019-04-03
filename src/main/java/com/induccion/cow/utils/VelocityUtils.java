package com.induccion.cow.utils;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

public class VelocityUtils {

    public static String getTemplateEngine(Map<String, Object> model, String path_to_template) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, path_to_template));
    }
}
