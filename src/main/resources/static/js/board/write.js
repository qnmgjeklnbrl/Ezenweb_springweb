let bcno = 2;//카테고리  번호 전역변수
function setboard(){
    let boardform = document.querySelector(".boardform")
    let formdata = new FormData(boardform)
    formdata.set("bcno",bcno)
    $.ajax({
        url: "/board/setboard",
        type: "POST",
        data: formdata,
        contentType:false,
        processData: false,
         success: function(re){
            console.log(re)

         },

    })

}

function setbcategory(){
    let data={bcname:document.querySelector(".bcname").value}
    $.ajax({
        url: "/board/setbcategory",
        data: JSON.stringify(data),
        type: 'POST',
        contentType:'application/json',
        success: function(re){
            if(re==true){
                alert("카테고리 추가 성공")
                bcategorylist()
            }else{alert("카테고리 추가 실패")}

        }


    })



}



bcategorylist()
function bcategorylist(){
    $.ajax({
        url:"/board/bcategorylist",
        type : "GET",
        success: function(re){
            let html ='';
            re.forEach( c =>{
                html += `<button type="button" class="cbtn" onclick="bcnochange(${c.bcno})">${c.bcname}</button>`;
            })
            document.querySelector(".bcategorybox").innerHTML = html;
            cbtn = document.querySelectorAll(".cbtn") //위에서 생성된 카테고리 버튼들을  호출
        }

    })

}

//4.카테고리를 선택했을때 선택된 카테고리 번호 변경
function bcnochange( cno ){ bcno = cno; alert(bcno+"번 카테고리 선택")}











