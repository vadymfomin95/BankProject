package nure.ua.fomin.configserver;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.springframework.util.StringUtils.hasLength;


public class TruststoreInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    protected static final String TRUSTSTORE_PATH_KEY = "truststore.path";
    protected static final String TRUSTSTORE_PASSWORD_KEY = "truststore.password";
    protected static final String JAVAX_TRUSTSTORE_PATH = "javax.net.ssl.trustStore";
    protected static final String JAVAX_TRUSTSTORE_PASSWORD = "javax.net.ssl.trustStorePassword";

    private final ResourceLoader resourceLoader;

    public TruststoreInitializer() {
        this(new DefaultResourceLoader());
    }

    public TruststoreInitializer(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        var environment = applicationContext.getEnvironment();
        var truststorePath = environment.getProperty(TRUSTSTORE_PATH_KEY);
        var truststorePassword = environment.getProperty(TRUSTSTORE_PASSWORD_KEY);
        if (!hasLength(truststorePath) || !hasLength(truststorePassword)) {
            return;
        }
        var truststoreAbsPath = getTruststoreAbsPath(truststorePath);
        System.setProperty(JAVAX_TRUSTSTORE_PATH, truststoreAbsPath);
        System.setProperty(JAVAX_TRUSTSTORE_PASSWORD, truststorePassword);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private String getTruststoreAbsPath(String truststorePath) {
        var truststoreResource = resourceLoader.getResource(truststorePath);
        if (!truststoreResource.exists()) {
            var msg = "Truststore path were found in properties but file doesn't exist. Path: " + truststorePath;
            throw new IllegalStateException(msg);
        }
        try {
            return truststoreResource.getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
