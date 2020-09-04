package com.prottonne.testing.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.prottonne.testing.constant.ResponseEnum;
import org.mockito.Mock;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.mockito.Mockito.when;
import com.prottonne.testing.stub.Stubs;
import com.prottonne.testing.dto.Response;
import org.apache.http.client.methods.HttpRequestBase;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class AwsS3ClientTest {

    @InjectMocks
    private AwsS3Client awsS3Client;

    @Mock
    private AmazonS3 amazonS3;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private ServletOutputStream servletOutputStream;

    @Mock
    private S3Object s3Object;

    @Mock
    private HttpRequestBase httpRequestBase;

    @Mock
    private MockMultipartFile multipartFile;

    @Test
    public void testPut() throws IOException {

        Response response = awsS3Client.put(Stubs.REQUEST());

        assertThat(response.getMessage(),
                is(
                        ResponseEnum.CODE_A.getData()
                ));
    }

    @Test
    public void testGet() throws IOException {

        when(s3Object.getObjectContent()).thenReturn(Stubs.S3_OBJECT_INPUT_STREAM(httpRequestBase));

        when(amazonS3.getObject(any(GetObjectRequest.class))).
                thenReturn(s3Object);

        when(httpServletResponse.getOutputStream()).thenReturn(servletOutputStream);

        Response response = awsS3Client.get(Stubs.REQUEST(), httpServletResponse);

        assertThat(response.getMessage(),
                is(
                        ResponseEnum.CODE_B.getData()
                ));
    }

    @Test
    public void testDeleteAll() {

        when(amazonS3.listObjects(any(ListObjectsRequest.class))).
                thenReturn(Stubs.OBJECT_LISTING(null));

        Response response = awsS3Client.deleteAll(Stubs.REQUEST());

        assertThat(response.getMessage(),
                is(
                        equalTo(ResponseEnum.CODE_C.getData())
                )
        );
    }

    @Test
    public void testIsUploaded() {

        when(amazonS3.listObjects(any(ListObjectsRequest.class))).
                thenReturn(Stubs.OBJECT_LISTING(Stubs.FILE_NAME));

        Response response = awsS3Client.isUploaded(Stubs.REQUEST());

        assertThat(response.getUploaded(),
                is(
                        Boolean.TRUE
                )
        );
    }

    @Ignore
    @Test
    public void testPutMultipart() throws IOException {

        when(multipartFile.getOriginalFilename()).thenReturn("data");

        when(multipartFile.getBytes()).thenReturn(Stubs.FILE_IN_BYTES());

        // TODO
    }

}
