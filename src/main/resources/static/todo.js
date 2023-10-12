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
                            <button type="button" onclick="changeState( ${r.tno}, ${r.tstate == true ? 0 : 1} )">상태변경</button>
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
function registerTodo(){
    let newTodo = document.querySelector('.newTodo').values;
    $.ajax({
        url:"http://localhost:80/todo",
        method:"post",
        data : {newTodo:newTodo},
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

}