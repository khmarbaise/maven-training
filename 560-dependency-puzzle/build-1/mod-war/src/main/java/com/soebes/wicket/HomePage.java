package com.soebes.wicket;

import java.io.IOException;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import com.soebes.utility.InformationProperties;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
     * @throws IOException 
	 */
    public HomePage(final PageParameters parameters) throws IOException {
        InformationProperties prop = new InformationProperties("/server.properties");
        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));
        
        add(new Label("url", "Server URL:" + prop.getServer().toString()));

    }
}
