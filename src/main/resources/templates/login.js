/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function login() 
{
      var userName = document.getElementById("userName").value;
      var password = document.getElementById("password").value;
      $.ajax({
        type: "POST",
        url : '/login',  
        data: { userid: userName, password: password},
        success : function(data) {
                console.log(data);
                var studentNo = JSON.parse(data)[0]; //asuming student number is the first element in json
                document.getElementById("welcome").innerHTML = "Welcome" + studentNo;                                      
        }
      });                       
}

