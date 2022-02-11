package hello.advanced.app.self;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SelfController {

    private final SelfService selfService;

    public String test1(){
        try {
            selfService.test();

            SelfBuilder builder = SelfBuilder.builder().name("yjw").age(0).build();
            return "ok";
        } catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

}
