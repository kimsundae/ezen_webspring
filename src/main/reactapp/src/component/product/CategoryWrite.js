import axios from "axios";
import {useEffect, useState} from "react";
import Category from "./Category";

export default function CategoryWrite(props){

    // 1. 카테고리 등록
    const addCategory = (e) => {
        const info = { pcname : document.querySelector('.pcname').value}
       axios.post('/product/category',info)
           .then(r=>{
               if( r.data ){alert("카테고리 등록 성공"); props.printCategory()}
               else alert("카테고리등록실패")
           })
    }

    // 3. 카테고리 수정
    const updateCategory = (e, pcno) => {
        const info = {pcno: pcno , pcname: ""  }
        axios.put('/product/category', info).then(r=>{console.log(r.data)})
    }
    // 4. 카테고리 삭제
    const deleteCategory = (e, pcno) => {
            axios.delete('/product/category',{params:{pcno:pcno}})
                .then(r=>{console.log(r.data); props.printCategory();})
    }
    return(<>
        <div style={{width:'300px', margin: '0 auto'}}>
            <h3>카테고리 동륵</h3>
            <form>
                <input type={"text"}
                       className={"pcname"}
                       placeholder={"등록할카테고리명"} />

                <button type={"button"} onClick={addCategory}>등록</button>
            </form>

            <h3> 카테고리 출력 </h3>
            {
                props.categoryList.map(
                    (c) => <Category category={c} deleteCategory={ deleteCategory } />
                )
            }
        </div>

    </>)
}