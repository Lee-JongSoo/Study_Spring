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

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
