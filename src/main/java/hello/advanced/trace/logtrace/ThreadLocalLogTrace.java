package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

//    private TraceId traceHolder; //traceId 동기화, 동시성 이슈 발생
    private ThreadLocal<TraceId> traceHolder = new ThreadLocal<>();
 
    @Override
    public TraceStatus begin(String message) {
        syncTraceId();  //값을 가져오기 전에 체크 로직 수행
        TraceId traceId = traceHolder.get();
        Long startTimeMs = System.currentTimeMillis();                //시작 시간 추출
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,
                traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    //새로 만들거나 있을 경우 레벨을 증가시킴
    private void syncTraceId(){
        TraceId traceId = traceHolder.get();
        if ( traceId == null ){
            traceHolder.set(new TraceId());
        } else {
            traceHolder.set(traceId.createNextId());
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
                    e.toString());
        }
        releaseTraceId();
    }

    //레벨을 감소시키다가, 첫 번째 레벨을 만날경우 TraceId를 제거
    private void releaseTraceId() {
        TraceId traceId = traceHolder.get();
        if (traceHolder.get().isFirstLevel()){
            traceHolder.remove();   //remove
        }else {
            traceHolder.set(traceId.createPreviousId());
        }
    }

    //레벨 - 1과 index가 같을 경우는 화살표, 아닐 경우는 빈 칸
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
