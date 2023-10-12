getList()
function getList(){
    $.ajax({
        url:"http://192.168.17.138/day04/todo",
        method:"get",
        data : {},
        success:array=>{
            console.log(array)
            let html = ``
            array.forEach(r=>{
                html +=
                    `<div class="todo ${r.tstate == true ? 'successTodo' : ''}"> <!-- todo 항목 1개 -->
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
    let tcontent = document.querySelector('.newTodo').value;
    $.ajax({
        url:"http://192.168.17.138/day04/todo",
        method:"post",
        data : JSON.stringify({tcontent:tcontent , tstate: false }),
        contentType: 'application/json; charset=utf-8',
        success: r => {
            if( r == true ) {
                alert('등록 되었습니다.')
                getList();
            }
            else
                alert('등록 실패되었습니다.')
        },
        error : e => {console.log(e)}
    })
}
// delete
function deleteTodo( tno ){
    $.ajax({
        url:"http://192.168.17.138/day04/todo",
        method:"delete",
        data : {tno:tno},
        success: r => {
            if( r ) {
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
    $.ajax({
        url:"http://192.168.17.138/day04/todo",
        method:"put",
        data : JSON.stringify({tno:tno , tstate: tstate }),
        contentType: 'application/json; charset=utf-8',
        success: r => {
            if( r ){
                alert('수정 되었습니다.')
                getList();

            }
            else
                alert('수정 실패되었습니다.')
        },
        error : e => {console.log(e)}
    });

}