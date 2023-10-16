package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MemberService {

    // Controller -> Service -> Repository
    // Controller <- Service <- Repository
    @Autowired
    private MemberEntityRepository memberEntityRepositoryEntity;

    // 1. [C] 회원가입
    public boolean postMember( MemberDto memberDto ){
        System.out.println("memberDto = " + memberDto);
        MemberEntity memberEntity = memberEntityRepositoryEntity.save(memberDto.toEntity());
        // 2. insert된 엔티티 확인 후 성공/실패 유무
            // 3. 만약에 회원번호가 0보다 크면 ( auto_increment 적용 됨. )
        if( memberEntity.getMno() >= 1 ) { return true;}
        return false;
    }
    // 2. [R] 회원정보 호출
    @GetMapping("")
    public MemberDto getMember( int mno ){
        System.out.println("mno = " + mno);
        // 1. mno[회원번호pk]를 이용한 회원 엔티티 찾기
        Optional<MemberEntity> entity = memberEntityRepositoryEntity.findById( mno );
        // 2. optional 클래스로 검색한 반환값 확인
        if( entity.isPresent() ){ // 3. 만약에 optinal 클래스 안에 엔티티가 들어있으면
            // 4. optinal 클래스에서 엔티티 꺼내고 Dto로 반환
            return entity.get().toDto();
        }
        return null;
    }
    // 3. [U] 회원정보 수정
    @PutMapping("")
    @Transactional
    public boolean putMember( MemberDto memberDto ){
        System.out.println("memberDto = " + memberDto);
        Optional<MemberEntity> entity = memberEntityRepositoryEntity.findById( memberDto.getMno() );
        if(entity.isPresent()){
            MemberEntity result = entity.get();
            result.setMname(memberDto.getMname());
            result.setMpassword(memberDto.getMpassword());
            result.setMphone(memberDto.getMphone());
        }
        return true;
    }
    // 4. [D] 회원탈퇴
    @DeleteMapping("")
    public boolean deleteMember( int mno ){
        System.out.println("mno = " + mno);
        memberEntityRepositoryEntity.deleteById(mno);
        return true;
    }
}
