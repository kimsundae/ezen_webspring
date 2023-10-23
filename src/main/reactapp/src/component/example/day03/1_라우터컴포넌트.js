/*
*
*   컴포넌트 만들기
*       - 파일명 : 아무거나.js 혹은 아무거나.jsx [ 권장 : 컴포넌트명과 동일 ]
*       - 컴포넌트 원형
*           - 컴포넌트명 : 첫글자는 대문자 [ 무조건, 카멜 표기법 ]
*           export default function 컴포넌트( props ){
*               return
*       }
*   컴포넌트 랜더링
*        - 최상위 랜더링( 가장 먼저 랜더링 )
*           1. index.js
*               import 컴포넌트명 from '컴포넌트경로'
*               root.render( <React.StrictMode> <컴포넌트명/> </React.StrictMode>)
*   - 라우터 : 가상 URL 만들기
*       - 실제 라우터 : 연결 경로를 자동으로 전환해주는 기계
*       - 리액트 라우터 : 가상 경로[URL]을 만들어서 컴포넌트를 전환해주는 라이브러리
*           1. https://www.npmjs.com/package/router-dom
*           2. router-dom
*           3. 리액트 버전 주의
*               npm i router-dom [ 2.2.11 ]
*               npm i react-router-dom [ 6.17.0 ] 수업 버전
*           4. 터미널(alt+f12)
*               1. 터미널 종료
*               2. 리액트 프로젝트 이동 [ cd src/main/reactapp ]
*           - 해당 파일에서 외부라이브러리 import
*
* */
import { BrowserRouter , Routes , Route, Link }from "react-router-dom"  ; //npm i react-router-dom 설치 후 가능
import 컴포넌트1 from '../day01/1_컴포넌트' // 다른 폴더에 있는 컴포넌트 호출
export default function 라우터컴포넌트( props ){
    return(<>
        <BrowserRouter>{/*브라우저 라우터 시작*/}
            <Routes>
                {/**/}
                <Route path='/day01/1_컴포넌트' element = {<컴포넌트1/>}/> {/*컴포넌트로 연결할 가상 url*/}
            </Routes>
        </BrowserRouter>{/*브라우저 라우터 끝*/}
    </>)
}


