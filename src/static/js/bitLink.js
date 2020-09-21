bitLink = new Object();
bitLink.getQR=function(obj){
	 
	var shorturl=$(obj).attr("shorturl");
	 
	$("#qr-modal-body").qrcode(shorturl);
	
	$("#qr-modal").modal("show");
	
	
} ;

bitLink.downloadQR=function(obj){
	var canvas =$("#qr-modal-body").find("canvas").get(0);
	var image = canvas.toDataURL("image/jpg");
    obj.href = image;
};


/*===================================================================================================*/
$(document).ready(function(){
	
$("#domainForm").validate();

 $('#urlForm').validate();

$('#domainTable').DataTable();
$('#urlTable').DataTable();
$('.dataTables_length').addClass('bs-select');

});


