package com.soebes.utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class InformationProperties {

    private static final String SERVER = "server";
    
    public static final String SERVER_DEFAULT = "http://www.server-default.com";
    
    private Properties properties;

    public InformationProperties(String propertiesFile) throws IOException {
        properties = new Properties();
        InputStream resourceAsStream = this.getClass().getResourceAsStream(propertiesFile);
        if (resourceAsStream == null) {
            properties.setProperty(SERVER, SERVER_DEFAULT);
        } else {
            properties.load(resourceAsStream);
        }
    }

    public URL getServer() throws MalformedURLException {
        String url = properties.getProperty(SERVER);
        return new URL(url);
    }

}
