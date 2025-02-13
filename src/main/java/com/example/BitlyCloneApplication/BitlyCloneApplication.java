package com.example.BitlyCloneApplication;

import com.example.BitlyCloneApplication.config.DataBaseConfiguration;
import com.example.BitlyCloneApplication.config.JWTConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({DataBaseConfiguration.class,JWTConfiguration.class})
public class BitlyCloneApplication{
	@Autowired
	DataBaseConfiguration dataBaseConfiguration;
	@Autowired
	JWTConfiguration jwtconfig;

//	private String profileActive;

	Logger logger= LoggerFactory.getLogger(DataBaseConfiguration.class);

	@Bean
	public CommandLineRunner getConnectionInfos() throws Exception {
		CommandLineRunner clr;
		clr = (args) ->
		{
			logger.info("DATABASE CONNECTED TO USER----------------->>>>>>>>>>>>>" + dataBaseConfiguration.getUsername());
			logger.info("DATABASE CONNECTION URL----------------->>>>>>>>>>>>>>>>" + dataBaseConfiguration.getUrl());
			logger.info("DATABASE CONFIGURATION OBJECT" + dataBaseConfiguration);
			logger.info("JWT EXPIRATION------------------>>>>>>>>>"+jwtconfig.getExpiration());
			logger.info("JWT SECRET KEY---------------->>>>>>>>>"+jwtconfig.getSecret());
		};
		return clr;
	}

	public static void main(String[] args) {
		PasswordEncoder pe=new BCryptPasswordEncoder();
		System.out.println("PASSWORD ::::::: "+pe.encode("123"));
		SpringApplication.run(BitlyCloneApplication.class, args);
	}


}
