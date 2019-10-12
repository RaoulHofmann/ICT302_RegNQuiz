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
                          "<td>"+
                          "<a href=\"javascript: document.startBooking.submit()\" role=\"button\">Start Booking "+bookingId+"</a>"+
                           "<form name=\"startBooking\" th:action=\"@{/startBooking/"+bookingId+"}\" method=\"get\" th:hidden=\"true\">"+
                               "<input hidden type=\"submit\" value=\"Sign Out\"/>"+
                           "</form>"+
                          "</td>" +
                      "</tr>"
                    );
                });
            }
    });
}
