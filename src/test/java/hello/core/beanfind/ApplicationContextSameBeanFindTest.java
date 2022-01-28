package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("when we find by type, if there is same type that duplicated, duplication error happens.")
    void  findBeanBytypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("if there is duplicated name, we can define bean's name.")
    void  findBeanByName(){
        MemberRepository memberRepository=ac.getBean("memberRepository1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("find all if there is specific type.")
    void findAllBeanByType(){
        Map<String,MemberRepository> beansOfType =ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key= "+key+"value= "+beansOfType.get(key));
        }
        System.out.println("beansOfType= "+beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static  class  SameBeanConfig{

        @Bean
        public MemberRepository memberRepository1() {
            return  new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return  new MemoryMemberRepository();
        }
    }
}
