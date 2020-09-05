package com.prottonne.testing.exception;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.prottonne.testing.aws.AwsS3Client;
import com.prottonne.testing.stub.Stubs;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExpectedExceptionTest {

    @InjectMocks
    private AwsS3Client awsS3Client;

    @Mock
    private AmazonS3 amazonS3;

    @Test(expected = NullPointerException.class)
    public void testPutNullPointerException() throws IOException {

        awsS3Client.put(null);

    }

    @Test(expected = AmazonServiceException.class)
    public void testPutAmazonServiceException() throws IOException {

        when(amazonS3.putObject(any(PutObjectRequest.class))).
                thenThrow(new AmazonServiceException("errorMessage"));

        awsS3Client.put(Stubs.REQUEST());

    }

}
