package helloSpring.core.order;

import helloSpring.core.AppConfig;
import helloSpring.core.member.Grade;
import helloSpring.core.member.Member;
import helloSpring.core.member.MemberService;
import helloSpring.core.member.MemberServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);
    }
}
