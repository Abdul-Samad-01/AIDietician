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

	var modal = document.getElementById("myModal");

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	
	// When the user clicks on the button, open the modal
	function showModal() {
	  modal.style.display = "block";
	}

	
	
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		
	  modal.style.display = "none";
	}
	
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
		modal.style.display = "none";
	  }
	}