/**
 * 
 */

function checkBillingAddress() {
	if($("#theSameAsShippingAddress").is(":checked")) {
		$(".billingAddress").prop("disabled", true);
	} else {
		$(".billingAddress").prop("disabled", false);
	}
}

$(document).ready(function(){

	$("#theSameAsShippingAddress").on('click', checkBillingAddress);



	$(".form_datetime").datetimepicker({
	        format: "yyyy MM dd - hh:mm",
	        startDate: "2017-05-08 10:00"
	    });
	
	//Dynamic subset validation . Billing disabled , while shipping is not provided
	 var value = $.trim($(".shippingAddress").val());
	 $('.billingAddress').prop('disabled',true);
	  $('.shippingAddress').change(function(){
	   $('.billingAddress').prop('disabled',value > 0 ?true:false);
	 });


	//Dynamic inheritance validation
	$('#cardLink').click(function(){
		$('.cardData').prop('required',true);
		$('.paypalData').prop('required',false);
	});

	$('#paypalLink').click(function(){
		$('.cardData').prop('required',false);
		$('.paypalData').prop('required',true);
	});


});