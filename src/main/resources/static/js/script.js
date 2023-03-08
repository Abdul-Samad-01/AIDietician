//console.log("console!!!")
const toggleSidebar=()=>{
	if($(".sidebar").is(":visible")){
		$(".sidebar").css("display", "none");
		$(".content").css("margin-left", "0%");
	}
	else{
		$(".sidebar").css("display", "block");
		$(".content").css("margin-left", "20%");
	}
};

$("#buttonn").click(function displayAlertMessage() {
	var timeOut = 10
	var message = "Request Send"
	jQuery('#messageBox').text(message).fadeIn()
	jQuery('#messageBox').css("display", "block")
	setTimeout(function() {
	jQuery('#messageBox').fadeOut()
	jQuery('#messageBox').css("display", "none")
	}, timeOut * 1000);
	});