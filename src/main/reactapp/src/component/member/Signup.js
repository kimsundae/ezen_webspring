
import styles from '../css/Signup.css'
import axios from "axios";
export default function Signup( props ){
    // 1. 회원가입 버튼을 클릭했을 때
    const onSignup = (e) => {
        let signupInfo = {
            memail: document.querySelector('.memail').value,
            mpassword: document.querySelector('.mpassword').value,
            mname: document.querySelector('.mname').value,
            mphone: document.querySelector('.mphone').value
        }
        axios
            .post('http://192.168.17.138:80/member',signupInfo)
            .then( r =>{
                console.log(r)
                if(r)
                    alert('회원가입 성공')
                else
                    alert('회원가입 실패')
            })
    }

    return(<>
        <form className={"formWrap"}>
            <div>
                 <div>이메일[아이디]</div>  <input type={'text'} placeholder={'@포함 7~30글자'} className={'memail'}/>
            </div>
            <div>
                <div>비밀번호</div>  <input type={"password"} placeholder={'특수문자 조합 5~30글자'} className={'mpassword'}/>
            </div>
            <div>
                <div>비밀번호 확인</div>  <input type={"password"} placeholder={'특수문자 조합 5~30글자'}/>
            </div>
            <div>
                <div>이름</div>  <input type={'text'} placeholder={'이름'} className={'mname'}/>
            </div>
            <div>
                <div>전화번호</div>  <input type={'text'} placeholder={'전화번호'} className={'mphone'}/>
            </div>
            <button type={'button'} onClick={onSignup} className={'signup'}>회원가입</button>
        </form>
    </>)
}