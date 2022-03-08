package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;


    @GetMapping("/v3/request")
    public String request(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);//핵심 기능, 나머지는 부가 기능
            trace.end(status);
            return "ok";
        } catch (Exception e){
            trace.exception(status, e);
            /**
             * 예외를 꼭 다시 던져줘야 한다. return 대신 throw 가능
             * 예외를 꼭 다시 던져주어야 한다. 던지지 않을 경우 정상 흐름으로 간다.
             */
            throw e;
        }
    }

}
