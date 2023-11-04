
import axios from 'axios'
import {useEffect, useState} from 'react';

export default function Info( props ){
    const [ member, setMember] = useState( null );

    // 로그인 정보를 호출해서 출력하기. [ 최초 1번 실행 ]
    useEffect(() => {
        axios.get('/member').
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
    // 전화번호 수정시
    const mphoneInputChange = (e) => {
        setMember({...member, mphone: e.target.value}) ;
    }
    //탈퇴 함수
    const DeleteMember = (e) => {
        if( !window.confirm('정말 탈퇴하시겠습니까?'))return;

        axios
            .delete('/member' , {params:{ 'mno' : member.mno }})
            .then( r=> {
                if (r.data) {
                    alert('회원탈퇴되었습니다');
                    sessionStorage.removeItem('login_token')
                    window.location.href = '/'
                } else
                    alert('회원 탈퇴 실패');
            })
    }
    const [ newPassword , setNewPassword ] = useState({mpassword: '', mpassword2 : ''})
    const onUpdate = (e) => {

        // 기존 비밀번호가 일치한지 유효성검사[x] --> 백엔드 해야할 일
        // 새로운 비밀번호 2개 일치한지 유효성검사 --> 프론트엔드 해야할 일
        if( newPassword.mpassword != newPassword.mpassword2 ) return;
        // 1. spring 서비스에게 보낼 데이터 구성 dto[ mno, mname, mpassword, mphone ]
        let info = {
            mno: member.mno,
            mname: member.mname,
            mpassword: newPassword.mpassword,
            mphone: member.mphone
        }; console.log(info)
        axios
            .put("/member" , info)
            .then(r=>{
                if(r){
                    alert('수정 성공');
                    // 세션 수정 (현재 info 저장중)
                    sessionStorage.setItem('login_token' , info);
                    window.location.href = '/'
                }
                else
                    alert('수정 실패')
            })
    }

    return(<>
        <div>
            <h3> ReactEzen Info </h3>
            <form className={'formWrap'}>
                <div>
                    회원등급 <div>{member != null ? member.mrol : ''}</div>
                </div>
                <div>
                    이메일 <input value={member!=null?member.email:''} disabled type={"text"} placeholder={"@포함 7~30글자"} className={'memail'}/>
                </div>
                <div>
                    새 비밀번호 <input type={"password"} placeholder={"특수문자 조합 5~30글자"} className={'mpassword'}
                              value={newPassword.mpassword}
                onChange={(e)=>{setNewPassword({...newPassword,mpassword: e.target.value})}}
            />
                </div>
                <div>
                새 비밀번호 확인 <input type={"password"} placeholder={"특수문자 조합 5~30글자"} className={'mpassword2'}
                value={newPassword.mpassword2}
                onChange={(e)=>{setNewPassword({...newPassword,mpassword2: e.target.value})}}
            />
                    </div>
                <div>
                이름 <input value={member!=null?member.mname:''} type={"text"} placeholder={"이름"} className={'mname'}
                          onChange={mnameInputChange}
                /></div>
                <div>
                전화번호 <input
                value={member!=null?member.mphone:''}
                type={"text"}
                placeholder={"연락처"}
                className={'mphone'}
                onChange={mphoneInputChange}
            /></div>
                <button type={"button"} onClick={onUpdate}>정보 수정</button>
                <button type={"button"} onClick={DeleteMember}>회원 탈퇴</button>
            </form>
        </div>
    </>);
}