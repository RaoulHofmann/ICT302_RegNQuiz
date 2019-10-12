/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loginRe(){
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    $.ajax({
        type: "POST",
        url : '/login',
        data: { userid: userName, password: password},
        success : function(data) {
            console.log("TEST1");
            console.log(data);
            console.log("TEST2");
            //window.location = '/staff.html';
        }
    });
}



function login(){
    var welcome = document.getElementById("welcome_div");
    var login = document.getElementById("login_form");
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    var logged_out_alert = document.getElementById("logged_out_alert");

    welcome.style.display = "none";

    if(logged_out_alert.value != null){
        logged_out_alert.style.display = "none";
    }

    $.ajax({
        type: "POST",
        url : '/login',
        data: { userid: userName, password: password},
        success : function(data) {
            console.log(data);
            var studentNo = data[1];
            var firstName = data[2];
            var lastName = data[3];
            var prefName = data[4];
            if(prefName != null){
                document.getElementById("welcome").innerHTML = "Welcome " + prefName + " " + lastName + " your ID is " + studentNo;
            }else{
                document.getElementById("welcome").innerHTML = "Welcome " + firstName + " " + lastName + " your ID is " + studentNo;
            }
            if(data[0] == 1){
                document.getElementById("welcome").innerHTML += "<br><p style=\"color: red\">You are a Staff Member</p>";
            }else if(data[0] == 2){
                document.getElementById("welcome").innerHTML += "<br><p style=\"color: green\">You are a Student</p>";
            }

            login.style.display = "none";
            welcome.style.display = "block";
        }
    });
}

function logout(){
    var welcome = document.getElementById("welcome_div");
    var login = document.getElementById("login_form");
    var logged_out_alert = document.getElementById("logged_out_alert");
    var logged_out_danger = document.getElementById("logged_out_danger");

    $.ajax({
        type: "POST",
        url : '/logout',
        data: { },
        success : function(data) {
            if(data == 1){
                welcome.style.display = "none";
                login.style.display = "block";
                logged_out_alert.style.display = "block";
            }else{
                logged_out_danger.style.display = "block";
            }

        }
    });
}

