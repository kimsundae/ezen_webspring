
getPhone();
//get
function getPhone(){

    $.ajax({
        url:"http://192.168.17.138/phone",
        method:"get",
        data : {},
        success: array => {
            console.log(array)
            let html = ``
            array.forEach(r=>{
                html +=
                    `  <div class="phone">
                            <div class="pName"> ${r.pname} </div>
                            <div class="phoneNumber">
                                ${r.ppnumber}
                            </div>
                            <div class="etcbtns">
                                <button type="button" onclick="onPut(${r.pno})">수정하기</button>
                                <button type="button" onclick="onDelete(${r.pno})">삭제하기</button>
                            </div>
                         </div>`
            });
            document.querySelector('.phone_bottom').innerHTML=html;
        },
        error : e => {console.log(e)}
    })
}

// post
function postPhone(){
    let pname = document.querySelector('.newName').value
    let ppnumber = document.querySelector('.newPhone').value
    if( pname == '' || ppnumber == '') return alert('전체 항목을 입력해주세요');

    $.ajax({
        url:"http://192.168.17.138/phone",
        method:"post",
        data : JSON.stringify({pname:pname , ppnumber: ppnumber }),
        contentType: 'application/json; charset=utf-8',
        success: r => {
            if( r == true ) {
                alert('등록 되었습니다.')
                getPhone();
            }
            else
                alert('등록 실패되었습니다.')
        },
        error : e => {console.log(e)}
    })

}
function onDelete(pno){
    $.ajax({
        url:"http://192.168.17.138/phone",
        method:"delete",
        data : {pno:pno},
        success: r => {
            if( r ) {
                alert('삭제 되었습니다.');
                getPhone();
            }
            else
                alert('삭제 실패 되었습니다.')
        },
        error : e => {console.log(e)}
    })
}
function onPut( pno ){
    let pname = prompt('수정할 이름을 입력해주세요')
    let ppnumber = prompt('수정할 전화번호를 입력해주세요')
    if( pname=='' || ppnumber == '' ) return alert('전체 항목을 입력해주세요.')
    $.ajax({
        url:"http://192.168.17.138/phone",
        method:"put",
        data : JSON.stringify({pno:pno,pname:pname , ppnumber: ppnumber }),
        contentType: 'application/json; charset=utf-8',
        success: r => {
            if( r  ) {
                alert('수정 되었습니다.')
                getPhone();
            }
            else
                alert('수정 실패되었습니다.')
        },
        error : e => {console.log(e)}
    })
}