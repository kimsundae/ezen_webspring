import axios from "axios";
import {useEffect, useState} from 'react';
export default function BoardList(props){

    // 0. 컴포넌트 상태변수 관리
    let [data,setRows] = useState([])
    useEffect(() => { // 컴포넌트가 생성될 때 한번
        axios.get('/board').then( r=>{console.log(r.data)
            setRows(r.data); // 응답받은 모든 게시물을 상태변수에 저장
            // setState : 해당 컴포넌트가 업데이트 (새로고침/재랜더링/return재실행)
        });
    }, []);


    let rows = [
        { bno: 1, btitle : "안녕1", mno:1, cdate: "2023-11-02", bview:0},
        { bno: 2, btitle : "안녕2", mno:1, cdate: "2023-11-02", bview:0},
        { bno: 3, btitle : "안녕3", mno:1, cdate: "2023-11-02", bview:0},
        { bno: 4, btitle : "안녕4", mno:1, cdate: "2023-11-02", bview:0},
        { bno: 5, btitle : "안녕5", mno:1, cdate: "2023-11-02", bview:0},
        { bno: 6, btitle : "안녕6", mno:1, cdate: "2023-11-02", bview:0}
    ]
    return(<>
        <div>
            <h3> 게시물 목록</h3>
            <a href={"/board/write"}>글쓰기</a>
            <table>
                <tr>
                    <th>번호</th><th>제목</th><th>작성자</th>
                    <th>작성일</th><th>조회수</th>
                </tr>
                {/* 게시물 내용 */}
                {
                    data.map( (row) =>{
                        return(<>
                            <tr>
                                <td>{row.bno}</td><td>{row.btitle}</td><td>{row.mno}</td>
                                <td>{row.cdate}</td><td>{row.bview}</td>
                            </tr>
                        </>)
                    })
                }

            </table>
        </div>
    </>)
}