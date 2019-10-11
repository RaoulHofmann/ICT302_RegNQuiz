/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function booking_info(){
    console.log("Booking Info");
    $("#booking_table tbody").empty();
    $.ajax({
        type: "POST",
        url : '/getbooking',
        success : function(data) {
            data.forEach(function(element) {
                console.log(element);
                    var bookingId = element.bookingID;
                    var date = element.date;
                    var time = element.time;
                    var accessCode = element.attendanceCode;
                    var bookingLength = element.bookingLength;
                    var unitId = element.unit.unitCode;
                    var venueId = element.venue.building+"."+element.venue.floor+"."+element.venue.room;
                    var userId = element.lecture.givenName+" "+element.lecture.lastName;
                    $("#booking_table tbody").append(
                      "<tr>" +
                          "<td>"+bookingId+"</td>" +
                          "<td>"+date+"</td>" +
                          "<td>"+time+"</td>" +
                          "<td>"+accessCode+"</td>" +
                          "<td>"+bookingLength+"</td>" +
                          "<td>"+unitId+"</td>" +
                          "<td>"+venueId+"</td>" +
                          "<td>"+userId+"</td>" +
                      "</tr>"
                    );
                });
            }
    });
}

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
    var staff_entry = document.getElementById("staff_entry");
    var login = document.getElementById("login_form");
   // var userName = document.getElementById("userName").value;
    var useName;
    var password = document.getElementById("password").value;
    var logged_out_alert = document.getElementById("logged_out_alert");

    welcome.style.display = "none";
    //add user name validation; using if else;
    var x;
    x=document.getElementById("userName").value;
      // If x is Not a Number or less than one or greater than 10
    if (isNaN(x) || x < 1 || x > 39999999)
    {
        alert( "Input not valid");
    } else 
    {
        userName =x;
    }

    if(logged_out_alert.value != null){
        logged_out_alert.style.display = "none";
    }

    $.ajax({
        type: "POST",
        url : '/login',
        data: { userid: userName, password: password},
        success : function(data) {
            var studentNo = data[1];
            var firstName = data[2];
            var lastName = data[3];
            var prefName = data[4];
            if(prefName != null){
                document.getElementById("welcome").innerHTML = "Welcome " + prefName + " " + lastName + " your ID is " + studentNo;
            }else{
                document.getElementById("welcome").innerHTML = "Welcome " + firstName + " " + lastName + " your ID is " + studentNo;
            }
            if(data[0] == 2){
                document.getElementById("welcome").innerHTML += "<br><p style=\"color: red\">You are a Staff Member</p>";
                staff_entry.style.display = "block";
                booking_info();
            }else if(data[0] == 3){
                document.getElementById("welcome").innerHTML += "<br><p style=\"color: green\">You are a Student</p>";
                staff_entry.style.display = "none";
            }
            login.style.display = "none";
            welcome.style.display = "block";
        }
    });
}

function logout(){
    var welcome = document.getElementById("welcome_div");
    var staff_entry = document.getElementById("staff_entry");
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
                staff_entry.style.display = "none";
                login.style.display = "block";
                logged_out_alert.style.display = "block";
            }else{
                logged_out_danger.style.display = "block";
            }

        }
    });
}

