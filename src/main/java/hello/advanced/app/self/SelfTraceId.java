package hello.advanced.app.self;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SelfTraceId {

    private String id;

    private int level;

    public SelfTraceId() {
        id = createTraceId();
        level = 0;
    }

    private String createTraceId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public void nextLevel(){
        level++;
    }

    public boolean isFirstLevel(int level){
        return level == 0;
    }

}
