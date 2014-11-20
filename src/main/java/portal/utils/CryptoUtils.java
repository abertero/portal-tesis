package portal.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portal.config.ApplicationContants;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CryptoUtils {

    private static final Logger logger = LoggerFactory.getLogger(CryptoUtils.class);
    private static final BASE64Encoder encoder = new BASE64Encoder();
    private static final BASE64Decoder decoder = new BASE64Decoder();

    public static String encript(String text) {
        try {
            return encoder.encode(text.getBytes(ApplicationContants.DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            logger.error("Error encriptando " + text + " :", e);
            return null;
        }
    }

    public static String decript(String text) {
        try {
            return new String(decoder.decodeBuffer(text), ApplicationContants.DEFAULT_ENCODING);
        } catch (IOException e) {
            logger.error("Error decriptando " + text + " :", e);
            return null;
        }
    }
}
