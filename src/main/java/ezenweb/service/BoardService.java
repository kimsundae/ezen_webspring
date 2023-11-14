package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private FileService fileService;
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
        MemberDto loginDto = memberService.getMember();
        if( loginDto == null) return false;
        // 2. 회원pk번호를 가지고 pk엔티티 찾기
        // =============== 단방향 ============= //
        Optional<MemberEntity> memberEntityOptional =
                memberEntityRepository.findById( loginDto.getMno() );
        // 3. 유효성 검사 [ 로그인이 안된 상태 글쓰기 실패 ]
        if( !memberEntityOptional.isPresent()){ return false;}
            // 4. 단방향 저장
                // 1. 게시물 생성
                // 게시물 엔티티 등록
        BoardEntity boardEntity = boardEntityRepository.save( boardDto.saveToEntity() );
            // 2. 게시물에 작성자 엔티티 넣어주기
        boardEntity.setMemberEntity(memberEntityOptional.get());
        //============= 양방향 ================ //
            // 5. 양방향 저장 [회원엔티티에 게시물 엔티티 넣어주기]
        memberEntityOptional.get().getBoardEntityList().add(boardEntity);
        if( boardEntity.getBno() >= 1) {
            // 게시물 쓰기 성공 시 파일 처리
            String filename
                    = fileService.fileUpload(boardDto.getFile());
            System.out.println("file = "+filename);
            // 파일 처리 결과를 db에 저장
            if( filename != null) {boardEntity.setBfile(filename);
                }
            return true;
        }
        return false;
    }
    // 2.
    @Transactional
    public PageDto getAll( int page, String key, String keyword, int view ){

        // * JPA 페이징 처리 라이브러리 지원
            // 1. Pageable : 페이지 인터페이스( 구현체: 구현[ 추상메소드(인터페이스 가지는 함수)를 구현]해주는 객체)
                // 사용이유 : Repository이너페이스가 페이징처리할 때 사용하는 인터페이스
            // 2. PageRequest : 페이지 구현체
                // of( 현재페이지, 페이지별 게시물 수)
                // 현재페이지 : 0부터 시작
                // 페이지별게시물수 : 만약에 2일때는 페이지마다 게시물 2개씩 출력
            // 3. Page: list와 마찬가지로 페이징결과의 여러개 객체를 저장하는 타입[인터페이스]
                // list와 다르게 추가적으로 함수 지원
        // 페이징 처리
        Pageable pageable = PageRequest.of( page-1, view, Sort.by(Sort.Direction.DESC,"cdate") );
        // 1. 모든 게시물 호출
        //Page<BoardEntity> list = boardEntityRepository.findAll(pageable);
        Page<BoardEntity> boardEntities = boardEntityRepository.findBySearch(key, keyword, pageable);

        // 2. List<BoardEntity> --> List<BoardDto>
        List<BoardDto> dtoList = new ArrayList<>();
        boardEntities.forEach( entity -> {
            dtoList.add( entity.toDto() );
        });

            // 3. 총 페이지수
        int totalPages = boardEntities.getTotalPages();
            // 4. 총 게시물 수
        Long totalCount = boardEntities.getTotalElements();

            // 5. pageDto 구성해서 axios에게 전달 DIS
        PageDto pageDto = PageDto.builder()
                .boardDtos(dtoList)
                .totalCount(totalCount)
                .totalPages(totalPages)
                .build();

        return pageDto;
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
            result.setBcontent(boardDto.getBcontent());
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
    // 5 [2-2] 개별게시물 출력
    @Transactional
    public BoardDto doGet( int bno ){

        // 1. pk 번호에 해당하는 엔티티 찾기
        Optional<BoardEntity> boardEntityOptional = boardEntityRepository.findById( bno );

        // 2. 검색된 엔티티가 존재하면
        if( boardEntityOptional.isPresent() ){
            // 3. 엔티티 꺼내기
            BoardEntity boardEntity = boardEntityOptional.get();
                // + 조회수 증가
                boardEntity.setBview(boardEntity.getBview()+1);
            // 4. 엔티티 -> dto 변환
            BoardDto boardDto = boardEntity.toDto();
            // 5. dto 반환
            return boardDto;
        }
        return null;
    }
}
