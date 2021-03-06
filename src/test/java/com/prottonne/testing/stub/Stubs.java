package com.prottonne.testing.stub;

import WSPackage.Output;
import WSPackage.Response;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.StringInputStream;
import com.prottonne.testing.dto.Request;
import com.prottonne.testing.exception.SomeException;
import com.prottonne.testing.jpa.ManyEntity;
import com.prottonne.testing.jpa.RootEntity;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpRequestBase;

public class Stubs {

    public static final String EXCEPTION_MESSAGE = "this is an exception";
    public static final String DATA_TO_PUT = "this is my data to put";
    public static final String DATA_TO_READ = "this is my data to read";
    public static final String FILE_NAME = "file";
    public static final String S3_OBJECT_KEY = "globalBucketName/folder/";
    public static final String PROP_ONE = "property one";
    public static final String PROP_TWO = "property two";
    public static final int READ_TIMEUP = 4000;
    public static final int CONNECTION_TIMEUP = 2000;
    public static final BigInteger GUID = BigInteger.valueOf(2);
    public static final String WS_DATA = "data from ws";

    public static SomeException SOME_EXCEPTION(String message) {
        return new SomeException(message);
    }

    public static SomeException SOME_EXCEPTION() {
        return new SomeException();
    }

    public static SomeException SOME_EXCEPTION(String message, Throwable cause) {
        return new SomeException(message, cause);
    }

    public static Request REQUEST() {
        Request request = new Request();
        request.setBase64(BASE64());
        request.setFileName(FILE_NAME);
        return request;
    }

    public static String BASE64() {
        byte[] binaryData = DATA_TO_PUT.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeBase64String(binaryData);

    }

    public static InputStream INPUT_STREAM() throws UnsupportedEncodingException {

        return new StringInputStream(DATA_TO_READ);

    }

    public static S3ObjectInputStream S3_OBJECT_INPUT_STREAM(HttpRequestBase httpRequestBase) throws UnsupportedEncodingException {
        S3ObjectInputStream s3ObjectInputStream = new S3ObjectInputStream(INPUT_STREAM(), httpRequestBase);
        return s3ObjectInputStream;
    }

    public static ObjectListing OBJECT_LISTING(String fileName) {
        ObjectListing objectListing = new ObjectListing();
        objectListing.getObjectSummaries().add(S3_OBJECT_SUMARY(fileName));
        return objectListing;
    }

    public static S3ObjectSummary S3_OBJECT_SUMARY(String fileName) {
        S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setKey(S3_OBJECT_KEY + fileName);
        return s3ObjectSummary;
    }

    public static byte[] FILE_IN_BYTES() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static BigInteger GENERATE_GUID() {
        return GUID;
    }

    public static RootEntity ROOT_ENTITY() {
        RootEntity rootEntity = new RootEntity();
        return rootEntity;
    }

    public static List<ManyEntity> MANY_ENTITY_LIST() {
        List<ManyEntity> list = new ArrayList<ManyEntity>();
        list.add(MANY_ENTITY());
        return list;
    }

    public static ManyEntity MANY_ENTITY() {
        ManyEntity manyEntity = new ManyEntity();
        return manyEntity;
    }

    public static Response WS_RESPONSE() {
        Response response = new Response();
        response.setOutput(OUTPUT());
        return response;
    }

    public static Output OUTPUT() {
        Output output = new Output();
        output.setSomeData(WS_DATA);
        return output;
    }

}
