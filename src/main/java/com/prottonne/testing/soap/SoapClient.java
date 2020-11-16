package com.prottonne.testing.soap;

import WSPackage.Input;
import WSPackage.Request;
import WSPackage.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class SoapClient extends WebServiceGatewaySupport {

    @Value("${ws.endpoint}")
    private String endpoint;

    public SoapClient() {
        super();
    }

    public Response someAction(String someData) {

        Input input = new Input();
        input.setSomeData(someData);

        Request request = new Request();
        request.setInput(input);

        return (Response) getWebServiceTemplate().
                marshalSendAndReceive(endpoint,
                        request);

    }

}
