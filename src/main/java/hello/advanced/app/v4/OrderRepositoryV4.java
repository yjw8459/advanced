package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId){
        new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                if ( itemId.equals("ex") ){
                    throw new IllegalStateException("예외 발생");
                }
                sleep(1000);
                return null;
            }
        }.execute("OrderRepository.save()");
    }

    private void sleep(int mills){
        try{
            Thread.sleep(mills);    //상품 저장 1초 가정
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
