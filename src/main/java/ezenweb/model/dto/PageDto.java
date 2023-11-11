package ezenweb.model.dto;

import java.util.List;

public class PageDto {

    // 1. 반환된 총 게시물들
    List<BoardDto> boardDtos;
    // 2. 반환된 총 페이지 수
    int totalPages;
    // 3. 반환된 총 게시물 수
    int totalCount;


}
