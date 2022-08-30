//package com.digitaldot.app.config;
//
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.env.Environment;
//import org.springframework.core.env.PropertySource;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Properties;
//
//@Component
//public class ReloadablePropertySource {
//
////    @Autowired
////    PropertiesConfiguration propertiesConfiguration;
//    @Autowired
//    Environment environment;
//
//    Properties properties = new Properties();
//
//    @Bean
//    public void test() throws IOException, ConfigurationException {
//        UrlResource urlResource = new UrlResource("classpath:/application.yaml");
//        InputStream  inputStream = urlResource.getInputStream();
//        properties.load(inputStream);
//        properties.list(System.out);
//
//        properties.setProperty("username", "sa");
//        properties.setProperty("password", "Leo123456*");
//        OutputStream outputStream = new FileOutputStream("/home/leonardo/personal/projects/back/digitaldot/app/src/main/resources/application.yaml");
//
//        properties.store(outputStream, null);
//
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        Resource resource = resourceLoader.getResource("classpath:/application.yaml");
//        environment.getProperty(resource.getFile().getAbsolutePath());
//        InputStream test = resource.getInputStream();
////        propertiesConfiguration.load(test);
//    }
//
////    public ReloadablePropertySource(String name, PropertiesConfiguration propertiesConfiguration) {
////        super(name);
////        this.propertiesConfiguration = propertiesConfiguration;
////    }
////
////    public ReloadablePropertySource(String name, String path) throws Exception {
////        super(StringUtils.hasText(name) ? path : name);
////        try {
////            this.propertiesConfiguration = new PropertiesConfiguration(path);
////            this.propertiesConfiguration.setReloadingStrategy(new FileChangedReloadingStrategy());
////        } catch (Exception e) {
////            throw new Exception(e);
////        }
////    }
////
////    @Override
////    public Object getProperty(String s) {
////        return propertiesConfiguration.getProperty(s);
////    }
//}
