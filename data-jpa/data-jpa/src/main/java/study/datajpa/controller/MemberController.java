package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    //비추
    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    /**
     * http://localhost:8080/members?page=0&size=3&sort=id,desc&sort=username,desc
     */

    /**
     * @PageableDefault
     @GetMapping("/members")
     public Page<Member> list(@PageableDefault(size = 5, sort="username") Pageable pageable) {
     Page<Member> page = memberRepository.findAll(pageable);
     return page;
     }
     */

    /**
     * @param pageable
     * @GetMapping("/members")
     *     public Page<Member> list(@PageableDefault(size = 5) Pageable pageable) {
     *         Page<Member> page = memberRepository.findAll(pageable);
     *         return page;
     *     }
     */

    /**
     * MemberDto로 변환
     * @GetMapping("/members")
     *     public Page<MemberDto> list(Pageable pageable) {
     *         Page<Member> page = memberRepository.findAll(pageable);
     *         Page<MemberDto> map = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
     *         return map;
     *     }
     * 변수 inLine으로 변환
     *
     */


    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(MemberDto::new);
    }



//    @PostConstruct
//    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }
}
