package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.PageDto;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    // 1. 게시물 등록
    @PostMapping("")
    public boolean write( BoardDto boardDto ){
        System.out.println("BoardController.write");
        return boardService.write( boardDto );
    }
    // 2. 전체 게시물 출력
    @GetMapping("")
    public PageDto getAll(@RequestParam int page, @RequestParam String key, @RequestParam String keyword ){
        System.out.println("BoardController.getAll");
        return boardService.getAll(page,key,keyword);
    }

    // 2-2 개별 게시물 출력
    @GetMapping("/doGet")
    public BoardDto doGet(@RequestParam int bno){return boardService.doGet( bno );}

    // 3. 게시물 수정
    @PutMapping ("")
    public boolean update( BoardDto boardDto ){
        System.out.println("BoardController.update");
        return boardService.update( boardDto );
    }

    // 4. 게시물 삭제
    @DeleteMapping("")
    public boolean delete(@RequestParam int bno ){
        System.out.println("BoardController.delete");
        return boardService.delete(bno);
    }
}
