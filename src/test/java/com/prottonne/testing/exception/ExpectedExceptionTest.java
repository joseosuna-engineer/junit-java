package com.prottonne.testing.exception;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.prottonne.testing.aws.AwsS3Client;
import com.prottonne.testing.dto.Request;
import com.prottonne.testing.stub.Stubs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpectedExceptionTest {

    @InjectMocks
    private AwsS3Client awsS3Client;

    @Mock
    private AmazonS3 amazonS3;

    @Test()
    void testPutNullPointerException() {

        Assertions.assertThrows(NullPointerException.class, () -> {
            awsS3Client.put(null);
        });

    }

    @Test()
    void testPutAmazonServiceException() {

        when(amazonS3.putObject(any(PutObjectRequest.class))).
                thenThrow(new AmazonServiceException("errorMessage"));

        Request request = Stubs.REQUEST();

        Assertions.assertThrows(AmazonServiceException.class, () -> {
            awsS3Client.put(request);
        });

    }

}
