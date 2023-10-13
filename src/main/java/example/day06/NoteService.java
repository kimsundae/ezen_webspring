package example.day06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    NoteEntityRepository noteEntityRepository;

    // 1. C
    public boolean bWrite( NoteDto noteDto ){
        noteEntityRepository.save( noteDto.toEntity() );
        return false;
    }
    // 2. R
    public List<NoteDto> bList( ){
        // 1. 모든 엔티티 리스트를 호출하기
        List<NoteEntity> entities = noteEntityRepository.findAll();
        // 2. 모든 엔티티 리스트 -> dto 리스트 변환
        List<NoteDto> noteDtos = new ArrayList<>();
        entities.forEach(e->{noteDtos.add(e.toDto());});
        return noteDtos;
    }
    // 3. U
    @Transactional // 트랜잭션 : 하나/여럿 작업들을 묶어서 최소 단위 업무처리
    public boolean bUpdate( NoteDto noteDto){
        // 1. 수정할 pk번호에 해당하는 엔티티 찾기.
        Optional<NoteEntity> optionalNoteEntity
                = noteEntityRepository.findById( noteDto.getNo() );
        // 2. 포장 내 내용물이 있는지 체크 = 안전하게 검토
        if(optionalNoteEntity.isPresent()){
            NoteEntity noteEntity = optionalNoteEntity.get();
            // 4. 수정 : 엔티티 객체의 필드를 수정하면 DB도 같이 수정[ 매핑이 된 상태이므로 ]
            noteEntity.setTitle(noteDto.getTitle());
            noteEntity.setWriter(noteDto.getWriter());
        }
        return false;
    }
    // 4. D
    public boolean bDelete( int no ){
        noteEntityRepository.deleteById(no);
        return false;
    }
}
