package com.prottonne.testing.feign;

import com.prottonne.testing.stub.Stubs;
import feign.Request.Options;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class FeignOptionsTest {

    @InjectMocks
    private FeignOptions feignOptions;

    @BeforeEach
    public void configure() {
        ReflectionTestUtils.setField(feignOptions, "readTimeup", Stubs.READ_TIMEUP);
        ReflectionTestUtils.setField(feignOptions, "connectionTimeup", Stubs.CONNECTION_TIMEUP);

    }

    @Test
    public void testOptions() {

        Options options = feignOptions.requestOptions();

        assertThat(options.connectTimeoutMillis(),
                is(
                        Stubs.CONNECTION_TIMEUP
                )
        );

        assertThat(options.readTimeoutMillis(),
                is(
                        Stubs.READ_TIMEUP
                )
        );
    }

}
