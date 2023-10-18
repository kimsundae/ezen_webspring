package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.repository.BoardEntityRepository;
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

    // 1.
    @Transactional // 함수 내 여럿SQL을 하나의 일처리 단위로 처리
    public boolean write( BoardDto boardDto ){

        boardEntityRepository.save( boardDto.saveToEntity() );
        return true;
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
