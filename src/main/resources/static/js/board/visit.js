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
              html+=`<tr>
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



