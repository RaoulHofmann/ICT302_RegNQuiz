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