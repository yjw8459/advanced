package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.ContextV1;
import hello.advanced.trace.strategy.code.Strategy;
import hello.advanced.trace.strategy.code.StrategyLogic1;
import hello.advanced.trace.strategy.code.StrategyLogic2;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.naming.Context;

@Slf4j
public class ContextTest {
    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    public void strategyV1(){


        StrategyLogic1 logic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(logic1);
        contextV1.execute();

        new AbstractTemplate<String>(null){
            @Override
            protected String call() {
                return null;
            }
        }.execute("");

        //람다 식 사용
        new ContextV1(() -> System.out.println("test")).execute();

        StrategyLogic2 logic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(logic1);
        contextV2.execute();

        ContextV1 contextV3 = new ContextV1(new StrategyLogic1());
        contextV3.execute();

    }

}
