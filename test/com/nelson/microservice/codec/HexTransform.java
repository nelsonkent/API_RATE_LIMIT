package com.nelson.microservice.codec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HexTransform {

    /**
     * Hex is a binary presentation base 16
     * Charset is a Word encode, ASCII 7bit, unicode 8-32 bit
     * @throws DecoderException
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testHex() throws DecoderException, UnsupportedEncodingException {
        String rawStr = "中文";
        final String encodeHexString = Hex.encodeHexString(rawStr.getBytes("gbk"));
        char[] toChar = new char[encodeHexString.length()];
        encodeHexString.getChars(0, encodeHexString.length(), toChar, 0);
        final byte[] bytes = Hex.decodeHex(toChar);
        System.out.println(new String(bytes, "gbk"));
    }

    /**
     * How a string value stored binary
     */
    @Test
    public void testBinary() throws UnsupportedEncodingException {
        String rawStr = "中";
        final byte[] bytes = rawStr.getBytes("unicode");
        for(byte b:bytes){
            System.out.println(b);
        }
    }

    @Test
    public void testFile() throws IOException {
        FileReader reader = new FileReader("/Users/nelson/projects/API_RATE_LIMIT/test/com/nelson/microservice/codec/sample.txt");
        char[] chars = new char[1024];
        reader.read(chars);
        System.out.println(chars.length);
    }
}
