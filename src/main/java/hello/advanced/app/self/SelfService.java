package hello.advanced.app.self;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelfService {

    private final SelfRepository selfRepository;

    public void test(){
        selfRepository.test();
    }


}
