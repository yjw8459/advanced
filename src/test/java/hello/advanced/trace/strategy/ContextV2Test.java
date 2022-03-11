package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.ContextV2;
import hello.advanced.trace.strategy.code.Strategy;
import hello.advanced.trace.strategy.code.StrategyLogic1;
import hello.advanced.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@Slf4j
public class ContextV2Test {

    /**
     * 전략 패턴 사용
     */
    @Test
    public void strategyV1(){
        //전략을 파라미터로 받는 경우는 컨텍스트를 하나만 생성해서 사용할 수 있다.
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }
    /**
     * 전략 패턴 익명 내부 클래스
     */
    @Test
    public void strategyV2(){
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {

            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {

            }
        });

        context.execute(() -> log.info("test"));

    }
}
