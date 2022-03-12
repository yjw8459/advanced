package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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

    /**
     * 전략 패턴 람다 사용
     * 선 조립 후 사용은 조립 후 변경하기 번거롭다.
     * 전략을 실시간으로 변경해야 하거나 컨텍스트를 싱글톤으로 사용할 경우,
     * 컨텍스트를 하나 더 생성하고 다른 Strategy를 주입하는 것이 나을 수 있다.
     */
    @Test
    public void strategyV2(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직1 실행")); //선 조립
        contextV1.execute();//후 실행

        //다른 컨텍스트 생성
        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
        contextV2.execute();
    }




}
