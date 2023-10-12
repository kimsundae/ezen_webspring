package example.task.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    PhoneEntityRepository phoneEntityRepository;

    public boolean doPost( PhoneDto phoneDto ){
        System.out.println("phoneDto = " + phoneDto);
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .pname(phoneDto.getPname())
                .ppnumber(phoneDto.getPpnumber())
                .build();
        phoneEntityRepository.save( phoneEntity );
        return true;
    }
    public List<PhoneDto> doGet(){

        List<PhoneEntity> phoneEntities = phoneEntityRepository.findAll();

        List<PhoneDto> list = new ArrayList<>();

        phoneEntities.forEach((entity)->{

            PhoneDto phoneDto = PhoneDto.builder()
                    .pno(entity.getPno())
                    .pname(entity.getPname() )
                    .ppnumber( entity.getPpnumber()).build();
            list.add( phoneDto );
        });
        return list;
    }
    public boolean doDelete( int pno ){
        System.out.println("pno = " + pno);
        phoneEntityRepository.deleteById( pno );
        return true;

    }
    @Transactional
    public boolean doPut( PhoneDto phoneDto ){

        Optional<PhoneEntity> phoneEntity = phoneEntityRepository.findById( phoneDto.getPno());

        if( phoneEntity.isPresent() ){
            PhoneEntity updateEntity = phoneEntity.get();

            updateEntity.setPpnumber(phoneDto.getPpnumber());
            updateEntity.setPname(phoneDto.getPname());
        }
        return true;
    }
}
