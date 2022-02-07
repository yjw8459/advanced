package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.request()");
            if ( itemId.equals("ex") ){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e){
            trace.exception(status, e);
            throw e;//예외를 꼭 다시 던져주어야 한다. return 생략 가능
        }
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
