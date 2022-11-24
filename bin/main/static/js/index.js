
   /*
   <button type="button"> 로그아웃 </button>
   <button type="button"> 비밀번호 찾기 </button>
    <button type="button"> 비밀번호 수정 </button>
    <button type="button"> 회원탈퇴  </button>
*/
$.ajax({
    url:"/member/getmno",
    success: function(re){
        console.log(re)
        if(re!==0){
            document.querySelector(".container").innerHTML+=`<button type="button" onclick="logout()"> 로그아웃 </button>
                                                         <button type="button" onclick="findpasswordPage()"> 비밀번호 찾기 </button>
                                                          <button type="button" onclick="updatePage()"> 비밀번호 수정 </button>
                                                          <button type="button" onclick="deletePage()"> 회원탈퇴  </button>`
        }
    }


})



function signupPage(){

    location.href="/member/signup"


}
function loginPage(){

    location.href="/member/login"


}


function findpasswordPage(){

    location.href="/member/findpassword"


}
function updatePage(){

    location.href="/member/update"


}

function deletePage(){
    console.log("loginPage")
    location.href="/member/delete"


}

function logout(){
    $.ajax({

         url:"/member/logout",
         success: function(re){
            location.href="/member/index"
         }


    })


}

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