
import Todo from './Todo'
export default function TodoList(){
    let response = [
        {name : '리액트배우기' } ,
        {name : '자바 배우기' } ,
        {name : '파이썬 배우기' },
        {name : 'C언어 배우기'}
    ];

    return(<>
        <div className="todowrap">
            <h1> 나만의 할일 목록 </h1>
            <div className="todo_top">
                <input className="newTodo" type="text"/>
                <button type="button"> 등록 </button>
            </div>
            <div className="todo_bottom">
                {response.map((r)=>{
                    return( <Todo name={r.name}/>)
                })}
            </div>
        </div>
    </>)

}