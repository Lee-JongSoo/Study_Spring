package helloSpring.core;

import helloSpring.core.member.Grade;
import helloSpring.core.member.Member;
import helloSpring.core.member.MemberService;
import helloSpring.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);


        Member findMember = memberService.findByMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
