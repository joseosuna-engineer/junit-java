package com.prottonne.testing.exception;

import com.prottonne.testing.stub.Stubs;
import com.prottonne.testing.dto.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void testSomeExceptionHandler() {

        Response reponse = globalExceptionHandler.someExceptionHandler(Stubs.SOME_EXCEPTION(Stubs.EXCEPTION_MESSAGE));

        assertThat(reponse.getMessage(),
                is(
                        Stubs.EXCEPTION_MESSAGE
                )
        );

    }

}
