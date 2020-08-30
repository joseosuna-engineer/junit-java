package com.prottonne.testing.exception;

import com.prottonne.testing.stub.Stubs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SomeExceptionTest {

    @Test
    public void testAll() {

        SomeException someException = Stubs.SOME_EXCEPTION();

        assertThat(someException.getMessage(),
                is(
                        nullValue()
                )
        );

        assertThat(someException.getCause(),
                is(
                        nullValue()
                )
        );

        someException = Stubs.SOME_EXCEPTION(Stubs.EXCEPTION_MESSAGE);

        assertThat(someException.getMessage(),
                is(
                        Stubs.EXCEPTION_MESSAGE
                )
        );

        assertThat(someException.getCause(),
                is(
                        nullValue()
                )
        );

        someException = Stubs.SOME_EXCEPTION(Stubs.EXCEPTION_MESSAGE, new RuntimeException());

        assertThat(someException.getMessage(),
                is(
                        Stubs.EXCEPTION_MESSAGE
                )
        );

        assertThat(someException.getCause(),
                is(
                        not(
                                nullValue()
                        )
                )
        );

    }

}
