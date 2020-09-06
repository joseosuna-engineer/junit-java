package com.prottonne.testing.soap;

import WSPackage.Request;
import WSPackage.Response;
import com.prottonne.testing.stub.Stubs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(MockitoJUnitRunner.class)
public class SoapClientTest {

    @InjectMocks
    private SoapClient soapClient;

    @Mock
    private WebServiceTemplate webServiceTemplate;

    @Test
    public void testSomeAction() {

        when(
                webServiceTemplate.marshalSendAndReceive(
                        anyString(),
                        any(Request.class)
                )
        ).thenReturn(Stubs.WS_RESPONSE());

        Response response = soapClient.someAction("some data");

        assertThat(response.getOutput().getSomeData(),
                is(
                        Stubs.WS_DATA
                ));
    }

}
