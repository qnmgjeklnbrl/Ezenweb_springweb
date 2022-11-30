
   /*
   <button type="button"> 로그아웃 </button>
   <button type="button"> 비밀번호 찾기 </button>
    <button type="button"> 비밀번호 수정 </button>
    <button type="button"> 회원탈퇴  </button>
*/
getloginMno();
function getloginMno(){
    $.ajax({
        url : "/member/getloginMno" ,
        type : "get" ,
        success : function(re) {
            let headerbox = '';
            if( re == "" ){ // 로그인 안했다 ..
                headerbox +=
                        '<a href="/member/signup"><button type="button"> 회원가입 </button></a>'+
                        '<a href="/member/login"><button type="button"> 로그인 </button></a>'
            }
            else{  // 로그인 했다.. [ 회원번호가 0 이 아니면 ]
                 headerbox +=
                        '<a href="/member/logout"><button type="button"> 로그아웃 </button></a>'+
                        '<a href="/member/findpassword"><button type="button"> 비밀번호찾기 </button></a>'+
                        '<a href="/member/delete"><button type="button"> 비밀번호수정 </button></a>'+
                        '<a href="/member/update"><button type="button"> 회원탈퇴 </button></a>'
            }
            document.querySelector(".headerbox").innerHTML = headerbox;

        }
    })
}




// function logout(){
//     $.ajax({

//          url:"/member/logout",
//          success: function(re){
//             location.href="/member/index"
//          }


//     })


// }

list()
function list(){
    $.ajax({
        url:"/member/list",
        success: function(re){
           let html = ''
           re.forEach( (m) => {
                html += `<tr>
                            <td>${m.mno}</td>
                            <td>${m.memail}</td>
                            <td>${m.mpassword}</td>
                        </tr>`

           })
            document.querySelector(".mtable").innerHTML += html
        }

    })



}