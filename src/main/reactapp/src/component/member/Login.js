import '../css/Login.css'
import {Link} from "react-router-dom";
import axios from "axios";
export default function Login( props ){
    // 로그인 버튼을 클릭했을 때
    function onLogin(e){
        // 2. axios를 이용한 Restful api로 spring Controller 데이터 전달
            // 3-1. 데이터구성[json]
            /*let info = {
                memail : document.querySelector('.memail').value,
                mpassword: document.querySelector('.mpassword').value
            }*/
            // 3-2 데이터구성[ FORM ]
            let loginForm = document.querySelectorAll('.loginForm')[0]
            let loginFormData = new FormData( loginForm )
        // 4. !! Axios 통신 [ Spring CONTROOLER 매핑 확인 후 ]
        axios
            .post('/member/login',loginFormData)
            .then( r=> { console.log(r.data);
                if( r.data ){
                    alert('로그인 성공')
                    window.location.href = '/'
                }
                else{ alert('로그인 실패')}


            })
        // CORS policy 오류 발생 해결방안
            // - 스프링 controller 클래스 @
    }

    return(<>
        <form className={"loginForm"}>
            <div className={'idBox'}>
                아이디  <input type={'text'} placeholder={'email address'} name={"memail"} className={'memail'} id={"memail"}/>
            </div>
            <div className={'pwBox'}>
                비밀번호  <input type={"password"} placeholder={'password'} name={"mpassword"} className={'mpassword'} id={'mpassword'}/>
            </div>
            <div className={'findBox'}>
                <Link to={''}>아이디찾기 |</Link><Link to={''}> 비밀번호 찾기 </Link>
                <button type={'button'} onClick={onLogin} className={'loginBtn'}>로그인</button>

                <a className={"oauthBtn"} href={"/oauth2/authorization/kakao"}>카카오 1초 로그인</a>
                <a className={"oauthBtn"} href={"/oauth2/authorization/naver"}>네이버 1초 로그인</a>
                <a className={"oauthBtn"} href={"/oauth2/authorization/google"}>구글 1초 로그인</a>
            </div>
        </form>
    </>)
}
/*
// AXIOS 이용한 로그인 처리 형식
<div className={'idBox'}>
    아이디  <input type={'text'} placeholder={'email address'} className={'memail'}/>
</div>
<div className={'pwBox'}>
    비밀번호  <input type={"password"} placeholder={'password'} className={'mpassword'}/>
</div>
<div className={'findBox'}>
    <Link to={''}>아이디찾기 |</Link><Link to={''}> 비밀번호 찾기 </Link>
    <button onClick={onLogin} type={'button'} className={'loginBtn'}>로그인</button>
</div>*/


/*<form action={"/member/login"} method={"POST"} className={'formWrap'}>
            <div className={'idBox'}>
                아이디  <input type={'text'} placeholder={'email address'} className={'memail'}/>
            </div>
            <div className={'pwBox'}>
                비밀번호  <input type={"password"} placeholder={'password'} className={'mpassword'}/>
            </div>
            <div className={'findBox'}>
                <Link to={''}>아이디찾기 |</Link><Link to={''}> 비밀번호 찾기 </Link>
                <button type={'submit'} className={'loginBtn'}>로그인</button>
            </div>
        </form>
* */

