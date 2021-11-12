package helloSpring.core;

import helloSpring.core.discount.DiscountPolicy;
import helloSpring.core.discount.FixDiscountPolicy;
import helloSpring.core.discount.RateDiscountPolicy;
import helloSpring.core.member.MemberRepository;
import helloSpring.core.member.MemberService;
import helloSpring.core.member.MemberServiceImpl;
import helloSpring.core.member.MemoryMemberRepository;
import helloSpring.core.order.OrderService;
import helloSpring.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
