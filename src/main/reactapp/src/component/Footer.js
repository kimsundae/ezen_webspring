import styles from './css/Footer.css'
export default function Footer(){
    return(<>
        <footer className={"footerWrap"}>
            <nav className={"leftInforBox"}>
                <a href={'/'} target={'_blank'}>회사소개 | </a>
                <a href={'/'} target={'_blank'}>개인정보규정 | </a>
                <a href={'/'} target={'_blank'}>환불규정 | </a>
                <a href={'/'} target={'_blank'}>찾아오시는길 | </a>
                <a href={'/'} target={'_blank'}>고객센터</a>
            </nav>
            <p class={"rightInforBox"}>
                <div> 이젠 리액트 </div>
                <div> nnn@nnn.com </div>
                <div> 010-0000-0000 </div>
                <div> Copyright 2023. ezenreact. All Rights Reserved </div>
            </p>
        </footer>
    </>)
}