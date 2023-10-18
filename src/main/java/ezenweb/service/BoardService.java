package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.repository.BoardEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;

    // 1.
    @Transactional // 함수 내 여럿SQL을 하나의 일처리 단위로 처리
    public boolean write( BoardDto boardDto ){
        System.out.println("BoardService.write");
        return true;
    }
    // 2.
    @Transactional
    public List<BoardDto> getAll(){
        System.out.println("BoardService.getAll");
        return null;
    }

    // 3.
    @Transactional
    public boolean update( BoardDto boardDto ){
        System.out.println("BoardService.update");
        return true;
    }

    // 4.
    @Transactional
    public boolean delete( int bno ){
        System.out.println("BoardService.delete");
        return true;
    }
}
