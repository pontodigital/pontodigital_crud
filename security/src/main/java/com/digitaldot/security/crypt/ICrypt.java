package com.digitaldot.security.crypt;

public interface ICrypt{

    String encoder(String value);

    String decoder(String value);
}
