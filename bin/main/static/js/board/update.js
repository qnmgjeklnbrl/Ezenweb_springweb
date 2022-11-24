
let urlstr = window.location.href;
let url = new URL(urlstr);
let bno = url.searchParams.get("bno");
$.ajax({
    url:"/board/getboard",
    data:{"bno":bno},
    success: function(re){

        document.querySelector(".btitle").value = re.btitle;
        document.querySelector(".bcontent").value = re.bcontent;




    }

})


function upboard(){
    let data={
       bno:bno,
       btitle:document.querySelector(".btitle").value,
       bcontent:document.querySelector(".bcontent").value,
       bfile:document.querySelector(".bfile").value
    }
    $.ajax({
        url: "/board/upboard",
        type: "PUT",
        data: JSON.stringify(data),
        contentType:'application/json',
         success: function(re){
            location.href="/board/list"
         },

    })

}
