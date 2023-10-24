/*

    - HTTP 기반의 비통기 통신 함수
*   Axios( React 라이브러리 ) <---> AJAX( Jquery라이브러리 )
    1. 설치
        1. 터미널 [ alt+f12 ]
        2. 리액트 프로젝트 폴더 내 설치
            - 키보드 위/아래 화살표 입력 시 기존 코드 표시
        3. npm i axios / npm insert axios

    2. 특징

    2. 컴포넌트에서 axios 사용하기
        1. import
            파일 상단에 import axios from 'axios';
        2. ajax vs axios
                $.ajax({})
                vs
                axios.methodType( url ).then( 반환매개변수 => {} )
        3.
            axios
                .methodType( url, data, headerOption )
                .then( 반환매개변수 => {} )
                .catch( 오류매개변수 => {} )

            - JSON객체를 HTTP BODY 형식으로 보낼 때
            axios
                .methodType( url, JSON 객체 )
                .then( 반환매개변수 => {} )

            - JSON객체를 쿼리스트링 형식 보낼 때
            axios
                .methodType( url, { params : JSON 객체 } )
                .then( 반환매개변수 => {} )


* */
import axios from 'axios' // npm i axios 설치 후 사용 가능
export default function Axios컴포넌트( props ){

    // 컴포넌트(함수) 안에서 함수 정의하기
    // 1. 기본함수
    function 함수1( e ){ console.log(e) } // e:event [event 실행후 상태/결과 저장된 매개변수 ]

    // 2. 화살표함수를 저장하는 상수
    const 함수2 = ( e ) => { console.log( e )}

    // 3. 화살표함수를 매개변수 받기
    const 함수3 = ( e, data ) => { console.log( e ); console.log( data )}
    // ---------------- AXIOS ---------------------- //
    // 1. GET
    function doGet(){
        //axios.메소드(url).then( result => {} )
        axios.get('https://jsonplaceholder.typicode.com/posts').then( response => { console.log(response);})

        axios.get('https://jsonplaceholder.typicode.com/posts/1').then( response => { console.log(response);})

        axios.get('https://jsonplaceholder.typicode.com/comments?postId=1').then( response => { console.log(response);})

        axios.get('https://jsonplaceholder.typicode.com/comments',{params:{'postId':1}}).then( response => { console.log(response);})
    }
    // 2. POST
    function doPost(){
        let saveInfo = { title: 'foo', body: 'bar',  userId: 1, }
        axios
            .post('https://jsonplaceholder.typicode.com/posts',saveInfo)
            .then( r=> {console.log( r.data )})
    }
    // 3. PUT
    function doPut(){
        let updateInfo = { id:1, title:'updateFoo', body:'updateBar' ,userId: 1 }
        axios
            .put('https://jsonplaceholder.typicode.com/posts/1',updateInfo)
            .then( r=> {console.log(r.data)})
    }
    // 4. DELETE
    function doDelete(){

        axios
            .delete('https://jsonplaceholder.typicode.com/posts/1')
            .then( r=> {console.log(r.data)})
    }
    return(<>
        <h3>AXIOS 테스트</h3>
        {/*JSX에서 이벤트속성 [ 1.이벤트명(카멜표기법) 2. { 함수명 }] 3. { e=>함수(e, 매개변수1, 매개변수2) }*/}
        <button type={"button"} onClick={ 함수1 }> 함수1 </button>
        <button type={"button"} onClick={ 함수2 }> 함수2 </button>
        <button type={"button"} onClick={ e => 함수3( e, 3)}> 함수3 </button>

        <button type={"button"} onClick={doGet}> doGet AXIOS </button>
        <button type={"button"} onClick={doPost}> doPost AXIOS </button>
        <button type={"button"} onClick={doPut}> doPut AXIOS </button>
        <button type={"button"} onClick={doDelete}> doDelete AXIOS </button>
    </>)
}

