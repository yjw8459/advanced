package hello.advanced.app.self;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SelfBuilder {

    private String name;

    private int age;

}
