package com.prottonne.testing.soap;

import WSPackage.Request;
import WSPackage.Response;
import com.prottonne.testing.stub.Stubs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@ExtendWith(MockitoExtension.class)
public class SoapClientTest {

    @InjectMocks
    private SoapClient soapClient;

    @Mock
    private WebServiceTemplate webServiceTemplate;

    @BeforeEach
    public void configure() {
        ReflectionTestUtils.setField(soapClient, "endpoint", Stubs.PROP_ONE);

    }

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
