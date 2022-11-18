
getboards();


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