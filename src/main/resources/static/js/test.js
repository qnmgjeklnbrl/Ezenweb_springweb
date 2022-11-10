alert("asd")
 function getMapping1() {
            $.ajax({
                url: '/api/v1/get-api/hello',
                type: 'GET',
                success: function(re){
                    alert(re);
                }
            })
        }
         function getMapping2() {
            $.ajax({
                url: '/api/v1/get-api/name',
                type: 'GET',
                success: function(re){
                    alert(re);
                }
            })
        }
        function getMapping3() {
            $.ajax({
                url: '/api/v1/get-api/variable1/{variable}',
                type: 'GET',
                success: function(re){
                    alert(re);
                }
            })
        }
        function getMapping4() {
            $.ajax({
                url: '/api/v1/get-api/variable2/{variable}',
                type: 'GET',
                success: function(re){
                    alert(re);
                }
            })
        }
         function getMapping5() {
            $.ajax({
                url: '/api/v1/get-api/request1?name=asd&email=asd@asd.com&organization=asdasd',
                type: 'GET',
                success: function(re){
                    alert(re);
                }
            })
        }
        function getMapping6() {
            $.ajax({
                url: '/api/v1/get-api/request2?name=asd&email=asd@asd.com&organization=asdasd',
                type: 'GET',
                success: function(re){
                    alert(re);
                }
            })
        }
        function getMapping7() {
            $.ajax({
                url: '/api/v1/get-api/request3?name=asd&email=asd@asd.com&organization=asdasd',
                type: 'GET',
                success: function(re){
                    alert(re);
                }
            })
        }


        function postMapping1() {
                    $.ajax({
                        url: '/api/v1/post-api/domain',
                        type: 'post',
                        success: function(re){
                            alert(re);
                        }
                    })
                }

        function postMapping2() {
            let member={
                name:"유재석",
                email:"asd@asdasd.com",
                organization:"asdasd"


            }
            $.ajax({
                url: '/api/v1/post-api/member',
                type: 'post',
                data: JSON.stringify(member),

                contentType: 'application/json',
                success: function(re){
                alert(re);
                }
            })
        }
         function postMapping3() {
            let member={
                name:"유재석",
                email:"asd@asdasd.com",
                organization:"asdasd"


            }
            $.ajax({
                url: '/api/v1/post-api/member2',
                type: 'post',
                data: JSON.stringify(member),

                contentType: 'application/json',
                success : function(re){
                alert(re);
                }
            })
                }






 function putMapping1() {
     let member={
                     name:"유재석",
                     email:"asd@asdasd.com",
                     organization:"asdasd"
     }

     $.ajax({
       url: '/api/v1/put-api/member',
       type: 'put',
       data: JSON.stringify(member),

       contentType: 'application/json',
       success : function(re){
       alert(re);
       }
     })


 }
 function putMapping2() {
      let member={
                      name:"유재석",
                      email:"asd@asdasd.com",
                      organization:"asdasd"
        }
      $.ajax({
                      url: '/api/v1/put-api/member1',
                      type: 'put',
                      data: JSON.stringify(member),

                      contentType: 'application/json',
                      success : function(re){
                      console.log(re);
                      }
                  })


  }


function putMapping3() {
     let member={
                     name:"유재석",
                     email:"asd@asdasd.com",
                     organization:"asdasd"
        }
     $.ajax({
                     url: '/api/v1/put-api/member2',
                     type: 'put',
                     data: JSON.stringify(member),

                     contentType: 'application/json',
                     success : function(re){

                     console.log(re);
                     }
                 })


 }




 function deleteMapping1() {
            $.ajax({
                url: '/api/v1/delete-api/{variable}',
                type: 'delete',
                success: function(re){
                    alert(re);
                }
            })
        }

 function deleteMapping2() {
            $.ajax({
                url: '/api/v1/delete-api/request1?email=asdasd@naver.asdasd',
                type: 'delete',
                success: function(re){
                    alert(re);
                }
            })
        }