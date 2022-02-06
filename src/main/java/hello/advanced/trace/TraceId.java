package hello.advanced.trace;

import java.util.UUID;

public class TraceId {

    //Transaction ID
    private String id;

    //Level
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    public TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        //UUID 생성 후 앞자리 8자리만 사용
        return UUID.randomUUID().toString().substring(0, 8);
    }

    //현재 TraceId를 기반으로 레벨 증가
    public TraceId createNextId(){
        return new TraceId(id, level + 1);
    }

    //현재 TraceId를 기반으로 레벨 감소
    public TraceId cratePreviousId(){
        return new TraceId(id, level - 1);
    }

    //첫 번째 레벨 여부를 판단
    public boolean isFirstLevel(){
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
