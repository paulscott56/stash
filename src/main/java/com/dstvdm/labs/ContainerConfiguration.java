package com.dstvdm.labs;

import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ContainerConfiguration {

	@Bean
	EmbeddedServletContainerCustomizer containerCustomizer(@Value("${keystore.file}") String keystoreFile,
			@Value("${server.port}") final String serverPort, @Value("${keystore.pass}") final String keystorePass,
			@Value("${https_enabled}") final String https_enabled) throws Exception {

		// This is boiler plate code to setup https on embedded Tomcat
		// with Spring Boot:

		final String absoluteKeystoreFile = new File(keystoreFile).getAbsolutePath();

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
				if (Boolean.valueOf(https_enabled)) {
					tomcat.addConnectorCustomizers(connector -> {
						connector.setPort(Integer.parseInt(serverPort));
						connector.setSecure(true);
						connector.setScheme("https");

						Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
						proto.setSSLEnabled(true);

						proto.setKeystoreFile(absoluteKeystoreFile);
						proto.setKeystorePass(keystorePass);
						proto.setKeystoreType("JKS");
						proto.setKeyAlias("tomcat");
					});
				}

				else {
					tomcat.addConnectorCustomizers(connector -> {
						connector.setPort(Integer.parseInt(serverPort));
						connector.setSecure(false);
						connector.setScheme("http");

						Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
						proto.setSSLEnabled(false);

					});

				}
			}
		};
	}
}