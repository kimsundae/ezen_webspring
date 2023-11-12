/*
    mui : 리액트 전용 라이브러리
        1. 설치
            - cd src/main/reactapp
            npm install @mui/material @emotion/react @emotion/styled
            npm install @mui/material @mui/styled-engine-sc styled-components
        2. 예제
            1.  사용할 mui 컴포넌트를 상단에 import
                import Button from '@mui/material/Button';
            2. 호출된 mui컴포넌트를 사용
                <Button variant="contained">Hello world</Button>
* */
import * as React from 'react';
import axios from "axios";
// ----- mui table 관련 컴포넌트 import ----- //
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
// ------------- //
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
// ------ sample ------- //

// --------------------//

import {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
export default function BoardList(props){


    // 0. 컴포넌트 상태변수 관리
    const [ pageDto, setPageDto ] = useState( {
        boardDtos : [],
        totalPages : 0,
        totalCount : 0
    } );

    const [ pageInfo, setPageInfo ] = useState({
        page: 1, key: '', keyword: ''
    });

    // 1. axios를 이용한 스프링의 컨트롤과 통신
    useEffect(() => { // 컴포넌트가 생성될 때 한번
        axios.get('/board', {params : pageInfo}).then( r=>{
            setPageDto(r.data); // 응답받은 모든 게시물을 상태변수에 저장
            // setState : 해당 컴포넌트가 업데이트 (새로고침/재랜더링/return재실행)
        });
    }, [pageInfo]);

    // 2. 페이지 번호를 클릭했을 때
    const onPageSelect = (e,value) => { console.log(value);
        pageInfo.page = value;
        setPageInfo({...pageInfo});
    }

    // 3. 검색 버튼을 눌렀을 때
    const onSearch = (e) => {}


    return(<>
        <h3> 게시물 목록</h3>
        <a href={"/board/write"}>글쓰기</a>

        <p>page : {pageInfo.page} totalCount : {pageDto.totalCount}</p>

        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align="right">번호</TableCell>
                        <TableCell align="right">제목(g)</TableCell>
                        <TableCell align="right">작성자(g)</TableCell>
                        <TableCell align="right">작성일</TableCell>
                        <TableCell align="right">조회수</TableCell>

                    </TableRow>
                </TableHead>
                <TableBody>
                    {pageDto.boardDtos.map((row) => (
                        <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                            <TableCell align="right">{row.bno}</TableCell>
                            <TableCell align={"right"}>
                                <Link to={"/board/view?bno="+row.bno}>{row.btitle}</Link>
                            </TableCell>
                            <TableCell align="right">{row.mno}</TableCell>
                            <TableCell align="right">{row.cdate}</TableCell>
                            <TableCell align="right">{row.bview}</TableCell>

                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
        <div style={{display : 'flex', flexDirection : 'column', alignItems: 'center',margin: '10px'}}>
            <Pagination count={pageDto.totalPages } onChange={ onPageSelect } />

            {/* 검색 */}
            <div style={{margin: '20px'}}>
                <select value={pageInfo.key} onChange={(e)=>{setPageInfo({...pageInfo,key:e.target.value})}}>
                    <option value={"btitle"}>제목</option>
                    <option value={"bcontent"}>내용</option>
                </select>
                <input type={"text"} value={pageInfo.keyword} onChange={(e)=>setPageInfo({...pageInfo,keyword:e.target.value})}/>
                <button type={"button"} onClick={ onSearch }>검색</button>
            </div>


        </div>
    </>)
}
{/* <div>
            <h3> 게시물 목록</h3>
            <a href={"/board/write"}>글쓰기</a>
            <table>
                <tr>
                    <th>번호</th><th>제목</th><th>작성자</th>
                    <th>작성일</th><th>조회수</th>
                </tr>
                 게시물 내용
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
        </div>*/}