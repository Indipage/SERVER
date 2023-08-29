package indipage.org.indipage.auth.service;

import indipage.org.indipage.auth.service.JWKs;
import indipage.org.indipage.auth.service.JWKs.JWKey;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
@RequiredArgsConstructor
public class PublicKeyGenerator {

    private static final String KEY_OF_HEADER_ALGORITHM = "alg";
    private static final String KEY_OF_HEADER_KEY_ID = "kid";
    private static final int POSITIVE_SIGN = 1;

    public PublicKey generatePublicKey(final Map<String, String> headers, final JWKs publicKeys) {
        JWKey publicKey = getRightJWKWithDecodedHeader(headers, publicKeys);
        RSAPublicKeySpec publicKeySpec = generateRSAPublicKeySpecFromJWK(publicKey);
        return generatePublicKeyFromKeySpecAndKeyType(publicKey.getKty(), publicKeySpec);
    }

    private JWKey getRightJWKWithDecodedHeader(final Map<String, String> headers, final JWKs publicKeys) {
        String alg = headers.get(KEY_OF_HEADER_ALGORITHM);
        String kid = headers.get(KEY_OF_HEADER_KEY_ID);

        return publicKeys.getRightJWK(alg, kid);
    }

    private RSAPublicKeySpec generateRSAPublicKeySpecFromJWK(final JWKey publicKey) {
        byte[] nBytes = Base64Utils.decodeFromUrlSafeString(publicKey.getN());
        byte[] eBytes = Base64Utils.decodeFromUrlSafeString(publicKey.getE());

        BigInteger n = new BigInteger(POSITIVE_SIGN, nBytes);
        BigInteger e = new BigInteger(POSITIVE_SIGN, eBytes);

        return new RSAPublicKeySpec(n, e);
    }

    public PublicKey generatePublicKeyFromKeySpecAndKeyType(final String keyType, final RSAPublicKeySpec publicKeySpec) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(keyType);
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new IllegalStateException();
        }
    }

}
