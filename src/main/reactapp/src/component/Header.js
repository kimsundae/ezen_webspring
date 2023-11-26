import {Link} from "react-router-dom";
import axios from "axios";
import {useEffect, useState, useRef} from "react";
export default function Header( props ){

    // 로그인 상태변수 선언
    let [ login, setLogin ] = useState( null );

    axios
        .get('/member')
        .then( r=> {console.log(r.data)})

    const Mlogout = (e) => {
        axios
            .get('/member/logout' )
            .then(r=>{
                console.log(r);
                if(r.data){
                    alert('로그아웃 되었습니다.');
                    sessionStorage.removeItem('login_token')
                    setLogin( null )
                }
                else
                    alert('이미 로그아웃 되었습니다.')
            })
    }
    useEffect(() => {
        // - 회원정보 호출 [ 로그인 여부 확인 ]
        axios.get('/member').then(r=>{
            // 2. 만약에 로그인이 되어 있으면
            if( r.data != '' ){

                sessionStorage.setItem('login_token' , JSON.stringify(r.data))
                setLogin( JSON.parse( sessionStorage.getItem('login_token')));
            }
        })
    }, []);


    return(<>
        <header className={"headerWrap"}>
            <h2> <Link to='/'> 이젠리액트 </Link> </h2>
            <ul className={'listBox'}>
                <li> <Link to='/example'>리액트예제 </Link></li>
                <li> <Link to='/example/task/task1'>도서 목록 </Link></li>
                <li> <Link to='/example/task/task2'>TODO </Link></li>
                <li> <Link to='/'>비회원게시판 </Link></li>
                <li> <Link to='/board/list'>회원게시판 </Link></li>
                <li> <Link to={'/admin/product'}>제품 관리</Link></li>
                {/* 삼항연산자 조건 ? 참 : 거짓 */}
                {login == null
                    ? (<>
                        <li> <Link to='/login'>로그인 </Link></li>
                        <li> <Link to='/Signup'>회원가입 </Link></li>
                    </>)
                    : (<>
                        <li>{login.memail}님</li>
                        <li><a href={'/info'}> 내정보 </a></li>
                        <li> <div onClick={Mlogout}>로그아웃 </div></li>
                    </>)}
            </ul>
        </header>
    </>)
}