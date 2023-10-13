package example.day06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Controller 사용처 웹:JS(AJAX), React(AXIOS), 앱, 소프트웨어
// 역할 : AJAX[외부인] <---- 연결다리[자바] ----> 서비스[자바]
@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    // 1. C
    @PostMapping("")
    public boolean bWrite(@RequestBody NoteDto noteDto ){
        boolean result = noteService.bWrite( noteDto );
        return false;
    }
    // 2. R
    public List<NoteDto> bList( ){
        List<NoteDto> result = noteService.bList();
        return null;
    }
    // 3. U
    public boolean bUpdate(@RequestBody NoteDto noteDto ){
        boolean result = noteService.bUpdate( noteDto );
        return false;
    }
    // 4. D
    public boolean bDelete(@RequestParam int no ){
        boolean result = noteService.bDelete( no );
        return false;
    }
}
