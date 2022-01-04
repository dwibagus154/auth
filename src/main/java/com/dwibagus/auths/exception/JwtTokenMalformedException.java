package com.dwibagus.auths.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
    private static final long serialVersionId = 1L;

    public JwtTokenMalformedException(String msg) {super(msg);}
}
