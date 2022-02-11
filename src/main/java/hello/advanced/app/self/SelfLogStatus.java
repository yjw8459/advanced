package hello.advanced.app.self;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SelfLogStatus {

    private SelfTraceId traceId;

    private int startMs;

    private String message;

}
