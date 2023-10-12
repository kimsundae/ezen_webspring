package example.day01.consoleMvc;

import java.time.LocalDate;
import java.util.List;

public class ConsoleController {

    public List<ConsoleDto> doGet(){
        ConsoleDao consoleDao = new ConsoleDao();
        List<ConsoleDto> result = consoleDao.doGet();
        return result;
    }

    public boolean doPost( String title ){

        // 1. 인수 받아서 Dto 생성
            // LocalDate.now() : 현재날짜 호출
        ConsoleDto dto = new ConsoleDto( 0 , title , LocalDate.now() , true);
        ConsoleDao dao = new ConsoleDao();
        boolean result = dao.doPost( dto );
        // 2.
        return result;
    }
}
