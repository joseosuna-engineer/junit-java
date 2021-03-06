package com.prottonne.testing;

import com.prottonne.testing.soap.SoapClient;
import java.io.IOException;
import java.net.HttpURLConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

@SpringBootApplication
public class Main {

    @Value("${ws.endpoint}")
    private String endpoint;

    @Value("${read.timeup}")
    private String readTimeup;

    @Value("${connection.timeup}")
    private String connectionTimeup;

    private static final String PATH_SERVICE = "WSPackage";
    private static final String CLOSE = "close";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(
                PATH_SERVICE);
        return marshaller;
    }

    @Bean
    public HttpUrlConnectionMessageSender httpUrlConnectionMessageSender() {
        return new HttpUrlConnectionMessageSender() {
            @Override
            protected void prepareConnection(HttpURLConnection connection) throws IOException {
                connection.setReadTimeout(Integer.parseInt(readTimeup));
                connection.setConnectTimeout(Integer.parseInt(connectionTimeup));
                connection.setRequestProperty(HttpHeaders.CONNECTION, CLOSE);
                connection.setDoOutput(Boolean.TRUE);
            }
        };
    }

    @Bean
    public SoapClient soapClient(Jaxb2Marshaller marshaller,
            HttpUrlConnectionMessageSender httpUrlConnectionMessageSender) {

        SoapClient soapClient = new SoapClient();
        soapClient.setDefaultUri(endpoint);
        soapClient.setMarshaller(marshaller);
        soapClient.setUnmarshaller(marshaller);
        soapClient.setMessageSender(httpUrlConnectionMessageSender);

        return soapClient;
    }

}
