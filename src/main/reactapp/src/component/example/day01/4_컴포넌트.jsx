/*
*   컴포넌트 만들기
*           - 리액트 2가지 컴포넌트 [ 클래스형 컴포넌트 vs 함수형 컴포넌트 ]
*           - 1. 리액트 컴포넌트 만들 때 사용하는 확장자 JSX 사용한 파일 생성
*           - 2. function 컴포넌트명(){} [영문일 때는 꼭 첫글자를 대문자]
*                   - js 함수 선언 비슷
*           - 3. export default 컴포넌트명;
*               - 해당 jsx파일을 import 했을 때 내보내기 할 컴포넌트 선언
*           - 4. return HTML문법작성
*               ** html 2줄 이상일 때는 시작과 끝 구분
*                   1. (<> HTML문법</>)
*                   2. (<div>HTML문법</div>)
           컴포넌트 내부에서 다른 컴포넌트를 호출하는 방법
                1. 같은 jsx파일이면 import 생략
                2. 다른 jsx파일이면 import
* */
/*
*   JSX 규칙
*       1. return(<>HTML문법</>)    : JSX 구역 표시
*       2. return(<>{JS문법}</>)    : JSX 구역에서 JS 문법을 사용할 때는 {JS문법}
* */
function 컴포넌트4(){
    return(<>
        <input type="text" value={"데이터"}/>
        <내가만든태그속성 이름={"유재석"} 나이={30}/>
        <내가만든태그속성 이름={"강호동"} 나이={40}/>
    </>)
}
function 내가만든태그속성( props ){ // props : 컴포넌트의 매개변수
    console.log(props); // 매개변수로 들어온 props 객체를 확인이 가능하다.
            // ----------- JSX구역 s -------------- //
    return(<>
        <div> 컴포넌트4가 전달한 속성 : {props.이름} / 나이 : {props.나이} </div>
        </>)
    // ----------- JSX구역 e -------------- //
}
export default 컴포넌트4;