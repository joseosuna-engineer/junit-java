package com.prottonne.testing.feign;

import com.prottonne.testing.stub.Stubs;
import feign.Request.Options;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class FeignOptionsTest {

    @InjectMocks
    private FeignOptions feignOptions;

    @Before
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
