
import {useState , useEffect } from 'react'
export default function 생명주기컴포넌트(){

    // 1. useState 함수를 이용한 초기값 0으로 하는 [ 변수, 수정함수 ] 리턴 받음
    let [ value , setValue ] = useState( 0 )

    const valueUpdate = (e) => { value++; setValue(value);}

    let [ data, setData ] = useState( 0 );
    const dataUpdate = (e) => {data++; setData( data );}

    //2. 컴포넌트 생명주기 1.탄생/2.업데이트/3.제거
        // 1. 컴포넌트 탄생 / 업데이트 <= 컴포넌트가 첫 실행과 업데이트 할 때 실행되는 함수
    useEffect( ()=> { console.log('[1]effect실행')})
        // 2. 컴포넌트  <= 컴포넌트가 첫 실행될 때만 실행되는 함수
        // useEffect( 함수, [] );
    useEffect(() => {
        console.log('[2]effect실행')
    }, []);
        // 3. 컴포넌트 탄생 / 특정 상태 업데이트
        // useEffect( 함수, [의존성배열] )
    useEffect(() => {
        console.log('[3]effect실행')
    }, [data]);

    return(<>
        <div> { value } </div>
        <button onClick={ valueUpdate }> + </button>
        <div>{data}</div>
        <button onClick={ dataUpdate }> + </button>
    </>)
}

/*
    컴포넌트의 생명주기[Life Cycle]


*/


