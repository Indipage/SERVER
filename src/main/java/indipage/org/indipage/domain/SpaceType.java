package indipage.org.indipage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SpaceType {
    독립서점("독립서점"),
    // 영어 값도 적기

    북카페("북카페 "),
    ;

    private final String typeName;
}
