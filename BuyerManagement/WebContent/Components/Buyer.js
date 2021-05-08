//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidBuyerIDSave").val("");
	$("#BUYER")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

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
	var type = ($("#hidBuyerIDSave").val() == "") ? "POST" : "PUT";

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
	
	$("#hidBuyerIDSave").val("");
	$("#BUYER")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "BuyerAPI",
		type : "DELETE",
		data : "buyerID=" + $(this).data("buyerID"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
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
			$("#hidBuyerIDSave").val($(this).data("buyerID"));
			$("#buyerName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#projectName").val($(this).closest("tr").find('td:eq(1)').text());
			$("#email").val($(this).closest("tr").find('td:eq(2)').text());
			$("#contactNo").val($(this).closest("tr").find('td:eq(3)').text());
			$("#researcherName").val($(this).closest("tr").find('td:eq(4)').text());
				
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// buyerName
	if ($("#buyerName").val().trim() == "") {
		return "Please insert buyerName.";
	}
	
	// projectName
	if ($("#projectName").val().trim() == "") {
		return "Please insert projectName.";
	}
	
	// email
	if ($("#email").val().trim() == "") {
		return "Please insert email.";
	}

	// contactNo
	if ($("#contactNo").val().trim() == "") {
		return "Please insert contactNo.";
	}
	
	// researcherName
	if ($("#researcherName").val().trim() == "") {
		return "Please insert researcherName.";
	}
	
	
	return true;
}
