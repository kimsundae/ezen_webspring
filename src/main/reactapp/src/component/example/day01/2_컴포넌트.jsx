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
* */

function 컴포넌트2(){
    return(<>
        <h1> 컴포넌트(함수)에서 HTML 작성 </h1>
        <div> HTML 2줄 이상일 때. </div>
    </>)
}

export default 컴포넌트2;