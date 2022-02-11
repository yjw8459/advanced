package hello.advanced.app.self;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class SelfBuilder {

    private String name;

    private int age;

}
