/*
*   Index : 여러 컴포넌트들을 연결하는 최상위 컴포넌트
* */
import{ BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';
import Main from './Main';
import ExampleList from "./ExampleList";
import 컴포넌트1 from './example/day01/1_컴포넌트'
import 컴포넌트2 from './example/day01/2_컴포넌트'
import 컴포넌트3 from './example/day01/3_컴포넌트'
import 컴포넌트4 from './example/day01/4_컴포넌트'
import CSS컴포넌트 from './example/day02/1_컴포넌트css'
import CommentList from './example/day02/CommentList'
import 도서목록 from './example/task/task1/과제1_도서목록'
import Todo from './example/task/task2/TodoList'
import Login from './member/Login'
import Signup from './member/Signup'
import Axios컴포넌트 from "./example/day04/1_Axios컴포넌트";
import styles from './css/Header.css'
import Info from "./member/Info";
export default function Index(props){
    return(<>
        <div className={'webContainer'}>
            <BrowserRouter>
                <Header/>
                    <Routes>

                        <Route path={'/'} element={<Main/>}></Route>
                        {/*Example*/}
                        <Route path={'/example'} element={<ExampleList/>}></Route>
                        <Route path={'/example/day01/컴포넌트1'} element={<컴포넌트1/>}></Route>
                        <Route path={'/example/day01/컴포넌트2'} element={<컴포넌트2/>}></Route>
                        <Route path={'/example/day01/컴포넌트3'} element={<컴포넌트3/>}></Route>
                        <Route path={'/example/day01/컴포넌트4'} element={<컴포넌트4/>}></Route>
                        <Route path={'/example/day02/CSS적용컴포넌트'} element={<CSS컴포넌트/>}></Route>
                        <Route path={'/example/day02/CommentList'} element={<CommentList/>}></Route>
                        <Route path={'/example/task/task1'} element={<도서목록/>}></Route>
                        <Route path={'/example/task/task2'} element={<Todo/>}></Route>

                        <Route path={'/example/day04/Axios컴포넌트'} element={<Axios컴포넌트/>}></Route>
                        {/*Member*/}
                        <Route path={'/login'} element={<Login/>}></Route>
                        <Route path={'/signup'} element={<Signup/>}></Route>
                        <Route path={'/myPage'} element={<Info/>}></Route>

                    </Routes>
                <Footer/>
            </BrowserRouter>
        </div>
    </>)
}