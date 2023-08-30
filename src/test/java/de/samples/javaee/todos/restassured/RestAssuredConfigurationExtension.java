package de.samples.javaee.todos.restassured;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

public class RestAssuredConfigurationExtension implements BeforeAllCallback, AfterAllCallback {

    private String baseURI;
    private int port;
    private String basePath;
    private AuthenticationScheme authentication;
    private String rootPath;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        // backup
        this.baseURI = RestAssured.baseURI;
        this.port = RestAssured.port;
        this.basePath = RestAssured.basePath;
        this.authentication = RestAssured.authentication;
        this.rootPath = RestAssured.rootPath;
        // assign values from annotation
        extensionContext
                .getElement()
                .flatMap(el -> AnnotationUtils.findAnnotation(el, RestAssuredConfiguration.class))
                .ifPresent(config -> {
                    RestAssured.baseURI = config.baseURI();
                    RestAssured.port = config.port();
                    RestAssured.basePath = config.basePath();
                    RestAssured.rootPath = config.rootPath();
                });
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        // restore
        RestAssured.baseURI = this.baseURI;
        RestAssured.port = this.port;
        RestAssured.basePath = this.basePath;
        RestAssured.authentication = this.authentication;
        RestAssured.rootPath = this.rootPath;
    }

}
