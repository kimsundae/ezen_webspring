

function BookListA(){
    return(<>
        <h1>도서명 : 이것이 자바다</h1>
        <BookListB 저자={"유재석"} 소비자가격={30000}/>
        <h1>도서명 : 이것이 파이썬</h1>
        <BookListB 저자={"강호동"} 소비자가격={25000}/>
        <h1>도서명 : 이것이 리액트</h1>
        <BookListB 저자={"신동엽"} 소비자가격={28000}/>
    </>)
}
function BookListB( props ){
    return(<>
        <div>저자 : {props.저자} / 소비자가격 : {props.소비자가격}</div>
    </>)
}
export default BookListA;