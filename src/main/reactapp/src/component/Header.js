import {Link} from "react-router-dom";
import axios from "axios";
export default function Header( props ){

    axios
        .get('/member')
        .then( r=> {console.log(r.data)})


    return(<>
        <header className={"headerWrap"}>
            <h2> <Link to='/'> 이젠리액트 </Link> </h2>
            <ul className={'listBox'}>
                <li> <Link to='/example'>리액트예제 </Link></li>
                <li> <Link to='/example/task/task1'>도서 목록 </Link></li>
                <li> <Link to='/example/task/task2'>TODO </Link></li>
                <li> <Link to='/'>비회원게시판 </Link></li>
                <li> <Link to='/'>회원게시판 </Link></li>

                <li> <Link to='/login'>로그인 </Link></li>
                <li> <Link to='/Signup'>회원가입 </Link></li>
                <li> <Link to='/'>로그아웃 </Link></li>
            </ul>
        </header>
    </>)
}