package ezenweb.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@Service
public class FileService {
    /* 파일관련 메소드 정의 : */
    // 0. 파일 경로 [ 배포전 ]
    private String fileRootPath = "c:\\java\\";
    // 1.업로드
    public String fileUpload(MultipartFile multipartFile){
        // 0. 유효성 검사
        if( multipartFile.isEmpty() ) return null;

        // 1. 파일명
        String fileName = multipartFile.getOriginalFilename();
        // 2. 파일 경로
        File file = new File( fileRootPath + fileName );
        // 3. 업로드
        try{
            multipartFile.transferTo( file );
            return fileName;
        }catch ( Exception e ){
            System.out.println("업로드 실패 " + e);
            return null;
        }
    }
    // 2. 다운로드
}
