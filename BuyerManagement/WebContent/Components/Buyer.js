//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidBuyerIDSave").val("");
	$("#BUYER")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	
	
	//alert(("#buyerName").val());
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#buyerID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "BuyerAPI",
		type : type,
		data : $("#BUYER").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#BuyerGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#buyerID").val("");
	$("#BUYER")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "BuyerAPI",
		type : "DELETE",
		data : "buyerID=" + event.target.value,
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
			window.location.reload(true);
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#BuyerGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{		
			$("#buyerID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#buyerName").val($(this).closest("tr").find('td:eq(1)').text());
			$("#projectName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#email").val($(this).closest("tr").find('td:eq(3)').text());
			$("#contactNo").val($(this).closest("tr").find('td:eq(4)').text());
			$("#researcherName").val($(this).closest("tr").find('td:eq(5)').text());
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// buyerName
	if ($("#buyerName").val() == "") {
		return "Please insert buyerName.";
	}
	
	// projectName
	if ($("#projectName").val() == "") {
		return "Please insert projectName.";
	}
	
	// email
	if ($("#email").val() == "") {
		return "Please insert email.";
	}

	// contactNo
	if ($("#contactNo").val() == "") {
		return "Please insert contactNo.";
	}
	
	// researcherName
	if ($("#researcherName").val() == "") {
		return "Please insert researcherName.";
	}
	
	
	return true;
}
