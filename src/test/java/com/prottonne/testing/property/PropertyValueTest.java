package com.prottonne.testing.property;

import com.prottonne.testing.dto.Response;
import com.prottonne.testing.stub.Stubs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class PropertyValueTest {

    @InjectMocks
    private PropertyValue propertyValue;

    @BeforeEach
    public void configure() {
        ReflectionTestUtils.setField(propertyValue, "myPropertyName", Stubs.PROP_ONE);
        ReflectionTestUtils.setField(propertyValue, "someProperty", Stubs.PROP_TWO);

    }

    @Test
    void testGetMessageWithProperties() {

        Response response = propertyValue.getMessageWithProperties();

        final String expectedMessage = Stubs.PROP_TWO + ";" + Stubs.PROP_ONE;

        assertThat(response.getMessage(),
                is(
                        expectedMessage
                ));
    }

}
