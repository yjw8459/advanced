package hello.advanced.app.self;


import hello.advanced.trace.TraceId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelfFieldLog implements SelfLogTrace {

    private final String START = "--->";
    private final String END = "<---";
    private final String EXCEPTION = "<X--";

    private SelfTraceId traceHolder;

    @Override
    public SelfTraceId begin(String message) {
        syncTraceId();
        SelfTraceId traceId = traceHolder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("{} {} [{}]", traceId.getId(), addSpace(START, traceId.getLevel()), message);
        return traceId;
    }

    private void syncTraceId() {
        if (traceHolder == null)
            traceHolder = new SelfTraceId();
         else
            traceHolder.nextLevel();
    }

    @Override
    public SelfTraceId end(String message) {
        return endProc();
    }

    @Override
    public SelfTraceId exception(String message) {
        return endProc();
    }

    private SelfTraceId endProc(){
        return null;
    }

    public String addSpace(String arrow, int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++)
            sb.append( ( i == level - 1 ) ? "|" + arrow : "|    ");
        return sb.toString();
    }
}
