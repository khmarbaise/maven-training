package com.soebes.utility;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;
import java.net.URL;

import org.testng.annotations.Test;

public class InformationPropertiesTest {

    @Test
    public void shouldReturnTheValueFromThePropertyFile() throws IOException {
        String propertiesFile = "/server.properties";
        InformationProperties ip = new InformationProperties(propertiesFile);
        assertThat(ip.getServer()).isEqualTo(new URL("http://www.test-server.com"));
    }
    
    @Test
    public void shouldReturnDefaultValueIfPropertyFileDoesNotExists() throws IOException {
        String propertiesFile = "/test.properties";
        InformationProperties ip = new InformationProperties(propertiesFile);
        assertThat(ip.getServer()).isEqualTo(new URL(InformationProperties.SERVER_DEFAULT));
        
    }
}
