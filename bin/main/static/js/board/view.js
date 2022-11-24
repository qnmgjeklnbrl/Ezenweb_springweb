
let urlstr = window.location.href;
let url = new URL(urlstr);
let bno = url.searchParams.get("bno");
$.ajax({
    url:"/board/getboard",
    data:{"bno":bno},
    success: function(re){
        let html=`<tr>
                      <th>글 번호</th>
                      <th>작성자 번호</th>
                      <th>제목</th>
                      <th>내용</th>
                      <th>첨부파일</th>
                 </tr>
                 <tr>
                   <td>${re.bno}</td>
                   <td>${re.memail}</td>
                   <td>${re.btitle}</td>
                   <td>${re.bcontent}</td>
                   <td>${re.bfile}</td>
                 </tr>
                 `;
        document.querySelector(".view").innerHTML=html;
    }
})
function delboard(){
    $.ajax({
        url:"/board/delboard",
        type:"DELETE",
        data:{"bno": bno},
        success:function(re){
            location.href="/board/list"
        }
    })
}
function update(){
    location.href=`/board/update?bno=${bno}`
}
function tolist(){
    location.href="/board/list"
}



