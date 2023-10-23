import {Link} from "react-router-dom";
export default function Header( props ){
    return(<>
        <header class={"headerWrap"}>
            <h2> <Link to='/'> 이젠리액트 </Link> </h2>
            <ul class={'listBox'}>
                <li> <Link to='/'>리액트예제 </Link></li>
                <li> <Link to='/'>TODO </Link></li>
                <li> <Link to='/'>비회원게시판 </Link></li>
                <li> <Link to='/'>회원게시판 </Link></li>

                <li> <Link to='/'>로그인 </Link></li>
                <li> <Link to='/'>회원가입 </Link></li>
                <li> <Link to='/'>로그아웃 </Link></li>
            </ul>
        </header>
    </>)
}