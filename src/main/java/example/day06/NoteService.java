package example.day06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteEntityRepository noteEntityRepository;

    // 1. C
    public boolean bWrite( NoteDto noteDto ){
        return false;
    }
    // 2. R
    public List<NoteDto> bList( ){
        return null;
    }
    // 3. U
    public boolean bUpdate( NoteDto noteDto){
        return false;
    }
    // 4. D
    public boolean bDelete( int no ){
        return false;
    }
}
