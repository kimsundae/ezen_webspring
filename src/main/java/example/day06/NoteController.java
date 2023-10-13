package example.day06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    // 1. C
    public boolean bwrite(){ return false; }
    // 2. R
    public List<Object> blist(){ return null; }
    // 3. U
    public boolean bupdate(){ return false; }
    // 4. D
    public boolean bdelete(){ return false; }
}
