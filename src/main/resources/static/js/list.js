
getboards();


function getboards() {
    let boardlist = document.querySelector(".boardlist");
    let html = `<tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>내용</th>

                </tr>`
    $.ajax({
        url: "/board/getboards",
        success: function(re){
            for (let i = 0; i < re.length; i++){
              html+=`<tr>
                       <td>${re[i].bno}</td>
                       <td>${re[i].btitle}</td>
                       <td>${re[i].bcontent}</td>
                     </tr>
                    `
            }
             boardlist.innerHTML = html
        }

    })
}