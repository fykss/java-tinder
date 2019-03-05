package ua.com.danit.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Freemarker {
    private final Configuration config;

    public Freemarker() {
        this.config = new Configuration(Configuration.VERSION_2_3_28) {{
                setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(),"templates");
                setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
                setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                setLogTemplateExceptions(false);
                setWrapUncheckedExceptions(true);

        }};
    }

    public void render(final String templateFile, final Map<String, Object> data, final HttpServletResponse resp) throws IOException {
        try {
            resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            config.getTemplate(templateFile).process(data, resp.getWriter());
        } catch (TemplateException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }
}
