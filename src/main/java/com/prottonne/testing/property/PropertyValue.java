package com.prottonne.testing.property;

import com.prottonne.testing.dto.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertyValue {

    @Value("${some.property}")
    private String someProperty;

    @Value("${another.property}")
    private String myPropertyName;

    public PropertyValue() {
        super();
    }

    public Response getMessageWithProperties() {
        Response response = new Response();
        String message = someProperty + ";" + myPropertyName;
        response.setMessage(message);
        return response;
    }

}
