package com.daydayup.petstore;

import java.util.Map;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daydayup.petstore.service.DatabaseInitService;

public abstract class BuildVerifyTest extends TestCase {

    private String             contextXmlFile = "test-appContext.xml";

    private ClassPathXmlApplicationContext context;

    @Override
    protected void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(contextXmlFile);
        
        DatabaseInitService initService = getBean(DatabaseInitService.class);
        initService.createUserTable();
    }
    
    protected void tearDown() throws Exception {
        context.close();
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        return (T) context.getBean(name);
    }
    
    public final <T> T getBean(Class<T> clazz) {
        Map<String, T> beans = context.getBeansOfType(clazz);
        
        if (beans.size() == 0) {
            return null;
        }
        
        if (beans.size() > 1) {
            throw new IllegalStateException("multi instance found. " + beans.size());
        }
        
        return beans.values().iterator().next();
    }
}
