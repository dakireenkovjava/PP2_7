package web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
        //Этот метод возвращает конфигурационные классы, которые будут использоваться для создания корневого контекста приложения.
        // Корневой контекст содержит общие для всего приложения бины, такие как сервисы и репозитории.
    }


    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class //указываем где находится spring конфигурация
        };
    }


    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    // все запросы от пользователя отпраляем на despetcherservlet

    // фильтры для чтения скрытого метода _mthods putch delete put в html5
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        // запускается при старте spring приложения
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        // здесь добавляем к приложению фильтр
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
        // перенаправляет http запрос на нужный метод контроллера например на update
    }



}