package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId){
        //저장 로직
        if ( itemId.equals("ex") ){
            throw new IllegalStateException("예외 발생!");
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
