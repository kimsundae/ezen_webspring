package example.객체연관관계;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main.main");

        // 1. [A객체생성] '강호동' 회원가입 진행.
        회원 A객체 = 회원.builder()
                .회원번호(1).회원아이디("qwe").회원이름("강호동").build();
        // 2. '강호동'회원(A객체) 글쓰기 진행.
        게시물 B객체 = 게시물.builder()
                .게시물번호(1).게시물제목("강호동이쓴글").게시물내용("호동이가 작성한 내용")
                .작성한회원( A객체 )// B게시물을 작성한 회원 A객체
                .build();
        // DB PK[A테이블] -- FK[B테이블] vs JAVA ??
        // ORM (Object Relational Mapping)
        // 3. B객체( A객체 참조 )
        System.out.println("B객체 = " + B객체);
        // ------------------------------ //
        // 4. '게시물 댓글달기'(C객체) 댓글 작성 진행 [ 비회원제 ]
        댓글 C객체 = 댓글.builder()
                .댓글번호(1)
                .댓글내용("댓글내용입니다.")
                .댓글게시물( B객체 ) // B객체에 C객체(댓글) 달기
                .build();
        System.out.println("C객체 = " + C객체);

        // -------------------------------------
        // 5. 양방향.. 회원이 작성한 글을 보고 싶을 때.. [ 내가 쓴 글 확인하기 ]
        System.out.println("A객체 = " + A객체);
        A객체.get내가쓴글().add(B객체);

        게시물 D객체 = 게시물.builder()
                .게시물번호(2).게시물제목("강호동이쓴글2").게시물내용("호동이가 작성한 내용2")
                .작성한회원( A객체 )// B게시물을 작성한 회원 A객체
                .build();

        A객체.get내가쓴글().add(D객체);

        System.out.println("A객체 = " + A객체);

    }
}
