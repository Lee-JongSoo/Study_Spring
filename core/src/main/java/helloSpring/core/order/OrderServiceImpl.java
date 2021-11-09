package helloSpring.core.order;

import helloSpring.core.discount.DiscountPolicy;
import helloSpring.core.discount.FixDiscountPolicy;
import helloSpring.core.member.Member;
import helloSpring.core.member.MemberRepository;
import helloSpring.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
