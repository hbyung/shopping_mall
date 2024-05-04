package study.shopping_mall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.dto.MemberDto;
import study.shopping_mall.entity.Member;
import study.shopping_mall.respository.MemberRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public long joinProcess(MemberDto memberDto) {
        String username = memberDto.getUsername();
        String password = memberDto.getPassword();
        String email = memberDto.getEmail();

        Boolean isExist = memberRepository.existsByEmail(email);

        if (isExist) {
           return 0;
        }

            if (email.equals("admin")) {
                return CreateMember(username, password, "ROLE_ADMIN", email);

            }else {
               return CreateMember(username, password, "ROLE_USER", email);

            }

    }

    private long CreateMember(String username, String password, String roleUser, String email) {
        Member member = new Member(username, bCryptPasswordEncoder.encode(password), roleUser, email);
        memberRepository.save(member);
        return member.getId();
    }

    public Member findByUsername(String username){
        Member member = memberRepository.findByUsername(username);
        return member;
    }

    public Member findByName(String name){
        Member member = memberRepository.findByName(name);
        return member;
    }

    public Member findByEmail(String email){
        Member member = memberRepository.findByEmail(email);
        return member;
    }


    public Member findById(long id){
        Member member = memberRepository.findById(id);
        return member;
    }
}
