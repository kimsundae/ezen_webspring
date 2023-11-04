package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    // 1.
    @Transactional // 함수 내 여럿SQL을 하나의 일처리 단위로 처리
    public boolean write( BoardDto boardDto ){

        // 1. 로그인된 회원의 pk번호 호출
        //memberService.getMember().getMno();
        // 2. 회원pk번호를 가지고 pk엔티티 찾기
        // =============== 단방향 ============= //
        Optional<MemberEntity> memberEntityOptional =
                memberEntityRepository.findById( memberService.getMember().getMno() );
        // 3. 유효성 검사 [ 로그인이 안된 상태 글쓰기 실패 ]
        if( !memberEntityOptional.isPresent()){ return false;}
            // 4. 단방향 저장
                // 1. 게시물 생성
        BoardEntity boardEntity = boardEntityRepository.save( boardDto.saveToEntity() );
            // 2. 게시물에 작성자 엔티티 넣어주기
        boardEntity.setMemberEntity(memberEntityOptional.get());
        //============= 양방향 ================ //
            // 5. 양방향 저장 [회원엔티티에 게시물 엔티티 넣어주기]
        memberEntityOptional.get().getBoardEntityList().add(boardEntity);
        if( boardEntity.getBno() >= 1) {return true;} return false;
    }
    // 2.
    @Transactional
    public List<BoardDto> getAll(){
        System.out.println("BoardService.getAll");
        // 1. 모든 게시물 호출
        List<BoardEntity> list = boardEntityRepository.findAll();
        // 2. List<BoardEntity> --> List<BoardDto>
        List<BoardDto> dtoList = new ArrayList<>();

        list.forEach( entity -> {
            dtoList.add( entity.toDto() );
        });
        return dtoList;
    }

    // 3.
    @Transactional
    public boolean update( BoardDto boardDto ){
        System.out.println("BoardService.update");
        // 1. 수정할 엔티티 찾기 [bno]
        Optional<BoardEntity> boardEntity = boardEntityRepository.findById(boardDto.getBno());
        // 2. 만약에 수정할 엔티티가 존재하면
        if( boardEntity.isPresent() ){
            // 3. 엔티티 꺼내기
            BoardEntity result = boardEntity.get();
            // 4. 수정
            result.setBcontent(boardDto.getBtitle());
            result.setBtitle(boardDto.getBtitle());
            result.setBfile(boardDto.getBfile());
            return true;
        }
        return false;
    }

    // 4.
    @Transactional
    public boolean delete( int bno ){
        System.out.println("BoardService.delete");

        boardEntityRepository.deleteById( bno );
        return true;
    }
}
