/*
*   Index : 여러 컴포넌트들을 연결하는 최상위 컴포넌트
* */
import{ BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';
import Main from './Main';
export default function Index(props){
    return(<>
        <BrowserRouter>
            <Header/>
                <Routes>
                    <Route path={'/'} element={<Main/>}></Route>
                </Routes>
            <Footer/>
        </BrowserRouter>
    </>)
}