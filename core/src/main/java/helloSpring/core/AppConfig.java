package helloSpring.core;

import helloSpring.core.discount.FixDiscountPolicy;
import helloSpring.core.member.MemberService;
import helloSpring.core.member.MemberServiceImpl;
import helloSpring.core.member.MemoryMemberRepository;
import helloSpring.core.order.OrderService;
import helloSpring.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
