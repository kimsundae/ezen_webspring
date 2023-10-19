
import styles from './Todo.css'
export default function Todo(progs){


    return(<>
        <div className="todo">
            <div className="tcontent"> {progs.name}</div>
            <div className="etcbtns">
                <button type="button">상태변경</button>
                <button type="button">제거하기</button>
            </div>
        </div>
    </>)
}


