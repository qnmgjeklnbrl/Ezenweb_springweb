// function login(){    [시큐리티 사용시 필요 없음]
//     let info = {
//            memail:document.querySelector(".memail").value,
//            mpassword:document.querySelector(".mpassword").value


//        }
//            $.ajax({

//                url:"/member/getmember",
//                type:"POST",
//                data: JSON.stringify(info),
//                contentType:"application/json",
//                success:function(re){
                  
//                   if(re=="1"){
//                     location.href="/"
//                   }else if(re=="2"){
//                     alert("패스워드 틀림")
//                   }else if (re=="0"){alert("아이디 틀림")}
//                }

//            })
// }