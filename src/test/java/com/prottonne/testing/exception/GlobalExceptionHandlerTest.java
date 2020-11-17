package com.prottonne.testing.exception;

import com.prottonne.testing.stub.Stubs;
import com.prottonne.testing.dto.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void testSomeExceptionHandler() {

        Response reponse = globalExceptionHandler.someExceptionHandler(Stubs.SOME_EXCEPTION(Stubs.EXCEPTION_MESSAGE));

        assertThat(reponse.getMessage(),
                is(
                        Stubs.EXCEPTION_MESSAGE
                )
        );

    }

}
