getList()
function getList(){
    console.log('실행')
    $.ajax({
        url:"http://localhost:80/todo",
        method:"get",
        data : {},
        success:array=>{
            console.log(array)
            let html = ``
            array.forEach(r=>{
                html +=
                    `<div class="${tstate == true ? 'successTodo' : 'todo'}"> <!-- todo 항목 1개 -->
                        <div class="tcontent">${r.tcontent}</div>
                        <div class="etcbtns">
                            <button type="button" onclick="putState( ${r.tno}, ${!r.tstate} )">상태변경</button>
                            <button type="button" onclick="deleteTodo( ${r.tno} )">제거하기</button>
                        </div>
                    </div>`
            });
            document.querySelector('.todo_bottom').innerHTML = html;
        },
        error:e=>{console.log(e)}
    })
}
// post
function postTodo(){
    let tcontent = document.querySelector('.newTodo').values;
    $.ajax({
        url:"http://localhost:80/todo",
        method:"post",
        data : {tcontent:newTodo , tstate: false },
        success: r => {
            if( r == true )
                alert('등록 되었습니다.')
            else
                alert('등록 실패되었습니다.')
        },
        error : e => {console.log(e)}
    })
}
// delete
function deleteTodo( tno ){
    $.ajax({
        url:"http://localhost:80/todo",
        method:"delete",
        data : {tno:tno},
        success: r => {
            if( r == true ) {
                alert('삭제 되었습니다.');
                getList();
            }
            else
                alert('삭제 실패 되었습니다.')
        },
        error : e => {console.log(e)}
    })
}
// put
function putState( tno , tstate ){
    let tcontent = prompt('수정할 내용을 입력해주세요.');
    $.ajax({
        url:"http://localhost:80/todo",
        method:"post",
        data : {tcontent:tcontent , tstate: tstate },
        success: r => {
            if( r == true )
                alert('수정 되었습니다.')
            else
                alert('수정 실패되었습니다.')
        },
        error : e => {console.log(e)}
    });

}