let vcno = 1;
 getvisit()
//카테고리 등록
function setvcategory(){
    let vcname = document.querySelector(".vcname").value;
    $.ajax({
        url: '/board/setvcategory',
        data:{"vcname": vcname},
        success: function(re){
            alert(re)
            getvcategory();
        }
    })
}
getvcategory()
function getvcategory(){
    $.ajax({
        url: '/board/getvcategory',
        success: function(re){
           let html =``;
           re.forEach(vc=>{
            html+=`<button type="button" onclick="vcnochange(${vc.vcno})">${vc.vcname}</button>`
           })
           document.querySelector(".vcategorybox").innerHTML=html;
        }
    })
}
function vcnochange(cno){
    vcno=cno;
    alert(vcno+"번 카테고리 선택");
    getvisit();
}
function setvisit(){
    let data={
        vtitle:document.querySelector(".vtitle").value,
        vwriter:document.querySelector(".vwriter").value,
        vcontent:document.querySelector(".vcontent").value,
        vcno:vcno
    }
    $.ajax({
        url: '/board/setvisit',
        data: JSON.stringify(data),
        type:"POST",
        contentType:'application/json',
        success: function(re){
            alert(re)
        }
    })
}
function getvisit() {
    let visitlist = document.querySelector(".visitlist");
    let html = `<tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                </tr>`
    $.ajax({
        url: "/board/visitlist",
        data:{"vcno":vcno},
        success: function(re){
            for (let i = 0; i < re.length; i++){
              html+=`<tr onclick="view(${re[i].vno})">
                       <td>${re[i].vno}</td>
                        <td>${re[i].vtitle}</td>
                       <td>${re[i].vwriter}</td>
                     </tr>
                    `
            }
             visitlist.innerHTML = html
        }
    })
}
function view(vno){
    $.ajax({
        url:"/board/viewvisit",
        data:{"vno":vno},
        success: function (re){
            let html=`
                        <span>번호: ${re.vno}</span><br>
                        <span>제목: ${re.vtitle} <button type="button" onclick="vdelete(${re.vno})">삭제</button>
                        <button type="button" onclick="vupdate(${re.vno})">수정</button></span><br>
                        <span>내용: ${re.vcontent}</span>

                    `
            document.querySelector(".view").innerHTML = html
        }
    })
}
function vdelete(vno){
    $.ajax({
        url:"/board/vdelete",
        type : "DELETE",
        data:{"vno": vno},
        success : function(re) {
            if(re==true){alert("삭제성공")}
            else{alert("삭제실패")}
            getvisit();
            document.querySelector(".view").innerHTML = '';
        }
    })
}
function vupdate(vno){
     $.ajax({
            url:"/board/viewvisit",
            data:{"vno":vno},
            success: function (re){
                let html=`  <form>
                            <span>번호: ${re.vno}</span><br>
                            <span>제목: <input type="text" value="${re.vtitle}" class="upvtitle"></span><br>
                            <span>내용: <input type="text" value="${re.vcontent}" class="upvcontent"></span><br>
                            <button type="button" onclick="vput(${re.vno})">수정하기</button>
                            </form>
                       `
                document.querySelector(".view").innerHTML = html
            }
        })
}
function vput(vno){
    let data={
            vno:vno,
            vtitle:document.querySelector(".upvtitle").value,
            vcontent:document.querySelector(".upvcontent").value,
        }
    $.ajax({
        url:"/board/vput",
        data:JSON.stringify(data),
        contentType:"application/json",
        type:"PUT",
        success: function(re){
            if(re==true){alert("수정성공");location.href="/board/visit"}
            else{alert("수정실패")}
        }
    })
}



