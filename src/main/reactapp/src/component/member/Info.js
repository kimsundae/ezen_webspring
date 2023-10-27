import styles from '../css/login.css'
import axios from 'axios'
import {useEffect, useState} from 'react';

export default function Info( props ){
    const [ member, setMember] = useState( null );

    // 로그인 정보를 호출해서 출력하기. [ 최초 1번 실행 ]
    useEffect(() => {
        axios.get('/member/get').
        then( r => { setMember( r.data );} )
    }, []);

    // 1. 이름 입력했을 때. 상태변경
    // function mnameInputChange(e){}
    const mnameInputChange = (e)=>{
        console.log( e.target.value ); // onChange 이벤트를 실행한 주체자[e.target]의 값 호출[.value]
        let mnameInput = e.target.value;
        // setMember( mnameInput ) 문제점
        // member 상태변수에 전체 수정만이 아닌 member 상태 변수내 특정 속성만 변경 해야함

       /* let changeMember = { ...member };      // 기존 객체를 새로운 객체? 에 대입
        changeMember.mname = mnameInput // 객체의 특정 속성만 새로운 값 대입
        setMember( changeMember )       // 수정된 새로운 객체를 상태변수에 대입*/
            // 문제점 : setState()는 상태변수의 주소값이 변경될 때 반응/랜더링
        let changeMember = { ...member }; // 기존 객체를 새로운 객체로 만듦
            // !! : 1. 객체 복사 방법 { ...객체명 } , 2.배열 복사 방법[...배열명]
            // ... Spread Operator : 얕은 복제
        // changeMember.mname = mnameInput; // 객체의 특정 속성만 새로운 값 대입
        // setMember( changeMember );       // 수정된 새로운 객체를 상태변수에 대입

        setMember( {...member, mname : mnameInput })
    }
    const mphoneInputChange = (e) => {
        setMember({...member, mphone: e.target.value}) ;
    }


    return(<>
        <div className={'loginContainer'}>
            <h3> ReactEzen Info </h3>
            <form>
                회원등급 <div>{member != null ? member.mrol : ''}</div>
                이메일 <input value={member!=null?member.email:''} disabled type={"text"} placeholder={"@포함 7~30글자"} className={'memail'}/>
                새 비밀번호 <input type={"password"} placeholder={"특수문자 조합 5~30글자"} className={'mpassword'}/>
                새 비밀번호 확인 <input type={"password"} placeholder={"특수문자 조합 5~30글자"} className={'mpassword2'}/>
                이름 <input value={member!=null?member.mname:''} type={"text"} placeholder={"이름"} className={'mname'}
                          onChange={mnameInputChange}
                />
                전화번호 <input
                value={member!=null?member.mphone:''}
                type={"text"}
                placeholder={"연락처"}
                className={'mphone'}
                onChange={mphoneInputChange}
            />
                <button type={"button"}>정보 수정</button>
                <button type={"button"}>회원 탈퇴</button>
            </form>
        </div>
    </>);
}