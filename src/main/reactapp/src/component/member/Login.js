import styles from '../css/Login.css'
import {Link} from "react-router-dom";
import axios from "axios";
export default function Login( props ){
    // 로그인 버튼을 클릭했을 때
    function onLogin(e){
        // 2. axios를 이용한 Restful api로 spring Controller 데이터 전달
            // 3. 데이터구성
            let info = {
                memail : document.querySelector('.memail').value,
                mpassword: document.querySelector('.mpassword').value
            }
        // 4. !! Axios 통신 [ Spring CONTROOLER 매핑 확인 후 ]
        axios
            .post('http://192.168.17.138:80/member/login',info)
            .then( r=> { console.log(r);
                alert(r.data)
            })
        // CORS policy 오류 발생 해결방안
            // - 스프링 controller 클래스 @
    }

    return(<>
        <form className={'formWrap'}>
            <div className={'idBox'}>
                아이디  <input type={'text'} placeholder={'email address'} className={'memail'}/>
            </div>
            <div className={'pwBox'}>
                비밀번호  <input type={"password"} placeholder={'password'} className={'mpassword'}/>
            </div>
            <div className={'findBox'}>
                <Link to={''}>아이디찾기 |</Link><Link to={''}> 비밀번호 찾기 </Link>
                <button onClick={onLogin} type={'button'} className={'loginBtn'}>로그인</button>
            </div>
        </form>
    </>)
}