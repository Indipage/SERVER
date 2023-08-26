package indipage.org.indipage.auth.service.apple;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplePublicKeys {

    private List<ApplePublicKey> keys;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class ApplePublicKey {
        private String kty;
        private String kid;
        private String use;
        private String alg;
        private String n;
        private String e;
    }

    public ApplePublicKey getPublicKey(String alg, String kid) {
        Optional<ApplePublicKey> matchingKey = this.keys
                .stream()
                .filter(k -> k.getAlg().equals(alg) && k.getKid().equals(kid))
                .findFirst();

        return matchingKey.orElseThrow(() -> new IllegalArgumentException("Apple JWT 값의 alg, kid 정보가 올바르지 않습니다."));
    }

}