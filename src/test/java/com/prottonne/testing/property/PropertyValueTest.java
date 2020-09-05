package com.prottonne.testing.property;

import com.prottonne.testing.dto.Response;
import com.prottonne.testing.stub.Stubs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class PropertyValueTest {

    @InjectMocks
    private PropertyValue propertyValue;

    @Before
    public void configure() {
        ReflectionTestUtils.setField(propertyValue, "myPropertyName", Stubs.PROP_ONE);
        ReflectionTestUtils.setField(propertyValue, "someProperty", Stubs.PROP_TWO);

    }

    @Test
    public void testGetMessageWithProperties() {

        Response response = propertyValue.getMessageWithProperties();

        final String expectedMessage = Stubs.PROP_TWO + ";" + Stubs.PROP_ONE;

        assertThat(response.getMessage(),
                is(
                        expectedMessage
                ));
    }

}
