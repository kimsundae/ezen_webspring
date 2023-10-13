package example.day06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteEntityRepository noteEntityRepository;

    // 1. C
    public boolean bwrite(){ return false; }
    // 2. R
    public List<Object> blist(){ return null; }
    // 3. U
    public boolean bupdate(){ return false; }
    // 4. D
    public boolean bdelete(){ return false; }
}
