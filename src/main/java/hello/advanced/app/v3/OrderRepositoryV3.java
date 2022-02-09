package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

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
