
function setmember(){

    let info = {
        memail:document.querySelector(".memail").value,
        mpassword:document.querySelector(".mpassword").value


    }
        $.ajax({

            url:"/member/setmember",
            type:"POST",
            data: JSON.stringify(info),
            contentType:"application/json",
            success:function(re){
                location.href="/member/login"
            }

        })


}


let auth = ''
let timer = 0;
//인증코드 전송
function getauth(){
    $.ajax({
        url:"/member/getauth",
        data:{"toemail":document.querySelector(".memail").value},
        success:function(re){
           auth= re;
           alert("해당 이메일로 인증코드가 발송 되었습니다.");
           document.querySelector(".getauthbtn").innerHTML="재인증코드 받기"
           timer = 120//초 단위
           setTimer()
        }



    })


}
let timerinter = 'null';
//타이머 함수
function setTimer(){

    timerinter = setInterval(function(){
         let minutes,seconds;
            minutes = parseint(timer/60);
            seconds = parseint(timer % 60);
            minutes = minutes<10 ? "0"+minutes : minutes
            seconds =  seconds<10 ? "0"+ seconds :  seconds
            let timehtml = minutes + " : " + seconds;
            document.querySelector(".timerbox").innerHTML = timehtml;
            timer--;
            if(timer == 0){
                clearInterval(timerinter);
                alert("인증실패:시간초과");
                auth =null; timer =0;
                document.querySelector(".getauthbtn").innerHTML="인증코드 받기";

            }



    },1000);








}


//인증코드 확인
function authcode() {
    let authinput = document.querySelector(".authinput").value
    if(authinput==auth){
        alert("인증 성공")
    }else{alert("인증실패:인증코드가 일치하지 않습니다")}


}










