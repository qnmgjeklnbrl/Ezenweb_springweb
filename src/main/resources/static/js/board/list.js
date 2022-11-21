let bcno = 0 ;
getboards();

bcategorylist();
function getboards() {
    let boardlist = document.querySelector(".boardlist");
    let html = `<tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>

                </tr>`
    $.ajax({
        url: "/board/boardlist",
        data:{"bcno":bcno},
        success: function(re){
            for (let i = 0; i < re.length; i++){
              html+=`<tr>
                       <td>${re[i].bno}</td>
                       <td><a href="/board/view?bno=${re[i].bno}">${re[i].btitle}</td>
                       <td>${re[i].memail}</td>
                       <td>${re[i].bview}</td>
                     </tr>
                    `
            }
             boardlist.innerHTML = html
        }

    })
}

function bcategorylist(){
    $.ajax({
        url:"/board/bcategorylist",
        type : "GET",
        success: function(re){
            let html = `<button type="button" class="cbtn" onclick="bcnochange(0)">전체보기</button>`;
            re.forEach( c =>{
                html += `<button type="button" class="cbtn" onclick="bcnochange(${c.bcno})">${c.bcname}</button>`;
            })
            document.querySelector(".bcategorybox").innerHTML = html;
            cbtn = document.querySelectorAll(".cbtn") //위에서 생성된 카테고리 버튼들을  호출
        }

    })

}


function bcnochange( cno ){
     bcno = cno;
     alert(bcno+"번 카테고리 선택");
     getboards();
 }




