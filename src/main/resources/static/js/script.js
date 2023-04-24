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



function sendReq(){
	Swal.fire({
		title: 'Request send Successfully',
		text: "Please wait for your diet from dietician",
		icon: 'success',
		
		confirmButtonColor: '#30d649',
		
		confirmButtonText: 'Ok'
	}).then((result) => {
		
		if (result.isConfirmed) {
			document.getElementById("reqsend").click();
		}
	})
}

function DietSend() {
	Swal.fire({
		title: 'Diet Suggested',
		text: "Diet has been Send to the user",
		icon: 'success',
		
		confirmButtonColor: '#30d649',
		
		confirmButtonText: 'Ok'
	}).then((result) => {
		
		if (result.isConfirmed) {
			document.getElementById("sub").click();
		}
	})


}



  function veg() {
	document.getElementById("veg").checked = true;

  }

  function nonveg() {
	document.getElementById("nonveg").checked = true;
  }

  function passkeyfun(param) {
	if (param=='open') {
		document.getElementById("passkey").style.display="block";
		
	} else {
		document.getElementById("passkey").style.display="none";
	}
	
  }








