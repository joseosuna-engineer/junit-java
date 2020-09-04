package com.prottonne.testing.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.prottonne.testing.constant.ResponseEnum;
import com.prottonne.testing.dto.Request;
import com.prottonne.testing.dto.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwsS3Client {

    private static final String S3_BUCKET_NAME = "bucketName";
    private static final String S3_GLOBAL_BUCKET_NAME = "globalBucketName";
    private static final String FOLDER = "folder";

    @Autowired
    private AmazonS3 amazonS3;

    public AwsS3Client() {
        super();
    }

    public Response put(Request request) throws IOException {

        final String path = getPath();

        final String fileName = request.getFileName();

        final String key = getKey(path, fileName);

        byte[] fileInBytes = Base64.decodeBase64(request.getBase64());

        InputStream inputStream = new ByteArrayInputStream(fileInBytes);
        byte[] contentBytes = IOUtils.toByteArray(inputStream);
        Long contentLength = Long.valueOf(contentBytes.length);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);

        amazonS3.putObject(
                new PutObjectRequest(
                        S3_BUCKET_NAME,
                        key,
                        new ByteArrayInputStream(fileInBytes),
                        metadata
                )
        );

        Response response = new Response();
        response.setMessage(ResponseEnum.CODE_A.getData());

        return response;
    }

    public Response get(Request request, HttpServletResponse httpServletResponse) throws IOException {

        final String path = getPath();

        final String fileName = request.getFileName();

        final String key = getKey(path, fileName);

        S3Object s3Object = amazonS3.getObject(
                new GetObjectRequest(
                        S3_BUCKET_NAME,
                        key
                )
        );

        InputStream s3ObjectContent = s3Object.getObjectContent();
        IOUtils.copy(s3ObjectContent, httpServletResponse.getOutputStream());

        httpServletResponse.setContentType("application/force-download");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + s3Object.getKey());
        httpServletResponse.flushBuffer();

        s3ObjectContent.close();

        Response response = new Response();
        response.setMessage(ResponseEnum.CODE_B.getData());

        return response;
    }

    public Response deleteAll(Request request) {

        final String path = getPath();

        final String fileName = request.getFileName();

        final String key = getKey(path, fileName);

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(S3_BUCKET_NAME);
        listObjectsRequest.setPrefix(key);
        listObjectsRequest.setDelimiter("/");

        ObjectListing objectListing = amazonS3.listObjects(listObjectsRequest);

        List<S3ObjectSummary> s3ObjectSummaryList = objectListing.getObjectSummaries();

        for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaryList) {

            amazonS3.deleteObject(
                    new DeleteObjectRequest(
                            S3_BUCKET_NAME,
                            s3ObjectSummary.getKey()
                    )
            );
        }

        Response response = new Response();
        response.setMessage(ResponseEnum.CODE_C.getData());

        return response;
    }

    public Response isUploaded(Request request) {

        Response response = new Response();

        final String path = getPath();

        final String fileName = request.getFileName();

        final String key = getKey(path, fileName);

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(S3_BUCKET_NAME);
        listObjectsRequest.setPrefix(key);
        listObjectsRequest.setDelimiter("/");

        ObjectListing objectListing = amazonS3.listObjects(listObjectsRequest);

        List<S3ObjectSummary> s3ObjectSummaryList = objectListing.getObjectSummaries();

        for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaryList) {

            if (s3ObjectSummary.getKey().contains(key)) {
                response.setUploaded(Boolean.TRUE);
                return response;
            }
        }

        response.setUploaded(Boolean.FALSE);
        return response;
    }

    private String getPath() {

        return S3_GLOBAL_BUCKET_NAME
                + "/"
                + FOLDER;
    }

    private String getKey(String path, String fileName) {
        return path + "/" + fileName;
    }

}
