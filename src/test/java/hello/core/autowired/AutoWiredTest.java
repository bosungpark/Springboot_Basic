package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void  AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false)
        public  void  setNoBean1(Member niBean1){
            System.out.println("niBean1 = " + niBean1);
        }

        @Autowired
        public  void  setNoBean2(@Nullable Member niBean2){
            System.out.println("niBean2 = " + niBean2);
        }

        @Autowired
        public  void  setNoBean3(Optional<Member> niBean3){
            System.out.println("niBean3 = " + niBean3);
        }
    }
}
