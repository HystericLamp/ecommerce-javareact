package com.ecommerce.bcruz.config;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

public class DotenvEnvironmentPostProcessor implements EnvironmentPostProcessor
{
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application)
	{
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		System.out.println("DOTENV POST PROCESSOR RUNNING");
		
        Properties props = new Properties();
        
        props.put("stripe.webhookSecret", dotenv.get("STRIPE_WEBHOOK_SECRET"));
        props.put("stripe.secretKey", dotenv.get("STRIPE_SECRET_KEY"));
        props.put("stripe.publishableKey", dotenv.get("STRIPE_PUBLISHABLE_KEY"));
        
        for (DotenvEntry entry : dotenv.entries()) 
		{
		    String key = entry.getKey()
		            .toLowerCase()
		            .replace("_", ".");

		    props.put(key, entry.getValue());
		}

        environment.getPropertySources()
                .addFirst(new PropertiesPropertySource("dotenv", props));
	}
}
