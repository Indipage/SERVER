package indipage.org.indipage.auth.service;

import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JWKs {

    private List<JWKey> keys;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static public class JWKey {
        private String kty;
        private String kid;
        private String use;
        private String alg;
        private String n;
        private String e;
    }

    public JWKey getRightJWK(String alg, String kid) {
        Optional<JWKey> matchingKey = this.keys
                .stream()
                .filter(k -> k.getAlg().equals(alg) && k.getKid().equals(kid))
                .findFirst();

        return matchingKey.orElseThrow(() -> new IllegalArgumentException("JWT 값의 alg, kid 정보가 올바르지 않습니다."));
    }

}