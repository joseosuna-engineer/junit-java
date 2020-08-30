package com.prottonne.testing.stub;

import com.prottonne.testing.exception.SomeException;

public class Stubs {

    public static final String EXCEPTION_MESSAGE = "this is an exception";

    public static SomeException SOME_EXCEPTION(String message) {
        return new SomeException(message);
    }

    public static SomeException SOME_EXCEPTION() {
        return new SomeException();
    }

    public static SomeException SOME_EXCEPTION(String message, Throwable cause) {
        return new SomeException(message, cause);
    }

}
