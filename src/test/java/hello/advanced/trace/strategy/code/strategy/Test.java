package hello.advanced.trace.strategy.code.strategy;

public class Test {

    public void test(){
        ContextV1 context = new ContextV1(new Strategy() {
            @Override
            public void call() {
                System.out.println("test");
            }
        });
    }
}
