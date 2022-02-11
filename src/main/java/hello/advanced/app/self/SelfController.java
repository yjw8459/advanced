package hello.advanced.app.self;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SelfController {

    private final SelfService selfService;

    public String test1(){
        try {
            selfService.test();
            return "ok";
        } catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

}
