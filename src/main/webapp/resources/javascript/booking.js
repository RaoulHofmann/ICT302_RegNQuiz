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
                          "<td id=\"booking_status_"+bookingId+"\"></td>"+
                          "<td>"+bookingId+"</td>" +
                          "<td>"+date+"</td>" +
                          "<td>"+time+"</td>" +
                          "<td>"+accessCode+"</td>" +
                          "<td>"+bookingLength+"</td>" +
                          "<td>"+unitId+"</td>" +
                          "<td>"+venueId+"</td>" +
                          "<td>"+userId+"</td>" +
                          "<td>"+
                          "<input type=\"button\"  onclick=\"location.href='/booking/"+bookingId+"'\" value=\"Start Booking\" >"+
                          "</td>" +
                      "</tr>"
                    );
                    booking_status(bookingId);
                });
            }
    });
}

function booking_status(id){
    $.ajax({
        type: "POST",
        url : '/booking/status',
        data: { bookingID: id },
        success : function(data) {
            if(data == 0){
                $("#booking_status_"+id).html("Inactive");
            }else if(data == 1){
                $("#booking_status_"+id).html("Active");
            }
        }
    });
}

var interval;
function getAttendance() {
    $.ajax({
        type: "POST",
        url : '/booking/getattendance',
        data: { bookingID: 2 },
        success: function (data) {
            console.log(data);
            $('#attendance_count').html(data);
            interval = setTimeout(getAttendance, 1000);
        }
    });
}
getAttendance();
